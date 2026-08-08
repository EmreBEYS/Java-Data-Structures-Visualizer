package com.emrebeys.datastructures.linkedlist.skiplist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    private SkipList<Integer> skipList;

    @BeforeEach
    void setUp() {
        skipList = new SkipList<>(
                Comparator.naturalOrder(),
                16,
                0.5,
                new Random(42)
        );
    }

    @Test
    void newSkipListShouldBeEmpty() {
        assertTrue(skipList.isEmpty());
        assertEquals(0, skipList.size());
        assertEquals(0, skipList.currentLevel());
    }

    @Test
    void shouldAddSingleValue() {
        assertTrue(skipList.add(10));

        assertEquals(1, skipList.size());
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(10));
    }

    @Test
    void shouldAddMultipleValues() {
        assertTrue(skipList.add(30));
        assertTrue(skipList.add(10));
        assertTrue(skipList.add(20));
        assertTrue(skipList.add(40));

        assertEquals(4, skipList.size());

        assertTrue(skipList.contains(10));
        assertTrue(skipList.contains(20));
        assertTrue(skipList.contains(30));
        assertTrue(skipList.contains(40));
    }

    @Test
    void shouldRejectDuplicateValues() {
        assertTrue(skipList.add(10));
        assertFalse(skipList.add(10));

        assertEquals(1, skipList.size());
    }

    @Test
    void shouldReturnFalseWhenValueDoesNotExist() {
        skipList.add(10);
        skipList.add(20);
        skipList.add(30);

        assertFalse(skipList.contains(99));
    }

    @Test
    void shouldFindExistingValues() {
        skipList.add(50);
        skipList.add(10);
        skipList.add(30);
        skipList.add(20);
        skipList.add(40);

        assertTrue(skipList.contains(10));
        assertTrue(skipList.contains(30));
        assertTrue(skipList.contains(50));
    }

    @Test
    void shouldRemoveExistingValue() {
        skipList.add(10);
        skipList.add(20);
        skipList.add(30);

        assertTrue(skipList.remove(20));

        assertFalse(skipList.contains(20));
        assertEquals(2, skipList.size());

        assertTrue(skipList.contains(10));
        assertTrue(skipList.contains(30));
    }

    @Test
    void shouldReturnFalseWhenRemovingNonExistingValue() {
        skipList.add(10);
        skipList.add(20);

        assertFalse(skipList.remove(99));

        assertEquals(2, skipList.size());
    }

    @Test
    void shouldRemoveFirstValue() {
        skipList.add(10);
        skipList.add(20);
        skipList.add(30);

        assertTrue(skipList.remove(10));

        assertFalse(skipList.contains(10));
        assertEquals(20, skipList.first());
        assertEquals(2, skipList.size());
    }

    @Test
    void shouldRemoveLastValue() {
        skipList.add(10);
        skipList.add(20);
        skipList.add(30);

        assertTrue(skipList.remove(30));

        assertFalse(skipList.contains(30));
        assertEquals(20, skipList.last());
        assertEquals(2, skipList.size());
    }

    @Test
    void shouldBecomeEmptyAfterRemovingAllValues() {
        skipList.add(10);
        skipList.add(20);

        assertTrue(skipList.remove(10));
        assertTrue(skipList.remove(20));

        assertTrue(skipList.isEmpty());
        assertEquals(0, skipList.size());
        assertEquals(0, skipList.currentLevel());
    }

    @Test
    void shouldReturnFirstValue() {
        skipList.add(50);
        skipList.add(20);
        skipList.add(10);
        skipList.add(40);
        skipList.add(30);

        assertEquals(10, skipList.first());
    }

    @Test
    void shouldReturnLastValue() {
        skipList.add(50);
        skipList.add(20);
        skipList.add(10);
        skipList.add(40);
        skipList.add(30);

        assertEquals(50, skipList.last());
    }

    @Test
    void shouldClearSkipList() {
        skipList.add(10);
        skipList.add(20);
        skipList.add(30);
        skipList.add(40);

        skipList.clear();

        assertTrue(skipList.isEmpty());
        assertEquals(0, skipList.size());
        assertEquals(0, skipList.currentLevel());

        assertFalse(skipList.contains(10));
        assertFalse(skipList.contains(20));
        assertFalse(skipList.contains(30));
        assertFalse(skipList.contains(40));
    }

    @Test
    void shouldAllowAddingValuesAfterClear() {
        skipList.add(10);
        skipList.add(20);

        skipList.clear();

        assertTrue(skipList.add(100));

        assertEquals(1, skipList.size());
        assertTrue(skipList.contains(100));
        assertEquals(100, skipList.first());
        assertEquals(100, skipList.last());
    }

    @Test
    void shouldHandleLargeNumberOfValues() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(skipList.add(i));
        }

        assertEquals(1000, skipList.size());

        for (int i = 0; i < 1000; i++) {
            assertTrue(skipList.contains(i));
        }

        assertEquals(0, skipList.first());
        assertEquals(999, skipList.last());
    }

    @Test
    void shouldRemoveMultipleValues() {
        for (int i = 0; i < 100; i++) {
            skipList.add(i);
        }

        for (int i = 0; i < 100; i += 2) {
            assertTrue(skipList.remove(i));
        }

        assertEquals(50, skipList.size());

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                assertFalse(skipList.contains(i));
            } else {
                assertTrue(skipList.contains(i));
            }
        }
    }

    @Test
    void currentLevelShouldStayWithinAllowedRange() {
        for (int i = 0; i < 1000; i++) {
            skipList.add(i);
        }

        assertTrue(skipList.currentLevel() >= 0);
        assertTrue(skipList.currentLevel() <= 16);
    }

    @Test
    void shouldSupportReverseComparator() {
        SkipList<Integer> reverseSkipList =
                new SkipList<>(
                        Comparator.reverseOrder(),
                        16,
                        0.5,
                        new Random(42)
                );

        reverseSkipList.add(10);
        reverseSkipList.add(30);
        reverseSkipList.add(20);
        reverseSkipList.add(50);
        reverseSkipList.add(40);

        assertEquals(50, reverseSkipList.first());
        assertEquals(10, reverseSkipList.last());

        assertTrue(reverseSkipList.contains(30));
    }

    @Test
    void shouldThrowExceptionWhenAddingNull() {
        assertThrows(
                NullPointerException.class,
                () -> skipList.add(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenSearchingNull() {
        assertThrows(
                NullPointerException.class,
                () -> skipList.contains(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenRemovingNull() {
        assertThrows(
                NullPointerException.class,
                () -> skipList.remove(null)
        );
    }

    @Test
    void firstShouldThrowExceptionWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                () -> skipList.first()
        );
    }

    @Test
    void lastShouldThrowExceptionWhenEmpty() {
        assertThrows(
                IllegalStateException.class,
                () -> skipList.last()
        );
    }

    @Test
    void constructorShouldRejectNullComparator() {
        assertThrows(
                NullPointerException.class,
                () -> new SkipList<Integer>(null)
        );
    }

    @Test
    void constructorShouldRejectNullRandom() {
        assertThrows(
                NullPointerException.class,
                () -> new SkipList<Integer>(
                        Comparator.naturalOrder(),
                        16,
                        0.5,
                        null
                )
        );
    }

    @Test
    void constructorShouldRejectInvalidMaximumLevel() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SkipList<Integer>(
                        Comparator.naturalOrder(),
                        0,
                        0.5
                )
        );
    }

    @Test
    void constructorShouldRejectZeroProbability() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SkipList<Integer>(
                        Comparator.naturalOrder(),
                        16,
                        0.0
                )
        );
    }

    @Test
    void constructorShouldRejectProbabilityOfOne() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SkipList<Integer>(
                        Comparator.naturalOrder(),
                        16,
                        1.0
                )
        );
    }

    @Test
    void constructorShouldRejectNegativeProbability() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SkipList<Integer>(
                        Comparator.naturalOrder(),
                        16,
                        -0.5
                )
        );
    }

    @Test
    void constructorShouldRejectProbabilityGreaterThanOne() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SkipList<Integer>(
                        Comparator.naturalOrder(),
                        16,
                        1.5
                )
        );
    }
}