package com.emrebeys.datastructures.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void newQueueShouldBeEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertEquals(10, queue.capacity());
    }

    @Test
    void shouldEnqueueElement() {
        queue.enqueue(10);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(10, queue.peek());
    }

    @Test
    void shouldEnqueueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertEquals(10, queue.peek());
    }

    @Test
    void shouldFollowFifoOrder() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
    }

    @Test
    void dequeueShouldRemoveFrontElement() {
        queue.enqueue(10);
        queue.enqueue(20);

        Integer removed = queue.dequeue();

        assertEquals(10, removed);
        assertEquals(1, queue.size());
        assertEquals(20, queue.peek());
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
    void shouldBecomeEmptyAfterRemovingAllElements() {
        queue.enqueue(10);
        queue.enqueue(20);

        queue.dequeue();
        queue.dequeue();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void dequeueShouldThrowExceptionWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                queue::dequeue
        );
    }

    @Test
    void peekShouldThrowExceptionWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                queue::peek
        );
    }

    @Test
    void shouldAutomaticallyResize() {
        for (int i = 0; i < 11; i++) {
            queue.enqueue(i);
        }

        assertEquals(11, queue.size());
        assertEquals(20, queue.capacity());
    }

    @Test
    void shouldResizeMultipleTimes() {
        for (int i = 0; i < 50; i++) {
            queue.enqueue(i);
        }

        assertEquals(50, queue.size());
        assertTrue(queue.capacity() >= 50);
        assertEquals(0, queue.peek());
    }

    @Test
    void resizeShouldPreserveElements() {
        for (int i = 0; i < 25; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 25; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    void clearShouldRemoveAllElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void clearShouldPreserveCapacity() {
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
        }

        assertEquals(20, queue.capacity());

        queue.clear();

        assertEquals(20, queue.capacity());
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
    }

    @Test
    void shouldSupportNullElements() {
        queue.enqueue(null);

        assertEquals(1, queue.size());
        assertNull(queue.peek());
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
        Queue<String> stringQueue = new Queue<>();

        stringQueue.enqueue("Java");
        stringQueue.enqueue("Queue");

        assertEquals("Java", stringQueue.dequeue());
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
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }

        assertEquals(1000, queue.size());
        assertEquals(0, queue.peek());

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }
}