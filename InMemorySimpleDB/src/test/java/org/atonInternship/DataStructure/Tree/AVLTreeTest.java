package org.atonInternship.DataStructure.Tree;

import org.atonInternship.DataStructure.Tree.Node.TaskTreeNode;
import org.atonInternship.DataStructure.Tree.Node.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

class AVLTreeTest {
    private AVLTree<Integer> tree;

    @BeforeEach
    public void setUp() {
        Comparator<Integer> comparator = Integer::compareTo;
        tree = new AVLTree<>(comparator);
    }

    @Test
    public void testInsertBalanced() {
        // Insert nodes in balanced order
        for (int i = 1; i <= 10; i++) {
            TreeNode<Integer> node = new TaskTreeNode<>(i);
            tree.insert(node);
            Assertions.assertTrue(tree.isBalanced());
        }
    }

    @Test
    public void testInsertRandom() {
        // Insert nodes in random order
        Integer[] values = {5, 3, 1, 4, 7, 9, 6, 2, 8, 10};
        for (int i = 0; i < values.length; i++) {
            TreeNode<Integer> node = new TaskTreeNode<>(values[i]);
            tree.insert(node);
            Assertions.assertTrue(tree.isBalanced());
        }
    }

    @Test
    public void testDelete() {
        // Insert nodes
        Integer[] values = {5, 3, 1, 4, 7, 9, 6, 2, 8, 10};
        for (int i = 0; i < values.length; i++) {
            TreeNode<Integer> node = new TaskTreeNode<>(values[i]);
            tree.insert(node);
        }

        // Delete some nodes
        tree.delete(2);
        Assertions.assertTrue(tree.isBalanced());
        tree.delete(4);
        Assertions.assertTrue(tree.isBalanced());
        tree.delete(9);
        Assertions.assertTrue(tree.isBalanced());
    }

    @Test
    public void testDeleteAll() {
        // Insert nodes
        Integer[] values = {5, 3, 1, 4, 7, 9, 6, 2, 8, 10};
        for (int i = 0; i < values.length; i++) {
            TreeNode<Integer> node = new TaskTreeNode<>(values[i]);
            tree.insert(node);
        }

        // Delete all nodes
        for (int i = 0; i < values.length; i++) {
            tree.delete(values[i]);
            Assertions.assertTrue(tree.isBalanced());
        }
        Assertions.assertNull(tree.getRoot());
    }
}
