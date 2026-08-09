package com.emrebeys.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
    }

    @Test
    void shouldCreateTreeWithCorrectSize() {
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
    }

    @Test
    void shouldContainInsertedValue() {
        assertTrue(tree.contains(40));
        assertTrue(tree.contains(70));
    }

    @Test
    void shouldReturnFalseForMissingValue() {
        assertFalse(tree.contains(100));
        assertFalse(tree.contains(-10));
    }

    @Test
    void shouldFindMinimumValue() {
        assertEquals(20, tree.findMin());
    }

    @Test
    void shouldFindMaximumValue() {
        assertEquals(80, tree.findMax());
    }

    @Test
    void shouldNotInsertDuplicateValue() {
        tree.insert(50);
        tree.insert(30);

        assertEquals(7, tree.size());
    }

    @Test
    void shouldDeleteLeafNode() {
        assertTrue(tree.delete(20));

        assertFalse(tree.contains(20));
        assertEquals(6, tree.size());
    }

    @Test
    void shouldDeleteNodeWithOneChild() {
        tree.delete(20);

        assertTrue(tree.delete(30));

        assertFalse(tree.contains(30));
        assertTrue(tree.contains(40));
        assertEquals(5, tree.size());
    }

    @Test
    void shouldDeleteNodeWithTwoChildren() {
        assertTrue(tree.delete(70));

        assertFalse(tree.contains(70));
        assertTrue(tree.contains(60));
        assertTrue(tree.contains(80));
        assertEquals(6, tree.size());
    }

    @Test
    void shouldDeleteRootNode() {
        assertTrue(tree.delete(50));

        assertFalse(tree.contains(50));
        assertEquals(6, tree.size());

        assertNotNull(tree.getRoot());
    }

    @Test
    void shouldReturnFalseWhenDeletingMissingValue() {
        assertFalse(tree.delete(999));

        assertEquals(7, tree.size());
    }

    @Test
    void shouldClearTree() {
        tree.clear();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test
    void shouldThrowExceptionWhenFindingMinOnEmptyTree() {
        tree.clear();

        assertThrows(
                IllegalStateException.class,
                () -> tree.findMin()
        );
    }

    @Test
    void shouldThrowExceptionWhenFindingMaxOnEmptyTree() {
        tree.clear();

        assertThrows(
                IllegalStateException.class,
                () -> tree.findMax()
        );
    }

    @Test
    void shouldWorkWithSingleNode() {
        BinarySearchTree singleTree = new BinarySearchTree();

        singleTree.insert(10);

        assertEquals(1, singleTree.size());
        assertEquals(10, singleTree.findMin());
        assertEquals(10, singleTree.findMax());
        assertTrue(singleTree.contains(10));
    }

    @Test
    void shouldDeleteSingleRootNode() {
        BinarySearchTree singleTree = new BinarySearchTree();

        singleTree.insert(10);

        assertTrue(singleTree.delete(10));

        assertTrue(singleTree.isEmpty());
        assertEquals(0, singleTree.size());
        assertNull(singleTree.getRoot());
    }
}