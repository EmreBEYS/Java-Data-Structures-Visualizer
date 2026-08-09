# 06 - Heap

Bu bölümde **Heap** veri yapısının iki temel türü uygulanmıştır:

- Min Heap
- Max Heap

Heap yapısı, complete binary tree mantığıyla çalışan ve elemanları öncelik ilişkisine göre düzenleyen bir veri yapısıdır.

Projede heap yapıları `ArrayList<Integer>` tabanlı olarak geliştirilmiştir.

---

# 1. Heap Nedir?

Heap, her seviyenin soldan sağa doğru doldurulduğu bir **Complete Binary Tree** yapısıdır.

Heap iki temel kurala göre uygulanabilir:

```text
Min Heap -> Parent <= Children
Max Heap -> Parent >= Children
```

Heap yapısı tamamen sıralı olmak zorunda değildir.

Önemli olan her parent düğümün çocuklarıyla olan ilişkisidir.

---

# 2. Array Tabanlı Heap Yapısı

Heap fiziksel olarak ağaç gibi düşünülse de projede dizi tabanlı olarak tutulmaktadır.

Örnek:

```text
        10
       /  \
     20    30
    / \    /
   40 50  60
```

Array karşılığı:

```text
[10, 20, 30, 40, 50, 60]
```

Bir düğümün index değeri biliniyorsa diğer düğümlere aşağıdaki formüllerle ulaşılabilir.

```text
Parent      = (index - 1) / 2
Left Child  = (2 * index) + 1
Right Child = (2 * index) + 2
```

---

# 3. Min Heap

Min Heap içerisinde her parent düğüm çocuklarından küçük veya onlara eşittir.

Kural:

```text
Parent <= Children
```

Örnek:

```text
        5
       / \
     10   30
    / \   /
   40 50 20
```

Bu yapıda en küçük değer her zaman root konumundadır.

```java
heap.peek();
```

sonucu:

```text
5
```

---

# 4. MinHeap Sınıfı

`MinHeap.java` sınıfı aşağıdaki temel operasyonları içerir:

```text
insert
peek
extractMin
contains
size
isEmpty
clear
toList
```

Ayrıca heap düzenini korumak için aşağıdaki yardımcı metotlar kullanılmaktadır:

```text
heapifyUp
heapifyDown
getParentIndex
getLeftChildIndex
getRightChildIndex
swap
```

---

# 5. Min Heap Insert

Yeni değer önce ArrayList'in sonuna eklenir.

Ardından `heapifyUp()` çalıştırılır.

Örnek:

```text
Başlangıç:

        10
       /  \
     20    30
```

`5` eklenirse ilk olarak:

```text
        10
       /  \
     20    30
    /
   5
```

oluşur.

Ardından parent değerleriyle karşılaştırılır.

Sonuç:

```text
        5
       / \
     10   30
    /
   20
```

Akış:

```text
Insert
  |
  v
Array sonuna ekle
  |
  v
Parent ile karşılaştır
  |
  v
Gerekirse swap
  |
  v
Root'a kadar devam et
```

---

# 6. Min Heap Peek

`peek()` heap içerisindeki en küçük değeri silmeden döndürür.

```java
int min = heap.peek();
```

Min Heap içerisinde minimum değer her zaman:

```text
index = 0
```

konumundadır.

Zaman karmaşıklığı:

```text
O(1)
```

---

# 7. Min Heap extractMin

`extractMin()` minimum değeri heap içerisinden çıkarır.

İşlem sırası:

```text
Root değerini al
      |
      v
Son elemanı root'a taşı
      |
      v
Son elemanı kaldır
      |
      v
heapifyDown()
      |
      v
Heap düzenini yeniden kur
```

Örnek:

```text
        5
       / \
     10   20
    / \
   40 30
```

`extractMin()` sonrası `5` çıkarılır.

Son eleman root'a taşınır:

```text
        30
       /  \
     10    20
    /
   40
```

`heapifyDown()` sonrasında:

```text
        10
       /  \
     30    20
    /
   40
```

elde edilir.

---

# 8. Max Heap

Max Heap, Min Heap yapısının tersidir.

Kural:

```text
Parent >= Children
```

Örnek:

```text
        90
       /  \
     70    80
    / \    /
   20 40  60
```

En büyük değer her zaman root konumundadır.

```java
heap.peek();
```

sonucu:

```text
90
```

---

# 9. MaxHeap Sınıfı

`MaxHeap.java` sınıfı aşağıdaki temel operasyonları içerir:

```text
insert
peek
extractMax
contains
size
isEmpty
clear
toList
```

Heap düzenini korumak için:

```text
heapifyUp
heapifyDown
getParentIndex
getLeftChildIndex
getRightChildIndex
swap
```

yardımcı metotları kullanılmaktadır.

---

