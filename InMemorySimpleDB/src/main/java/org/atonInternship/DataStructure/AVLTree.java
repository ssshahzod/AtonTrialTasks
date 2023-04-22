package org.atonInternship.DataStructure;


import java.util.Comparator;
import lombok.AllArgsConstructor;

//for avl tree:
//it takes O(log n) to find element in the worst case
// and O(n) the worst case memory usage
@AllArgsConstructor
public class AVLTree<K> implements Tree<K>{
    private TreeNode<K> root = null;
    private Comparator<K> comparator;

    public AVLTree(Comparator<K> comparator){
        this.comparator = comparator;
    }

    //side = 0 => left; side = 1 => right;

    private void updateHeight(TreeNode<K> node){
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    private int height(TreeNode<K> node){
        return node == null ? -1 : node.getHeight();
    }
    private int getBalance(TreeNode<K> node){
        return node == null ? 0 : height(node.right) - height(node.left);
    }

    private TreeNode<K> littleLeftRotation(TreeNode<K> aNode){
        var bNode = aNode.right;
        aNode.right = bNode.left;
        bNode.left = aNode;
        updateHeight(aNode);
        updateHeight(bNode);
        return bNode;
    }

    private TreeNode<K> littleRightRotation(TreeNode<K> bNode){
        var aNode = bNode.left;
        bNode.left = aNode.right;
        aNode.right = bNode;
        updateHeight(aNode);
        updateHeight(bNode);
        return aNode;
    }

    private TreeNode<K> balance(TreeNode<K> node){
        updateHeight(node);
        int balance = getBalance(node);
        if(balance > 1){
            if(!(height(node.right.right) > height(node.right.left))){
                node.right = littleRightRotation(node.right);
            }
            node = littleLeftRotation(node);
        } else if (balance < -1){
            if(!(height(node.left.left) > height(node.left.right))){
                node.left = littleRightRotation(node.left);
            }
            node = littleRightRotation(node);
        }
        return node;
    }

    public TreeNode<K> insert(TreeNode<K> rootNode, TreeNode<K> insertNode){
        if(rootNode == null){
            return insertNode;
        }
        else if(comparator.compare(rootNode.getValue(), insertNode.getValue()) > 0){
            rootNode.left = insert(rootNode.left, insertNode);
        }
        else if(comparator.compare(rootNode.getValue(), insertNode.getValue()) < 0){
            rootNode.right = insert(rootNode.right, insertNode);
        } else{
            throw new RuntimeException("Key duplicate!");
        }
        return balance(rootNode);
    }

    public TreeNode<K> find(K key){
        TreeNode<K> res = root;
        while(res != null){
            if(res.getValue().equals(key))
                break;
            if(comparator.compare(res.getValue(), key) < 0)
                res = res.right;
            else
                res = res.left;
        }

        return res;
    }

    private TreeNode<K> mostLeftChild(TreeNode<K> node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public TreeNode<K> delete(TreeNode<K> node, K key){
        if(node == null){
            return null;
        } else if(comparator.compare(node.getValue(), key) < 0){
            node.right = delete(node.right, key);
        } else if(comparator.compare(node.getValue(), key) > 0){
            node.left = delete(node.left, key);
        } else{
            if(node.left == null || node.right == null)
                node = (node.left == null) ? node.right : node.left;
            else{
                TreeNode<K> mostLeftChild = mostLeftChild(node.right);
                node.setValue(mostLeftChild.getValue());
                node.right = delete(node.right, key);
            }
        }
        if(node != null)
            node = balance(node);
        return node;
    }


}
