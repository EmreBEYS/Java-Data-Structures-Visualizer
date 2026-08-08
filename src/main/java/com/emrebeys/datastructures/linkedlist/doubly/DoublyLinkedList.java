package com.emrebeys.datastructures.linkedlist.doubly;

import java.util.Objects;

public class DoublyLinkedList<T> {

    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int size;

    public void add(T element) {
        addLast(element);
    }

    public void addFirst(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }

        size++;
    }

    public void addLast(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
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

        DoublyNode<T> currentNode = getNode(index);
        DoublyNode<T> previousNode = currentNode.previous;
        DoublyNode<T> newNode = new DoublyNode<>(element);

        newNode.previous = previousNode;
        newNode.next = currentNode;

        previousNode.next = newNode;
        currentNode.previous = newNode;

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

        DoublyNode<T> node = getNode(index);
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

        DoublyNode<T> removedNode = getNode(index);
        DoublyNode<T> previousNode = removedNode.previous;
        DoublyNode<T> nextNode = removedNode.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        removedNode.previous = null;
        removedNode.next = null;

        size--;

        return removedNode.data;
    }

    public T removeFirst() {
        ensureNotEmpty();

        T removedValue = head.data;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            DoublyNode<T> oldHead = head;
            head = head.next;
            head.previous = null;
            oldHead.next = null;
        }

        size--;
        return removedValue;
    }

    public T removeLast() {
        ensureNotEmpty();

        T removedValue = tail.data;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            DoublyNode<T> oldTail = tail;
            tail = tail.previous;
            tail.next = null;
            oldTail.previous = null;
        }

        size--;
        return removedValue;
    }

    public boolean remove(T element) {
        DoublyNode<T> current = head;

        while (current != null) {
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
        DoublyNode<T> current = head;
        int index = 0;

        while (current != null) {
            if (Objects.equals(current.data, element)) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    public int lastIndexOf(T element) {
        DoublyNode<T> current = tail;
        int index = size - 1;

        while (current != null) {
            if (Objects.equals(current.data, element)) {
                return index;
            }

            current = current.previous;
            index--;
        }

        return -1;
    }

    public void clear() {
        DoublyNode<T> current = head;

        while (current != null) {
            DoublyNode<T> nextNode = current.next;

            current.previous = null;
            current.next = null;
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
        DoublyNode<T> current = head;

        System.out.print("[");

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" <-> ");
            }

            current = current.next;
        }

        System.out.println("]");
    }

    public void printBackward() {
        DoublyNode<T> current = tail;

        System.out.print("[");

        while (current != null) {
            System.out.print(current.data);

            if (current.previous != null) {
                System.out.print(" <-> ");
            }

            current = current.previous;
        }

        System.out.println("]");
    }

    private void unlink(DoublyNode<T> node) {
        if (node == head) {
            removeFirst();
            return;
        }

        if (node == tail) {
            removeLast();
            return;
        }

        DoublyNode<T> previousNode = node.previous;
        DoublyNode<T> nextNode = node.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;

        node.previous = null;
        node.next = null;

        size--;
    }

    private DoublyNode<T> getNode(int index) {
        if (index < size / 2) {
            DoublyNode<T> current = head;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            return current;
        }

        DoublyNode<T> current = tail;

        for (int i = size - 1; i > index; i--) {
            current = current.previous;
        }

        return current;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Linked list is empty.");
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