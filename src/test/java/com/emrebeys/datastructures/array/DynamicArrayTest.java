package com.emrebeys.datastructures.array;

import com.emrebeys.datastructures.array.DynamicArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void shouldAddElements() {

        DynamicArray<Integer> array = new DynamicArray<>();

        array.add(10);
        array.add(20);

        assertEquals(2, array.size());
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
    }

    @Test
    void shouldInsertElementAtIndex() {

        DynamicArray<Integer> array = new DynamicArray<>();

        array.add(10);
        array.add(30);

        array.add(1,20);

        assertEquals(3,array.size());
        assertEquals(20,array.get(1));
    }

    @Test
    void shouldRemoveElement() {

        DynamicArray<Integer> array = new DynamicArray<>();

        array.add(10);
        array.add(20);
        array.add(30);

        Integer removed=array.remove(1);

        assertEquals(20,removed);
        assertEquals(2,array.size());
        assertEquals(30,array.get(1));
    }

    @Test
    void shouldReplaceElement() {

        DynamicArray<String> array=new DynamicArray<>();

        array.add("Java");

        array.set(0,"Python");

        assertEquals("Python",array.get(0));
    }

    @Test
    void shouldClearArray() {

        DynamicArray<Integer> array=new DynamicArray<>();

        array.add(1);
        array.add(2);

        array.clear();

        assertTrue(array.isEmpty());
        assertEquals(0,array.size());
    }

    @Test
    void shouldContainElement() {

        DynamicArray<String> array=new DynamicArray<>();

        array.add("Tree");

        assertTrue(array.contains("Tree"));
        assertFalse(array.contains("Graph"));
    }

    @Test
    void shouldThrowExceptionForInvalidIndex() {

        DynamicArray<Integer> array=new DynamicArray<>();

        assertThrows(IndexOutOfBoundsException.class,
                ()->array.get(5));
    }

}