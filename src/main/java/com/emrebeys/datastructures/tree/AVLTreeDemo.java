package com.emrebeys.datastructures.tree;

public class AVLTreeDemo {

    public static void main(String[] args) {

        System.out.println("=== AVL Tree Demo ===");

        // LL Rotation
        AVLTree llTree = new AVLTree();
        llTree.insert(30);
        llTree.insert(20);
        llTree.insert(10);

        System.out.println("\n--- LL Rotation ---");
        System.out.print("Inorder  : ");
        llTree.inorderTraversal();

        System.out.print("Preorder : ");
        llTree.preorderTraversal();

        System.out.println("Root: " + llTree.getRoot().getValue());

        // RR Rotation
        AVLTree rrTree = new AVLTree();
        rrTree.insert(10);
        rrTree.insert(20);
        rrTree.insert(30);

        System.out.println("\n--- RR Rotation ---");
        System.out.print("Inorder  : ");
        rrTree.inorderTraversal();

        System.out.print("Preorder : ");
        rrTree.preorderTraversal();

        System.out.println("Root: " + rrTree.getRoot().getValue());

        // LR Rotation
        AVLTree lrTree = new AVLTree();
        lrTree.insert(30);
        lrTree.insert(10);
        lrTree.insert(20);

        System.out.println("\n--- LR Rotation ---");
        System.out.print("Inorder  : ");
        lrTree.inorderTraversal();

        System.out.print("Preorder : ");
        lrTree.preorderTraversal();

        System.out.println("Root: " + lrTree.getRoot().getValue());

        // RL Rotation
        AVLTree rlTree = new AVLTree();
        rlTree.insert(10);
        rlTree.insert(30);
        rlTree.insert(20);

        System.out.println("\n--- RL Rotation ---");
        System.out.print("Inorder  : ");
        rlTree.inorderTraversal();

        System.out.print("Preorder : ");
        rlTree.preorderTraversal();

        System.out.println("Root: " + rlTree.getRoot().getValue());

        // Genel AVL örneği
        AVLTree tree = new AVLTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        tree.insert(10);

        System.out.println("\n--- Genel AVL Ağacı ---");

        System.out.print("Inorder   : ");
        tree.inorderTraversal();

        System.out.print("Preorder  : ");
        tree.preorderTraversal();

        System.out.print("Postorder : ");
        tree.postorderTraversal();

        System.out.println("Size: " + tree.size());
        System.out.println("Min: " + tree.findMin());
        System.out.println("Max: " + tree.findMax());
        System.out.println("40 var mı?: " + tree.contains(40));
        System.out.println("100 var mı?: " + tree.contains(100));

        System.out.println("\nRoot: " + tree.getRoot().getValue());
        System.out.println("Root balance: " + tree.getBalance(tree.getRoot()));

        System.out.println("\n=== Demo Tamamlandı ===");
    }
}