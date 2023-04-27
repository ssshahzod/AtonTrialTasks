package org.atonInternship.DataStructure.Table;
import org.atonInternship.DataStructure.Tree.Node.TaskTreeNode;
import org.atonInternship.DataStructure.Tree.Node.TreeNode;
import org.atonInternship.Exceptions.KeyNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class MyHashTableTest {


    private MyHashTable<String, TaskTreeNode<String>> table;
    @BeforeEach
    void setUp() {
        Comparator<String> comparator = String::compareTo;
        table = new MyHashTable<>(comparator);
    }

    @Test
    void testInsert() {
        TaskTreeNode<String> node1 = new TaskTreeNode<>("hello");
        TaskTreeNode<String> node2 = new TaskTreeNode<>("world");

        Assertions.assertEquals(0, table.insert("hello", node1));
        Assertions.assertEquals(1, table.insert("world", node2));
    }

    @Test
    void testDelete() {
        TaskTreeNode<String> node1 = new TaskTreeNode<>("hello");
        table.insert("hello", node1);
        table.delete("hello");

        Assertions.assertThrows(Exception.class, () -> table.find("hello"));
    }

    @Test
    void testFind() {
        TaskTreeNode<String> node1 = new TaskTreeNode<>("hello");
        TaskTreeNode<String> node2 = new TaskTreeNode<>("world");
        table.insert("hello", node1);
        table.insert("world", node2);

        Assertions.assertEquals(node1, table.find("hello"));
        Assertions.assertEquals(node2, table.find("world"));

        Assertions.assertThrows(Exception.class, () -> table.find("tmp"));
    }
}
