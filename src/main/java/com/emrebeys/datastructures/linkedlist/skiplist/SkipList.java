package com.emrebeys.datastructures.linkedlist.skiplist;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class SkipList<T> {

    private static final int DEFAULT_MAX_LEVEL = 16;
    private static final double DEFAULT_PROBABILITY = 0.5;

    private final int maxLevel;
    private final double probability;
    private final Comparator<? super T> comparator;
    private final Random random;
    private final SkipListNode<T> head;

    private int currentLevel;
    private int size;

    public SkipList(Comparator<? super T> comparator) {
        this(
                comparator,
                DEFAULT_MAX_LEVEL,
                DEFAULT_PROBABILITY,
                new Random()
        );
    }

    public SkipList(
            Comparator<? super T> comparator,
            int maxLevel,
            double probability
    ) {
        this(comparator, maxLevel, probability, new Random());
    }

    SkipList(
            Comparator<? super T> comparator,
            int maxLevel,
            double probability,
            Random random
    ) {
        this.comparator = Objects.requireNonNull(
                comparator,
                "Comparator cannot be null."
        );

        this.random = Objects.requireNonNull(
                random,
                "Random cannot be null."
        );

        if (maxLevel < 1) {
            throw new IllegalArgumentException(
                    "Maximum level must be at least 1."
            );
        }

        if (probability <= 0.0 || probability >= 1.0) {
            throw new IllegalArgumentException(
                    "Probability must be between 0 and 1."
            );
        }

        this.maxLevel = maxLevel;
        this.probability = probability;
        this.head = new SkipListNode<>(null, maxLevel);
    }

    public boolean add(T value) {
        requireValue(value);

        SkipListNode<T>[] update = createUpdateArray();
        SkipListNode<T> current = head;

        for (int level = currentLevel; level >= 0; level--) {
            while (
                    current.forward[level] != null
                            && compare(
                            current.forward[level].value,
                            value
                    ) < 0
            ) {
                current = current.forward[level];
            }

            update[level] = current;
        }

        current = current.forward[0];

        if (current != null && compare(current.value, value) == 0) {
            return false;
        }

        int newLevel = randomLevel();

        if (newLevel > currentLevel) {
            for (
                    int level = currentLevel + 1;
                    level <= newLevel;
                    level++
            ) {
                update[level] = head;
            }

            currentLevel = newLevel;
        }

        SkipListNode<T> newNode =
                new SkipListNode<>(value, newLevel);

        for (int level = 0; level <= newLevel; level++) {
            newNode.forward[level] = update[level].forward[level];
            update[level].forward[level] = newNode;
        }

        size++;
        return true;
    }

    public boolean contains(T value) {
        requireValue(value);

        SkipListNode<T> current = findPreviousNode(value);
        current = current.forward[0];

        return current != null
                && compare(current.value, value) == 0;
    }

    public boolean remove(T value) {
        requireValue(value);

        SkipListNode<T>[] update = createUpdateArray();
        SkipListNode<T> current = head;

        for (int level = currentLevel; level >= 0; level--) {
            while (
                    current.forward[level] != null
                            && compare(
                            current.forward[level].value,
                            value
                    ) < 0
            ) {
                current = current.forward[level];
            }

            update[level] = current;
        }

        current = current.forward[0];

        if (current == null || compare(current.value, value) != 0) {
            return false;
        }

        for (int level = 0; level <= currentLevel; level++) {
            if (update[level].forward[level] != current) {
                break;
            }

            update[level].forward[level] =
                    current.forward[level];
        }

        while (
                currentLevel > 0
                        && head.forward[currentLevel] == null
        ) {
            currentLevel--;
        }

        size--;
        return true;
    }

    public T first() {
        ensureNotEmpty();
        return head.forward[0].value;
    }

    public T last() {
        ensureNotEmpty();

        SkipListNode<T> current = head;

        for (int level = currentLevel; level >= 0; level--) {
            while (current.forward[level] != null) {
                current = current.forward[level];
            }
        }

        return current.value;
    }

    public int size() {
        return size;
    }

    public int currentLevel() {
        return currentLevel;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int level = 0; level <= maxLevel; level++) {
            head.forward[level] = null;
        }

        currentLevel = 0;
        size = 0;
    }

    public void printLevels() {
        if (isEmpty()) {
            System.out.println("SkipList is empty.");
            return;
        }

        for (int level = currentLevel; level >= 0; level--) {
            System.out.print("Level " + level + ": ");

            SkipListNode<T> current = head.forward[level];

            while (current != null) {
                System.out.print(current.value);

                current = current.forward[level];

                if (current != null) {
                    System.out.print(" -> ");
                }
            }

            System.out.println();
        }
    }

    private SkipListNode<T> findPreviousNode(T value) {
        SkipListNode<T> current = head;

        for (int level = currentLevel; level >= 0; level--) {
            while (
                    current.forward[level] != null
                            && compare(
                            current.forward[level].value,
                            value
                    ) < 0
            ) {
                current = current.forward[level];
            }
        }

        return current;
    }

    private int randomLevel() {
        int level = 0;

        while (
                level < maxLevel
                        && random.nextDouble() < probability
        ) {
            level++;
        }

        return level;
    }

    private int compare(T first, T second) {
        return comparator.compare(first, second);
    }

    private void requireValue(T value) {
        Objects.requireNonNull(
                value,
                "Skip List does not support null values."
        );
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Skip List is empty."
            );
        }
    }

    @SuppressWarnings("unchecked")
    private SkipListNode<T>[] createUpdateArray() {
        return new SkipListNode[maxLevel + 1];
    }
}