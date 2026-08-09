package com.emrebeys.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    private AVLTree tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree();
    }

    @Test
    void shouldCreateEmptyTree() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test
    void shouldInsertSingleValue() {
        tree.insert(50);

        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertEquals(50, tree.getRoot().getValue());
    }

    @Test
    void shouldInsertMultipleValues() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        assertEquals(3, tree.size());

        assertTrue(tree.contains(50));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(70));
    }

    @Test
    void shouldNotInsertDuplicateValue() {
        tree.insert(50);
        tree.insert(50);
        tree.insert(50);

        assertEquals(1, tree.size());
    }

    @Test
    void shouldReturnFalseForMissingValue() {
        tree.insert(50);
        tree.insert(30);

        assertFalse(tree.contains(100));
    }

    // LL Rotation
    @Test
    void shouldPerformLLRotation() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        assertEquals(20, tree.getRoot().getValue());
        assertEquals(10, tree.getRoot().getLeft().getValue());
        assertEquals(30, tree.getRoot().getRight().getValue());
    }

    // RR Rotation
    @Test
    void shouldPerformRRRotation() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assertEquals(20, tree.getRoot().getValue());
        assertEquals(10, tree.getRoot().getLeft().getValue());
        assertEquals(30, tree.getRoot().getRight().getValue());
    }

    // LR Rotation
    @Test
    void shouldPerformLRRotation() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        assertEquals(20, tree.getRoot().getValue());
        assertEquals(10, tree.getRoot().getLeft().getValue());
        assertEquals(30, tree.getRoot().getRight().getValue());
    }

    // RL Rotation
    @Test
    void shouldPerformRLRotation() {
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        assertEquals(20, tree.getRoot().getValue());
        assertEquals(10, tree.getRoot().getLeft().getValue());
        assertEquals(30, tree.getRoot().getRight().getValue());
    }

    @Test
    void shouldFindMinimumValue() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);

        assertEquals(20, tree.findMin());
    }

    @Test
    void shouldFindMaximumValue() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        assertEquals(80, tree.findMax());
    }

    @Test
    void shouldThrowExceptionWhenFindingMinOnEmptyTree() {
        assertThrows(
                IllegalStateException.class,
                tree::findMin
        );
    }

    @Test
    void shouldThrowExceptionWhenFindingMaxOnEmptyTree() {
        assertThrows(
                IllegalStateException.class,
                tree::findMax
        );
    }

    @Test
    void shouldMaintainBalancedTree() {
        int[] values = {
                50, 30, 70,
                20, 40, 60, 80,
                10, 25, 35, 45,
                55, 65, 75, 90
        };

        for (int value : values) {
            tree.insert(value);
        }

        assertTrue(isBalanced(tree.getRoot()));
        assertEquals(15, tree.size());
    }

    @Test
    void shouldMaintainCorrectHeights() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        AVLNode root = tree.getRoot();

        assertEquals(2, root.getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
    }

    @Test
    void shouldClearTree() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        tree.clear();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    private boolean isBalanced(AVLNode node) {

        if (node == null) {
            return true;
        }

        int balance = tree.getBalance(node);

        if (balance < -1 || balance > 1) {
            return false;
        }

        return isBalanced(node.getLeft())
                && isBalanced(node.getRight());
    }
    @Test
    void shouldDeleteLeafNode() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);

        assertTrue(tree.delete(20));

        assertFalse(tree.contains(20));
        assertEquals(2, tree.size());
        assertTrue(isBalanced(tree.getRoot()));
    }

    @Test
    void shouldDeleteNodeWithOneChild() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);

        assertTrue(tree.delete(20));

        assertFalse(tree.contains(20));
        assertTrue(tree.contains(10));
        assertEquals(3, tree.size());
        assertTrue(isBalanced(tree.getRoot()));
    }

    @Test
    void shouldDeleteNodeWithTwoChildren() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        assertTrue(tree.delete(70));

        assertFalse(tree.contains(70));
        assertTrue(tree.contains(60));
        assertTrue(tree.contains(80));

        assertEquals(4, tree.size());
        assertTrue(isBalanced(tree.getRoot()));
    }

    @Test
    void shouldDeleteRootNode() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        assertTrue(tree.delete(50));

        assertFalse(tree.contains(50));
        assertEquals(2, tree.size());
        assertTrue(isBalanced(tree.getRoot()));
    }

    @Test
    void shouldReturnFalseWhenDeletingMissingValue() {
        tree.insert(50);
        tree.insert(30);

        assertFalse(tree.delete(999));
        assertEquals(2, tree.size());
    }

    @Test
    void shouldRebalanceAfterDeletion() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);

        tree.delete(40);

        assertEquals(20, tree.getRoot().getValue());
        assertEquals(10, tree.getRoot().getLeft().getValue());
        assertEquals(30, tree.getRoot().getRight().getValue());

        assertTrue(isBalanced(tree.getRoot()));
    }

    @Test
    void shouldRemainBalancedAfterMultipleDeletions() {
        int[] values = {
                50, 30, 70, 20, 40,
                60, 80, 10, 25, 35,
                45, 55, 65, 75, 90
        };

        for (int value : values) {
            tree.insert(value);
        }

        tree.delete(90);
        tree.delete(80);
        tree.delete(75);
        tree.delete(70);
        tree.delete(20);

        assertEquals(10, tree.size());
        assertTrue(isBalanced(tree.getRoot()));
    }
}