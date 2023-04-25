package org.atonInternship.DataStructure.Tree;

import com.sun.source.tree.Tree;

public interface TreeNode<K> {
    //how to make it in different way???
    void setLink(TreeNode<K> link);
    TreeNode<K> getLink();



    void setValue(K value);
    K getValue();

    void setHeight(int height);
    void setLeft(TreeNode<K> left);
    void setRight(TreeNode<K> right);
    int getHeight();
    TreeNode<K> getLeft();
    TreeNode<K> getRight();
}
