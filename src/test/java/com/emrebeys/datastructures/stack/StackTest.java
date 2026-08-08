package com.emrebeys.datastructures.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void newStackShouldBeEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertEquals(10, stack.capacity());
    }

    @Test
    void shouldPushElement() {
        stack.push(10);

        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals(10, stack.peek());
    }

    @Test
    void shouldPushMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals(30, stack.peek());
    }

    @Test
    void shouldFollowLifoOrder() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void popShouldRemoveTopElement() {
        stack.push(10);
        stack.push(20);

        Integer removed = stack.pop();

        assertEquals(20, removed);
        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
    }

    @Test
    void peekShouldNotRemoveElement() {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.peek());
        assertEquals(2, stack.size());

        assertEquals(20, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void shouldBecomeEmptyAfterRemovingAllElements() {
        stack.push(10);
        stack.push(20);

        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void popShouldThrowExceptionWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                () -> stack.pop()
        );
    }

    @Test
    void peekShouldThrowExceptionWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                () -> stack.peek()
        );
    }

    @Test
    void shouldAutomaticallyResize() {
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }

        assertEquals(11, stack.size());
        assertEquals(20, stack.capacity());
    }

    @Test
    void shouldResizeMultipleTimes() {
        for (int i = 0; i < 50; i++) {
            stack.push(i);
        }

        assertEquals(50, stack.size());
        assertTrue(stack.capacity() >= 50);
        assertEquals(49, stack.peek());
    }

    @Test
    void resizeShouldPreserveElements() {
        for (int i = 0; i < 25; i++) {
            stack.push(i);
        }

        for (int i = 24; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    void clearShouldRemoveAllElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void clearShouldPreserveCapacity() {
        for (int i = 0; i < 15; i++) {
            stack.push(i);
        }

        assertEquals(20, stack.capacity());

        stack.clear();

        assertEquals(20, stack.capacity());
        assertEquals(0, stack.size());
    }

    @Test
    void shouldAllowPushAfterClear() {
        stack.push(10);
        stack.push(20);

        stack.clear();

        stack.push(100);

        assertEquals(1, stack.size());
        assertEquals(100, stack.peek());
    }

    @Test
    void shouldSupportNullElements() {
        stack.push(null);

        assertEquals(1, stack.size());
        assertNull(stack.peek());
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void shouldSupportDuplicateElements() {
        stack.push(10);
        stack.push(10);
        stack.push(10);

        assertEquals(3, stack.size());

        assertEquals(10, stack.pop());
        assertEquals(10, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void shouldSupportGenericTypes() {
        Stack<String> stringStack = new Stack<>();

        stringStack.push("Java");
        stringStack.push("Stack");

        assertEquals("Stack", stringStack.pop());
        assertEquals("Java", stringStack.pop());
    }

    @Test
    void sizeShouldIncreaseAfterPush() {
        assertEquals(0, stack.size());

        stack.push(10);
        assertEquals(1, stack.size());

        stack.push(20);
        assertEquals(2, stack.size());
    }

    @Test
    void sizeShouldDecreaseAfterPop() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());

        stack.pop();
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void shouldHandleLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }

        assertEquals(1000, stack.size());
        assertEquals(999, stack.peek());

        for (int i = 999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }
}