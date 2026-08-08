# Queue (Kuyruk)

Bu doküman, Queue (Kuyruk) veri yapısının çalışma mantığını, FIFO prensibini, bellek davranışını, zaman karmaşıklıklarını ve bu proje kapsamında geliştirilen **Queue** ile **Circular Queue** implementasyonlarını açıklamak amacıyla hazırlanmıştır.

---

# 📑 İçindekiler

- Amaç
- Queue Nedir?
- FIFO Prensibi
- Bellekte Nasıl Çalışır?
- Temel Queue Operasyonları
- Enqueue
- Dequeue
- Peek
- Avantajları
- Dezavantajları
- Bu Projede Geliştirilen Queue
- Otomatik Kapasite Artırma
- Circular Queue Nedir?
- Wrap-Around Mekanizması
- Circular Queue Resize Mekanizması
- Queue ve Circular Queue Karşılaştırması
- Zaman Karmaşıklıkları
- Alan Karmaşıklığı
- Kullanım Örnekleri
- Test Coverage
- Proje Yapısı
- Gerçek Hayattan Örnek
- Kullanım Alanları
- Sonuç
- Status

---

# 🎯 Amaç

Bu dokümanın amacı;

- Queue veri yapısının temel mantığını açıklamak
- FIFO çalışma prensibini göstermek
- Queue'nun bellekte nasıl organize edildiğini incelemek
- Temel Queue operasyonlarını açıklamak
- Array tabanlı Queue implementasyonunu belgelemek
- Circular Queue çalışma mantığını göstermek
- Wrap-Around mekanizmasını açıklamak
- Otomatik kapasite artırma mekanizmasını incelemek
- Queue ve Circular Queue arasındaki farkları göstermek
- Zaman ve alan karmaşıklıklarını karşılaştırmak
- JUnit 5 test kapsamını belgelemek

olarak belirlenmiştir.

---

# 📚 Queue Nedir?

Queue, elemanların belirli bir sıraya göre eklendiği ve çıkarıldığı doğrusal bir veri yapısıdır.

Queue yapısında ilk eklenen eleman ilk çıkarılan elemandır.

Bu çalışma prensibine:

```text
FIFO
```

adı verilir.

FIFO açılımı:

```text
First In
First Out
```

şeklindedir.

Türkçe olarak:

```text
İlk Giren
İlk Çıkar
```

mantığı ile çalışır.

---

# 🔄 FIFO Prensibi

Queue içerisine sırasıyla şu değerlerin eklendiğini düşünelim:

```text
10
20
30
40
```

Queue görünümü:

```text
FRONT                         REAR
  │                             │
  ▼                             ▼
+----+    +----+    +----+    +----+
| 10 | -> | 20 | -> | 30 | -> | 40 |
+----+    +----+    +----+    +----+
```

Burada ilk eklenen eleman:

```text
10
```

olduğu için ilk çıkarılacak eleman da `10` olacaktır.

```text
dequeue()
```

işleminden sonra:

```text
FRONT                REAR
  │                    │
  ▼                    ▼
+----+    +----+    +----+
| 20 | -> | 30 | -> | 40 |
+----+    +----+    +----+
```

şeklinde bir yapı oluşur.

---

# 💾 Bellekte Nasıl Çalışır?

Bu projede geliştirilen temel Queue implementasyonu **Array tabanlıdır**.

Elemanlar dahili olarak:

```java
Object[] elements;
```

dizisi içerisinde saklanmaktadır.

Ayrıca:

```java
int size;
```

değişkeni mevcut eleman sayısını takip eder.

Örnek:

```text
Index
  0     1     2     3     4

+-----+-----+-----+-----+-----+
| 10  | 20  | 30  |     |     |
+-----+-----+-----+-----+-----+

size = 3
```

Queue'nun önündeki eleman:

```text
elements[0]
```

konumunda bulunur.

Yeni elemanlar ise:

```text
elements[size]
```

konumuna eklenir.

---

# ⚙️ Temel Queue Operasyonları

Queue veri yapısında temel olarak üç önemli operasyon bulunmaktadır:

```text
enqueue()
dequeue()
peek()
```

Bu projede bunlara ek olarak:

```text
size()
isEmpty()
capacity()
clear()
```

metotları da desteklenmektedir.

---

# ➕ Enqueue

`enqueue()` Queue'nun sonuna yeni bir eleman ekler.

Örnek:

```java
queue.enqueue(10);
queue.enqueue(20);
queue.enqueue(30);
```

