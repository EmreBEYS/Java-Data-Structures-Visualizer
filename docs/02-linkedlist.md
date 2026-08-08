# Linked List (Bağlı Liste)

Bu doküman, Linked List (Bağlı Liste) veri yapısının çalışma mantığını, bellek davranışını, zaman karmaşıklıklarını ve bu proje kapsamında geliştirilen farklı Linked List implementasyonlarını açıklamak amacıyla hazırlanmıştır.

---

# 📑 İçindekiler

- Amaç
- Linked List Nedir?
- Bellekte Nasıl Çalışır?
- Node Yapısı
- Avantajları
- Dezavantajları
- Linked List Türleri
- Singly Linked List
- Doubly Linked List
- Circular Singly Linked List
- Circular Doubly Linked List
- Skip List
- Unrolled Linked List
- Zaman Karmaşıklıkları
- Alan Karmaşıklığı
- Kullanım Örnekleri
- Test Coverage
- Proje Yapısı
- Gerçek Hayattan Örnek
- Sonuç
- Status

---

# 🎯 Amaç

Bu dokümanın amacı;

- Linked List veri yapısının temel mantığını açıklamak
- Node tabanlı veri organizasyonunu göstermek
- Bellekte nasıl çalıştığını incelemek
- Farklı Linked List türleri arasındaki farkları açıklamak
- Bu projede geliştirilen Java implementasyonlarını belgelemek
- Zaman ve alan karmaşıklıklarını karşılaştırmak
- JUnit test kapsamını göstermek

olarak belirlenmiştir.

---

# 📚 Linked List Nedir?

Linked List, elemanların bellekte ardışık olarak tutulmak zorunda olmadığı, her elemanın bir **Node (Düğüm)** içerisinde saklandığı dinamik bir veri yapısıdır.

Her Node genel olarak iki temel bilgi taşır:

```text
+-------------+-------------+
|    Data     |    Next     |
+-------------+-------------+
```

Burada;

- `Data` → Node içerisinde saklanan veriyi
- `Next` → Bir sonraki Node'un referansını

temsil eder.

Örnek:

```text
HEAD
 │
 ▼
+----+------+    +----+------+    +----+------+
| 10 | next | -> | 20 | next | -> | 30 | null |
+----+------+    +----+------+    +----+------+
```

Array yapısından farklı olarak elemanların bellekte yan yana bulunması gerekmez.

---

# 💾 Bellekte Nasıl Çalışır?

Array yapısında elemanlar bellekte ardışık adreslerde tutulur.

Linked List yapısında ise Node'lar belleğin farklı bölgelerinde bulunabilir.

Örneğin:

```text
Node 1
Adres: 1000

+------+------+
|  10  | 4080 |
+------+------+

        │
        ▼

Node 2
Adres: 4080

+------+------+
|  20  | 7200 |
+------+------+

        │
        ▼

Node 3
Adres: 7200

+------+------+
|  30  | null |
+------+------+
```

Node'lar birbirlerine referanslar üzerinden bağlanır.

Bu nedenle Linked List yapısında rastgele indeks erişimi Array kadar hızlı değildir.

Örneğin;

```java
list.get(2);
```

işlemi için liste başından başlanarak ilgili Node'a kadar ilerlenmesi gerekir.

Bu nedenle genel erişim karmaşıklığı:

```text
O(n)
```

şeklindedir.

---

# 🔗 Node Yapısı

Linked List yapısının temel bileşeni Node'dur.

Basit bir Singly Linked List Node'u:

```java
class Node<T> {

    T value;
    Node<T> next;

}
```

Doubly Linked List içerisinde ise Node hem önceki hem sonraki düğümü bilir.

```java
class DoublyNode<T> {

    T value;

    DoublyNode<T> previous;
    DoublyNode<T> next;

}
```

Bu yapı çift yönlü hareket etmeyi mümkün hale getirir.

---

# ✅ Avantajları

- Dinamik boyuta sahiptir
- Eleman sayısı önceden bilinmek zorunda değildir
- Baştan ekleme işlemi oldukça hızlıdır
- Baştan silme işlemi oldukça hızlıdır
- Node tabanlı esnek veri organizasyonu sağlar
- Bazı yapılarda ekleme ve silme işlemleri Array'e göre daha verimlidir
- Büyük veri yapılarının dinamik oluşturulmasına uygundur

---

# ❌ Dezavantajları

- Rastgele erişim desteklemez
- İndeks erişimi genellikle O(n)'dir
- Her Node ek referans belleği kullanır
- Array kadar CPU Cache dostu değildir
- Pointer / reference yönetimi daha karmaşıktır
- Doubly Linked List gibi yapılarda daha fazla bellek kullanılır

