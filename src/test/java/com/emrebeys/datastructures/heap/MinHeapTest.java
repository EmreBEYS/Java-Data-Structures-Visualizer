package com.emrebeys.datastructures.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    private MinHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap();
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
    void shouldKeepMinimumAtRoot() {
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);

        assertEquals(5, heap.peek());
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
    void shouldExtractMinimumValue() {
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);

        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.peek());
        assertEquals(3, heap.size());
    }

    @Test
    void shouldExtractValuesInAscendingOrder() {
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);
        heap.insert(50);
        heap.insert(5);

        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.extractMin());
        assertEquals(30, heap.extractMin());
        assertEquals(40, heap.extractMin());
        assertEquals(50, heap.extractMin());

        assertTrue(heap.isEmpty());
    }

    @Test
    void shouldHandleDuplicateValues() {
        heap.insert(10);
        heap.insert(10);
        heap.insert(5);

        assertEquals(3, heap.size());

        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
        assertEquals(10, heap.extractMin());
    }

    @Test
    void shouldHandleNegativeValues() {
        heap.insert(-10);
        heap.insert(5);
        heap.insert(-20);

        assertEquals(-20, heap.peek());
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
                heap::extractMin
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