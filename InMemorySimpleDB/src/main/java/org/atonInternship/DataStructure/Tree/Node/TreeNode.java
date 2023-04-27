package org.atonInternship.DataStructure.Tree.Node;

public interface TreeNode<K> {
    //how to make it in different way???
    void setLink(TreeNode<?> link);
    TreeNode<?> getLink();


    void setValue(K value);
    K getValue();

    void setHeight(int height);
    void setLeft(TreeNode<K> left);
    void setRight(TreeNode<K> right);
    int getHeight();
    TreeNode<K> getLeft();
    TreeNode<K> getRight();
}