Sonuç:

```text
FRONT                REAR
  │                    │
  ▼                    ▼
10 -> 20 -> 30
```

Temel Array tabanlı işlem:

```java
elements[size] = element;
size++;
```

mantığı ile gerçekleştirilir.

Dizi doluysa önce kapasite artırılır.

---

# ➖ Dequeue

`dequeue()` Queue'nun önündeki elemanı kaldırır ve geri döndürür.

Örnek:

```text
FRONT
  │
  ▼
10 -> 20 -> 30
```

```java
queue.dequeue();
```

sonrasında:

```text
FRONT
  │
  ▼
20 -> 30
```

olur.

Dönen değer:

```text
10
```

şeklindedir.

Bu projedeki temel Queue implementasyonunda ilk eleman çıkarıldıktan sonra kalan elemanlar sola kaydırılır.

Örneğin:

```text
Önce:

[10] [20] [30] [40]

dequeue()

Sonra:

[20] [30] [40] [ ]
```

Bu nedenle temel Array tabanlı `dequeue()` işlemi:

```text
O(n)
```

maliyetine sahiptir.

Queue boşken `dequeue()` çağrılırsa:

```java
IllegalStateException
```

fırlatılır.

---

# 👀 Peek

`peek()` Queue'nun önündeki elemanı silmeden döndürür.

Örnek:

```text
10 -> 20 -> 30
```

```java
queue.peek();
```

sonucu:

```text
10
```

olur.

Queue yapısı değişmez.

Queue boş olduğunda `peek()` çağrılırsa:

```java
IllegalStateException
```

fırlatılır.

---

# ✅ Avantajları

- FIFO tabanlı işlemler için oldukça uygundur
- Basit ve anlaşılır yapıya sahiptir
- Ekleme işlemi hızlıdır
- Görev sıralama sistemlerinde kullanılabilir
- Veri akışı yönetimine uygundur
- Generic olarak farklı veri tiplerini destekleyebilir
- Producer-Consumer sistemlerinin temelini oluşturabilir
- Scheduling algoritmalarında kullanılabilir

---

# ❌ Dezavantajları

- Ortadaki elemanlara doğrudan erişim sağlamaz
- Klasik Array tabanlı yapıda `dequeue()` sırasında kaydırma maliyeti oluşur
- Rastgele erişim için uygun değildir
- Arama işlemi genellikle O(n) maliyetlidir
- Kapasite yönetimi gerekir
- Basit Queue implementasyonunda kullanılmayan boş alanlar verimsiz değerlendirilebilir

---

# 🚀 Bu Projede Geliştirilen Queue

Bu proje kapsamında Java kullanılarak Generic ve Array tabanlı bir Queue implementasyonu geliştirilmiştir.

Temel sınıf:

```java
Queue<T>
```

şeklindedir.

Desteklenen operasyonlar:

```text
enqueue()
dequeue()
peek()
size()
isEmpty()
capacity()
clear()
```

Varsayılan başlangıç kapasitesi:

```text
10
```

olarak belirlenmiştir.

---

# 🔄 Otomatik Kapasite Artırma

Queue'nun dahili dizisi tamamen dolduğunda kapasite otomatik olarak artırılır.

Örneğin:

```text
Capacity = 10
Size     = 10
```

durumunda yeni bir eleman eklenirse kapasite:

```text
10 -> 20
```

şeklinde artırılır.

Eski elemanlar yeni diziye kopyalanır.

Temel mantık:

```java
Object[] newElements =
        new Object[elements.length * 2];

for (int i = 0; i < elements.length; i++) {
    newElements[i] = elements[i];
}

elements = newElements;
```

Bu nedenle `enqueue()` işlemi ortalama olarak:

```text
O(1) Amortized
```

karmaşıklığına sahiptir.

---

# 🔄 Circular Queue Nedir?

Circular Queue, klasik Queue yapısının daha verimli bir Array tabanlı versiyonudur.

Klasik Queue yapısında `dequeue()` sonrasında elemanların sola kaydırılması gerekebilir.

Circular Queue ise bunu yapmaz.

Bunun yerine iki indeks kullanır:

```text
front
rear
```

- `front` → çıkarılacak elemanı
- `rear` → bir sonraki eklenecek konumu

gösterir.

---

# 🔁 Wrap-Around Mekanizması

Circular Queue'nun en önemli özelliği dizinin sonuna gelindiğinde tekrar başa dönebilmesidir.

Örnek:

