package com.emrebeys.datastructures.queue;

public class CircularQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int front;
    private int rear;
    private int size;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(
                    "Capacity must be at least 1."
            );
        }

        this.elements = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public void enqueue(T element) {
        ensureCapacity();

        elements[rear] = element;

        rear = (rear + 1) % elements.length;

        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        ensureNotEmpty();

        T removedElement = (T) elements[front];

        elements[front] = null;

        front = (front + 1) % elements.length;

        size--;

        if (size == 0) {
            front = 0;
            rear = 0;
        }

        return removedElement;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        ensureNotEmpty();

        return (T) elements[front];
    }

    @SuppressWarnings("unchecked")
    public T rear() {
        ensureNotEmpty();

        int rearIndex =
                (rear - 1 + elements.length)
                        % elements.length;

        return (T) elements[rearIndex];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }

        front = 0;
        rear = 0;
        size = 0;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }

        int oldCapacity = elements.length;
        int newCapacity = oldCapacity * 2;

        Object[] newElements =
                new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] =
                    elements[(front + i) % oldCapacity];
        }

        elements = newElements;

        front = 0;
        rear = size;
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Circular Queue is empty."
            );
        }
    }
}