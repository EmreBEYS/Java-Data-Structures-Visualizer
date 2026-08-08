# Array (Dizi)

Bu doküman, Array (Dizi) veri yapısının çalışma mantığını, bellek davranışını, zaman karmaşıklıklarını ve bu proje kapsamında geliştirilen **DynamicArray** implementasyonunu açıklamak amacıyla hazırlanmıştır.

---

# 📑 İçindekiler

- Amaç
- Array Nedir?
- Bellekte Nasıl Çalışır?
- Avantajları
- Dezavantajları
- Dynamic Array Nedir?
- DynamicArray Implementasyonu
- Bellek Yapısı
- Resize (Kapasite Artırma) Mekanizması
- Zaman Karmaşıklıkları
- Alan Karmaşıklığı
- Kullanım Örneği
- Test Kapsamı
- Proje Yapısı
- Gerçek Hayattan Örnek
- Sonuç

---

# 🎯 Amaç

Bu dokümanın amacı;

- Array veri yapısının temel mantığını açıklamak
- Bellekte nasıl çalıştığını göstermek
- Dynamic Array algoritmasını anlatmak
- Bu projedeki Java implementasyonunu belgelemek
- Zaman ve alan karmaşıklıklarını incelemektir.

---

# 📚 Array Nedir?

Array (Dizi), aynı veri tipindeki elemanları bellekte **ardışık (Contiguous Memory)** olarak saklayan temel veri yapılarından biridir.

Her elemanın bellekte sabit bir konumu bulunduğu için istenilen elemana doğrudan indeks numarası ile erişilebilir.

Örnek:

```
İndeks

0     1     2     3

+-----+-----+-----+-----+
| 10  | 20  | 30  | 40  |
+-----+-----+-----+-----+
```

Burada;

- 10 → Index 0
- 20 → Index 1
- 30 → Index 2
- 40 → Index 3

şeklinde tutulmaktadır.

---

# 💾 Bellekte Nasıl Çalışır?

Array elemanları bellekte ardışık adreslerde tutulur.

```
Adres

1000
1004
1008
1012
```

Örneğin;

```java
array[2]
```

işlemi doğrudan ilgili bellek adresine gider.

Bu nedenle erişim süresi

```
O(1)
```

karmaşıklığındadır.

Bu özellik Array veri yapısını oldukça hızlı hale getirir.

---

# ✅ Avantajları

- Çok hızlı rastgele erişim (Random Access)
- CPU Cache dostudur
- Bellekte düzenli saklanır
- Basit implementasyona sahiptir
- Okuma işlemleri oldukça hızlıdır

---

# ❌ Dezavantajları

- Boyutu sabittir
- Araya eleman eklemek maliyetlidir
- Eleman silmek maliyetlidir
- Büyük diziler yeniden oluşturulmak zorundadır

---

# 🚀 Dynamic Array Nedir?

Java'daki **ArrayList**, C++'taki **vector** ve Python'daki **list** veri yapıları aslında Dynamic Array mantığı ile çalışmaktadır.

Başlangıçta belirli bir kapasite oluşturulur.

Örneğin;

```
Capacity = 2

+----+----+
|10  |20  |
+----+----+
```

Yeni eleman geldiğinde;

```
30
```

eklenemez.

Bu durumda yeni ve daha büyük bir dizi oluşturulur.

```
Capacity = 4

+----+----+----+----+
|10  |20  |30  |    |
+----+----+----+----+
```

Eski dizideki elemanlar yeni diziye kopyalanır.

---

# ⚙️ Bu Projede Geliştirilen DynamicArray

Bu proje kapsamında Java kullanılarak tamamen sıfırdan Generic bir Dynamic Array geliştirilmiştir.

Desteklenen işlemler;

- add()
- add(index, element)
- get()
- set()
- remove()
- contains()
- indexOf()
- clear()
- size()
- capacity()

---

# 🧠 Bellek Yapısı

