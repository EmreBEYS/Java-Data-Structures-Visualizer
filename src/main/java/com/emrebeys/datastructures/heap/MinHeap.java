package com.emrebeys.datastructures.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private final List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        return heap.get(0);
    }

    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        int min = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }

        return min;
    }

    private void heapifyUp(int index) {
        int currentIndex = index;

        while (currentIndex > 0) {
            int parentIndex = getParentIndex(currentIndex);

            if (heap.get(currentIndex) >= heap.get(parentIndex)) {
                break;
            }

            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int currentIndex = index;

        while (true) {
            int leftIndex = getLeftChildIndex(currentIndex);
            int rightIndex = getRightChildIndex(currentIndex);

            int smallestIndex = currentIndex;

            if (leftIndex < heap.size()
                    && heap.get(leftIndex) < heap.get(smallestIndex)) {
                smallestIndex = leftIndex;
            }

            if (rightIndex < heap.size()
                    && heap.get(rightIndex) < heap.get(smallestIndex)) {
                smallestIndex = rightIndex;
            }

            if (smallestIndex == currentIndex) {
                break;
            }

            swap(currentIndex, smallestIndex);
            currentIndex = smallestIndex;
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, temp);
    }

    public boolean contains(int value) {
        return heap.contains(value);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public List<Integer> toList() {
        return new ArrayList<>(heap);
    }
}