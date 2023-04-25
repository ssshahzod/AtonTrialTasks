package org.atonInternship.DataStructure.Tree;

public interface RecursiveTree<K> {

    TreeNode<K> getRoot();
    int insert(TreeNode<K> insertNode);
    TreeNode<K> find(K key);
    void delete(K key);
}
