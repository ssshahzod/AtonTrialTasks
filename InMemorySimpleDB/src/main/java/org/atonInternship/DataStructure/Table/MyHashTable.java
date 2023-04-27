package org.atonInternship.DataStructure.Table;

import java.util.ArrayList;
import java.util.Comparator;

import org.atonInternship.DataStructure.Tree.AVLTree;
import org.atonInternship.DataStructure.Tree.Node.TreeNode;
import org.atonInternship.Exceptions.KeyNotFoundException;

public class MyHashTable<K, V extends TreeNode<K>> implements MyHashTableInterface<K, V>{
    private int defaultSize = 20;
    private final ArrayList<AVLTree<K>> data = new ArrayList<>(defaultSize);
    private final Comparator<K> comparator;
    private int capacity = 0;

    public MyHashTable(Comparator<K> comparator){
        this.comparator = comparator;
    }

    public MyHashTable(Comparator<K> comparator, int size){
        this.comparator = comparator;
        this.defaultSize = size;
    }

    @Override
    public int insert(final K key, final V value) {
        int index = hash(key);
        AVLTree<K> tree = data.get(index);
        if(tree == null){
            tree = new AVLTree<>(comparator);
            data.add(index, tree);
            capacity++;
        }
        return tree.insert(value);
    }

    @Override
    public int hash(final K key) {
        return key.hashCode() % data.size();
    }

    @Override
    public void delete(final K key) {
        int index = hash(key);
        AVLTree<K> tree = data.get(index);
        if(tree != null){
            tree.delete(key);
            if(tree.getRoot() == null){
                data.set(index, null);
                capacity--;
            }
        }
    }

    @Override
    public V find(final K key) {
        AVLTree<K> tree = data.get(hash(key));
        if(tree != null){
            @SuppressWarnings("unchecked") V node = (V) tree.find(key);

            if(node != null && node.getValue().equals(key)){
                return node;
            }
        }
        throw new KeyNotFoundException("No value found with key:" + key.toString());
    }
}