```text
Capacity = 5

Index
 0    1    2    3    4

[10] [20] [30] [40] [50]
 ↑
front
```

İki eleman çıkarıldığında:

```text
[ ] [ ] [30] [40] [50]
        ↑
      front
```

Yeni elemanlar eklendiğinde boşalan ilk indeksler tekrar kullanılabilir:

```text
[60] [70] [30] [40] [50]
          ↑
        front
```

Mantıksal sıra:

```text
30 -> 40 -> 50 -> 60 -> 70
```

şeklindedir.

Fiziksel dizi sırası farklı olsa bile FIFO davranışı korunur.

---

# 🧮 Modulo Kullanımı

Circular Queue'nun temel mekanizması modulo işlemidir.

`rear` ilerletilirken:

```java
rear = (rear + 1) % elements.length;
```

kullanılır.

`front` ilerletilirken:

```java
front = (front + 1) % elements.length;
```

kullanılır.

Örneğin kapasite:

```text
5
```

ise ve indeks:

```text
4
```

konumundaysa:

```text
(4 + 1) % 5 = 0
```

sonucu elde edilir.

Böylece indeks tekrar dizinin başına döner.

---

# 📌 Front ve Rear

Örnek Circular Queue:

```text
Index
 0    1    2    3    4

[60] [70] [30] [40] [50]
          ↑         ↑
        front      rear
```

`front` mantıksal olarak ilk elemanı temsil eder.

`rear` ise yeni elemanın yazılacağı pozisyonu takip eder.

Son elemanı bulmak için:

```java
(rear - 1 + elements.length) % elements.length
```

hesabı kullanılabilir.

---

# 📦 Circular Queue Resize Mekanizması

Circular Queue tamamen dolduğunda yeni eleman eklemek için kapasite artırılır.

Ancak Circular Queue fiziksel olarak parçalanmış durumda olabilir.

Örnek:

```text
[60] [70] [30] [40] [50]
          ↑
        front
```

Mantıksal sıra:

```text
30
40
50
60
70
```

şeklindedir.

Resize sırasında elemanlar `front` indeksinden başlayarak yeni diziye kopyalanır.

Yeni yapı:

```text
Index

0    1    2    3    4    5    6    7    8    9

[30] [40] [50] [60] [70] [ ]  [ ]  [ ]  [ ]  [ ]
 ↑                       ↑
front                   rear
```

Resize sonrasında:

```text
front = 0
rear = size
```

olarak ayarlanır.

Bu işlem Circular Queue'nun mantıksal sırasını korur.

---

# 🆚 Queue ve Circular Queue Karşılaştırması

| Özellik | Queue | Circular Queue |
|---|:---:|:---:|
| FIFO | ✅ | ✅ |
| Array Tabanlı | ✅ | ✅ |
| Generic | ✅ | ✅ |
| Otomatik Resize | ✅ | ✅ |
| Wrap-Around | ❌ | ✅ |
| Front Index | Sabit 0 | Dinamik |
| Rear Index | Size | Dinamik |
| Dequeue Kaydırması | ✅ | ❌ |
| Dequeue | O(n) | O(1) |
| Boş Alan Yeniden Kullanımı | Sınırlı | ✅ |
| `rear()` | ❌ | ✅ |
| `isFull()` | ❌ | ✅ |

Circular Queue, Array tabanlı Queue implementasyonlarında daha verimli bir yaklaşım sağlar.

---

# 📊 Zaman Karmaşıklıkları

## Queue

| İşlem | Ortalama | En Kötü |
|---|:---:|:---:|
| enqueue() | O(1) Amortized | O(n) |
| dequeue() | O(n) | O(n) |
| peek() | O(1) | O(1) |
| size() | O(1) | O(1) |
| isEmpty() | O(1) | O(1) |
| capacity() | O(1) | O(1) |
| clear() | O(n) | O(n) |

---

## Circular Queue

| İşlem | Ortalama | En Kötü |
|---|:---:|:---:|
| enqueue() | O(1) Amortized | O(n) |
| dequeue() | O(1) | O(1) |
| peek() | O(1) | O(1) |
| rear() | O(1) | O(1) |
| size() | O(1) | O(1) |
| isEmpty() | O(1) | O(1) |
| isFull() | O(1) | O(1) |
| clear() | O(n) | O(n) |

Circular Queue'da `dequeue()` sırasında elemanlar kaydırılmadığı için klasik Array Queue'ya göre önemli bir performans avantajı bulunur.

---

# 📦 Alan Karmaşıklığı

