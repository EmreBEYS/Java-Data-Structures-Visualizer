package com.emrebeys.datastructures.queue;

public class QueueDemo {

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();

        System.out.println("=== QUEUE DEMO ===");

        System.out.println("\n1) Initial State");
        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.capacity());
        System.out.println("Is Empty: " + queue.isEmpty());

        System.out.println("\n2) Enqueue Operations");

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        System.out.println("Enqueued: 10");
        System.out.println("Enqueued: 20");
        System.out.println("Enqueued: 30");
        System.out.println("Enqueued: 40");

        System.out.println("\nSize: " + queue.size());
        System.out.println("Front Element: " + queue.peek());

        System.out.println("\n3) Dequeue Operation");

        Integer removed = queue.dequeue();

        System.out.println("Removed Element: " + removed);
        System.out.println("New Front Element: " + queue.peek());
        System.out.println("Size: " + queue.size());

        System.out.println("\n4) FIFO Demonstration");

        while (!queue.isEmpty()) {
            System.out.println("Dequeue -> " + queue.dequeue());
        }

        System.out.println("\nIs Empty: " + queue.isEmpty());

        System.out.println("\n5) Automatic Resize");

        for (int i = 1; i <= 15; i++) {
            queue.enqueue(i * 10);
        }

        System.out.println("Size: " + queue.size());
        System.out.println("Capacity: " + queue.capacity());
        System.out.println("Front Element: " + queue.peek());

        System.out.println("\n6) Clear Operation");

        queue.clear();

        System.out.println("Size: " + queue.size());
        System.out.println("Is Empty: " + queue.isEmpty());
        System.out.println("Capacity: " + queue.capacity());

        System.out.println("\n=== DEMO COMPLETED ===");
    }
}