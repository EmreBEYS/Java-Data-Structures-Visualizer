package com.emrebeys.datastructures.array;

import com.emrebeys.datastructures.array.DynamicArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    private DynamicArray<Integer> dynamicArray;

    @BeforeEach
    void setUp() {
        dynamicArray = new DynamicArray<>();
    }

    @Test
    void shouldStartEmpty() {
        assertTrue(dynamicArray.isEmpty());
        assertEquals(0, dynamicArray.size());
    }

    @Test
    void shouldAddElement() {
        dynamicArray.add(10);

        assertEquals(1, dynamicArray.size());
        assertEquals(10, dynamicArray.get(0));
    }

    @Test
    void shouldAddMultipleElements() {
        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);

        assertEquals(3, dynamicArray.size());
        assertEquals(10, dynamicArray.get(0));
        assertEquals(20, dynamicArray.get(1));
        assertEquals(30, dynamicArray.get(2));
    }

    @Test
    void shouldResizeWhenCapacityIsExceeded() {
        for (int i = 0; i < 20; i++) {
            dynamicArray.add(i);
        }

        assertEquals(20, dynamicArray.size());

        for (int i = 0; i < 20; i++) {
            assertEquals(i, dynamicArray.get(i));
        }
    }

    @Test
    void shouldSetElementAtGivenIndex() {
        dynamicArray.add(10);
        dynamicArray.add(20);

        dynamicArray.set(1, 99);

        assertEquals(99, dynamicArray.get(1));
        assertEquals(2, dynamicArray.size());
    }

    @Test
    void shouldRemoveElementByIndex() {
        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);

        Integer removedElement = dynamicArray.remove(1);

        assertEquals(20, removedElement);
        assertEquals(2, dynamicArray.size());
        assertEquals(10, dynamicArray.get(0));
        assertEquals(30, dynamicArray.get(1));
    }

    @Test
    void shouldReturnTrueWhenElementExists() {
        dynamicArray.add(10);
        dynamicArray.add(20);

        assertTrue(dynamicArray.contains(20));
    }

    @Test
    void shouldReturnFalseWhenElementDoesNotExist() {
        dynamicArray.add(10);

        assertFalse(dynamicArray.contains(99));
    }

    @Test
    void shouldReturnCorrectIndexOfElement() {
        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);

        assertEquals(1, dynamicArray.indexOf(20));
    }

    @Test
    void shouldReturnMinusOneWhenElementDoesNotExist() {
        dynamicArray.add(10);

        assertEquals(-1, dynamicArray.indexOf(99));
    }

    @Test
    void shouldClearAllElements() {
        dynamicArray.add(10);
        dynamicArray.add(20);

        dynamicArray.clear();

        assertTrue(dynamicArray.isEmpty());
        assertEquals(0, dynamicArray.size());
    }

    @Test
    void shouldThrowExceptionWhenGettingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> dynamicArray.get(0)
        );
    }

    @Test
    void shouldThrowExceptionWhenRemovingInvalidIndex() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> dynamicArray.remove(0)
        );
    }
}