Her iki implementasyon da:

```text
O(n)
```

alan kullanmaktadır.

Queue'nun kapasitesi mevcut eleman sayısından daha büyük olabilir.

Örneğin:

```text
Size     = 15
Capacity = 20
```

şeklinde bir durum mümkündür.

---

# 💻 Queue Kullanım Örneği

```java
Queue<Integer> queue = new Queue<>();

queue.enqueue(10);
queue.enqueue(20);
queue.enqueue(30);

System.out.println(queue.peek());

System.out.println(queue.dequeue());

System.out.println(queue.size());
```

Çıktı:

```text
10
10
2
```

---

# 💻 Circular Queue Kullanım Örneği

```java
CircularQueue<Integer> queue =
        new CircularQueue<>(5);

queue.enqueue(10);
queue.enqueue(20);
queue.enqueue(30);

System.out.println(queue.peek());
System.out.println(queue.rear());

queue.dequeue();
```

---

# 🧪 Test Coverage

Queue ailesi **JUnit 5** kullanılarak kapsamlı şekilde test edilmiştir.

Temel test senaryoları:

- ✅ Empty Queue
- ✅ Enqueue
- ✅ Multiple Enqueue
- ✅ FIFO Behaviour
- ✅ Dequeue
- ✅ Peek
- ✅ Size Control
- ✅ Clear
- ✅ Automatic Resize
- ✅ Multiple Resize
- ✅ Resize Data Preservation
- ✅ Empty Queue Exceptions
- ✅ Null Elements
- ✅ Duplicate Elements
- ✅ Generic Type Support
- ✅ Large Dataset Operations

---

# 🧪 Queue Testleri

Temel Queue implementasyonu için kapsamlı testler uygulanmıştır.

Test edilen senaryolar:

- Queue başlangıç durumu
- Tek eleman ekleme
- Çoklu eleman ekleme
- FIFO sırası
- Dequeue
- Peek
- Resize
- Resize sonrası veri korunması
- Clear
- Clear sonrası tekrar kullanım
- Null eleman
- Duplicate eleman
- Generic veri tipi
- Exception handling
- 1000 elemanlık stres testi

---

# 🔄 Circular Queue Testleri

Circular Queue implementasyonu için ayrıca kapsamlı testler uygulanmıştır.

Toplam:

```text
29 başarılı test
```

bulunmaktadır.

Test edilen senaryolar:

- ✅ Empty Circular Queue
- ✅ Enqueue
- ✅ Multiple Enqueue
- ✅ FIFO Behaviour
- ✅ Dequeue
- ✅ Peek
- ✅ Rear
- ✅ isFull
- ✅ Wrap-Around
- ✅ Multiple Wrap-Around
- ✅ Automatic Resize
- ✅ Resize After Wrap-Around
- ✅ Resize FIFO Preservation
- ✅ Index Reset After Empty
- ✅ Clear
- ✅ Capacity Preservation
- ✅ Null Elements
- ✅ Duplicate Elements
- ✅ Generic Type Support
- ✅ Constructor Validation
- ✅ Empty Queue Exceptions
- ✅ Size Increase
- ✅ Size Decrease
- ✅ Large Dataset Operations

---

# 🧪 Büyük Veri Testi

Queue implementasyonları yalnızca küçük veri kümeleriyle değil, daha büyük veri setleriyle de test edilmiştir.

Örneğin:

```text
1000 element
```

Queue içerisine eklenmiştir.

Daha sonra elemanların tamamı FIFO sırasıyla çıkarılmıştır.

Ekleme:

```text
0
1
2
...
998
999
```

Çıkarma:

```text
0
1
2
...
998
999
```

Bu test Queue ve Circular Queue yapılarının büyük veri miktarlarında da sıra bütünlüğünü koruduğunu doğrulamaktadır.

---

# 📁 Proje Yapısı

```text
src
├── main
│   └── java
│       └── com
│           └── emrebeys
│               └── datastructures
│                   └── queue
│                       ├── Queue.java
│                       ├── QueueDemo.java
│                       ├── CircularQueue.java
│                       └── CircularQueueDemo.java
│
└── test
    └── java
        └── com
            └── emrebeys
                └── datastructures
                    └── queue
                        ├── QueueTest.java
                        └── CircularQueueTest.java
```

---

# ▶️ Demo Applications

Queue ailesinin çalışma mantığını göstermek amacıyla iki farklı demo uygulaması oluşturulmuştur.

```text
QueueDemo.java
CircularQueueDemo.java
```

