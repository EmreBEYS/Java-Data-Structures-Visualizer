# Stack (Yığın)

Bu doküman, Stack (Yığın) veri yapısının çalışma mantığını, LIFO prensibini, bellek davranışını, zaman karmaşıklıklarını ve bu proje kapsamında geliştirilen **Generic Stack** implementasyonunu açıklamak amacıyla hazırlanmıştır.

---

# 📑 İçindekiler

- Amaç
- Stack Nedir?
- LIFO Prensibi
- Bellekte Nasıl Çalışır?
- Temel Stack Operasyonları
- Push
- Pop
- Peek
- Avantajları
- Dezavantajları
- Bu Projede Geliştirilen Stack
- Otomatik Kapasite Artırma
- Zaman Karmaşıklıkları
- Alan Karmaşıklığı
- Kullanım Örneği
- Test Coverage
- Proje Yapısı
- Gerçek Hayattan Örnek
- Kullanım Alanları
- Sonuç
- Status

---

# 🎯 Amaç

Bu dokümanın amacı;

- Stack veri yapısının temel mantığını açıklamak
- LIFO çalışma prensibini göstermek
- Stack'in bellekte nasıl organize edildiğini incelemek
- Temel Stack operasyonlarını açıklamak
- Bu projede geliştirilen Java implementasyonunu belgelemek
- Otomatik kapasite artırma mekanizmasını açıklamak
- Zaman ve alan karmaşıklıklarını incelemek
- JUnit 5 test kapsamını göstermek

olarak belirlenmiştir.

---

# 📚 Stack Nedir?

Stack, elemanların belirli bir sıraya göre eklendiği ve çıkarıldığı doğrusal bir veri yapısıdır.

Stack yapısında son eklenen eleman ilk çıkarılan elemandır.

Bu çalışma prensibine:

```text
LIFO
```

adı verilir.

LIFO açılımı:

```text
Last In
First Out
```

şeklindedir.

Türkçe olarak:

```text
Son Giren
İlk Çıkar
```

mantığı ile çalışır.

---

# 🔄 LIFO Prensibi

Örnek olarak Stack'e sırasıyla şu değerlerin eklendiğini düşünelim:

```text
10
20
30
40
```

Stack görünümü:

```text
TOP
 │
 ▼
+----+
| 40 |
+----+
| 30 |
+----+
| 20 |
+----+
| 10 |
+----+
```

Burada en son eklenen eleman:

```text
40
```

olduğu için ilk çıkarılacak eleman da `40` olacaktır.

```text
pop()
```

işleminden sonra:

```text
TOP
 │
 ▼
+----+
| 30 |
+----+
| 20 |
+----+
| 10 |
+----+
```

şeklinde bir yapı oluşur.

---

# 💾 Bellekte Nasıl Çalışır?

Bu projede geliştirilen Stack implementasyonu **Array tabanlıdır**.

Stack elemanları dahili olarak:

```java
Object[] elements;
```

dizisi içerisinde saklanmaktadır.

Ayrıca:

```java
int size;
```

değişkeni Stack içerisindeki mevcut eleman sayısını takip eder.

Örnek:

```text
elements

Index
  0     1     2     3     4

+-----+-----+-----+-----+-----+
| 10  | 20  | 30  |     |     |
+-----+-----+-----+-----+-----+

size = 3
```

Stack'in en üstündeki eleman:

```text
elements[size - 1]
```

konumunda bulunur.

Bu örnekte:

```text
size = 3

size - 1 = 2
```

olduğu için en üstteki eleman:

```text
elements[2] = 30
```

şeklindedir.

---

# ⚙️ Temel Stack Operasyonları

Stack veri yapısında üç temel işlem bulunur:

```text
push()
pop()
peek()
```

Bu projede bunlara ek olarak:

```text
size()
isEmpty()
capacity()
clear()
```

operasyonları da desteklenmektedir.

---

# ➕ Push

