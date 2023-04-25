package org.atonInternship.DataStructure.Tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskRecursiveTreeNodeTest {

    @Test
    void testSetValue() {
        TaskTreeNode<String> node = new TaskTreeNode<>("test");

        node.setValue("new value");

        assertEquals("new value", node.getValue());
    }

    @Test
    void testGetValue() {
        TaskTreeNode<String> node = new TaskTreeNode<>("test");

        assertEquals("test", node.getValue());
    }

    @Test
    void testGetLink() {
        TaskTreeNode<String> node = new TaskTreeNode<>("test");

        TaskTreeNode<String> linkNode = new TaskTreeNode<>("link");

        node.setLink(linkNode);

        assertEquals(linkNode, node.getLink());
    }

    @Test
    void testSetLink() {
        TaskTreeNode<String> node = new TaskTreeNode<>("test");

        TaskTreeNode<String> linkNode = new TaskTreeNode<>("link");

        node.setLink(linkNode);

        assertEquals(linkNode, node.getLink());
    }
}