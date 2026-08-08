package com.emrebeys.datastructures.linkedlist;

import java.util.Objects;

public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T element) {
        addLast(element);
    }

    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
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

        Node<T> previous = getNode(index - 1);
        Node<T> newNode = new Node<>(element);

        newNode.next = previous.next;
        previous.next = newNode;
        size++;
    }

    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }

    public T set(int index, T element) {
        checkElementIndex(index);

        Node<T> node = getNode(index);
        T oldValue = node.data;
        node.data = element;

        return oldValue;
    }

    public T remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            T removedValue = head.data;
            head = head.next;
            size--;
            return removedValue;
        }

        Node<T> previous = getNode(index - 1);
        Node<T> removedNode = previous.next;

        previous.next = removedNode.next;
        size--;

        return removedNode.data;
    }

    public boolean remove(T element) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.data, element)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;

        while (current.next != null) {
            if (Objects.equals(current.next.data, element)) {
                current.next = current.next.next;
                size--;
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
        Node<T> current = head;
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

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        Node<T> current = head;

        System.out.print("[");

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println("]");
    }

    private Node<T> getNode(int index) {
        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
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