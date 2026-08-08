package com.emrebeys.datastructures.queue;

public class Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public Queue() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void enqueue(T element) {
        ensureCapacity();

        elements[size] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }

        T removedElement = (T) elements[0];

        for (int i = 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }

        size--;
        elements[size] = null;

        return removedElement;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }

        return (T) elements[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return elements.length;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }

        Object[] newElements = new Object[elements.length * 2];

        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }
}