`QueueDemo` aşağıdaki işlemleri göstermektedir:

- Initial State
- Enqueue
- Dequeue
- FIFO
- Automatic Resize
- Clear

`CircularQueueDemo` ise bunlara ek olarak:

- Front
- Rear
- isFull
- Wrap-Around
- Circular Index Management

davranışlarını göstermektedir.

---

# 🎬 Gerçek Hayattan Örnek

Queue yapısını günlük hayattaki bir sıra gibi düşünebiliriz.

Örneğin bir otobüs durağında:

```text
Otobüs
  ↑

Kişi 1
Kişi 2
Kişi 3
Kişi 4
```

Sıraya ilk giren kişi otobüse ilk binen kişidir.

```text
First In
First Out
```

mantığı burada doğrudan görülür.

---

# 🔄 Circular Queue Gerçek Hayat Örneği

Circular Queue için en iyi örneklerden biri sürekli dönen görev sıralarıdır.

Örneğin:

```text
Process A
   ↓
Process B
   ↓
Process C
   ↓
Process D
   ↓
Process A
```

Bu yapı Round-Robin Scheduling mantığına benzer.

Son göreve gelindiğinde tekrar ilk göreve dönülür.

---

# 🌍 Kullanım Alanları

Queue veri yapıları bilgisayar bilimlerinde birçok farklı alanda kullanılmaktadır.

## Task Scheduling

Görevler geliş sırasına göre çalıştırılabilir.

```text
Task 1
Task 2
Task 3
```

---

## Printer Queue

Birden fazla yazdırma isteği sıraya alınabilir.

```text
Document A
Document B
Document C
```

İlk gönderilen belge önce yazdırılır.

---

## Network Packets

Ağ üzerinden gelen paketler işlenmek üzere Queue içerisinde tutulabilir.

---

## Producer - Consumer

Bir üretici tarafından oluşturulan veriler Queue içerisinde bekletilebilir.

```text
Producer
   |
   ▼
 Queue
   |
   ▼
Consumer
```

---

## Breadth First Search

Graph ve Tree algoritmalarında:

```text
BFS
```

Queue veri yapısından yararlanır.

---

## Operating Systems

Process scheduling ve I/O işlemlerinde Queue kullanılabilir.

---

## Circular Buffers

Ses, video, ağ ve gerçek zamanlı veri akışlarında Circular Queue yaklaşımı kullanılabilir.

---

# 🎓 Sonuç

Queue, bilgisayar bilimlerindeki en temel doğrusal veri yapılarından biridir.

Bu proje kapsamında Java'nın hazır Queue veya Collection implementasyonları kullanılmadan iki farklı Queue yapısı sıfırdan geliştirilmiştir:

- Queue
- Circular Queue

Temel Queue implementasyonu FIFO mantığının basit Array tabanlı gösterimini sağlamaktadır.

Circular Queue ise:

- `front`
- `rear`
- modulo arithmetic
- wrap-around
- boş alanların yeniden kullanılması

mekanizmaları sayesinde Array tabanlı Queue yaklaşımını daha verimli hale getirmektedir.

Bu implementasyonlar sayesinde;

- FIFO prensibi
- Generic Programming
- Array tabanlı veri yapıları
- Dynamic Capacity Management
- Automatic Resize
- Circular Indexing
- Modulo Arithmetic
- Wrap-Around
- Reference Management
- Exception Handling
- Big-O Analizi
- JUnit 5
- Boundary Testing
- Stress Testing
- Clean Code

konuları uygulamalı olarak gösterilmiştir.

Queue ailesi, projenin **Version 0.3.0 — Stack & Queue** aşamasının ikinci bölümünü oluşturmaktadır.

---

# ✅ Status

## Queue

- Queue Implementation ✔️
- Generic Type Support ✔️
- Enqueue ✔️
- Dequeue ✔️
- Peek ✔️
- Dynamic Resize ✔️
- Clear ✔️
- Queue Demo ✔️
- JUnit Tests ✔️

## Circular Queue

- CircularQueue Implementation ✔️
- Enqueue ✔️
- Dequeue ✔️
- Peek ✔️
- Rear ✔️
- Wrap-Around ✔️
- isFull ✔️
- Dynamic Resize ✔️
- Resize After Wrap-Around ✔️
- CircularQueue Demo ✔️
- 29 JUnit Tests ✔️

## Documentation

- Queue Documentation ✔️

**Queue Family:** Completed ✅

**Version:** 0.3.0