package com.emrebeys.datastructures.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularQueueTest {

    private CircularQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new CircularQueue<>(5);
    }

    @Test
    void newQueueShouldBeEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertEquals(5, queue.capacity());
        assertFalse(queue.isFull());
    }

    @Test
    void shouldEnqueueSingleElement() {
        queue.enqueue(10);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(10, queue.peek());
        assertEquals(10, queue.rear());
    }

    @Test
    void shouldEnqueueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertEquals(10, queue.peek());
        assertEquals(30, queue.rear());
    }

    @Test
    void shouldFollowFifoOrder() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void dequeueShouldRemoveFrontElement() {
        queue.enqueue(10);
        queue.enqueue(20);

        Integer removed = queue.dequeue();

        assertEquals(10, removed);
        assertEquals(1, queue.size());
        assertEquals(20, queue.peek());
        assertEquals(20, queue.rear());
    }

    @Test
    void peekShouldNotRemoveElement() {
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(10, queue.peek());
        assertEquals(2, queue.size());

        assertEquals(10, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    void rearShouldReturnLastElement() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(30, queue.rear());
        assertEquals(3, queue.size());
    }

    @Test
    void shouldDetectFullQueue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        assertTrue(queue.isFull());
        assertEquals(5, queue.size());
    }

    @Test
    void shouldNotBeFullAfterDequeue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        queue.dequeue();

        assertFalse(queue.isFull());
        assertEquals(4, queue.size());
    }

    @Test
    void shouldWrapAroundCorrectly() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());

        queue.enqueue(60);
        queue.enqueue(70);

        assertEquals(5, queue.size());
        assertEquals(30, queue.peek());
        assertEquals(70, queue.rear());

        assertEquals(30, queue.dequeue());
        assertEquals(40, queue.dequeue());
        assertEquals(50, queue.dequeue());
        assertEquals(60, queue.dequeue());
        assertEquals(70, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldPreserveOrderAfterMultipleWrapArounds() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());

        queue.enqueue(6);
        queue.enqueue(7);

        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());

        queue.enqueue(8);
        queue.enqueue(9);

        assertEquals(5, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(7, queue.dequeue());
        assertEquals(8, queue.dequeue());
        assertEquals(9, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldAutomaticallyResizeWhenFull() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        assertEquals(5, queue.capacity());

        queue.enqueue(60);

        assertEquals(6, queue.size());
        assertEquals(10, queue.capacity());
        assertEquals(10, queue.peek());
        assertEquals(60, queue.rear());
    }

    @Test
    void resizeShouldPreserveFifoOrder() {
        for (int i = 1; i <= 8; i++) {
            queue.enqueue(i * 10);
        }

        assertEquals(10, queue.capacity());

        for (int i = 1; i <= 8; i++) {
            assertEquals(i * 10, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    void resizeAfterWrapAroundShouldPreserveOrder() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());

        queue.enqueue(60);
        queue.enqueue(70);

        queue.enqueue(80);

        assertEquals(10, queue.capacity());
        assertEquals(6, queue.size());

        assertEquals(30, queue.dequeue());
        assertEquals(40, queue.dequeue());
        assertEquals(50, queue.dequeue());
        assertEquals(60, queue.dequeue());
        assertEquals(70, queue.dequeue());
        assertEquals(80, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldResetIndexesAfterBecomingEmpty() {
        queue.enqueue(10);
        queue.enqueue(20);

        queue.dequeue();
        queue.dequeue();

        assertTrue(queue.isEmpty());

        queue.enqueue(100);

        assertEquals(100, queue.peek());
        assertEquals(100, queue.rear());
        assertEquals(1, queue.size());
    }

    @Test
    void clearShouldRemoveAllElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertFalse(queue.isFull());
    }

    @Test
    void clearShouldPreserveCapacity() {
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
        }

        assertEquals(10, queue.capacity());

        queue.clear();

        assertEquals(10, queue.capacity());
        assertEquals(0, queue.size());
    }

    @Test
    void shouldAllowEnqueueAfterClear() {
        queue.enqueue(10);
        queue.enqueue(20);

        queue.clear();

        queue.enqueue(100);

        assertEquals(1, queue.size());
        assertEquals(100, queue.peek());
        assertEquals(100, queue.rear());
    }

    @Test
    void dequeueShouldThrowWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                queue::dequeue
        );
    }

    @Test
    void peekShouldThrowWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                queue::peek
        );
    }

    @Test
    void rearShouldThrowWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                queue::rear
        );
    }

    @Test
    void constructorShouldRejectZeroCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CircularQueue<Integer>(0)
        );
    }

    @Test
    void constructorShouldRejectNegativeCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new CircularQueue<Integer>(-5)
        );
    }

    @Test
    void shouldSupportNullElements() {
        queue.enqueue(null);

        assertEquals(1, queue.size());
        assertNull(queue.peek());
        assertNull(queue.rear());
        assertNull(queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldSupportDuplicateElements() {
        queue.enqueue(10);
        queue.enqueue(10);
        queue.enqueue(10);

        assertEquals(3, queue.size());

        assertEquals(10, queue.dequeue());
        assertEquals(10, queue.dequeue());
        assertEquals(10, queue.dequeue());
    }

    @Test
    void shouldSupportGenericTypes() {
        CircularQueue<String> stringQueue =
                new CircularQueue<>(3);

        stringQueue.enqueue("Java");
        stringQueue.enqueue("Circular");
        stringQueue.enqueue("Queue");

        assertEquals("Java", stringQueue.dequeue());
        assertEquals("Circular", stringQueue.dequeue());
        assertEquals("Queue", stringQueue.dequeue());
    }

    @Test
    void sizeShouldIncreaseAfterEnqueue() {
        assertEquals(0, queue.size());

        queue.enqueue(10);
        assertEquals(1, queue.size());

        queue.enqueue(20);
        assertEquals(2, queue.size());
    }

    @Test
    void sizeShouldDecreaseAfterDequeue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());

        queue.dequeue();
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    void shouldHandleLargeNumberOfElements() {
        CircularQueue<Integer> largeQueue =
                new CircularQueue<>(2);

        for (int i = 0; i < 1000; i++) {
            largeQueue.enqueue(i);
        }

        assertEquals(1000, largeQueue.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, largeQueue.dequeue());
        }

        assertTrue(largeQueue.isEmpty());
    }
}