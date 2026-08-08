package com.emrebeys.datastructures.linkedlist.doubly;

final class DoublyNode<T> {

    T data;
    DoublyNode<T> previous;
    DoublyNode<T> next;

    DoublyNode(T data) {
        this.data = data;
    }
}