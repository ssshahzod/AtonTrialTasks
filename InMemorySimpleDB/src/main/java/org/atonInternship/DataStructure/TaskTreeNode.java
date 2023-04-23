package org.atonInternship.DataStructure;
import java.util.HashMap;
public class TaskTreeNode<K> extends TreeNode<K>{

    private TreeNode<?> link = null;

    public TaskTreeNode(K value, TreeNode<K> left, TreeNode<K> right, TreeNode<K> link){
        super.left = left;
        super.right = right;
        super.value = value;
        this.link = link;
    }

    @Override
    public void setValue(final K value) {
        super.value = value;
    }
    @Override
    public K getValue(){
        return super.value;
    }
    public TreeNode<?> getLink() {
        return link;
    }
    public void setLink(final TreeNode<?> link) {
        this.link = link;
    }
    public TaskTreeNode(K value){
        super.value = value;
    }

}
