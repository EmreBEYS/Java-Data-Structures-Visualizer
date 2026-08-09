package com.emrebeys.datastructures.tree;

public class BinarySearchTree {

    private TreeNode root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private TreeNode insertRecursive(TreeNode current, int value) {
        if (current == null) {
            size++;
            return new TreeNode(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertRecursive(current.getRight(), value));
        }

        return current;
    }

    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(TreeNode current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.getValue()) {
            return true;
        }

        if (value < current.getValue()) {
            return containsRecursive(current.getLeft(), value);
        }

        return containsRecursive(current.getRight(), value);
    }

    public int findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty.");
        }

        TreeNode current = root;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getValue();
    }

    public int findMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty.");
        }

        TreeNode current = root;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current.getValue();
    }

    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(TreeNode current) {
        if (current == null) {
            return;
        }

        inorderRecursive(current.getLeft());
        System.out.print(current.getValue() + " ");
        inorderRecursive(current.getRight());
    }

    public void preorderTraversal() {
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(TreeNode current) {
        if (current == null) {
            return;
        }

        System.out.print(current.getValue() + " ");
        preorderRecursive(current.getLeft());
        preorderRecursive(current.getRight());
    }

    public void postorderTraversal() {
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(TreeNode current) {
        if (current == null) {
            return;
        }

        postorderRecursive(current.getLeft());
        postorderRecursive(current.getRight());
        System.out.print(current.getValue() + " ");
    }

    public TreeNode getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
        size = 0;
    }
    public boolean delete(int value) {
        if (!contains(value)) {
            return false;
        }

        root = deleteRecursive(root, value);
        size--;
        return true;
    }

    private TreeNode deleteRecursive(TreeNode current, int value) {
        if (current == null) {
            return null;
        }

        if (value < current.getValue()) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(deleteRecursive(current.getRight(), value));
        } else {

            // 1. Durum: Yaprak düğüm
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            // 2. Durum: Sadece sağ çocuk var
            if (current.getLeft() == null) {
                return current.getRight();
            }

            // 2. Durum: Sadece sol çocuk var
            if (current.getRight() == null) {
                return current.getLeft();
            }

            // 3. Durum: İki çocuk var
            int smallestValue = findMinValue(current.getRight());

            current.setValue(smallestValue);

            current.setRight(
                    deleteRecursive(current.getRight(), smallestValue)
            );
        }

        return current;
    }

    private int findMinValue(TreeNode node) {
        TreeNode current = node;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getValue();
    }
}