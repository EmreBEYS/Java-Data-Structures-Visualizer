package com.emrebeys.datastructures.hashmap;

/**
 * HashMap veri yapısının temel kullanım örneklerini gösterir.
 */

public class HashMapDemo {
    public static void main(String[] args){
        HashMap<String,Integer> map=new HashMap<>();
        System.out.println("=== HashMap Demo ===");

        //Elaman ekleme
        map.put("Java",95);
        map.put("Python",90);
        map.put("C",85);
        map.put("Kotlin",80);

        System.out.println("Baslangic: ");
        System.out.println(map);

        //Eleman okuma
        System.out.println("\nJava degeri: "+map.get("Java"));
        System.out.println("\nKotlin degeri: "+map.get("Kotlin"));

        //Mevcut anahtarın degerini günceleme
        map.put("Java",100);
        System.out.println(map);

        // Anahtar kontrolü
        System.out.println("\nJava var mı? " + map.containsKey("Java"));
        System.out.println("Go var mı? " + map.containsKey("Go"));

        // Değer kontrolü
        System.out.println("100 değeri var mı? " + map.containsValue(100));
        System.out.println("50 değeri var mı? " + map.containsValue(50));

        // Eleman silme
        Integer removedValue = map.remove("Python");

        System.out.println("\nSilinen Python değeri: " + removedValue);
        System.out.println(map);

        // Eleman sayısı
        System.out.println("\nBoyut: " + map.size());
        System.out.println("Boş mu? " + map.isEmpty());

        // Null anahtar ve değer desteği
        map.put(null, 75);
        map.put("Rust", null);

        System.out.println("\nNull değer testleri:");
        System.out.println(map);
        System.out.println("null key değeri: " + map.get(null));
        System.out.println("Rust değeri: " + map.get("Rust"));

        // Resize mekanizmasını göstermek için
        // küçük kapasiteli yeni bir HashMap oluşturuyoruz.
        HashMap<Integer, String> resizeMap = new HashMap<>(4);

        System.out.println("\n=== Resize Testi ===");
        System.out.println("Başlangıç kapasitesi: " + resizeMap.capacity());

        resizeMap.put(1, "Bir");
        resizeMap.put(2, "İki");
        resizeMap.put(3, "Üç");
        resizeMap.put(4, "Dört");

        System.out.println("Yeni kapasite: " + resizeMap.capacity());
        System.out.println(resizeMap);

        // Tüm elemanları temizleme
        map.clear();

        System.out.println("\n=== Clear Testi ===");
        System.out.println("Map: " + map);
        System.out.println("Boyut: " + map.size());
        System.out.println("Boş mu? " + map.isEmpty());
    }
}
