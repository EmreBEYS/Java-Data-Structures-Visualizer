package com.emrebeys.datastructures.linkedlist.skiplist;

import java.util.Comparator;

public class SkipListDemo {

    public static void main(String[] args) {
        SkipList<Integer> skipList =
                new SkipList<>(Comparator.naturalOrder());

        skipList.add(30);
        skipList.add(10);
        skipList.add(50);
        skipList.add(20);
        skipList.add(40);
        skipList.add(15);
        skipList.add(35);

        System.out.println("Skip List levels:");
        skipList.printLevels();

        System.out.println();
        System.out.println("Size: " + skipList.size());
        System.out.println("First: " + skipList.first());
        System.out.println("Last: " + skipList.last());

        System.out.println(
                "Contains 20: " + skipList.contains(20)
        );

        System.out.println(
                "Contains 99: " + skipList.contains(99)
        );

        System.out.println(
                "Duplicate 20 added: " + skipList.add(20)
        );

        skipList.remove(10);
        skipList.remove(30);
        skipList.remove(50);

        System.out.println();
        System.out.println("After removals:");
        skipList.printLevels();

        System.out.println("Size: " + skipList.size());
    }
}