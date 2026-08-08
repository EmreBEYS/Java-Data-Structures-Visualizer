package com.emrebeys.datastructures.stack;

public class StackDemo {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        System.out.println("=== STACK DEMO ===");

        System.out.println("\n1) Initial State");
        System.out.println("Size: " + stack.size());
        System.out.println("Capacity: " + stack.capacity());
        System.out.println("Is Empty: " + stack.isEmpty());

        System.out.println("\n2) Push Operations");

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        System.out.println("Pushed: 10");
        System.out.println("Pushed: 20");
        System.out.println("Pushed: 30");
        System.out.println("Pushed: 40");

        System.out.println("\nSize: " + stack.size());
        System.out.println("Top Element: " + stack.peek());

        System.out.println("\n3) Pop Operation");

        Integer removed = stack.pop();

        System.out.println("Removed Element: " + removed);
        System.out.println("New Top Element: " + stack.peek());
        System.out.println("Size: " + stack.size());

        System.out.println("\n4) LIFO Demonstration");

        while (!stack.isEmpty()) {
            System.out.println("Pop -> " + stack.pop());
        }

        System.out.println("\nIs Empty: " + stack.isEmpty());

        System.out.println("\n5) Automatic Resize");

        for (int i = 1; i <= 15; i++) {
            stack.push(i * 10);
        }

        System.out.println("Size: " + stack.size());
        System.out.println("Capacity: " + stack.capacity());
        System.out.println("Top Element: " + stack.peek());

        System.out.println("\n6) Clear Operation");

        stack.clear();

        System.out.println("Size: " + stack.size());
        System.out.println("Is Empty: " + stack.isEmpty());
        System.out.println("Capacity: " + stack.capacity());

        System.out.println("\n=== DEMO COMPLETED ===");
    }
}