`push()` Stack'in üzerine yeni bir eleman ekler.

Örnek:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Sonuç:

```text
TOP
 │
 ▼
30
20
10
```

Array tabanlı yapıda işlem temel olarak:

```java
elements[size] = element;
size++;
```

mantığı ile gerçekleştirilir.

Eğer mevcut dizi tamamen doluysa önce kapasite artırılır.

---

# ➖ Pop

`pop()` Stack'in en üstündeki elemanı kaldırır ve döndürür.

Örnek:

```text
TOP
 │
 ▼
30
20
10
```

```java
stack.pop();
```

işleminden sonra:

```text
TOP
 │
 ▼
20
10
```

olur.

Dönen değer:

```text
30
```

şeklindedir.

Stack boş olduğunda `pop()` çağrılırsa:

```java
IllegalStateException
```

fırlatılır.

Silinen elemanın referansı:

```java
elements[size] = null;
```

ile temizlenir.

Bu işlem gereksiz nesne referanslarının tutulmasını önler.

---

# 👀 Peek

`peek()` Stack'in en üstündeki elemanı döndürür fakat elemanı silmez.

Örnek:

```text
TOP
 │
 ▼
30
20
10
```

```java
stack.peek();
```

sonucu:

```text
30
```

olur.

Ancak Stack değişmez:

```text
30
20
10
```

Bu nedenle `peek()` yalnızca Stack'in üstündeki elemanı görmek için kullanılır.

Stack boş olduğunda `peek()` çağrılırsa:

```java
IllegalStateException
```

fırlatılır.

---

# ✅ Avantajları

- Basit veri yapısına sahiptir
- Push işlemi oldukça hızlıdır
- Pop işlemi oldukça hızlıdır
- Peek işlemi sabit zamanda gerçekleşir
- LIFO tabanlı problemlerde oldukça kullanışlıdır
- Fonksiyon çağrı mekanizmalarında kullanılabilir
- Undo/Redo sistemlerinin temelini oluşturabilir
- Recursive algoritmaların çalışma mantığını anlamaya yardımcı olur
- Generic olarak farklı veri tipleriyle kullanılabilir

---

# ❌ Dezavantajları

- Ortadaki elemanlara doğrudan erişim sunmaz
- Belirli bir elemanı aramak için Stack yapısının tamamı incelenebilir
- LIFO dışındaki erişim senaryoları için uygun değildir
- Array tabanlı implementasyonda kapasite yönetimi gerekir
- Kapasite artışı sırasında elemanların yeni diziye kopyalanması gerekir

---

# 🚀 Bu Projede Geliştirilen Stack

Bu proje kapsamında Java kullanılarak Generic ve Array tabanlı bir Stack yapısı sıfırdan geliştirilmiştir.

Temel sınıf:

```java
Stack<T>
```

şeklindedir.

Desteklenen operasyonlar:

```text
push()
pop()
peek()
size()
isEmpty()
capacity()
clear()
```

Stack başlangıçta varsayılan olarak:

```text
Capacity = 10
```

kapasitesi ile oluşturulur.

---

# 🔄 Otomatik Kapasite Artırma

Stack içerisindeki dahili dizi dolduğunda kapasite otomatik olarak artırılır.

Örnek:

```text
Capacity = 10
Size     = 10
```

Yeni eleman eklenmek istendiğinde mevcut dizide boş yer bulunmaz.

Bu durumda:

```text
10 -> 20
```

şeklinde kapasite iki katına çıkarılır.

---

## Eski Yapı

```text
Capacity = 10

+----+----+----+----+----+----+----+----+----+----+
| 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 |100 |
+----+----+----+----+----+----+----+----+----+----+
```

Yeni eleman:

```text
110
```

---

## Yeni Yapı

```text
Capacity = 20

+----+----+----+----+----+----+----+----+----+----+
| 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 |100 |
+----+----+----+----+----+----+----+----+----+----+

+----+----+----+----+----+----+----+----+----+----+
|110 |    |    |    |    |    |    |    |    |    |
+----+----+----+----+----+----+----+----+----+----+
```

