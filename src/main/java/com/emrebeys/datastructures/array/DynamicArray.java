package com.emrebeys.datastructures.array;

import java.util.Arrays;
import java.util.Objects;

/**
 * Java'daki ArrayList yapısının temel çalışma mantığını
 * göstermek amacıyla geliştirilmiş dinamik dizi sınıfıdır.
 *
 * @param <T> Dizide tutulacak veri tipi
 */
public class DynamicArray<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                    "Başlangıç kapasitesi negatif olamaz."
            );
        }

        elements = new Object[initialCapacity];
    }

    /**
     * Dizinin sonuna yeni bir eleman ekler.
     *
     * Ortalama zaman karmaşıklığı: O(1)
     * Yeniden boyutlandırma gerçekleşirse: O(n)
     */
    public void add(T element) {
        ensureCapacity();
        elements[size] = element;
        size++;
    }

    /**
     * Belirtilen indekse eleman ekler.
     *
     * Zaman karmaşıklığı: O(n)
     */
    public void add(int index, T element) {
        checkPositionIndex(index);
        ensureCapacity();

        int movedElementCount = size - index;

        if (movedElementCount > 0) {
            System.arraycopy(
                    elements,
                    index,
                    elements,
                    index + 1,
                    movedElementCount
            );
        }

        elements[index] = element;
        size++;
    }

    /**
     * Belirtilen indeksteki elemanı döndürür.
     *
     * Zaman karmaşıklığı: O(1)
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkElementIndex(index);
        return (T) elements[index];
    }

    /**
     * Belirtilen indeksteki elemanı değiştirir.
     *
     * Zaman karmaşıklığı: O(1)
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkElementIndex(index);

        T previousElement = (T) elements[index];
        elements[index] = element;

        return previousElement;
    }

    /**
     * Belirtilen indeksteki elemanı siler.
     *
     * Zaman karmaşıklığı: O(n)
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkElementIndex(index);

        T removedElement = (T) elements[index];

        int movedElementCount = size - index - 1;

        if (movedElementCount > 0) {
            System.arraycopy(
                    elements,
                    index + 1,
                    elements,
                    index,
                    movedElementCount
            );
        }

        elements[size - 1] = null;
        size--;

        return removedElement;
    }

    /**
     * Verilen elemanın ilk bulunduğu konumu siler.
     *
     * Zaman karmaşıklığı: O(n)
     */
    public boolean remove(T element) {
        int index = indexOf(element);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    /**
     * Elemanın dizideki ilk indeksini döndürür.
     *
     * Zaman karmaşıklığı: O(n)
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }

        int newCapacity;

        if (elements.length == 0) {
            newCapacity = 1;
        } else {
            newCapacity = elements.length * 2;
        }

        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Geçersiz indeks: " + index +
                            ", mevcut eleman sayısı: " + size
            );
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Geçersiz ekleme indeksi: " + index +
                            ", mevcut eleman sayısı: " + size
            );
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(
                Arrays.copyOf(elements, size)
        );
    }
}