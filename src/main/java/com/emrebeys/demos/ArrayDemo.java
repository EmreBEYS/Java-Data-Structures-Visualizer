package com.emrebeys.demos;

import com.emrebeys.datastructures.array.DynamicArray;

public final class ArrayDemo {

    private ArrayDemo() {
    }

    public static void run() {
        System.out.println("=== Dynamic Array Demo ===");

        DynamicArray<String> courses = new DynamicArray<>(2);

        printState("Başlangıç", courses);

        courses.add("Veri Yapıları");
        printState("İlk eleman eklendi", courses);

        courses.add("Algoritmalar");
        printState("İkinci eleman eklendi", courses);

        courses.add("Veritabanı Sistemleri");
        printState("Kapasite doldu ve dizi büyüdü", courses);

        courses.add(1, "İşletim Sistemleri");
        printState("1. indekse eleman eklendi", courses);

        courses.set(0, "İleri Veri Yapıları");
        printState("0. indeks güncellendi", courses);

        String removedCourse = courses.remove(2);

        System.out.println("Silinen eleman: " + removedCourse);
        printState("Eleman silindi", courses);

        System.out.println(
                "Veritabanı Sistemleri var mı? "
                        + courses.contains("Veritabanı Sistemleri")
        );

        System.out.println(
                "İlk eleman: " + courses.get(0)
        );
    }

    private static void printState(
            String operation,
            DynamicArray<?> dynamicArray
    ) {
        System.out.println();
        System.out.println("İşlem: " + operation);
        System.out.println("Dizi: " + dynamicArray);
        System.out.println("Boyut: " + dynamicArray.size());
        System.out.println("Kapasite: " + dynamicArray.capacity());
    }
}