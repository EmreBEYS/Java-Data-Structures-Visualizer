package com.emrebeys.datastructures.heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {

    private final List<Integer> heap;

    public MaxHeap() {
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

    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        int max = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }

        return max;
    }

    private void heapifyUp(int index) {
        int currentIndex = index;

        while (currentIndex > 0) {
            int parentIndex = getParentIndex(currentIndex);

            if (heap.get(currentIndex) <= heap.get(parentIndex)) {
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

            int largestIndex = currentIndex;

            if (leftIndex < heap.size()
                    && heap.get(leftIndex) > heap.get(largestIndex)) {
                largestIndex = leftIndex;
            }

            if (rightIndex < heap.size()
                    && heap.get(rightIndex) > heap.get(largestIndex)) {
                largestIndex = rightIndex;
            }

            if (largestIndex == currentIndex) {
                break;
            }

            swap(currentIndex, largestIndex);
            currentIndex = largestIndex;
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