package org.atonInternship.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.atonInternship.DataStructure.Tree.AVLTree;
import org.atonInternship.DataStructure.Tree.Node.TaskTreeNode;
import org.atonInternship.Object.SimpleDBObject;

public class SimpleDB implements DB<SimpleDBObject> {
    private int size = 0;

    //strNode -> lngNode -> dblNode
    private final AVLTree<String> treeOfNames = new AVLTree<>(String::compareTo);
    private final AVLTree<Long> treeOfAccounts = new AVLTree<>(Long::compareTo);
    private final AVLTree<Double> treeOfValues = new AVLTree<>(Double::compareTo);

    public SimpleDB(){}
    @Override
    public int insertData(final SimpleDBObject data) {
        var tmp = get(data);
        if(tmp != null && tmp.contains(data))
            return 1;
        size++;
        var strData = new TaskTreeNode<>(data.getName());
        var lngData = new TaskTreeNode<>(data.getAccount());
        var dblData = new TaskTreeNode<>(data.getValue());

        strData.setLink(lngData);
        lngData.setLink(dblData);
        dblData.setLink(strData);

        treeOfNames.insert(strData);
        treeOfAccounts.insert(lngData);
        treeOfValues.insert(dblData);
        return 0;
    }

    private ArrayList<SimpleDBObject> getByName(final SimpleDBObject object){
        List<TaskTreeNode<String>> res = treeOfNames.find(object.getName())
                .stream()
                .map(node -> ((TaskTreeNode<String>) node))
                .toList();
        if(res.size() == 0)
            return null;
        var result = new ArrayList<SimpleDBObject>();

        res.forEach(r -> result.add(new SimpleDBObject(
                (Long) r.getLink().getValue(),
                 r.getValue(),
                (Double) r.getLink().getLink().getValue())));
        return result;
    }
    private ArrayList<SimpleDBObject> getByAccount(final SimpleDBObject object){
        List<TaskTreeNode<Long>> res = treeOfAccounts.find(object.getAccount())
                .stream()
                .map(node -> ((TaskTreeNode<Long>) node))
                .toList();
        if(res.isEmpty())
            return null;
        var result = new ArrayList<SimpleDBObject>();

        res.forEach(r -> result.add(new SimpleDBObject(
                r.getValue(),
                (String) r.getLink().getLink().getValue(),
                (Double) r.getLink().getValue())));
        return result;
    }
    private ArrayList<SimpleDBObject> getByValue(final SimpleDBObject object){
        List<TaskTreeNode<Double>> res = treeOfValues.find(object.getValue())
                .stream()
                .map(node -> ((TaskTreeNode<Double>) node))
                .toList();
        if(res.isEmpty())
            return null;
        var result = new ArrayList<SimpleDBObject>();

        res.forEach(r -> result.add(new SimpleDBObject(
                (Long) r.getLink().getLink().getValue(),
                (String) r.getLink().getValue(),
                r.getValue())));
        return result;
    }

    @Override
    public List<SimpleDBObject> get(final SimpleDBObject data) {
        ArrayList<SimpleDBObject> res;
        if(data.getName() != null){
            res = getByName(data);
        } else if(data.getValue() != null){
            res = getByValue(data);
        } else{
            res = getByAccount(data);
        }
        return res;
    }

    private List<SimpleDBObject> getOne(final SimpleDBObject data){
        var list = get(data);
        int sum = data.fillness();

        if(sum == 1 || sum == 4 || sum == 5 || sum == 7){
            list.removeIf(object1 -> !object1.getName().equals(data.getName()));
        }
        if(sum == 2 || sum == 6 || sum == 7){
            list.removeIf(object1 -> !object1.getAccount().equals(data.getAccount()));
        }
        if(sum == 3 || sum == 7){
            list.removeIf(object1 -> !object1.getValue().equals(data.getValue()));
        }
        return list;
    }



    @Override
    public int remove(final SimpleDBObject data){
        if(size == 0){
            return 1;
        }
        var tmp = getOne(data);
        if(tmp == null)
            return 1;
        if(tmp.size() > 1)
            return 2;
        treeOfNames.delete(tmp.get(0).getName());
        treeOfValues.delete(tmp.get(0).getValue());
        treeOfAccounts.delete(tmp.get(0).getAccount());
        return 0;
    }
}
