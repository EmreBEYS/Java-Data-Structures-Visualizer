package com.emrebeys.datastructures.linkedlist.skiplist;

final class SkipListNode<T> {

    final T value;
    final SkipListNode<T>[] forward;

    @SuppressWarnings("unchecked")
    SkipListNode(T value, int level) {
        this.value = value;
        this.forward = new SkipListNode[level + 1];
    }

    int level() {
        return forward.length - 1;
    }
}