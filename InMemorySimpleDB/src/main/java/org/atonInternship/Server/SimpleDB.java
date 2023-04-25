package org.atonInternship.Server;

import lombok.NoArgsConstructor;
import org.atonInternship.Object.SimpleDBObject;

@NoArgsConstructor
public class SimpleDB implements DB<SimpleDBObject> {
    private int size = 0;
    /*private final RecursiveTree<String> recursiveTreeOfNames = new AVLRecursiveTree<>(String::compareTo);
    private final RecursiveTree<Long> recursiveTreeOfAccounts = new AVLRecursiveTree<>(Long::compareTo);
    private final RecursiveTree<Double> recursiveTreeOfValues = new AVLRecursiveTree<>(Double::compareTo);*/


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
