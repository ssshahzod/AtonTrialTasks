package org.atonInternship.DataStructure;

public abstract class TreeNode<K> {
    protected K value;
    protected int height = 0;

    protected TreeNode<K> left = null;
    protected TreeNode<K> right = null;


    public abstract void setValue(K value);
    public abstract K getValue();

    public void setHeight(int height) {
        this.height = height;
    }
    public void setLeft(TreeNode<K> left){
        this.left = left;
    }
    public void setRight(TreeNode<K> right){
        this.right = right;
    }

    public int getHeight(){
        return height;
    };
    public TreeNode<K> getLeft(){
        return left;
    }
    public TreeNode<K> getRight(){
        return right;
    }
}
