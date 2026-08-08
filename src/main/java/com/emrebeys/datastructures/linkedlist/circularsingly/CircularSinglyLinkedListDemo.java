package com.emrebeys.datastructures.linkedlist.circularsingly;

public class CircularSinglyLinkedListDemo {

    public static void main(String[] args) {
        CircularSinglyLinkedList<Integer> list =
                new CircularSinglyLinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        list.addFirst(5);
        list.addLast(40);
        list.add(2, 15);

        System.out.println("Circular list:");
        list.print();

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Index 3: " + list.get(3));
        System.out.println("Contains 30: " + list.contains(30));
        System.out.println("Index of 30: " + list.indexOf(30));

        list.removeFirst();
        list.removeLast();
        list.remove(Integer.valueOf(20));

        System.out.println("After removals:");
        list.print();

        System.out.println("Size: " + list.size());
    }
}