---

# 🌳 Linked List Türleri

Bu proje kapsamında aşağıdaki Linked List türleri sıfırdan geliştirilmiştir:

```text
Linked List Family
│
├── Singly Linked List
│
├── Doubly Linked List
│
├── Circular Singly Linked List
│
├── Circular Doubly Linked List
│
├── Skip List
│
└── Unrolled Linked List
```

Her yapı farklı kullanım amaçlarına ve performans özelliklerine sahiptir.

---

# 1️⃣ Singly Linked List

Singly Linked List, her Node'un yalnızca bir sonraki Node'u bildiği en temel Linked List türüdür.

```text
HEAD
 │
 ▼
+----+------+    +----+------+    +----+------+
| 10 | next | -> | 20 | next | -> | 30 | null |
+----+------+    +----+------+    +----+------+
```

Her Node şu iki alanı içerir:

```text
Value
Next
```

Liste üzerinde yalnızca ileri yönde hareket edilebilir.

## Desteklenen İşlemler

- Eleman ekleme
- Baştan ekleme
- Sondan ekleme
- İndekse göre ekleme
- Eleman silme
- Arama
- Eleman erişimi
- Liste temizleme
- Size kontrolü

## Temel Özellik

Başa ekleme işlemi:

```text
O(1)
```

karmaşıklığındadır.

---

# 2️⃣ Doubly Linked List

Doubly Linked List içerisinde her Node hem önceki hem de sonraki Node'un referansını tutar.

```text
null <- [10] <-> [20] <-> [30] -> null
```

Node yapısı:

```text
+----------+-------+----------+
| Previous | Value |   Next   |
+----------+-------+----------+
```

Bu yapı sayesinde liste üzerinde hem ileri hem geri hareket edilebilir.

## Avantajı

Bir Node biliniyorsa önceki veya sonraki Node'a doğrudan ulaşılabilir.

## Dezavantajı

Her Node iki referans tuttuğu için Singly Linked List'e göre daha fazla bellek kullanır.

---

# 3️⃣ Circular Singly Linked List

Circular Singly Linked List yapısında son Node'un `next` referansı `null` değildir.

Son Node tekrar ilk Node'u gösterir.

```text
        ┌─────────────────────┐
        │                     │
        ▼                     │
      [10] -> [20] -> [30] ---┘
```

Bu nedenle liste dairesel bir yapı oluşturur.

Özellikle;

- Round-Robin Scheduling
- Döngüsel görev sistemleri
- Sürekli tekrar eden listeler

için kullanılabilir.

---

# 4️⃣ Circular Doubly Linked List

Circular Doubly Linked List, Doubly Linked List ve Circular Linked List özelliklerini bir araya getirir.

```text
      ┌──────────────────────────┐
      │                          │
      ▼                          │
    [10] <-> [20] <-> [30]
      ▲                          │
      └──────────────────────────┘
```

Burada;

```text
tail.next = head
head.previous = tail
```

ilişkisi bulunmaktadır.

Liste üzerinde hem ileri hem geri yönde dairesel hareket mümkündür.

---

# 5️⃣ Skip List

Skip List, sıralı Linked List yapısının üzerine birden fazla seviye ekleyerek arama işlemlerini hızlandırmayı amaçlayan olasılıksal bir veri yapısıdır.

Normal Linked List:

```text
10 -> 20 -> 30 -> 40 -> 50 -> 60
```

Skip List:

```text
Level 2: 10 ----------------> 50
           |                   |
Level 1: 10 ------> 30 ------> 50
           |         |         |
Level 0: 10 -> 20 -> 30 -> 40 -> 50 -> 60
```

Üst seviyeler bazı Node'ları atlayarak ilerlemeyi sağlar.

Bu sayede arama işlemleri ortalama olarak:

```text
O(log n)
```

karmaşıklığında gerçekleştirilebilir.

## Bu Projedeki SkipList

Desteklenen işlemler:

- `add()`
- `contains()`
- `remove()`
- `first()`
- `last()`
- `size()`
- `currentLevel()`
- `isEmpty()`
- `clear()`

Duplicate değerlerin eklenmesine izin verilmez.

Seviyeler olasılıksal olarak oluşturulur.

Varsayılan değerler:

```text
Maximum Level = 16
Probability   = 0.5
```

---

# 6️⃣ Unrolled Linked List

Unrolled Linked List, klasik Linked List'ten farklı olarak her Node içerisinde tek bir eleman yerine birden fazla eleman saklar.

Klasik Linked List:

```text
[10] -> [20] -> [30] -> [40]
```

