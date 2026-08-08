package com.emrebeys.datastructures.linkedlist.doubly;

public class DoublyLinkedListDemo {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

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

        list.removeFirst();
        list.removeLast();
        list.remove(Integer.valueOf(20));

        System.out.println("After removals:");
        list.printForward();

        System.out.println("Size: " + list.size());
    }
}