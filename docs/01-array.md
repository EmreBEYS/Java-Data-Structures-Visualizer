# Array (Dizi)

## Amaç

Bu doküman, dizi (Array) veri yapısının çalışma mantığını, zaman karmaşıklıklarını ve bu projede geliştirilen DynamicArray implementasyonunu açıklamak amacıyla hazırlanmıştır.

---

# Array Nedir?

Array (Dizi), aynı veri tipindeki elemanları bellekte ardışık (contiguous) olarak saklayan temel veri yapılarından biridir.

Her elemanın bellekte belirli bir adresi bulunduğu için istenilen elemana indeks numarası ile doğrudan erişilebilir.

Örnek:

```
İndeks

0     1     2     3

+-----+-----+-----+-----+
| 10  | 20  | 30  | 40  |
+-----+-----+-----+-----+
```

Burada;

- 10 → index 0
- 20 → index 1
- 30 → index 2
- 40 → index 3

şeklinde tutulur.

---

# Bellekte Nasıl Çalışır?

Array elemanları bellekte yan yana tutulur.

```
Adres

1000
1004
1008
1012
```

Bu yapı sayesinde;

```
array[2]
```

işlemi doğrudan ilgili bellek adresine gider.

Bu nedenle erişim süresi

```
O(1)
```

olur.

---

# Avantajları

- Çok hızlı erişim sağlar.
- Bellekte düzenli tutulur.
- CPU Cache açısından oldukça verimlidir.
- Uygulaması oldukça basittir.

---

# Dezavantajları

- Boyutu sabittir.
- Araya eleman eklemek maliyetlidir.
- Eleman silmek maliyetlidir.
- Büyük dizilerde yeniden oluşturma gerekebilir.

---

# Dynamic Array Nedir?

Java'daki ArrayList gibi yapılar aslında Dynamic Array mantığı ile çalışır.

Başlangıçta belirli bir kapasite oluşturulur.

Örneğin;

```
Capacity = 2

+----+----+
| 10 | 20 |
+----+----+
```

Yeni eleman geldiğinde;

```
30
```

eklenemez.

Yeni bir dizi oluşturulur.

```
Capacity = 4

+----+----+----+----+
|10  |20  |30  |    |
+----+----+----+----+
```

Eski elemanlar yeni diziye kopyalanır.

---

# Bu Projede Geliştirilen DynamicArray

Bu proje kapsamında Java kullanılarak Generic bir Dynamic Array geliştirilmiştir.

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

# Zaman Karmaşıklıkları

| İşlem | Karmaşıklık |
|-------|-------------|
| get() | O(1) |
| set() | O(1) |
| add() | Ortalama O(1) |
| add(index) | O(n) |
| remove() | O(n) |
| contains() | O(n) |
| indexOf() | O(n) |

---

# Alan Karmaşıklığı

Array veri yapısı

```
O(n)
```

alan kullanır.

---

# Gerçek Hayattan Örnek

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

Array mantığı da aynı şekilde çalışır.

---

# Sonuç

Array, bilgisayar bilimlerinin en temel veri yapılarından biridir.

Bu projede geliştirilen DynamicArray sınıfı sayesinde dizilerin çalışma mantığı sıfırdan uygulanmış, otomatik kapasite artırma mekanizması ve temel veri yapısı işlemleri Java ile gerçekleştirilmiştir.