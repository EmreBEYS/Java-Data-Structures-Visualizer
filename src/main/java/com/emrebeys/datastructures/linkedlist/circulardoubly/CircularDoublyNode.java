package com.emrebeys.datastructures.linkedlist.circulardoubly;

final class CircularDoublyNode<T> {

    T data;
    CircularDoublyNode<T> previous;
    CircularDoublyNode<T> next;

    CircularDoublyNode(T data) {
        this.data = data;
    }
}