Unrolled Linked List:

```text
Node 1              Node 2
+----+----+         +----+----+
| 10 | 20 |   ->    | 30 | 40 |
+----+----+         +----+----+
```

Bu yaklaşım Node sayısını azaltır ve cache kullanımını iyileştirebilir.

Bu projede varsayılan Node kapasitesi:

```text
4
```

olarak belirlenmiştir.

Örneğin:

```text
Node Capacity = 4

Node 1
+----+----+----+----+
| 10 | 20 | 30 | 40 |
+----+----+----+----+
```

Yeni eleman geldiğinde yeni Node oluşturulabilir.

```text
Node 1                     Node 2
+----+----+----+----+     +----+----+----+----+
| 10 | 20 | 30 | 40 | --> | 50 |    |    |    |
+----+----+----+----+     +----+----+----+----+
```

Bazı araya ekleme işlemlerinde dolu Node bölünerek yeni Node oluşturulur.

## Desteklenen İşlemler

- `add()`
- `addFirst()`
- `addLast()`
- `add(index, element)`
- `get()`
- `set()`
- `remove(index)`
- `remove(element)`
- `contains()`
- `indexOf()`
- `getFirst()`
- `getLast()`
- `clear()`
- `size()`
- `nodeCount()`
- `nodeCapacity()`

---

# 📊 Zaman Karmaşıklıkları

## Genel Linked List Karşılaştırması

| İşlem | Singly | Doubly | Circular | Skip List | Unrolled |
|---|:---:|:---:|:---:|:---:|:---:|
| Başa Ekleme | O(1) | O(1) | O(1) | - | O(1)* |
| Sona Ekleme | O(1)* | O(1)* | O(1)* | O(log n) Ort. | O(1)* |
| Arama | O(n) | O(n) | O(n) | O(log n) Ort. | O(n) |
| İndeks Erişimi | O(n) | O(n) | O(n) | - | O(n) |
| Silme | O(n) | O(n) | O(n) | O(log n) Ort. | O(n) |
| Bellek | O(n) | O(n) | O(n) | O(n) | O(n) |

> `*` İşlemin gerçek maliyeti implementasyon detayına, tail referansına veya Node bölme gereksinimine göre değişebilir.

---

# 📦 Alan Karmaşıklığı

Linked List ailesindeki veri yapılarının genel alan karmaşıklığı:

```text
O(n)
```

şeklindedir.

Ancak kullanılan ek referans miktarı yapıya göre değişmektedir.

Örneğin;

```text
Singly Linked List
Value + Next
```

```text
Doubly Linked List
Previous + Value + Next
```

Skip List ise her Node için birden fazla ileri seviye referansı tutabilir.

Unrolled Linked List ise tek Node içerisinde birden fazla eleman saklayarak Node sayısını azaltmayı hedefler.

---

# 💻 Kullanım Örnekleri

## Singly Linked List

```java
LinkedList<Integer> list = new LinkedList<>();

list.add(10);
list.add(20);
list.add(30);
```

## Skip List

```java
SkipList<Integer> skipList =
        new SkipList<>(Integer::compareTo);

skipList.add(30);
skipList.add(10);
skipList.add(20);

System.out.println(skipList.contains(20));
```

## Unrolled Linked List

```java
UnrolledLinkedList<Integer> list =
        new UnrolledLinkedList<>();

list.add(10);
list.add(20);
list.add(30);

System.out.println(list.get(1));
```

---

# 🧪 Test Coverage

Linked List implementasyonları **JUnit 5** kullanılarak kapsamlı şekilde test edilmiştir.

Test edilen temel senaryolar:

- ✅ Empty List
- ✅ Add Element
- ✅ Add First
- ✅ Add Last
- ✅ Insert By Index
- ✅ Remove Element
- ✅ Remove By Index
- ✅ Get
- ✅ Set
- ✅ Contains
- ✅ IndexOf
- ✅ Clear
- ✅ Size Control
- ✅ Boundary Tests
- ✅ Invalid Index Exception
- ✅ Generic Type Support
- ✅ Duplicate Handling
- ✅ Circular Structure Behaviour
- ✅ Node Splitting
- ✅ Capacity Handling
- ✅ Large Dataset Operations

## Skip List

Skip List için:

```text
31 başarılı test
```

uygulanmıştır.

Testler;

- Ekleme
- Arama
- Silme
- Duplicate kontrolü
- İlk ve son eleman
- Geçersiz constructor parametreleri
- Null kontrolü
- Level sınırları
- Büyük veri setleri

gibi senaryoları kapsamaktadır.

## Unrolled Linked List

