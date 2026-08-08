package com.emrebeys.datastructures.queue;

public class CircularQueueDemo {

    public static void main(String[] args) {

        CircularQueue<Integer> queue =
                new CircularQueue<>(5);

        System.out.println("=== CIRCULAR QUEUE DEMO ===");

        System.out.println("\n1) Initial State");
        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.capacity());
        System.out.println("Is Empty: " + queue.isEmpty());
        System.out.println("Is Full: " + queue.isFull());

        System.out.println("\n2) Enqueue Operations");

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        System.out.println("Enqueued: 10");
        System.out.println("Enqueued: 20");
        System.out.println("Enqueued: 30");
        System.out.println("Enqueued: 40");
        System.out.println("Enqueued: 50");

        System.out.println("\nSize: " + queue.size());
        System.out.println("Front Element: " + queue.peek());
        System.out.println("Rear Element: " + queue.rear());
        System.out.println("Is Full: " + queue.isFull());

        System.out.println("\n3) Dequeue Operations");

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());

        System.out.println("\nFront Element: " + queue.peek());
        System.out.println("Rear Element: " + queue.rear());
        System.out.println("Size: " + queue.size());

        System.out.println("\n4) Wrap-Around Demonstration");

        queue.enqueue(60);
        queue.enqueue(70);

        System.out.println("Enqueued: 60");
        System.out.println("Enqueued: 70");

        System.out.println("\nFront Element: " + queue.peek());
        System.out.println("Rear Element: " + queue.rear());
        System.out.println("Size: " + queue.size());
        System.out.println("Is Full: " + queue.isFull());

        System.out.println("\nFIFO Order:");

        while (!queue.isEmpty()) {
            System.out.println("Dequeue -> " + queue.dequeue());
        }

        System.out.println("\n5) Automatic Resize");

        for (int i = 1; i <= 8; i++) {
            queue.enqueue(i * 10);
        }

        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.capacity());
        System.out.println("Front Element: " + queue.peek());
        System.out.println("Rear Element: " + queue.rear());

        System.out.println("\n6) Clear Operation");

        queue.clear();

        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.capacity());
        System.out.println("Is Empty: " + queue.isEmpty());

        System.out.println("\n=== DEMO COMPLETED ===");
    }
}