Eski elemanlar yeni diziye kopyalanır.

Temel mekanizma:

```java
private void ensureCapacity() {

    if (size < elements.length) {
        return;
    }

    Object[] newElements =
            new Object[elements.length * 2];

    for (int i = 0; i < elements.length; i++) {
        newElements[i] = elements[i];
    }

    elements = newElements;
}
```

Bu mekanizma sayesinde Stack'in kapasitesi dinamik olarak büyüyebilir.

---

# 📊 Zaman Karmaşıklıkları

| İşlem | En İyi | Ortalama | En Kötü |
|---|:---:|:---:|:---:|
| push() | O(1) | O(1) Amortized | O(n) |
| pop() | O(1) | O(1) | O(1) |
| peek() | O(1) | O(1) | O(1) |
| size() | O(1) | O(1) | O(1) |
| isEmpty() | O(1) | O(1) | O(1) |
| capacity() | O(1) | O(1) | O(1) |
| clear() | O(1) | O(n) | O(n) |

`push()` işleminin en kötü durumda `O(n)` olmasının nedeni kapasite artırılırken mevcut elemanların yeni diziye kopyalanmasıdır.

Ancak her `push()` işleminde resize gerçekleşmediği için ortalama ekleme maliyeti:

```text
O(1) Amortized
```

olarak kabul edilir.

---

# 📦 Alan Karmaşıklığı

Stack veri yapısı eleman sayısına bağlı olarak:

```text
O(n)
```

alan kullanır.

Array tabanlı implementasyonda kapasite eleman sayısından daha yüksek olabilir.

Örneğin:

```text
Size     = 15
Capacity = 20
```

Burada 15 aktif eleman bulunmasına rağmen 20 elemanlık alan ayrılmıştır.

---

# 💻 Kullanım Örneği

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek());

System.out.println(stack.pop());

System.out.println(stack.size());
```

Çıktı:

```text
30
30
2
```

---

# 🧪 Test Coverage

Stack implementasyonu **JUnit 5** kullanılarak kapsamlı şekilde test edilmiştir.

Toplam:

```text
21 başarılı test
```

bulunmaktadır.

Test edilen senaryolar:

- ✅ Empty Stack
- ✅ Push Element
- ✅ Multiple Push
- ✅ LIFO Behaviour
- ✅ Pop
- ✅ Peek
- ✅ Peek Does Not Remove Element
- ✅ Size Increase
- ✅ Size Decrease
- ✅ Automatic Resize
- ✅ Multiple Resize
- ✅ Resize Data Preservation
- ✅ Clear
- ✅ Capacity Preservation After Clear
- ✅ Push After Clear
- ✅ Empty Pop Exception
- ✅ Empty Peek Exception
- ✅ Null Elements
- ✅ Duplicate Elements
- ✅ Generic Type Support
- ✅ Large Dataset Operations

---

# 🧪 Büyük Veri Testi

Stack implementasyonu yalnızca küçük örneklerle değil, daha büyük veri setleriyle de test edilmiştir.

Test kapsamında:

```text
1000 element
```

Stack içerisine eklenmiştir.

Daha sonra tüm elemanlar LIFO sırasına göre çıkarılmıştır.

Örneğin:

```text
Push:

0
1
2
...
998
999
```

Pop sırası:

```text
999
998
997
...
1
0
```

Bu test Stack'in kapasite artırma mekanizmasının ve LIFO davranışının büyük veri miktarlarında da doğru çalıştığını doğrulamaktadır.

---

# 📁 Proje Yapısı

```text
src
├── main
│   └── java
│       └── com
│           └── emrebeys
│               └── datastructures
│                   └── stack
│                       ├── Stack.java
│                       └── StackDemo.java
│
└── test
    └── java
        └── com
            └── emrebeys
                └── datastructures
                    └── stack
                        └── StackTest.java