Unrolled Linked List için:

```text
40 başarılı test
```

uygulanmıştır.

Testler;

- Node kapasitesi
- Node oluşturma
- Node split
- Head split
- Index işlemleri
- Null elemanlar
- Duplicate elemanlar
- Büyük veri setleri
- Boundary kontrolleri

gibi senaryoları kapsamaktadır.

---

# 📁 Proje Yapısı

```text
src
├── main
│   └── java
│       └── com
│           └── emrebeys
│               └── datastructures
│                   └── linkedlist
│                       ├── singly
│                       │   ├── LinkedList.java
│                       │   ├── LinkedListDemo.java
│                       │   └── Node.java
│                       │
│                       ├── doubly
│                       │   ├── DoublyLinkedList.java
│                       │   ├── DoublyLinkedListDemo.java
│                       │   └── DoublyNode.java
│                       │
│                       ├── circularsingly
│                       │   ├── CircularSinglyLinkedList.java
│                       │   ├── CircularSinglyLinkedListDemo.java
│                       │   └── CircularSinglyNode.java
│                       │
│                       ├── circulardoubly
│                       │   ├── CircularDoublyLinkedList.java
│                       │   ├── CircularDoublyLinkedListDemo.java
│                       │   └── CircularDoublyNode.java
│                       │
│                       ├── skiplist
│                       │   ├── SkipList.java
│                       │   ├── SkipListDemo.java
│                       │   └── SkipListNode.java
│                       │
│                       └── unrolled
│                           ├── UnrolledLinkedList.java
│                           ├── UnrolledLinkedListDemo.java
│                           └── UnrolledNode.java
│
└── test
    └── java
        └── com
            └── emrebeys
                └── datastructures
                    └── linkedlist
                        ├── singly
                        │   └── LinkedListTest.java
                        │
                        ├── doubly
                        │   └── DoublyLinkedListTest.java
                        │
                        ├── circularsingly
                        │   └── CircularSinglyLinkedListTest.java
                        │
                        ├── circulardoubly
                        │   └── CircularDoublyLinkedListTest.java
                        │
                        ├── skiplist
                        │   └── SkipListTest.java
                        │
                        └── unrolled
                            └── UnrolledLinkedListTest.java
```

---

# 🎬 Gerçek Hayattan Örnek

Linked List yapısını bir tren vagonları dizisi gibi düşünebiliriz.

```text
[Lokomotif] -> [Vagon 1] -> [Vagon 2] -> [Vagon 3]
```

Her vagon kendisinden sonraki vagona bağlıdır.

Yeni bir vagon eklemek için bütün tren yapısının yeniden oluşturulması gerekmez.

Sadece bağlantılar değiştirilir.

Doubly Linked List yapısında ise bunu çift yönlü tren bağlantısı gibi düşünebiliriz:

```text
[Vagon 1] <-> [Vagon 2] <-> [Vagon 3]
```

Circular Linked List ise son vagonun tekrar ilk vagona bağlandığı kapalı bir ray sistemi gibi düşünülebilir.

Skip List, bazı istasyonları atlayan ekspres hatlara benzetilebilir.

Unrolled Linked List ise tek vagonda birden fazla yolcu grubu saklanması gibi düşünülebilir.

---

# 🎓 Sonuç

Linked List, bilgisayar bilimlerinde dinamik veri yönetiminin temel yapı taşlarından biridir.

Bu proje kapsamında Linked List ailesinin yalnızca temel versiyonu değil, farklı kullanım senaryolarına yönelik toplam **6 farklı implementasyonu** geliştirilmiştir.

Bunlar:

- Singly Linked List
- Doubly Linked List
- Circular Singly Linked List
- Circular Doubly Linked List
- Skip List
- Unrolled Linked List

şeklindedir.

Bu implementasyonlar sayesinde;

- Generic Programlama
- Node tabanlı veri organizasyonu
- Reference yönetimi
- Dinamik bellek yaklaşımı
- Liste algoritmaları
- Circular bağlantılar
- Olasılıksal veri yapıları
- Node splitting
- Big-O analizi
- Clean Code
- JUnit 5 ile test geliştirme

konuları uygulamalı olarak gösterilmiştir.

---

# ✅ Status

- Singly Linked List Implementation ✔️
- Doubly Linked List Implementation ✔️
- Circular Singly Linked List Implementation ✔️
- Circular Doubly Linked List Implementation ✔️
- Skip List Implementation ✔️
- Unrolled Linked List Implementation ✔️
- JUnit Tests ✔️
- Documentation ✔️

**Linked List Family:** Completed ✅

**Version:** 0.2.0