package com.emrebeys.datastructures.linkedlist.circulardoubly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListTest {

    private CircularDoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CircularDoublyLinkedList<>();
    }

    @Test
    void shouldStartEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void shouldAddElement() {
        list.add(10);

        assertEquals(1, list.size());
        assertEquals(10, list.getFirst());
        assertEquals(10, list.getLast());
    }

    @Test
    void shouldAddFirst() {
        list.add(20);
        list.addFirst(10);

        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void shouldAddLast() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());
    }

    @Test
    void shouldAddAtGivenIndex() {
        list.add(10);
        list.add(30);

        list.add(1, 20);

        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void shouldAddAtBeginningUsingIndex() {
        list.add(20);

        list.add(0, 10);

        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());
    }

    @Test
    void shouldAddAtEndUsingIndex() {
        list.add(10);

        list.add(1, 20);

        assertEquals(20, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void shouldGetElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(20, list.get(1));
    }

    @Test
    void shouldSetElementAtGivenIndex() {
        list.add(10);
        list.add(20);

        Integer oldValue = list.set(1, 99);

        assertEquals(20, oldValue);
        assertEquals(99, list.get(1));
    }

    @Test
    void shouldRemoveElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer removed = list.remove(1);

        assertEquals(20, removed);
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @Test
    void shouldRemoveFirstElement() {
        list.add(10);
        list.add(20);

        Integer removed = list.removeFirst();

        assertEquals(10, removed);
        assertEquals(20, list.getFirst());
        assertEquals(1, list.size());
    }

    @Test
    void shouldRemoveLastElement() {
        list.add(10);
        list.add(20);

        Integer removed = list.removeLast();

        assertEquals(20, removed);
        assertEquals(10, list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    void shouldRemoveElementByValue() {
        list.add(10);
        list.add(20);
        list.add(30);

        boolean removed = list.remove(Integer.valueOf(20));

        assertTrue(removed);
        assertFalse(list.contains(20));
        assertEquals(2, list.size());
    }

    @Test
    void shouldRemoveHeadByValue() {
        list.add(10);
        list.add(20);

        assertTrue(list.remove(Integer.valueOf(10)));
        assertEquals(20, list.getFirst());
    }

    @Test
    void shouldRemoveTailByValue() {
        list.add(10);
        list.add(20);

        assertTrue(list.remove(Integer.valueOf(20)));
        assertEquals(10, list.getLast());
    }

    @Test
    void shouldReturnFalseWhenElementCannotBeRemoved() {
        list.add(10);

        assertFalse(list.remove(Integer.valueOf(99)));
        assertEquals(1, list.size());
    }

    @Test
    void shouldContainElement() {
        list.add(10);
        list.add(20);

        assertTrue(list.contains(20));
        assertFalse(list.contains(99));
    }

    @Test
    void shouldReturnCorrectIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(1, list.indexOf(20));
    }

    @Test
    void shouldReturnLastIndex() {
        list.add(10);
        list.add(20);
        list.add(10);

        assertEquals(2, list.lastIndexOf(10));
    }

    @Test
    void shouldReturnMinusOneWhenElementDoesNotExist() {
        list.add(10);

        assertEquals(-1, list.indexOf(99));
        assertEquals(-1, list.lastIndexOf(99));
    }

    @Test
    void shouldHandleNullElements() {
        list.add(null);
        list.add(10);
        list.add(null);

        assertTrue(list.contains(null));
        assertEquals(0, list.indexOf(null));
        assertEquals(2, list.lastIndexOf(null));
    }

    @Test
    void shouldRemoveNullElement() {
        list.add(10);
        list.add(null);
        list.add(20);

        assertTrue(list.remove(null));
        assertFalse(list.contains(null));
        assertEquals(2, list.size());
    }

    @Test
    void shouldClearList() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void shouldClearEmptyListWithoutError() {
        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void shouldHandleSingleElementRemoveFirst() {
        list.add(10);

        assertEquals(10, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void shouldHandleSingleElementRemoveLast() {
        list.add(10);

        assertEquals(10, list.removeLast());
        assertTrue(list.isEmpty());
    }

    @Test
    void shouldRemainUsableAfterBecomingEmpty() {
        list.add(10);
        list.removeFirst();

        list.add(20);

        assertEquals(20, list.getFirst());
        assertEquals(20, list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    void shouldPreserveOrderAfterMultipleOperations() {
        list.addLast(20);
        list.addFirst(10);
        list.addLast(40);
        list.add(2, 30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(40, list.get(3));

        list.remove(1);

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(40, list.get(2));
    }

    @Test
    void shouldThrowWhenGettingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(0)
        );
    }

    @Test
    void shouldThrowWhenSettingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(0, 10)
        );
    }

    @Test
    void shouldThrowWhenAddingAtInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(1, 10)
        );
    }

    @Test
    void shouldThrowWhenRemovingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(0)
        );
    }

    @Test
    void shouldThrowWhenGettingFirstFromEmptyList() {
        assertThrows(
                IllegalStateException.class,
                list::getFirst
        );
    }

    @Test
    void shouldThrowWhenGettingLastFromEmptyList() {
        assertThrows(
                IllegalStateException.class,
                list::getLast
        );
    }

    @Test
    void shouldThrowWhenRemovingFirstFromEmptyList() {
        assertThrows(
                IllegalStateException.class,
                list::removeFirst
        );
    }

    @Test
    void shouldThrowWhenRemovingLastFromEmptyList() {
        assertThrows(
                IllegalStateException.class,
                list::removeLast
        );
    }
}