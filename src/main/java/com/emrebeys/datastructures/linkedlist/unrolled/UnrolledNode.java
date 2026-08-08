package com.emrebeys.datastructures.linkedlist.unrolled;

final class UnrolledNode<T> {

    final Object[] elements;
    int count;
    UnrolledNode<T> next;

    UnrolledNode(int capacity) {
        this.elements = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        return (T) elements[index];
    }

    void set(int index, T element) {
        elements[index] = element;
    }
}