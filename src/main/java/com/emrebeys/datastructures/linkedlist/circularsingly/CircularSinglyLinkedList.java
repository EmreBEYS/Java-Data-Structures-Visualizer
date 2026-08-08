package com.emrebeys.datastructures.linkedlist.circularsingly;

import java.nio.channels.NonWritableChannelException;
import java.util.Objects;


public class CircularSinglyLinkedList <T> {
    private CircularSinglyNode<T> head;
    private CircularSinglyNode<T> tail;
    private int size;

    public void add(T element){
        addLast(element);
    }
    public void addFirst(T element){
        CircularSinglyNode<T> newNode= new CircularSinglyNode<>(element);
        if(isEmpty()){
            head=newNode;
            tail= newNode;
            newNode.next=newNode;

        }
        else{
            newNode.next=head;
            head=newNode;
            tail.next=head;
        }
        size ++;
    }
    public void addLast(T element) {
        CircularSinglyNode<T> newNode = new CircularSinglyNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = newNode;
        } else {
            newNode.next = head;
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
        CircularSinglyNode<T> previous = getNode(index - 1);
        CircularSinglyNode<T> newNode = new CircularSinglyNode<>(element);

        newNode.next = previous.next;
        previous.next = newNode;

        size++;
    }
    public T get(int index){
        checkElementIndex(index);
        return getNode(index).data;
    }
    public T getFirst(){
        ensureNotEmpty();
        return head.data;
    }
    public T getLast(){
        ensureNotEmpty();
        return tail.data;
    }
    public T set(int index, T element) {
        checkElementIndex(index);

        CircularSinglyNode<T> node = getNode(index);
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

        CircularSinglyNode<T> previous = getNode(index - 1);
        CircularSinglyNode<T> removedNode = previous.next;

        previous.next = removedNode.next;
        removedNode.next = null;

        size--;
        return removedNode.data;
    }

    public T removeFirst() {
        ensureNotEmpty();

        T removedValue = head.data;

        if (size == 1) {
            head.next = null;
            head = null;
            tail = null;
        } else {
            CircularSinglyNode<T> oldHead = head;
            head = head.next;
            tail.next = head;
            oldHead.next = null;
        }

        size--;
        return removedValue;
    }

    public T removeLast() {
        ensureNotEmpty();

        T removedValue = tail.data;

        if (size == 1) {
            tail.next = null;
            head = null;
            tail = null;
        } else {
            CircularSinglyNode<T> previous = getNode(size - 2);
            CircularSinglyNode<T> oldTail = tail;

            previous.next = head;
            tail = previous;
            oldTail.next = null;
        }

        size--;
        return removedValue;
    }

    public boolean remove(T element) {
        if (isEmpty()) {
            return false;
        }

        if (Objects.equals(head.data, element)) {
            removeFirst();
            return true;
        }

        CircularSinglyNode<T> current = head;

        for (int i = 0; i < size - 1; i++) {
            CircularSinglyNode<T> nextNode = current.next;

            if (Objects.equals(nextNode.data, element)) {
                if (nextNode == tail) {
                    removeLast();
                } else {
                    current.next = nextNode.next;
                    nextNode.next = null;
                    size--;
                }

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
        CircularSinglyNode<T> current = head;

        for (int index = 0; index < size; index++) {
            if (Objects.equals(current.data, element)) {
                return index;
            }

            current = current.next;
        }

        return -1;
    }

    public void clear() {
        if (isEmpty()) {
            return;
        }

        CircularSinglyNode<T> current = head;

        for (int i = 0; i < size; i++) {
            CircularSinglyNode<T> nextNode = current.next;
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

    public void print() {
        System.out.print("[");

        CircularSinglyNode<T> current = head;

        for (int i = 0; i < size; i++) {
            System.out.print(current.data);

            if (i < size - 1) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        if (!isEmpty()) {
            System.out.print(" -> HEAD");
        }

        System.out.println("]");
    }

    private CircularSinglyNode<T> getNode(int index) {
        CircularSinglyNode<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Circular singly linked list is empty."
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
