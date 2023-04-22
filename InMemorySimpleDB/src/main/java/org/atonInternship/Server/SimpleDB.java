package org.atonInternship.Server;

import java.util.Comparator;
import lombok.NoArgsConstructor;
import org.atonInternship.DataStructure.AVLTree;
import org.atonInternship.DataStructure.Tree;
import org.atonInternship.Object.SimpleDBObject;

@NoArgsConstructor
public class SimpleDB implements DB<SimpleDBObject> {
    private int size = 0;
    private final Tree<String> treeOfNames = new AVLTree<>(String::compareTo);
    private final Tree<Long> treeOfAccounts = new AVLTree<>(Long::compareTo);
    private final Tree<Double> treeOfValues = new AVLTree<>(Double::compareTo);

    @Override
    public void insertData(final SimpleDBObject data) {
        size++;
    }

    @Override
    public SimpleDBObject get() {
        return null;
    }

    @Override
    public void remove(){
        if(size == 0){
            return;
        }

    }
}
