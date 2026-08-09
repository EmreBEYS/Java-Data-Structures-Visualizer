package com.emrebeys.datastructures.tree;

public class BinarySearchTreeDemo {

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("=== Binary Search Tree Demo ===");

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("\nAğaca eklenen değerler:");
        System.out.println("50, 30, 70, 20, 40, 60, 80");

        System.out.println("\nSize: " + tree.size());
        System.out.println("Boş mu?: " + tree.isEmpty());

        System.out.println("\n--- Arama ---");
        System.out.println("40 var mı?: " + tree.contains(40));
        System.out.println("90 var mı?: " + tree.contains(90));

        System.out.println("\n--- Minimum / Maximum ---");
        System.out.println("Minimum: " + tree.findMin());
        System.out.println("Maximum: " + tree.findMax());

        System.out.println("\n--- Traversal ---");

        System.out.print("Inorder   : ");
        tree.inorderTraversal();

        System.out.print("Preorder  : ");
        tree.preorderTraversal();

        System.out.print("Postorder : ");
        tree.postorderTraversal();

        System.out.println("\n--- Yaprak Düğüm Silme ---");
        System.out.println("20 silindi mi?: " + tree.delete(20));

        System.out.print("Inorder: ");
        tree.inorderTraversal();

        System.out.println("Size: " + tree.size());

        System.out.println("\n--- Tek Çocuklu Düğüm Silme ---");
        System.out.println("30 silindi mi?: " + tree.delete(30));

        System.out.print("Inorder: ");
        tree.inorderTraversal();

        System.out.println("Size: " + tree.size());

        System.out.println("\n--- İki Çocuklu Düğüm Silme ---");
        System.out.println("50 silindi mi?: " + tree.delete(50));

        System.out.print("Inorder: ");
        tree.inorderTraversal();

        System.out.println("Size: " + tree.size());

        System.out.println("\n--- Olmayan Değeri Silme ---");
        System.out.println("999 silindi mi?: " + tree.delete(999));
        System.out.println("Size: " + tree.size());

        System.out.println("\n=== Demo Tamamlandı ===");
    }
}