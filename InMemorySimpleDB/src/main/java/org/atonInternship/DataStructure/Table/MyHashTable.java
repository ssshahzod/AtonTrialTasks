package org.atonInternship.DataStructure.Table;

import java.util.ArrayList;
import java.util.Comparator;
import lombok.NoArgsConstructor;
import org.atonInternship.DataStructure.Tree.AVLTree;
import org.atonInternship.DataStructure.Tree.TreeNode;


@NoArgsConstructor
public class MyHashTable<K, V extends TreeNode<K>> implements MyHashTableInterface<K, V>{
    private final int DEFAULT_SIZE = 10;
    private final ArrayList<AVLTree<K>> data = new ArrayList<>(DEFAULT_SIZE);
    private Comparator<K> comparator;
    private int capacity = 0;

    public MyHashTable(Comparator<K> comparator){
        this.comparator = comparator;
    }

    @Override
    public int insert(final K key, final V value) {
        int index = hash(key);
        var node = data.get(index);
        if(node == null){
            data.add(index, new AVLTree<>(value, comparator));
            capacity++;
            return 0;
        }
        else
            return (node.insert(value));
    }


    @Override
    public int hash(final K key) {
        return key.hashCode() & (data.size() - 1);
    }

    @Override
    public void delete(final K key) {
        var node = data.get(hash(key));
        if(node == null){
            return;
        }
        else{
            node.delete(key);
        }
    }

    @Override
    public V find(final K key) {
        return null;
    }
}