# 10. Max Heap Insert

Yeni değer ArrayList'in sonuna eklenir.

Ardından `heapifyUp()` çalıştırılır.

Bu kez child değeri parent değerinden büyükse swap yapılır.

Örnek:

```text
        50
       /  \
     30    40
```

`80` eklenirse:

```text
        50
       /  \
     30    40
    /
   80
```

heapify sonrası:

```text
        80
       /  \
     50    40
    /
   30
```

elde edilir.

---

# 11. Max Heap Peek

`peek()` Max Heap içerisindeki en büyük değeri silmeden döndürür.

```java
int max = heap.peek();
```

Maximum değer her zaman:

```text
index = 0
```

konumundadır.

Zaman karmaşıklığı:

```text
O(1)
```

---

# 12. Max Heap extractMax

`extractMax()` root değerini kaldırır.

Akış:

```text
Root değerini al
      |
      v
Son elemanı root'a taşı
      |
      v
Son elemanı kaldır
      |
      v
heapifyDown()
      |
      v
Heap düzenini yeniden kur
```

Max Heap'te `heapifyDown()` sırasında daha büyük olan child seçilir.

---

# 13. heapifyUp

`heapifyUp()` yeni eklenen bir elemanın heap kuralını bozması durumunda yukarı doğru taşınmasını sağlar.

Min Heap:

```text
Child < Parent -> Swap
```

Max Heap:

```text
Child > Parent -> Swap
```

Bu işlem root'a ulaşılana veya heap kuralı sağlanana kadar devam eder.

Zaman karmaşıklığı:

```text
O(log n)
```

---

# 14. heapifyDown

`heapifyDown()` root veya ara bir düğümün heap kuralını bozması durumunda aşağı doğru taşınmasını sağlar.

Min Heap:

```text
Daha küçük child seçilir.
```

Max Heap:

```text
Daha büyük child seçilir.
```

Zaman karmaşıklığı:

```text
O(log n)
```

---

# 15. Duplicate Değerler

Heap yapılarında duplicate değerlere izin verilmektedir.

Örnek:

```text
10
10
5
```

Min Heap extraction sırası:

```text
5
10
10
```

Max Heap extraction sırası:

```text
10
10
5
```

---

# 16. Negatif Değerler

MinHeap ve MaxHeap negatif değerlerle de çalışabilir.

Örnek:

```text
-10
5
-20
```

Min Heap:

```text
peek() -> -20
```

Max Heap:

```text
peek() -> 5
```

---

# 17. contains

Heap içerisindeki bir değerin mevcut olup olmadığını kontrol eder.

Örnek:

```java
heap.contains(20);
```

ArrayList tabanlı doğrusal arama kullanıldığı için karmaşıklık:

```text
O(n)
```

---

# 18. size ve isEmpty

Heap içerisindeki eleman sayısı:

```java
heap.size();
```

Heap'in boş olup olmadığı:

```java
heap.isEmpty();
```

ile kontrol edilir.

---

# 19. clear

Heap içerisindeki tüm elemanları temizler.

```java
heap.clear();
```

Sonrasında:

```text
size = 0
isEmpty = true
```

olur.

---

# 20. toList

Heap'in iç ArrayList yapısının kopyasını döndürür.

```java
heap.toList();
```

Bu metod özellikle demo ve debug işlemlerinde kullanılmaktadır.

Önemli:

```text
Heap listesi tamamen sıralı olmak zorunda değildir.
```

Örneğin geçerli bir Min Heap:

```text
[5, 20, 10, 40, 50, 30]
```

şeklinde olabilir.

---

# 21. Heap Demo

Projede `HeapDemo.java` sınıfı hazırlanmıştır.

Demo içerisinde hem Min Heap hem de Max Heap çalıştırılmıştır.

Min Heap için:

```text
insert
peek
contains
extractMin
size
isEmpty
clear
```

Max Heap için:

```text
insert
peek
contains
extractMax
size
isEmpty
```

işlemleri gösterilmiştir.

Min Heap extraction sırası:

```text
5
10
20
30
40
50
```

Max Heap extraction sırası:

```text
50
40
30
20
10
5
```

Demo başarıyla çalıştırılmıştır.

---

# 22. MinHeap Testleri

`MinHeapTest.java` içerisinde aşağıdaki senaryolar test edilmektedir:

- Empty heap
- Single insert
- Minimum root kontrolü
- Size kontrolü
- Contains
- Missing value
- extractMin
- Artan sırada extraction
- Duplicate değerler
- Negatif değerler
- Empty heap peek exception
- Empty heap extract exception
- Clear

Toplam:

```text
13 JUnit Test
```

---

# 23. MaxHeap Testleri

`MaxHeapTest.java` içerisinde aşağıdaki senaryolar test edilmektedir:

