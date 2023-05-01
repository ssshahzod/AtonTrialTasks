package org.atonInternship.DataStructure.Tree;


import java.util.ArrayList;
import java.util.Comparator;
import org.atonInternship.DataStructure.Tree.Node.TreeNode;


public class AVLTree<K> implements TreeInterface<K> {
    private TreeNode<K> root = null;
    private final Comparator<K> comparator;

    public AVLTree(Comparator<K> comparator){
        this.comparator = comparator;
    }
    public AVLTree(TreeNode<K> root, Comparator<K> comparator){
        this.root = root;
        this.comparator = comparator;
    }

    private void updateHeight(TreeNode<K> node){
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }
    private int height(TreeNode<K> node){
        return node == null ? -1 : node.getHeight();
    }
    private int getBalance(TreeNode<K> node){
        return node == null ? 0 : height(node.getRight()) - height(node.getLeft());
    }

    private TreeNode<K> littleLeftRotation(TreeNode<K> aNode){
        var bNode = aNode.getRight();
        aNode.setRight(bNode.getLeft());
        bNode.setLeft(aNode);
        updateHeight(aNode);
        updateHeight(bNode);
        return bNode;
    }
    private TreeNode<K> littleRightRotation(TreeNode<K> bNode){
        var aNode = bNode.getLeft();
        bNode.setLeft(aNode.getRight());
        aNode.setRight(bNode);
        updateHeight(aNode);
        updateHeight(bNode);
        return aNode;
    }
    private TreeNode<K> balance(TreeNode<K> node){
        updateHeight(node);
        int balance = getBalance(node);
        if(balance > 1){
            if(!(height(node.getRight().getRight()) > height(node.getRight().getLeft()))){
                var tmp = node.getRight();
                tmp = littleRightRotation(node.getRight());
            }
            node = littleLeftRotation(node);
        } else if (balance < -1){
            if(!(height(node.getLeft().getLeft()) > height(node.getLeft().getRight()))){
                var tmp = node.getLeft();
                tmp = littleRightRotation(node.getLeft());
            }
            node = littleRightRotation(node);
        }
        return node;
    }
    private TreeNode<K> mostLeftChild(TreeNode<K> node){
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public int insert(TreeNode<K> insertNode){
        var tmp = innerInsert(root, insertNode);
        if(tmp != null){
            root = tmp;
            return 0;
        } else{
            return 1;
        }
    }
    public TreeNode<K> innerInsert(TreeNode<K> rootNode, TreeNode<K> insertNode){
        if(rootNode == null){
            return insertNode;
        }
        else if(comparator.compare(rootNode.getValue(), insertNode.getValue()) > 0){
            rootNode.setLeft(innerInsert(rootNode.getLeft(), insertNode));
        }
        else if(comparator.compare(rootNode.getValue(), insertNode.getValue()) <= 0){
            rootNode.setRight(innerInsert(rootNode.getRight(), insertNode));
        } else{
            return null;
        }
        return balance(rootNode);
    }

    @Override
    public TreeNode<K> getRoot(){
        return root;
    }


    public ArrayList<TreeNode<K>> find(K key){
        TreeNode<K> res = root;
        ArrayList<TreeNode<K>> result = new ArrayList<>();
        while(res != null){
            if(res.getValue().equals(key))
                result.add(res);
            if(comparator.compare(res.getValue(), key) <= 0)
                res = res.getRight();
            else
                res = res.getLeft();
        }
        return result;
    }

    public void delete(K key){
        root = delete(root, key);
    }

    private TreeNode<K> delete(TreeNode<K> node, K key){
        if(node == null){
            return null;
        } else if(comparator.compare(node.getValue(), key) < 0){
            node.setRight(delete(node.getRight(), key));
        } else if(comparator.compare(node.getValue(), key) > 0){
            node.setLeft(delete(node.getLeft(), key));
        } else{
            if(node.getLeft() == null || node.getRight() == null)
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            else{
                TreeNode<K> mostLeftChild = mostLeftChild(node.getRight());
                node.setValue(mostLeftChild.getValue());
                node.setLink(mostLeftChild.getLink());
                node.setRight(delete(node.getRight(), node.getValue()));
            }
        }
        if(node != null)
            node = balance(node);
        return node;
    }

    public boolean isBalanced(TreeNode<K> node) {
        if (node == null) {
            return true;
        }
        int balance = getBalance(node);
        if (Math.abs(balance) > 1) {
            return false;
        }
        return isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }


}
