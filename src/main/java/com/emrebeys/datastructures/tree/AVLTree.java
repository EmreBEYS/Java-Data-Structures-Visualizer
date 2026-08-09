package com.emrebeys.datastructures.tree;

public class AVLTree {

    private AVLNode root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private AVLNode insertRecursive(AVLNode node, int value) {

        if (node == null) {
            size++;
            return new AVLNode(value);
        }

        if (value < node.getValue()) {
            node.setLeft(insertRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRecursive(node.getRight(), value));
        } else {
            // Duplicate değer eklemiyoruz.
            return node;
        }

        updateHeight(node);

        int balance = getBalance(node);

        // LL - Left Left
        if (balance > 1 && value < node.getLeft().getValue()) {
            return rotateRight(node);
        }

        // RR - Right Right
        if (balance < -1 && value > node.getRight().getValue()) {
            return rotateLeft(node);
        }

        // LR - Left Right
        if (balance > 1 && value > node.getLeft().getValue()) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // RL - Right Left
        if (balance < -1 && value < node.getRight().getValue()) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode rotateRight(AVLNode y) {

        AVLNode x = y.getLeft();
        AVLNode temp = x.getRight();

        x.setRight(y);
        y.setLeft(temp);

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {

        AVLNode y = x.getRight();
        AVLNode temp = y.getLeft();

        y.setLeft(x);
        x.setRight(temp);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private void updateHeight(AVLNode node) {
        node.setHeight(
                1 + Math.max(
                        getHeight(node.getLeft()),
                        getHeight(node.getRight())
                )
        );
    }

    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.getHeight();
    }

    public int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    public boolean contains(int value) {
        AVLNode current = root;

        while (current != null) {

            if (value == current.getValue()) {
                return true;
            }

            if (value < current.getValue()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return false;
    }

    public int findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty.");
        }

        AVLNode current = root;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getValue();
    }

    public int findMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty.");
        }

        AVLNode current = root;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current.getValue();
    }

    public void inorderTraversal() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(AVLNode node) {
        if (node == null) {
            return;
        }

        inorderRecursive(node.getLeft());
        System.out.print(node.getValue() + " ");
        inorderRecursive(node.getRight());
    }

    public void preorderTraversal() {
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(AVLNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getValue() + " ");
        preorderRecursive(node.getLeft());
        preorderRecursive(node.getRight());
    }

    public void postorderTraversal() {
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(AVLNode node) {
        if (node == null) {
            return;
        }

        postorderRecursive(node.getLeft());
        postorderRecursive(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    public AVLNode getRoot() {
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

    private AVLNode deleteRecursive(AVLNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(deleteRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(deleteRecursive(node.getRight(), value));
        } else {

            // Yaprak veya tek çocuklu düğüm
            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            // İki çocuklu düğüm
            AVLNode successor = findMinNode(node.getRight());

            node.setValue(successor.getValue());

            node.setRight(
                    deleteRecursive(
                            node.getRight(),
                            successor.getValue()
                    )
            );
        }

        updateHeight(node);

        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rotateRight(node);
        }

        // LR
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return rotateLeft(node);
        }

        // RL
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode findMinNode(AVLNode node) {
        AVLNode current = node;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

}