Örnek DynamicArray görünümü

```
Capacity = 8

+----+----+----+----+----+----+----+----+
|10  |20  |30  |40  |    |    |    |    |
+----+----+----+----+----+----+----+----+

Size = 4
Capacity = 8
```

---

# 🔄 Resize (Kapasite Artırma) Mekanizması

Dizi dolduğunda kapasite otomatik olarak artırılır.

```
Eski Kapasite

Capacity = 4

+----+----+----+----+
|10  |20  |30  |40  |
+----+----+----+----+

↓

Yeni Kapasite

Capacity = 8

+----+----+----+----+----+----+----+----+
|10  |20  |30  |40  |50  |    |    |    |
+----+----+----+----+----+----+----+----+
```

Temel algoritma

```java
if(size == capacity){
    resize(capacity * 2);
}
```

Bu yöntem sayesinde ekleme işlemleri ortalama olarak oldukça hızlı gerçekleşmektedir.

---

# 📊 Zaman Karmaşıklıkları

| İşlem | En İyi | Ortalama | En Kötü |
|-------|:------:|:--------:|:--------:|
| get() | O(1) | O(1) | O(1) |
| set() | O(1) | O(1) | O(1) |
| add() | O(1) | O(1) (Amortized) | O(n) |
| add(index) | O(n) | O(n) | O(n) |
| remove() | O(1)* | O(n) | O(n) |
| contains() | O(1)* | O(n) | O(n) |
| indexOf() | O(1)* | O(n) | O(n) |

> *En iyi durum; ilk elemanın bulunması veya son elemanın silinmesi gibi özel senaryolardır.

---

# 📦 Alan Karmaşıklığı

Dynamic Array

```
O(n)
```

alan kullanmaktadır.

---

# 💻 Kullanım Örneği

```java
DynamicArray<Integer> numbers = new DynamicArray<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.get(1));

numbers.remove(0);
```

---

# 🧪 Test Coverage

DynamicArray implementasyonu aşağıdaki senaryolar için **JUnit 5** kullanılarak test edilmiştir.

- ✅ Empty Array
- ✅ Add Element
- ✅ Multiple Add
- ✅ Automatic Resize
- ✅ Remove
- ✅ Set
- ✅ Contains
- ✅ IndexOf
- ✅ Clear
- ✅ Invalid Index Exception
- ✅ Boundary Tests
- ✅ Size Control
- ✅ Generic Type Support

Toplam **13 başarılı test** bulunmaktadır.

---

# 📁 Proje Yapısı

```
src
├── main
│   └── java
│       └── com
│           └── emrebeys
│               └── datastructures
│                   └── array
│                       └── DynamicArray.java
│
└── test
    └── java
        └── com
            └── emrebeys
                └── datastructures
                    └── array
                        └── DynamicArrayTest.java
```

---

# 🎬 Gerçek Hayattan Örnek

Bir sinema salonundaki koltuklar;

```
1
2
3
4
5
6
7
8
```

şeklinde numaralandırılır.

5 numaralı koltuğa ulaşmak için tüm koltukları dolaşmaya gerek yoktur.

Doğrudan ilgili koltuğa gidilir.

Array veri yapısı da aynı mantıkla çalışmaktadır.

---

# 🎓 Sonuç

Array, bilgisayar bilimlerinin en temel veri yapılarından biridir.

Bu proje kapsamında geliştirilen **DynamicArray**, Java'nın hazır koleksiyonlarını kullanmadan tamamen sıfırdan geliştirilmiştir.

Bu implementasyon sayesinde;

- Generic Programlama
- Bellek Yönetimi
- Dynamic Resize Algoritması
- Big-O Analizi
- Temiz Kod Prensipleri
- JUnit ile Test Geliştirme

konuları uygulamalı olarak gösterilmiştir.

---

# ✅ Status

- DynamicArray Implementation ✔️
- JUnit Tests ✔️
- Documentation ✔️

**Version:** 0.1.0