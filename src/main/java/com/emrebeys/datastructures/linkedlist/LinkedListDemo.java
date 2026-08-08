package com.emrebeys.datastructures.linkedlist;

public class LinkedListDemo {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        list.addFirst(5);
        list.addLast(40);
        list.add(2, 15);

        list.print();

        System.out.println("Size: " + list.size());
        System.out.println("Index 3: " + list.get(3));
        System.out.println("Contains 20: " + list.contains(20));

        list.remove(0);
        list.remove(Integer.valueOf(30));

        list.print();
    }
}