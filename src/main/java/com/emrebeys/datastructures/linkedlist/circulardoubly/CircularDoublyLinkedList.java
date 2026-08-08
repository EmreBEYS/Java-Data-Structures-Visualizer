package com.emrebeys.datastructures.linkedlist.circulardoubly;

import java.util.Objects;

public class CircularDoublyLinkedList<T> {

    private CircularDoublyNode<T> head;
    private CircularDoublyNode<T> tail;
    private int size;

    public void add(T element) {
        addLast(element);
    }

    public void addFirst(T element) {
        CircularDoublyNode<T> newNode =
                new CircularDoublyNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;

            newNode.next = newNode;
            newNode.previous = newNode;
        } else {
            newNode.next = head;
            newNode.previous = tail;

            head.previous = newNode;
            tail.next = newNode;

            head = newNode;
        }

        size++;
    }

    public void addLast(T element) {
        CircularDoublyNode<T> newNode =
                new CircularDoublyNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;

            newNode.next = newNode;
            newNode.previous = newNode;
        } else {
            newNode.previous = tail;
            newNode.next = head;

            tail.next = newNode;
            head.previous = newNode;

            tail = newNode;
        }

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

        CircularDoublyNode<T> current = getNode(index);
        CircularDoublyNode<T> previous = current.previous;
        CircularDoublyNode<T> newNode =
                new CircularDoublyNode<>(element);

        newNode.previous = previous;
        newNode.next = current;

        previous.next = newNode;
        current.previous = newNode;

        size++;
    }

    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }

    public T getFirst() {
        ensureNotEmpty();
        return head.data;
    }

    public T getLast() {
        ensureNotEmpty();
        return tail.data;
    }

    public T set(int index, T element) {
        checkElementIndex(index);

        CircularDoublyNode<T> node = getNode(index);
        T oldValue = node.data;
        node.data = element;

        return oldValue;
    }

    public T remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        CircularDoublyNode<T> removedNode = getNode(index);
        T removedValue = removedNode.data;

        unlink(removedNode);

        return removedValue;
    }

    public T removeFirst() {
        ensureNotEmpty();

        T removedValue = head.data;

        if (size == 1) {
            head.next = null;
            head.previous = null;

            head = null;
            tail = null;
        } else {
            CircularDoublyNode<T> oldHead = head;
            head = head.next;

            head.previous = tail;
            tail.next = head;

            oldHead.next = null;
            oldHead.previous = null;
        }

        size--;
        return removedValue;
    }

    public T removeLast() {
        ensureNotEmpty();

        T removedValue = tail.data;

        if (size == 1) {
            tail.next = null;
            tail.previous = null;

            head = null;
            tail = null;
        } else {
            CircularDoublyNode<T> oldTail = tail;
            tail = tail.previous;

            tail.next = head;
            head.previous = tail;

            oldTail.next = null;
            oldTail.previous = null;
        }

        size--;
        return removedValue;
    }

    public boolean remove(T element) {
        if (isEmpty()) {
            return false;
        }

        CircularDoublyNode<T> current = head;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.data, element)) {
                unlink(current);
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        CircularDoublyNode<T> current = head;

        for (int index = 0; index < size; index++) {
            if (Objects.equals(current.data, element)) {
                return index;
            }

            current = current.next;
        }

        return -1;
    }

    public int lastIndexOf(T element) {
        CircularDoublyNode<T> current = tail;

        for (int index = size - 1; index >= 0; index--) {
            if (Objects.equals(current.data, element)) {
                return index;
            }

            current = current.previous;
        }

        return -1;
    }

    public void clear() {
        if (isEmpty()) {
            return;
        }

        CircularDoublyNode<T> current = head;

        for (int i = 0; i < size; i++) {
            CircularDoublyNode<T> nextNode = current.next;

            current.next = null;
            current.previous = null;

            current = nextNode;
        }

        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printForward() {
        System.out.print("[");

        CircularDoublyNode<T> current = head;

        for (int i = 0; i < size; i++) {
            System.out.print(current.data);

            if (i < size - 1) {
                System.out.print(" <-> ");
            }

            current = current.next;
        }

        if (!isEmpty()) {
            System.out.print(" <-> HEAD");
        }

        System.out.println("]");
    }

    public void printBackward() {
        System.out.print("[");

        CircularDoublyNode<T> current = tail;

        for (int i = 0; i < size; i++) {
            System.out.print(current.data);

            if (i < size - 1) {
                System.out.print(" <-> ");
            }

            current = current.previous;
        }

        if (!isEmpty()) {
            System.out.print(" <-> TAIL");
        }

        System.out.println("]");
    }

    private void unlink(CircularDoublyNode<T> node) {
        if (node == head) {
            removeFirst();
            return;
        }

        if (node == tail) {
            removeLast();
            return;
        }

        CircularDoublyNode<T> previousNode = node.previous;
        CircularDoublyNode<T> nextNode = node.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        node.next = null;
        node.previous = null;

        size--;
    }

    private CircularDoublyNode<T> getNode(int index) {
        if (index < size / 2) {
            CircularDoublyNode<T> current = head;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            return current;
        }

        CircularDoublyNode<T> current = tail;

        for (int i = size - 1; i > index; i--) {
            current = current.previous;
        }

        return current;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Circular doubly linked list is empty."
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
}