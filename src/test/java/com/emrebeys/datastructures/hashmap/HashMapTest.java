package com.emrebeys.datastructures.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void newHashMapShouldBeEmpty() {
        HashMap<String, Integer> map = new HashMap<>();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    void putShouldAddElement() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);

        assertEquals(1, map.size());
        assertEquals(95, map.get("Java"));
    }

    @Test
    void putShouldUpdateExistingKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 90);
        map.put("Java", 100);

        assertEquals(1, map.size());
        assertEquals(100, map.get("Java"));
    }

    @Test
    void getShouldReturnNullForUnknownKey() {
        HashMap<String, Integer> map = new HashMap<>();

        assertNull(map.get("Python"));
    }

    @Test
    void containsKeyShouldReturnTrueForExistingKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);

        assertTrue(map.containsKey("Java"));
    }

    @Test
    void containsKeyShouldReturnFalseForUnknownKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);

        assertFalse(map.containsKey("Python"));
    }

    @Test
    void containsValueShouldReturnTrueForExistingValue() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);
        map.put("Python", 90);

        assertTrue(map.containsValue(90));
    }

    @Test
    void containsValueShouldReturnFalseForUnknownValue() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);

        assertFalse(map.containsValue(50));
    }

    @Test
    void removeShouldDeleteExistingElement() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);
        map.put("Python", 90);

        Integer removedValue = map.remove("Java");

        assertEquals(95, removedValue);
        assertNull(map.get("Java"));
        assertFalse(map.containsKey("Java"));
        assertEquals(1, map.size());
    }

    @Test
    void removeShouldReturnNullForUnknownKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);

        assertNull(map.remove("Python"));
        assertEquals(1, map.size());
    }

    @Test
    void clearShouldRemoveAllElements() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);
        map.put("Python", 90);
        map.put("C", 85);

        map.clear();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("Java"));
        assertNull(map.get("Python"));
        assertNull(map.get("C"));
    }

    @Test
    void hashMapShouldSupportNullKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put(null, 75);

        assertEquals(75, map.get(null));
        assertTrue(map.containsKey(null));
    }

    @Test
    void nullKeyShouldBeUpdatedInsteadOfDuplicated() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put(null, 50);
        map.put(null, 100);

        assertEquals(1, map.size());
        assertEquals(100, map.get(null));
    }

    @Test
    void hashMapShouldSupportNullValue() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", null);

        assertTrue(map.containsKey("Java"));
        assertTrue(map.containsValue(null));
        assertNull(map.get("Java"));
    }

    @Test
    void removeShouldSupportNullKey() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put(null, 75);

        Integer removedValue = map.remove(null);

        assertEquals(75, removedValue);
        assertFalse(map.containsKey(null));
        assertTrue(map.isEmpty());
    }

    @Test
    void mapShouldResizeWhenLoadFactorIsExceeded() {
        HashMap<Integer, String> map = new HashMap<>(4);

        assertEquals(4, map.capacity());

        map.put(1, "Bir");
        map.put(2, "İki");
        map.put(3, "Üç");

        // 3 / 4 = 0.75 olduğu için henüz resize olmamalı.
        assertEquals(4, map.capacity());

        map.put(4, "Dört");

        // 4 / 4 > 0.75 olduğundan kapasite iki katına çıkar.
        assertEquals(8, map.capacity());
        assertEquals(4, map.size());
    }

    @Test
    void elementsShouldRemainAccessibleAfterResize() {
        HashMap<Integer, String> map = new HashMap<>(2);

        map.put(1, "Bir");
        map.put(2, "İki");
        map.put(3, "Üç");
        map.put(4, "Dört");
        map.put(5, "Beş");

        assertEquals("Bir", map.get(1));
        assertEquals("İki", map.get(2));
        assertEquals("Üç", map.get(3));
        assertEquals("Dört", map.get(4));
        assertEquals("Beş", map.get(5));

        assertEquals(5, map.size());
    }

    @Test
    void collisionShouldBeHandledCorrectly() {
        HashMap<CollisionKey, String> map = new HashMap<>();

        CollisionKey key1 = new CollisionKey("A");
        CollisionKey key2 = new CollisionKey("B");
        CollisionKey key3 = new CollisionKey("C");

        map.put(key1, "Bir");
        map.put(key2, "İki");
        map.put(key3, "Üç");

        assertEquals(3, map.size());

        assertEquals("Bir", map.get(key1));
        assertEquals("İki", map.get(key2));
        assertEquals("Üç", map.get(key3));
    }

    @Test
    void removeShouldWorkCorrectlyDuringCollision() {
        HashMap<CollisionKey, String> map = new HashMap<>();

        CollisionKey key1 = new CollisionKey("A");
        CollisionKey key2 = new CollisionKey("B");
        CollisionKey key3 = new CollisionKey("C");

        map.put(key1, "Bir");
        map.put(key2, "İki");
        map.put(key3, "Üç");

        assertEquals("İki", map.remove(key2));

        assertEquals("Bir", map.get(key1));
        assertNull(map.get(key2));
        assertEquals("Üç", map.get(key3));

        assertEquals(2, map.size());
    }

    @Test
    void removingFirstNodeInCollisionChainShouldWork() {
        HashMap<CollisionKey, String> map = new HashMap<>();

        CollisionKey key1 = new CollisionKey("A");
        CollisionKey key2 = new CollisionKey("B");

        map.put(key1, "Bir");
        map.put(key2, "İki");

        // Yeni node bucket başına eklendiği için key2 zincirin başındadır.
        assertEquals("İki", map.remove(key2));

        assertEquals("Bir", map.get(key1));
        assertEquals(1, map.size());
    }

    @Test
    void customCapacityShouldBeUsed() {
        HashMap<String, Integer> map = new HashMap<>(32);

        assertEquals(32, map.capacity());
        assertTrue(map.isEmpty());
    }

    @Test
    void invalidCapacityShouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new HashMap<String, Integer>(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new HashMap<String, Integer>(-5)
        );
    }

    @Test
    void invalidLoadFactorShouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new HashMap<String, Integer>(16, 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new HashMap<String, Integer>(16, -0.5)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new HashMap<String, Integer>(16, Double.NaN)
        );
    }

    @Test
    void clearShouldAllowMapToBeUsedAgain() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Java", 95);
        map.put("Python", 90);

        map.clear();

        map.put("Kotlin", 100);

        assertEquals(1, map.size());
        assertEquals(100, map.get("Kotlin"));
        assertFalse(map.containsKey("Java"));
    }

    /**
     * Collision testlerinde bütün anahtarların aynı bucket'a
     * düşmesini sağlamak için sabit hashCode döndüren yardımcı sınıf.
     */
    private static class CollisionKey {

        private final String value;

        private CollisionKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 42;
        }

        @Override
        public boolean equals(Object object) {

            if (this == object) {
                return true;
            }

            if (!(object instanceof CollisionKey other)) {
                return false;
            }

            return value.equals(other.value);
        }
    }
}
