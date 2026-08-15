package com.emrebeys.datastructures.hashmap;


/**
 * Separate Chaining yöntemi kullanan temel HashMap implementasyonu.
 *
 * Collision durumlarında bucket içerisinde bağlı liste kullanılır.
 * Load factor sınırı aşıldığında kapasite otomatik olarak artırılır.
 *
 * @param <K> Anahtar tipi
 * @param <V> Değer tipi
 */

public class HashMap<K, V>{
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private HashNode<K, V>[] buckets;
    private int size;
    private int capacity;
    private final double loadFactor;

    public HashMap(){
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public HashMap(int initialCapacity){
        this(initialCapacity,DEFAULT_LOAD_FACTOR);
    }
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity, double loadFactor) {

        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Başlangıç kapasitesi 0'dan büyük olmalıdır."
            );
        }

        if (loadFactor <= 0 || Double.isNaN(loadFactor)) {
            throw new IllegalArgumentException(
                    "Load factor 0'dan büyük olmalıdır."
            );
        }

        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = (HashNode<K, V>[]) new HashNode[capacity];
        this.size = 0;
    }

    /**
     * Yeni bir anahtar-değer çifti ekler.
     *
     * Anahtar zaten varsa mevcut değeri günceller.
     */
    public void put(K key, V value) {

        int index = getBucketIndex(key);

        HashNode<K, V> current = buckets[index];

        // Anahtar daha önce eklenmişse değerini güncelliyoruz.
        while (current != null) {

            if (keysEqual(current.getKey(), key)) {
                current.setValue(value);
                return;
            }

            current = current.getNext();
        }

        // Yeni node bucket'ın başına ekleniyor.
        HashNode<K, V> newNode = new HashNode<>(key, value);

        newNode.setNext(buckets[index]);
        buckets[index] = newNode;

        size++;

        // Load factor sınırı aşılmışsa kapasiteyi büyütüyoruz.
        if ((double) size / capacity > loadFactor) {
            resize();
        }
    }

    /**
     * Verilen anahtara ait değeri döndürür.
     *
     * Anahtar bulunamazsa null döner.
     */
    public V get(K key) {

        HashNode<K, V> node = findNode(key);

        return node == null
                ? null
                : node.getValue();
    }

    /**
     * Verilen anahtarı HashMap içerisinden siler.
     *
     * Silinen değeri döndürür.
     * Anahtar bulunamazsa null döner.
     */
    public V remove(K key) {

        int index = getBucketIndex(key);

        HashNode<K, V> current = buckets[index];
        HashNode<K, V> previous = null;

        while (current != null) {

            if (keysEqual(current.getKey(), key)) {

                if (previous == null) {

                    // Silinen node bucket'ın ilk elemanıysa
                    // bucket artık sonraki node'u göstermelidir.
                    buckets[index] = current.getNext();

                } else {

                    previous.setNext(current.getNext());
                }

                size--;

                return current.getValue();
            }

            previous = current;
            current = current.getNext();
        }

        return null;
    }

    /**
     * Anahtarın HashMap içerisinde bulunup bulunmadığını kontrol eder.
     */
    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    /**
     * Verilen değerin HashMap içerisinde bulunup bulunmadığını kontrol eder.
     */
    public boolean containsValue(V value) {

        for (HashNode<K, V> bucket : buckets) {

            HashNode<K, V> current = bucket;

            while (current != null) {

                if (valuesEqual(current.getValue(), value)) {
                    return true;
                }

                current = current.getNext();
            }
        }

        return false;
    }

    /**
     * HashMap içerisindeki eleman sayısını döndürür.
     */
    public int size() {
        return size;
    }

    /**
     * HashMap boşsa true döndürür.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * HashMap içerisindeki tüm elemanları temizler.
     */
    @SuppressWarnings("unchecked")
    public void clear() {

        buckets = (HashNode<K, V>[]) new HashNode[capacity];

        size = 0;
    }

    /**
     * HashMap'in mevcut bucket kapasitesini döndürür.
     *
     * Testlerde resize mekanizmasını kontrol etmek için kullanışlıdır.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Verilen anahtara karşılık gelen node'u bulur.
     */
    private HashNode<K, V> findNode(K key) {

        int index = getBucketIndex(key);

        HashNode<K, V> current = buckets[index];

        while (current != null) {

            if (keysEqual(current.getKey(), key)) {
                return current;
            }

            current = current.getNext();
        }

        return null;
    }

    /**
     * Anahtarın hash değerinden bucket index'i hesaplar.
     */
    private int getBucketIndex(K key) {

        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();

        // Negatif hashCode değerlerinde de geçerli
        // bir bucket index'i üretir.
        hash ^= (hash >>> 16);

        return (hash & 0x7fffffff) % capacity;
    }

    /**
     * Kapasiteyi iki katına çıkarır ve
     * tüm elemanları yeni bucket dizisine yeniden dağıtır.
     */
    @SuppressWarnings("unchecked")
    private void resize() {

        HashNode<K, V>[] oldBuckets = buckets;

        capacity *= 2;

        buckets = (HashNode<K, V>[]) new HashNode[capacity];

        // Eleman sayısını değiştirmiyoruz.
        // Sadece mevcut node'ları yeni bucket'lara dağıtıyoruz.
        for (HashNode<K, V> bucket : oldBuckets) {

            HashNode<K, V> current = bucket;

            while (current != null) {

                HashNode<K, V> next = current.getNext();

                int newIndex = getBucketIndex(current.getKey());

                current.setNext(buckets[newIndex]);
                buckets[newIndex] = current;

                current = next;
            }
        }
    }

    /**
     * Null anahtarları da destekleyen anahtar karşılaştırması.
     */
    private boolean keysEqual(K first, K second) {

        if (first == null) {
            return second == null;
        }

        return first.equals(second);
    }

    /**
     * Null değerleri de destekleyen değer karşılaştırması.
     */
    private boolean valuesEqual(V first, V second) {

        if (first == null) {
            return second == null;
        }

        return first.equals(second);
    }

    /**
     * HashMap içeriğini okunabilir biçimde döndürür.
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("{");

        boolean firstElement = true;

        for (HashNode<K, V> bucket : buckets) {

            HashNode<K, V> current = bucket;

            while (current != null) {

                if (!firstElement) {
                    builder.append(", ");
                }

                builder
                        .append(current.getKey())
                        .append("=")
                        .append(current.getValue());

                firstElement = false;

                current = current.getNext();
            }
        }

        builder.append("}");

        return builder.toString();
    }

}
