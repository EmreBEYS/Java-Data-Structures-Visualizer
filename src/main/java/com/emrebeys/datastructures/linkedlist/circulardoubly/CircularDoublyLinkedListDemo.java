package com.emrebeys.datastructures.linkedlist.circulardoubly;

public class CircularDoublyLinkedListDemo {

    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> list =
                new CircularDoublyLinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        list.addFirst(5);
        list.addLast(40);
        list.add(2, 15);

        System.out.println("Forward:");
        list.printForward();

        System.out.println("Backward:");
        list.printBackward();

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Index 3: " + list.get(3));
        System.out.println("Contains 30: " + list.contains(30));
        System.out.println("Index of 30: " + list.indexOf(30));

        list.removeFirst();
        list.removeLast();
        list.remove(Integer.valueOf(20));

        System.out.println("After removals:");
        list.printForward();
        list.printBackward();

        System.out.println("Size: " + list.size());
    }
}