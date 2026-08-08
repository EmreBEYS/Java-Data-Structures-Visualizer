package com.emrebeys.datastructures.linkedlist.unrolled;

import java.util.Objects;

public class UnrolledLinkedList<T> {

    private static final int DEFAULT_NODE_CAPACITY = 4;

    private final int nodeCapacity;

    private UnrolledNode<T> head;
    private UnrolledNode<T> tail;

    private int size;
    private int nodeCount;

    public UnrolledLinkedList() {
        this(DEFAULT_NODE_CAPACITY);
    }

    public UnrolledLinkedList(int nodeCapacity) {
        if (nodeCapacity < 2) {
            throw new IllegalArgumentException(
                    "Node capacity must be at least 2."
            );
        }

        this.nodeCapacity = nodeCapacity;
    }

    public void add(T element) {
        addLast(element);
    }

    public void addLast(T element) {
        if (tail == null) {
            tail = new UnrolledNode<>(nodeCapacity);
            head = tail;
            nodeCount++;
        }

        if (tail.count == nodeCapacity) {
            UnrolledNode<T> newNode =
                    new UnrolledNode<>(nodeCapacity);

            tail.next = newNode;
            tail = newNode;
            nodeCount++;
        }

        tail.set(tail.count, element);
        tail.count++;
        size++;
    }

    public void addFirst(T element) {
        if (head == null) {
            head = new UnrolledNode<>(nodeCapacity);
            tail = head;
            nodeCount++;
        }

        if (head.count == nodeCapacity) {
            splitHead();
        }

        shiftRight(head, 0);
        head.set(0, element);
        head.count++;
        size++;
    }

    public void add(int index, T element) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        NodeLocation<T> location = findLocation(index);
        UnrolledNode<T> node = location.node;
        int localIndex = location.localIndex;

        if (node.count == nodeCapacity) {
            UnrolledNode<T> newNode = splitNode(node);

            if (localIndex > node.count) {
                localIndex -= node.count;
                node = newNode;
            }
        }

        shiftRight(node, localIndex);
        node.set(localIndex, element);
        node.count++;
        size++;
    }

    public T get(int index) {
        checkElementIndex(index);

        NodeLocation<T> location = findLocation(index);
        return location.node.get(location.localIndex);
    }

    public T set(int index, T element) {
        checkElementIndex(index);

        NodeLocation<T> location = findLocation(index);
        T oldValue = location.node.get(location.localIndex);

        location.node.set(location.localIndex, element);

        return oldValue;
    }

    public T remove(int index) {
        checkElementIndex(index);

        NodeLocation<T> location = findLocation(index);
        UnrolledNode<T> node = location.node;
        int localIndex = location.localIndex;

        T removedValue = node.get(localIndex);

        shiftLeft(node, localIndex);

        node.count--;
        node.set(node.count, null);
        size--;

        if (node.count == 0) {
            removeEmptyNode(node);
        }

        return removedValue;
    }

    public boolean remove(T element) {
        UnrolledNode<T> current = head;
        int globalIndex = 0;

        while (current != null) {
            for (int i = 0; i < current.count; i++) {
                if (Objects.equals(current.get(i), element)) {
                    remove(globalIndex + i);
                    return true;
                }
            }

            globalIndex += current.count;
            current = current.next;
        }

        return false;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        UnrolledNode<T> current = head;
        int globalIndex = 0;

        while (current != null) {
            for (int i = 0; i < current.count; i++) {
                if (Objects.equals(current.get(i), element)) {
                    return globalIndex + i;
                }
            }

            globalIndex += current.count;
            current = current.next;
        }

        return -1;
    }

    public T getFirst() {
        ensureNotEmpty();
        return head.get(0);
    }

    public T getLast() {
        ensureNotEmpty();
        return tail.get(tail.count - 1);
    }

    public void clear() {
        UnrolledNode<T> current = head;

        while (current != null) {
            UnrolledNode<T> nextNode = current.next;

            for (int i = 0; i < current.count; i++) {
                current.set(i, null);
            }

            current.count = 0;
            current.next = null;
            current = nextNode;
        }

        head = null;
        tail = null;
        size = 0;
        nodeCount = 0;
    }

    public int size() {
        return size;
    }

    public int nodeCount() {
        return nodeCount;
    }

    public int nodeCapacity() {
        return nodeCapacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        UnrolledNode<T> current = head;

        System.out.print("[");

        while (current != null) {
            System.out.print("{");

            for (int i = 0; i < current.count; i++) {
                System.out.print(current.get(i));

                if (i < current.count - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print("}");

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println("]");
    }

    private void splitHead() {
        UnrolledNode<T> oldHead = head;
        UnrolledNode<T> newHead =
                new UnrolledNode<>(nodeCapacity);

        int moveCount = oldHead.count / 2;

        for (int i = 0; i < moveCount; i++) {
            newHead.set(i, oldHead.get(i));
            newHead.count++;
        }

        for (int i = moveCount; i < oldHead.count; i++) {
            oldHead.set(i - moveCount, oldHead.get(i));
        }

        for (
                int i = oldHead.count - moveCount;
                i < oldHead.count;
                i++
        ) {
            oldHead.set(i, null);
        }

        oldHead.count -= moveCount;

        newHead.next = oldHead;
        head = newHead;
        nodeCount++;
    }

    private UnrolledNode<T> splitNode(UnrolledNode<T> node) {
        UnrolledNode<T> newNode =
                new UnrolledNode<>(nodeCapacity);

        int splitIndex = node.count / 2;
        int newNodeIndex = 0;

        for (int i = splitIndex; i < node.count; i++) {
            newNode.set(newNodeIndex++, node.get(i));
            newNode.count++;
            node.set(i, null);
        }

        node.count = splitIndex;

        newNode.next = node.next;
        node.next = newNode;

        if (tail == node) {
            tail = newNode;
        }

        nodeCount++;

        return newNode;
    }

    private void shiftRight(
            UnrolledNode<T> node,
            int startIndex
    ) {
        for (int i = node.count; i > startIndex; i--) {
            node.set(i, node.get(i - 1));
        }
    }

    private void shiftLeft(
            UnrolledNode<T> node,
            int startIndex
    ) {
        for (int i = startIndex; i < node.count - 1; i++) {
            node.set(i, node.get(i + 1));
        }
    }

    private void removeEmptyNode(UnrolledNode<T> node) {
        if (head == node) {
            head = head.next;
            node.next = null;
            nodeCount--;

            if (head == null) {
                tail = null;
            }

            return;
        }

        UnrolledNode<T> previous = head;

        while (previous.next != node) {
            previous = previous.next;
        }

        previous.next = node.next;

        if (tail == node) {
            tail = previous;
        }

        node.next = null;
        nodeCount--;
    }

    private NodeLocation<T> findLocation(int index) {
        UnrolledNode<T> current = head;
        int remainingIndex = index;

        while (current != null) {
            if (remainingIndex < current.count) {
                return new NodeLocation<>(
                        current,
                        remainingIndex
                );
            }

            remainingIndex -= current.count;
            current = current.next;
        }

        throw new IllegalStateException(
                "Could not locate index: " + index
        );
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Unrolled linked list is empty."
            );
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    private static final class NodeLocation<T> {

        private final UnrolledNode<T> node;
        private final int localIndex;

        private NodeLocation(
                UnrolledNode<T> node,
                int localIndex
        ) {
            this.node = node;
            this.localIndex = localIndex;
        }
    }
}