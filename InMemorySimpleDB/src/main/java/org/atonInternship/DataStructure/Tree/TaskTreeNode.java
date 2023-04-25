package org.atonInternship.DataStructure.Tree;

public class TaskTreeNode<K> implements TreeNode<K>{
    private K value;
    private int height = 0;

    protected TreeNode<K> left = null;
    protected TreeNode<K> right = null;
    private TreeNode<K> link = null;

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

    public TreeNode<K> getLink() {
        return link;
    }
    public void setLink(final TreeNode<K> link) {
        this.link = link;
    }
    public TaskTreeNode(K value){
        this.value = value;
    }

}
