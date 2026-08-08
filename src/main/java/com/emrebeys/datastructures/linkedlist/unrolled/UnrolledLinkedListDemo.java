package com.emrebeys.datastructures.linkedlist.unrolled;

public class UnrolledLinkedListDemo {

    public static void main(String[] args) {
        UnrolledLinkedList<Integer> list =
                new UnrolledLinkedList<>(4);

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(70);

        System.out.println("Initial list:");
        list.print();

        list.addFirst(5);
        list.add(3, 25);

        System.out.println("After additions:");
        list.print();

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Index 4: " + list.get(4));
        System.out.println("Contains 50: " + list.contains(50));
        System.out.println("Index of 60: " + list.indexOf(60));

        list.remove(0);
        list.remove(Integer.valueOf(40));
        list.remove(list.size() - 1);

        System.out.println("After removals:");
        list.print();

        System.out.println("Size: " + list.size());
        System.out.println("Node count: " + list.nodeCount());
        System.out.println(
                "Node capacity: " + list.nodeCapacity()
        );
    }
}