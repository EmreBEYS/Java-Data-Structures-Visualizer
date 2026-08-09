package com.emrebeys.datastructures.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    private MaxHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MaxHeap();
    }

    @Test
    void shouldCreateEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void shouldInsertSingleValue() {
        heap.insert(10);

        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        assertEquals(10, heap.peek());
    }

    @Test
    void shouldKeepMaximumAtRoot() {
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(50);

        assertEquals(50, heap.peek());
    }

    @Test
    void shouldMaintainCorrectSize() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        assertEquals(3, heap.size());
    }

    @Test
    void shouldContainInsertedValue() {
        heap.insert(10);
        heap.insert(20);

        assertTrue(heap.contains(20));
    }

    @Test
    void shouldReturnFalseForMissingValue() {
        heap.insert(10);

        assertFalse(heap.contains(100));
    }

    @Test
    void shouldExtractMaximumValue() {
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(50);

        assertEquals(50, heap.extractMax());
        assertEquals(40, heap.peek());
        assertEquals(3, heap.size());
    }

    @Test
    void shouldExtractValuesInDescendingOrder() {
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);
        heap.insert(50);
        heap.insert(5);

        assertEquals(50, heap.extractMax());
        assertEquals(40, heap.extractMax());
        assertEquals(30, heap.extractMax());
        assertEquals(20, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(5, heap.extractMax());

        assertTrue(heap.isEmpty());
    }

    @Test
    void shouldHandleDuplicateValues() {
        heap.insert(10);
        heap.insert(10);
        heap.insert(20);

        assertEquals(3, heap.size());

        assertEquals(20, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(10, heap.extractMax());
    }

    @Test
    void shouldHandleNegativeValues() {
        heap.insert(-10);
        heap.insert(5);
        heap.insert(-20);

        assertEquals(5, heap.peek());
    }

    @Test
    void shouldThrowWhenPeekOnEmptyHeap() {
        assertThrows(
                IllegalStateException.class,
                heap::peek
        );
    }

    @Test
    void shouldThrowWhenExtractOnEmptyHeap() {
        assertThrows(
                IllegalStateException.class,
                heap::extractMax
        );
    }

    @Test
    void shouldClearHeap() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        heap.clear();

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }
}