```

---

# ▶️ Stack Demo

Stack yapısının çalışma mantığını göstermek amacıyla:

```text
StackDemo.java
```

oluşturulmuştur.

Demo uygulaması aşağıdaki işlemleri göstermektedir:

- Başlangıç durumu
- Push işlemleri
- Peek işlemi
- Pop işlemi
- LIFO davranışı
- Otomatik kapasite artırma
- Clear işlemi

Örnek çıktı:

```text
=== STACK DEMO ===

1) Initial State
Size: 0
Capacity: 10
Is Empty: true

2) Push Operations

Pushed: 10
Pushed: 20
Pushed: 30
Pushed: 40

Size: 4
Top Element: 40
```

---

# 🎬 Gerçek Hayattan Örnek

Stack yapısını bir tabak yığını gibi düşünebiliriz.

```text
       TOP

      +-----+
      |Tabak|
      +-----+
      |Tabak|
      +-----+
      |Tabak|
      +-----+
```

Yeni bir tabak geldiğinde yığının üzerine konulur.

```text
push()
```

Bir tabak alınmak istendiğinde ise en üstteki tabak alınır.

```text
pop()
```

Alttaki tabağı almak için önce üzerindeki tabakların kaldırılması gerekir.

Bu davranış Stack'in:

```text
Last In First Out
```

prensibini açık şekilde göstermektedir.

---

# 🌍 Kullanım Alanları

Stack veri yapısı bilgisayar bilimlerinde birçok farklı alanda kullanılır.

## Fonksiyon Çağrıları

Program çalışırken fonksiyon çağrıları bir Call Stack içerisinde tutulabilir.

```text
main()
  |
  └── methodA()
        |
        └── methodB()
```

`methodB()` tamamlandıktan sonra kontrol `methodA()` metoduna döner.

---

## Undo / Redo Sistemleri

Metin editörleri ve grafik programlarında yapılan işlemler Stack mantığıyla saklanabilir.

```text
Action 1
Action 2
Action 3
```

Undo işlemi:

```text
Action 3
```

üzerinden başlayabilir.

---

## Expression Evaluation

Matematiksel ifadelerin işlenmesinde Stack kullanılabilir.

Örneğin:

```text
(10 + 20) * 30
```

gibi ifadelerde operatör ve parantez yönetiminde Stack yapısından yararlanılabilir.

---

## Depth First Search

Graph ve Tree algoritmalarında Depth First Search:

```text
DFS
```

Stack mantığı kullanılarak gerçekleştirilebilir.

---

## Parantez Kontrolü

Örneğin:

```text
{ [ ( ) ] }
```

gibi ifadelerin dengeli olup olmadığı Stack ile kontrol edilebilir.

---

# 🎓 Sonuç

Stack, bilgisayar bilimlerinin en temel doğrusal veri yapılarından biridir.

Bu proje kapsamında Java'nın hazır Stack veya Collection yapıları kullanılmadan, tamamen sıfırdan Generic ve Array tabanlı bir Stack implementasyonu geliştirilmiştir.

Bu implementasyon sayesinde;

- LIFO prensibi
- Generic Programming
- Array tabanlı veri yapıları
- Dynamic Capacity Management
- Automatic Resize
- Reference Cleaning
- Exception Handling
- Big-O Analizi
- JUnit 5
- Boundary Testing
- Stress Testing
- Clean Code

konuları uygulamalı olarak gösterilmiştir.

Stack modülü, projenin **Version 0.3.0 — Stack & Queue** aşamasının ilk tamamlanan veri yapısıdır.

---

# ✅ Status

- Stack Implementation ✔️
- Generic Type Support ✔️
- Push ✔️
- Pop ✔️
- Peek ✔️
- Dynamic Resize ✔️
- Clear ✔️
- Stack Demo ✔️
- 21 JUnit Tests ✔️
- Documentation ✔️

**Stack Module:** Completed ✅

**Version:** 0.3.0