- Empty heap
- Single insert
- Maximum root kontrolü
- Size kontrolü
- Contains
- Missing value
- extractMax
- Azalan sırada extraction
- Duplicate değerler
- Negatif değerler
- Empty heap peek exception
- Empty heap extract exception
- Clear

Toplam:

```text
13 JUnit Test
```

---

# 24. Heap Test Özeti

Heap bölümü için hazırlanan test sayısı:

```text
MinHeapTest : 13
MaxHeapTest : 13
----------------
Toplam      : 26
```

---

# 25. Min Heap ve Max Heap Karşılaştırması

| Özellik | Min Heap | Max Heap |
|---|---|---|
| Root | Minimum değer | Maximum değer |
| Parent kuralı | Parent <= Children | Parent >= Children |
| Insert | Var | Var |
| Peek | Minimum | Maximum |
| Extraction | extractMin | extractMax |
| Duplicate | Desteklenir | Desteklenir |
| Negatif değer | Desteklenir | Desteklenir |
| Heapify Up | Var | Var |
| Heapify Down | Var | Var |

---

# 26. Zaman Karmaşıklıkları

| Operasyon | Min Heap | Max Heap |
|---|---:|---:|
| insert | O(log n) | O(log n) |
| peek | O(1) | O(1) |
| extract | O(log n) | O(log n) |
| contains | O(n) | O(n) |
| size | O(1) | O(1) |
| isEmpty | O(1) | O(1) |
| clear | O(n) | O(n) |

---

# 27. Proje Dosya Yapısı

```text
src
├── main
│   └── java
│       └── com
│           └── emrebeys
│               └── datastructures
│                   └── heap
│                       ├── MinHeap.java
│                       ├── MaxHeap.java
│                       └── HeapDemo.java
│
└── test
    └── java
        └── com
            └── emrebeys
                └── datastructures
                    └── heap
                        ├── MinHeapTest.java
                        └── MaxHeapTest.java
```

---

# 28. Sınıf Sorumlulukları

## MinHeap.java

Minimum değeri root'ta tutan heap implementasyonudur.

Temel operasyonlar:

```text
insert
peek
extractMin
contains
size
isEmpty
clear
toList
```

---

## MaxHeap.java

Maximum değeri root'ta tutan heap implementasyonudur.

Temel operasyonlar:

```text
insert
peek
extractMax
contains
size
isEmpty
clear
toList
```

---

## HeapDemo.java

Min Heap ve Max Heap davranışlarını terminal üzerinde gösteren demo sınıfıdır.

---

## MinHeapTest.java

Min Heap algoritmalarının JUnit testlerini içerir.

```text
13 Test
```

---

## MaxHeapTest.java

Max Heap algoritmalarının JUnit testlerini içerir.

```text
13 Test
```

---

# 29. Heap Genel Mimarisi

```text
Heap
│
├── Min Heap
│   ├── Insert
│   ├── Peek
│   ├── Extract Min
│   ├── Heapify Up
│   ├── Heapify Down
│   ├── Contains
│   ├── Size
│   ├── Clear
│   └── Tests
│
└── Max Heap
    ├── Insert
    ├── Peek
    ├── Extract Max
    ├── Heapify Up
    ├── Heapify Down
    ├── Contains
    ├── Size
    ├── Clear
    └── Tests
```

---

# 30. Kazanımlar

Bu bölüm ile birlikte aşağıdaki konular uygulanmıştır:

- Complete Binary Tree mantığı
- Array tabanlı heap saklama
- Parent-child index hesaplamaları
- Min Heap
- Max Heap
- Heap insert
- Heap extraction
- heapifyUp
- heapifyDown
- Priority mantığı
- Duplicate değer yönetimi
- Negatif değer yönetimi
- JUnit ile heap davranışlarının test edilmesi

---

# 31. Sonuç

Heap bölümünde iki temel heap yapısı geliştirilmiştir.

```text
Heap
├── Min Heap ✅
│   ├── Insert ✅
│   ├── Peek ✅
│   ├── Extract Min ✅
│   ├── Heapify Up ✅
│   ├── Heapify Down ✅
│   ├── Contains ✅
│   ├── Clear ✅
│   ├── Demo ✅
│   └── 13 Test
│
└── Max Heap ✅
    ├── Insert ✅
    ├── Peek ✅
    ├── Extract Max ✅
    ├── Heapify Up ✅
    ├── Heapify Down ✅
    ├── Contains ✅
    ├── Clear ✅
    ├── Demo ✅
    └── 13 Test
```

Heap bölümü tamamlanmıştır.

---

# Sonraki Bölüm

Bir sonraki veri yapısı:

```text
07 - Hash Map
```

Ardından:

```text
08 - Graph
```

ile veri yapıları projesinin ana bölümleri tamamlanacaktır.
