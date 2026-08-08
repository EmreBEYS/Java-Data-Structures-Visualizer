package com.emrebeys.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();
    }

    @Test
    void shouldStartEmpty() {
        assertTrue(linkedList.isEmpty());
        assertEquals(0, linkedList.size());
    }

    @Test
    void shouldAddElement() {
        linkedList.add(10);

        assertEquals(1, linkedList.size());
        assertEquals(10, linkedList.get(0));
    }

    @Test
    void shouldAddElementToBeginning() {
        linkedList.add(20);
        linkedList.addFirst(10);

        assertEquals(2, linkedList.size());
        assertEquals(10, linkedList.get(0));
        assertEquals(20, linkedList.get(1));
    }

    @Test
    void shouldAddElementToEnd() {
        linkedList.addLast(10);
        linkedList.addLast(20);

        assertEquals(2, linkedList.size());
        assertEquals(20, linkedList.get(1));
    }

    @Test
    void shouldAddElementAtGivenIndex() {
        linkedList.add(10);
        linkedList.add(30);

        linkedList.add(1, 20);

        assertEquals(3, linkedList.size());
        assertEquals(10, linkedList.get(0));
        assertEquals(20, linkedList.get(1));
        assertEquals(30, linkedList.get(2));
    }

    @Test
    void shouldGetElementByIndex() {
        linkedList.add(10);
        linkedList.add(20);

        assertEquals(20, linkedList.get(1));
    }

    @Test
    void shouldSetElementAtGivenIndex() {
        linkedList.add(10);
        linkedList.add(20);

        Integer oldValue = linkedList.set(1, 99);

        assertEquals(20, oldValue);
        assertEquals(99, linkedList.get(1));
    }

    @Test
    void shouldRemoveElementByIndex() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        Integer removedValue = linkedList.remove(1);

        assertEquals(20, removedValue);
        assertEquals(2, linkedList.size());
        assertEquals(30, linkedList.get(1));
    }

    @Test
    void shouldRemoveElementByValue() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        boolean removed = linkedList.remove(Integer.valueOf(20));

        assertTrue(removed);
        assertEquals(2, linkedList.size());
        assertFalse(linkedList.contains(20));
    }

    @Test
    void shouldReturnFalseWhenElementCannotBeRemoved() {
        linkedList.add(10);

        assertFalse(linkedList.remove(Integer.valueOf(99)));
        assertEquals(1, linkedList.size());
    }

    @Test
    void shouldReturnTrueWhenElementExists() {
        linkedList.add(10);
        linkedList.add(20);

        assertTrue(linkedList.contains(20));
    }

    @Test
    void shouldReturnFalseWhenElementDoesNotExist() {
        linkedList.add(10);

        assertFalse(linkedList.contains(99));
    }

    @Test
    void shouldReturnCorrectIndexOfElement() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        assertEquals(1, linkedList.indexOf(20));
    }

    @Test
    void shouldReturnMinusOneWhenElementDoesNotExist() {
        linkedList.add(10);

        assertEquals(-1, linkedList.indexOf(99));
    }

    @Test
    void shouldClearAllElements() {
        linkedList.add(10);
        linkedList.add(20);

        linkedList.clear();

        assertTrue(linkedList.isEmpty());
        assertEquals(0, linkedList.size());
    }

    @Test
    void shouldThrowExceptionWhenGettingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> linkedList.get(0)
        );
    }

    @Test
    void shouldThrowExceptionWhenAddingAtInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> linkedList.add(1, 10)
        );
    }

    @Test
    void shouldThrowExceptionWhenRemovingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> linkedList.remove(0)
        );
    }

    @Test
    void shouldHandleNullElements() {
        linkedList.add(null);
        linkedList.add(10);

        assertTrue(linkedList.contains(null));
        assertEquals(0, linkedList.indexOf(null));
    }
}