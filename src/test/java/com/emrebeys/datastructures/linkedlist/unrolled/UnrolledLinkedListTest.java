package com.emrebeys.datastructures.linkedlist.unrolled;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnrolledLinkedListTest {

    private UnrolledLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new UnrolledLinkedList<>();
    }

    @Test
    void newListShouldBeEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals(0, list.nodeCount());
        assertEquals(4, list.nodeCapacity());
    }

    @Test
    void shouldAddElement() {
        list.add(10);

        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(10, list.get(0));
    }

    @Test
    void shouldAddMultipleElements() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(3, list.size());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void shouldAddFirst() {
        list.addFirst(20);
        list.addFirst(10);

        assertEquals(2, list.size());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void shouldAddLast() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(3, list.size());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void shouldAddAtBeginning() {
        list.add(10);
        list.add(20);

        list.add(0, 5);

        assertEquals(3, list.size());

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(20, list.get(2));
    }

    @Test
    void shouldAddAtMiddle() {
        list.add(10);
        list.add(30);

        list.add(1, 20);

        assertEquals(3, list.size());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void shouldAddAtEnd() {
        list.add(10);
        list.add(20);

        list.add(2, 30);

        assertEquals(3, list.size());

        assertEquals(30, list.get(2));
    }

    @Test
    void shouldCreateNewNodeWhenCapacityExceeded() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        assertEquals(1, list.nodeCount());

        list.add(50);

        assertEquals(2, list.nodeCount());
        assertEquals(5, list.size());
    }

    @Test
    void shouldSplitHeadWhenAddingFirstToFullHead() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        list.addFirst(5);

        assertEquals(5, list.size());
        assertEquals(2, list.nodeCount());

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(20, list.get(2));
        assertEquals(30, list.get(3));
        assertEquals(40, list.get(4));
    }

    @Test
    void shouldSplitNodeWhenAddingToFullNode() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        list.add(2, 25);

        assertEquals(5, list.size());
        assertEquals(2, list.nodeCount());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(25, list.get(2));
        assertEquals(30, list.get(3));
        assertEquals(40, list.get(4));
    }

    @Test
    void shouldGetElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(20, list.get(1));
    }

    @Test
    void shouldSetElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer oldValue = list.set(1, 99);

        assertEquals(20, oldValue);
        assertEquals(99, list.get(1));
        assertEquals(3, list.size());
    }

    @Test
    void shouldRemoveElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer removed = list.remove(1);

        assertEquals(20, removed);
        assertEquals(2, list.size());

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }

    @Test
    void shouldRemoveFirstElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.remove(0));

        assertEquals(2, list.size());
        assertEquals(20, list.getFirst());
    }

    @Test
    void shouldRemoveLastElementByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(30, list.remove(2));

        assertEquals(2, list.size());
        assertEquals(20, list.getLast());
    }

    @Test
    void shouldRemoveElementByValue() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.remove(Integer.valueOf(20)));

        assertFalse(list.contains(20));
        assertEquals(2, list.size());
    }

    @Test
    void shouldReturnFalseWhenRemovingMissingValue() {
        list.add(10);
        list.add(20);

        assertFalse(list.remove(Integer.valueOf(99)));

        assertEquals(2, list.size());
    }

    @Test
    void shouldContainExistingElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.contains(20));
    }

    @Test
    void shouldNotContainMissingElement() {
        list.add(10);
        list.add(20);

        assertFalse(list.contains(99));
    }

    @Test
    void shouldReturnCorrectIndexOfElement() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        assertEquals(0, list.indexOf(10));
        assertEquals(2, list.indexOf(30));
        assertEquals(4, list.indexOf(50));
    }

    @Test
    void shouldReturnMinusOneForMissingElement() {
        list.add(10);
        list.add(20);

        assertEquals(-1, list.indexOf(99));
    }

    @Test
    void shouldReturnFirstElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.getFirst());
    }

    @Test
    void shouldReturnLastElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(30, list.getLast());
    }

    @Test
    void shouldClearList() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals(0, list.nodeCount());
    }

    @Test
    void shouldAllowAddingAfterClear() {
        list.add(10);
        list.add(20);

        list.clear();

        list.add(100);

        assertEquals(1, list.size());
        assertEquals(100, list.getFirst());
        assertEquals(100, list.getLast());
    }

    @Test
    void shouldRemoveEmptyNode() {
        UnrolledLinkedList<Integer> smallList =
                new UnrolledLinkedList<>(2);

        smallList.add(10);
        smallList.add(20);
        smallList.add(30);

        assertEquals(2, smallList.nodeCount());

        smallList.remove(2);

        assertEquals(1, smallList.nodeCount());
        assertEquals(2, smallList.size());
    }

    @Test
    void shouldHandleLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        assertEquals(1000, list.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, list.get(i));
        }

        assertEquals(0, list.getFirst());
        assertEquals(999, list.getLast());
    }

    @Test
    void shouldSupportNullElements() {
        list.add(10);
        list.add(null);
        list.add(20);

        assertTrue(list.contains(null));
        assertEquals(1, list.indexOf(null));

        assertTrue(list.remove((Integer) null));

        assertFalse(list.contains(null));
        assertEquals(2, list.size());
    }

    @Test
    void shouldSupportDuplicateElements() {
        list.add(10);
        list.add(20);
        list.add(10);

        assertEquals(3, list.size());
        assertEquals(0, list.indexOf(10));

        assertTrue(list.remove(Integer.valueOf(10)));

        assertEquals(2, list.size());
        assertEquals(1, list.indexOf(10));
    }

    @Test
    void constructorShouldUseCustomCapacity() {
        UnrolledLinkedList<Integer> customList =
                new UnrolledLinkedList<>(8);

        assertEquals(8, customList.nodeCapacity());
        assertEquals(0, customList.size());
        assertEquals(0, customList.nodeCount());
    }

    @Test
    void constructorShouldRejectCapacityBelowTwo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new UnrolledLinkedList<Integer>(1)
        );
    }

    @Test
    void getShouldRejectNegativeIndex() {
        list.add(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(-1)
        );
    }

    @Test
    void getShouldRejectIndexEqualToSize() {
        list.add(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(1)
        );
    }

    @Test
    void setShouldRejectInvalidIndex() {
        list.add(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.set(5, 20)
        );
    }

    @Test
    void removeShouldRejectInvalidIndex() {
        list.add(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(5)
        );
    }

    @Test
    void addShouldRejectNegativeIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(-1, 10)
        );
    }

    @Test
    void addShouldRejectIndexGreaterThanSize() {
        list.add(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.add(2, 20)
        );
    }

    @Test
    void getFirstShouldThrowWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                () -> list.getFirst()
        );
    }

    @Test
    void getLastShouldThrowWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                () -> list.getLast()
        );
    }
}