package org.atonInternship.DataStructure.Tree.Node;

import org.atonInternship.DataStructure.Tree.Node.TreeNode;

public class TaskTreeNode<K> implements TreeNode<K> {
    private K value;
    private int height = 0;

    protected TreeNode<K> left = null;
    protected TreeNode<K> right = null;
    private TreeNode<?> link = null;

    public TaskTreeNode(K value, TreeNode<K> left, TreeNode<K> right, TreeNode<K> link){
        this.left = left;
        this.right = right;
        this.value = value;
        this.link = link;
    }


    public void setValue(final K value) {
        this.value = value;
    }
    @Override
    public K getValue(){
        return this.value;
    }

    @Override
    public void setHeight(final int height) {

    }

    @Override
    public void setLeft(final TreeNode<K> left) {
        this.left = left;
    }
    @Override
    public void setRight(final TreeNode<K> right) {
        this.right = right;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public TreeNode<K> getLeft() {
        return left;
    }

    @Override
    public TreeNode<K> getRight() {
        return right;
    }

    public TreeNode<?> getLink() {
        return link;
    }
    public void setLink(final TreeNode<?> link) {
        this.link = link;
    }
    public TaskTreeNode(K value){
        this.value = value;
    }

}
