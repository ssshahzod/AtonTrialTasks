package org.atonInternship.DataStructure.Tree;

import org.atonInternship.DataStructure.Tree.Node.TreeNode;

public interface TreeInterface<K> {

    TreeNode<K> getRoot();
    int insert(TreeNode<K> insertNode);
    TreeNode<K> find(K key);
    void delete(K key);
}
