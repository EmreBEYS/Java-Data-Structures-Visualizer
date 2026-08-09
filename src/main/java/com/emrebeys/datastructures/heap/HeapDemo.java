package com.emrebeys.datastructures.heap;

public class HeapDemo {

    public static void main(String[] args) {

        System.out.println("=== Heap Demo ===");

        // =========================
        // Min Heap
        // =========================
        System.out.println("\n--- Min Heap ---");

        MinHeap minHeap = new MinHeap();

        minHeap.insert(40);
        minHeap.insert(10);
        minHeap.insert(30);
        minHeap.insert(20);
        minHeap.insert(50);
        minHeap.insert(5);

        System.out.println("Heap: " + minHeap.toList());
        System.out.println("Size: " + minHeap.size());
        System.out.println("Peek (Min): " + minHeap.peek());

        System.out.println("20 var mı?: " + minHeap.contains(20));
        System.out.println("99 var mı?: " + minHeap.contains(99));

        System.out.println("\nMin değerler sırayla çıkarılıyor:");

        while (!minHeap.isEmpty()) {
            System.out.println(
                    "Extract Min: " + minHeap.extractMin()
                            + " | Heap: " + minHeap.toList()
            );
        }

        System.out.println("MinHeap boş mu?: " + minHeap.isEmpty());

        // =========================
        // Max Heap
        // =========================
        System.out.println("\n--- Max Heap ---");

        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(40);
        maxHeap.insert(10);
        maxHeap.insert(30);
        maxHeap.insert(20);
        maxHeap.insert(50);
        maxHeap.insert(5);

        System.out.println("Heap: " + maxHeap.toList());
        System.out.println("Size: " + maxHeap.size());
        System.out.println("Peek (Max): " + maxHeap.peek());

        System.out.println("30 var mı?: " + maxHeap.contains(30));
        System.out.println("100 var mı?: " + maxHeap.contains(100));

        System.out.println("\nMax değerler sırayla çıkarılıyor:");

        while (!maxHeap.isEmpty()) {
            System.out.println(
                    "Extract Max: " + maxHeap.extractMax()
                            + " | Heap: " + maxHeap.toList()
            );
        }

        System.out.println("MaxHeap boş mu?: " + maxHeap.isEmpty());

        // =========================
        // Clear Test
        // =========================
        System.out.println("\n--- Clear Test ---");

        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(30);

        System.out.println("Clear öncesi MinHeap: " + minHeap.toList());

        minHeap.clear();

        System.out.println("Clear sonrası MinHeap: " + minHeap.toList());
        System.out.println("Size: " + minHeap.size());
        System.out.println("Boş mu?: " + minHeap.isEmpty());

        System.out.println("\n=== Heap Demo Tamamlandı ===");
    }
}