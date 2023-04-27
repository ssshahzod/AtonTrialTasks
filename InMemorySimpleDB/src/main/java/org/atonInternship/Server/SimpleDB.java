package org.atonInternship.Server;

import org.atonInternship.DataStructure.Table.MyHashTable;
import org.atonInternship.DataStructure.Tree.Node.TaskTreeNode;
import org.atonInternship.Object.SimpleDBObject;

public class SimpleDB implements DB<SimpleDBObject> {
    private int size = 0;
    private final int tableSize = 20;

    //strNode -> lngNode -> dblNode
    private final MyHashTable<String, TaskTreeNode<String>> tableOfNames = new MyHashTable<>(String::compareTo, tableSize);
    private final MyHashTable<Long, TaskTreeNode<Long>> tableOfAccounts = new MyHashTable<>(Long::compareTo, tableSize);
    private final MyHashTable<Double, TaskTreeNode<Double>> tableOfValues = new MyHashTable<>(Double::compareTo, tableSize);

    public SimpleDB(){}
    @Override
    public void insertData(final SimpleDBObject data) {
        size++;
        var strData = new TaskTreeNode<>(data.getName());
        var lngData = new TaskTreeNode<>(data.getAccount());
        var dblData = new TaskTreeNode<>(data.getValue());

        strData.setLink(lngData);
        lngData.setLink(dblData);
        dblData.setLink(strData);

        tableOfNames.insert(data.getName(), strData);
        tableOfAccounts.insert(data.getAccount(), lngData);
        tableOfValues.insert(data.getValue(), dblData);
    }

    private void getByName(final SimpleDBObject object){
        TaskTreeNode<String> res = tableOfNames.find(object.getName());
        object.setAccount((Long) res.getLink().getValue());
        object.setValue((Double) res.getLink().getLink().getValue());
    }
    private void getByAccount(final SimpleDBObject object){
        TaskTreeNode<Long> res = tableOfAccounts.find(object.getAccount());
        object.setValue((Double) res.getLink().getValue());
        object.setName((String) res.getLink().getLink().getValue());
    }
    private void getByValue(final SimpleDBObject object){
        TaskTreeNode<Double> res = tableOfValues.find(object.getValue());
        object.setName((String) res.getLink().getValue());
        object.setAccount((Long) res.getLink().getLink().getValue());
    }

    @Override
    public SimpleDBObject get(final SimpleDBObject data) {
        SimpleDBObject res = new SimpleDBObject();
        if(data.getName() != null){
            getByName(res);
        } else if(data.getValue() != null){
            getByValue(res);
        } else{
            getByAccount(res);
        }
        return res;
    }

    private void removeByName(final SimpleDBObject data){

    }

    private void removeByAccount(final SimpleDBObject data){

    }

    private void removeByValue(final SimpleDBObject data){

    }

    @Override
    public void remove(final SimpleDBObject data){
        if(size == 0){
            return;
        }
        var tmp = get(data);
        tableOfNames.delete(data.getName());

    }
}
