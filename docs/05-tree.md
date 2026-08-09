# 05 - Tree

Bu bölümde ağaç veri yapılarının temel ve dengeli türleri uygulanmıştır.

Projede iki farklı ağaç yapısı bulunmaktadır:

- Binary Search Tree
- AVL Tree

Her iki yapı için ekleme, arama, minimum-maksimum değer bulma, traversal işlemleri ve silme operasyonları uygulanmıştır.

---

# 1. Binary Search Tree

Binary Search Tree, her düğümün sol tarafında kendisinden küçük, sağ tarafında ise kendisinden büyük değerlerin bulunduğu hiyerarşik bir veri yapısıdır.

Temel yapı:

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

BST kuralları:

```text
Sol Alt Ağaç  < Node
Sağ Alt Ağaç  > Node
```

Projede tekrar eden değerlerin eklenmesine izin verilmemektedir.

---

## 1.1 TreeNode

BST içerisindeki her düğüm `TreeNode` sınıfı ile temsil edilir.

Temel alanlar:

```text
TreeNode
├── value
├── left
└── right
```

- `value`: Düğümün tuttuğu değer
- `left`: Sol çocuk
- `right`: Sağ çocuk

Örnek düğüm yapısı:

```java
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}
```

---

# 2. Binary Search Tree Operasyonları

## 2.1 Insert

Yeni değer BST kurallarına göre uygun konuma yerleştirilir.

Örnek:

```text
Insert:
50, 30, 70, 20, 40
```

Sonuç:

```text
        50
       /  \
     30    70
    / \
   20 40
```

Ekleme sırasında:

- Küçük değerler sola gider.
- Büyük değerler sağa gider.
- Duplicate değerler eklenmez.

Örnek kullanım:

```java
BinarySearchTree tree = new BinarySearchTree();

tree.insert(50);
tree.insert(30);
tree.insert(70);
```

Ortalama zaman karmaşıklığı:

```text
O(log n)
```

En kötü durumda:

```text
O(n)
```

Bu durum ağacın tamamen tek tarafa doğru büyümesiyle oluşabilir.

Örnek:

```text
10
  \
   20
     \
      30
        \
         40
```

---

## 2.2 Contains

Belirtilen değerin ağaç içerisinde bulunup bulunmadığını kontrol eder.

Örnek:

```java
tree.contains(40);
```

Sonuç:

```text
true
```

Olmayan bir değer:

```java
tree.contains(100);
```

Sonuç:

```text
false
```

---

## 2.3 Find Minimum

BST içerisindeki minimum değer ağacın en solundaki düğümdür.

Örnek:

```text
        50
       /
     30
     /
   20
```

Minimum:

```text
20
```

Örnek kullanım:

```java
int min = tree.findMin();
```

---

## 2.4 Find Maximum

BST içerisindeki maksimum değer ağacın en sağındaki düğümdür.

Örnek:

```text
50
  \
   70
     \
      80
```

Maximum:

```text
80
```

Örnek kullanım:

```java
int max = tree.findMax();
```

---

# 3. Tree Traversal

Projede üç farklı traversal yöntemi uygulanmıştır:

- Inorder
- Preorder
- Postorder

Örnek ağaç:

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

---

## 3.1 Inorder Traversal

Sıralama:

```text
Left -> Root -> Right
```

Sonuç:

```text
20 30 40 50 60 70 80
```

BST üzerinde inorder traversal değerleri küçükten büyüğe sıralı şekilde verir.

Örnek kullanım:

```java
tree.inorderTraversal();
```

---

## 3.2 Preorder Traversal

Sıralama:

```text
Root -> Left -> Right
```

Sonuç:

```text
50 30 20 40 70 60 80
```

Örnek kullanım:

```java
tree.preorderTraversal();
```

---

## 3.3 Postorder Traversal

Sıralama:

```text
Left -> Right -> Root
```

Sonuç:

```text
20 40 30 60 80 70 50
```

Örnek kullanım:

```java
tree.postorderTraversal();
```

---

# 4. Binary Search Tree Delete

BST silme işlemi üç farklı durumdan oluşur.

---

## 4.1 Yaprak Düğüm Silme

Çocuğu olmayan düğüm doğrudan silinir.

Önce:

```text
    30
   /
  20
```

`20` silindikten sonra:

```text
30
```

---

## 4.2 Tek Çocuklu Düğüm Silme

Silinen düğümün yerine mevcut çocuğu geçirilir.

Önce:

```text
30
  \
   40
```

`30` silindikten sonra:

```text
40
```

---

## 4.3 İki Çocuklu Düğüm Silme

Bir düğümün hem sol hem sağ çocuğu varsa, sağ alt ağacın minimum değeri successor olarak kullanılır.

Önce:

```text
        50
       /  \
     30    70
          /  \
         60  80
```

`50` silindiğinde sağ alt ağacın minimum değeri:

```text
60
```

Yeni yapı:

```text
        60
       /  \
     30    70
             \
              80
```

---

# 5. Binary Search Tree Yardımcı Metotları

Projede aşağıdaki yardımcı operasyonlar da bulunmaktadır.

## size()

Ağaç içerisindeki toplam düğüm sayısını döndürür.

```java
tree.size();
```

---

## isEmpty()

Ağacın boş olup olmadığını kontrol eder.

```java
tree.isEmpty();
```

---

## clear()

Ağaç içerisindeki bütün düğümleri temizler.

```java
tree.clear();
```

Sonrasında:

```text
root = null
size = 0
```

---

# 6. Binary Search Tree Demo

BST için `BinarySearchTreeDemo` sınıfı hazırlanmıştır.

Demo içerisinde aşağıdaki işlemler çalıştırılmıştır:

- Insert
- Contains
- Minimum
- Maximum
- Inorder
- Preorder
- Postorder
- Yaprak düğüm silme
- Tek çocuklu düğüm silme
- İki çocuklu düğüm silme
- Olmayan değeri silme
- Size kontrolü

Örnek başlangıç ağacı:

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

---

# 7. Binary Search Tree Testleri

BST için toplam:

```text
16 JUnit Test
```

başarıyla çalıştırılmıştır.

Test edilen başlıca senaryolar:

- Tree oluşturma
- Insert
- Multiple insert
- Duplicate kontrolü
- Contains
- Eksik değer arama
- Minimum değer
- Maximum değer
- Leaf node delete
- One child delete
- Two children delete
- Root delete
- Olmayan değeri silme
- Clear
- Empty tree exception
- Single node işlemleri

Test sonucu:

```text
16 / 16 TEST PASSED
```

---

# 8. AVL Tree

AVL Tree, kendi kendini dengeleyen bir Binary Search Tree türüdür.

Normal BST yapısında veriler sıralı şekilde eklenirse ağaç dengesiz hale gelebilir.

Örnek:

```text
10
  \
   20
     \
      30
        \
         40
```

Bu yapı pratikte bağlı liste benzeri bir hale gelir ve arama performansı:

```text
O(n)
```

seviyesine düşebilir.

AVL Tree ise ağacı otomatik olarak dengeler.

Örnek dengeli yapı:

```text
    20
   /  \
 10    30
```

AVL Tree sayesinde temel işlemler dengeli durumda:

```text
O(log n)
```

karmaşıklığında gerçekleştirilebilir.

---

# 9. AVLNode

AVL Tree için kullanılan düğüm yapısında BST düğümüne ek olarak `height` bilgisi bulunmaktadır.

```text
AVLNode
├── value
├── left
├── right
└── height
```

Alanların görevleri:

- `value`: Düğümün değeri
- `left`: Sol çocuk
- `right`: Sağ çocuk
- `height`: Düğümün ağaç içerisindeki yüksekliği

Başlangıçta yeni bir düğümün yüksekliği:

```text
1
```

olarak tutulmaktadır.

---

# 10. AVL Height

Her AVL düğümünün yüksekliği çocuklarının yüksekliğine göre hesaplanır.

Mantık:

```text
height =
1 + max(leftHeight, rightHeight)
```

Örneğin:

```text
      20
     /  \
   10    30
```

Yaprak düğümlerin height değeri:

```text
10 -> 1
30 -> 1
```

Root:

```text
20 -> 2
```

---

# 11. Balance Factor

AVL Tree içerisindeki her düğümün dengesi Balance Factor kullanılarak hesaplanır.

Formül:

```text
Balance Factor =
Height(Left) - Height(Right)
```

Geçerli AVL balance değerleri:

```text
-1
 0
 1
```

Eğer balance factor bu aralığın dışına çıkarsa rotation uygulanır.

---

# 12. AVL Rotations

AVL Tree içerisinde dört temel rotation senaryosu bulunmaktadır.

- LL
- RR
- LR
- RL

---

## 12.1 LL Rotation

Left-Left durumudur.

Önce:

```text
    30
   /
  20
 /
10
```

Right Rotation uygulanır.

Sonuç:

```text
    20
   /  \
 10    30
```

---

## 12.2 RR Rotation

Right-Right durumudur.

Önce:

```text
10
  \
   20
     \
      30
```

Left Rotation uygulanır.

Sonuç:

```text
    20
   /  \
 10    30
```

---

## 12.3 LR Rotation

Left-Right durumudur.

Önce:

```text
    30
   /
  10
    \
     20
```

İlk olarak Left Rotation, ardından Right Rotation uygulanır.

Sonuç:

```text
    20
   /  \
 10    30
```

---

## 12.4 RL Rotation

Right-Left durumudur.

Önce:

```text
10
  \
   30
   /
  20
```

İlk olarak Right Rotation, ardından Left Rotation uygulanır.

Sonuç:

```text
    20
   /  \
 10    30
```

---

# 13. AVL Insert

AVL insert işlemi öncelikle normal BST ekleme mantığını kullanır.

Akış:

```text
Yeni değer
    |
    v
BST Insert
    |
    v
Height Update
    |
    v
Balance Factor
    |
    v
Balance kontrolü
    |
    v
Gerekirse Rotation
```

Duplicate değerler AVL Tree içerisinde de eklenmemektedir.

---

# 14. AVL Search

AVL Tree BST kurallarını koruduğu için değer arama işlemi aynı prensiple gerçekleştirilir.

Örnek:

```java
tree.contains(40);
```

Aranan değer mevcutsa:

```text
true
```

mevcut değilse:

```text
false
```

döndürülür.

Dengeli yapıda zaman karmaşıklığı:

```text
O(log n)
```

---

# 15. AVL Minimum ve Maximum

Minimum değer en sol düğüm üzerinden bulunur.

```text
Minimum -> Leftmost Node
```

Maximum değer ise en sağ düğümdür.

```text
Maximum -> Rightmost Node
```

Örnek kullanım:

```java
tree.findMin();
tree.findMax();
```

---

# 16. AVL Traversal

AVL Tree içerisinde de üç traversal yöntemi bulunmaktadır.

```text
Inorder
Preorder
Postorder
```

AVL bir BST olduğu için inorder traversal yine sıralı çıktı üretir.

Örnek:

```text
20 30 40 50 60 70 80
```

---

# 17. AVL Delete

AVL Tree silme işlemi iki ana aşamadan oluşur.

İlk aşama normal BST delete işlemidir.

İkinci aşamada ağaç tekrar dengelenir.

Genel akış:

```text
Delete
  |
  v
BST Delete
  |
  v
Height Update
  |
  v
Balance Factor
  |
  v
Balance Control
  |
  v
Rotation
```

---

## 17.1 Leaf Delete

Yaprak düğüm doğrudan kaldırılır.

Örnek:

```text
      30
     /  \
   20    40
```

`20` silindiğinde:

```text
30
  \
   40
```

Ardından balance kontrol edilir.

---

## 17.2 One Child Delete

Tek çocuğu olan düğüm silindiğinde yerine çocuğu geçirilir.

---

## 17.3 Two Children Delete

İki çocuklu düğümde sağ alt ağacın minimum değeri successor olarak kullanılır.

---

## 17.4 Delete Sonrası Rebalance

Silme işlemi bazı durumlarda ağacın dengesini bozabilir.

Örneğin:

```text
      30
     /  \
   20    40
  /
10
```

`40` silindiğinde:

```text
      30
     /
   20
  /
10
```

Bu yapı AVL kurallarına göre dengesizdir.

Rotation sonrası:

```text
     20
    /  \
  10    30
```

elde edilir.

---

# 18. AVL Demo

AVL Tree için `AVLTreeDemo` sınıfı hazırlanmıştır.

Demo içerisinde özellikle dört rotation senaryosu ayrı ayrı test edilmiştir:

- LL Rotation
- RR Rotation
- LR Rotation
- RL Rotation

Ayrıca genel AVL ağacı üzerinde:

- Insert
- Contains
- Minimum
- Maximum
- Inorder
- Preorder
- Postorder
- Size
- Root
- Balance Factor

işlemleri çalıştırılmıştır.

---

# 19. AVL Testleri

AVL Tree için toplam:

```text
23 JUnit Test
```

başarıyla tamamlanmıştır.

Test edilen başlıca senaryolar:

- Empty tree
- Single insert
- Multiple insert
- Duplicate kontrolü
- Contains
- Missing value
- Minimum
- Maximum
- Empty tree minimum exception
- Empty tree maximum exception
- LL Rotation
- RR Rotation
- LR Rotation
- RL Rotation
- Height kontrolü
- Genel balance kontrolü
- Leaf node delete
- One child delete
- Two children delete
- Root delete
- Olmayan değeri silme
- Delete sonrası rebalance
- Multiple deletion sonrası balance
- Clear

Test sonucu:

```text
23 / 23 TEST PASSED
```

---

# 20. BST ve AVL Karşılaştırması

| Özellik | Binary Search Tree | AVL Tree |
|---|---|---|
| Insert | Var | Var |
| Search | Var | Var |
| Delete | Var | Var |
| Min / Max | Var | Var |
| Traversal | Var | Var |
| Duplicate kontrolü | Var | Var |
| Otomatik dengeleme | Yok | Var |
| Height bilgisi | Gerekmez | Gerekir |
| Balance Factor | Yok | Var |
| Rotation | Yok | Var |
| Ortalama Search | O(log n) | O(log n) |
| Worst Case Search | O(n) | O(log n) |
| Worst Case Insert | O(n) | O(log n) |
| Worst Case Delete | O(n) | O(log n) |

---

# 21. Zaman Karmaşıklıkları

## Binary Search Tree

| Operasyon | Ortalama | En Kötü |
|---|---:|---:|
| Insert | O(log n) | O(n) |
| Search | O(log n) | O(n) |
| Delete | O(log n) | O(n) |
| Minimum | O(log n) | O(n) |
| Maximum | O(log n) | O(n) |
| Traversal | O(n) | O(n) |

---

## AVL Tree

| Operasyon | Karmaşıklık |
|---|---:|
| Insert | O(log n) |
| Search | O(log n) |
| Delete | O(log n) |
| Minimum | O(log n) |
| Maximum | O(log n) |
| Rotation | O(1) |
| Traversal | O(n) |

---

# 22. Proje Dosya Yapısı

```text
src
├── main
│   └── java
│       └── com
│           └── emrebeys
│               └── datastructures
│                   └── tree
│                       ├── TreeNode.java
│                       ├── BinarySearchTree.java
│                       ├── BinarySearchTreeDemo.java
│                       ├── AVLNode.java
│                       ├── AVLTree.java
│                       └── AVLTreeDemo.java
│
└── test
    └── java
        └── com
            └── emrebeys
                └── datastructures
                    └── tree
                        ├── BinarySearchTreeTest.java
                        └── AVLTreeTest.java
```

---

# 23. Sınıf Sorumlulukları

## TreeNode.java

Binary Search Tree düğüm modelidir.

```text
value
left
right
```

---

## BinarySearchTree.java

BST'nin temel algoritmalarını içerir.

Başlıca operasyonlar:

```text
insert
contains
findMin
findMax
delete
inorderTraversal
preorderTraversal
postorderTraversal
size
isEmpty
clear
```

---

## BinarySearchTreeDemo.java

BST operasyonlarının terminal üzerinde çalıştırıldığı demo sınıfıdır.

---

## BinarySearchTreeTest.java

Binary Search Tree davranışlarının JUnit ile doğrulandığı test sınıfıdır.

Toplam:

```text
16 Test
```

---

## AVLNode.java

AVL Tree düğüm modelidir.

```text
value
height
left
right
```

---

## AVLTree.java

AVL Tree algoritmalarını içerir.

Başlıca operasyonlar:

```text
insert
delete
contains
findMin
findMax
getBalance
inorderTraversal
preorderTraversal
postorderTraversal
size
isEmpty
clear
```

Ayrıca dahili olarak:

```text
rotateLeft
rotateRight
updateHeight
findMinNode
```

gibi yardımcı algoritmalar bulunmaktadır.

---

## AVLTreeDemo.java

AVL Tree'nin temel operasyonları ve dört rotation senaryosunun çalıştırıldığı demo sınıfıdır.

---

## AVLTreeTest.java

AVL Tree algoritmalarının JUnit ile doğrulandığı test sınıfıdır.

Toplam:

```text
23 Test
```

---

# 24. Test Özeti

Tree bölümü içerisinde toplam:

```text
Binary Search Tree : 16 Test
AVL Tree           : 23 Test
--------------------------------
Toplam              : 39 Test
```

Başarı durumu:

```text
39 / 39 TEST PASSED
```

---

# 25. Tree Bölümü Genel Mimarisi

```text
Tree
│
├── Binary Search Tree
│   ├── TreeNode
│   ├── Insert
│   ├── Search
│   ├── Min / Max
│   ├── Delete
│   │   ├── Leaf
│   │   ├── One Child
│   │   └── Two Children
│   ├── Traversal
│   │   ├── Inorder
│   │   ├── Preorder
│   │   └── Postorder
│   ├── Demo
│   └── JUnit Tests
│
└── AVL Tree
    ├── AVLNode
    ├── Height
    ├── Balance Factor
    ├── Insert
    ├── Search
    ├── Min / Max
    ├── Delete
    ├── Rotation
    │   ├── LL
    │   ├── RR
    │   ├── LR
    │   └── RL
    ├── Traversal
    ├── Demo
    └── JUnit Tests
```

---

# 26. Kazanımlar

Bu bölüm ile birlikte aşağıdaki konular uygulanmıştır:

- Hiyerarşik veri yapıları
- Node tabanlı veri organizasyonu
- Binary Search Tree mantığı
- Recursive insert
- Recursive search
- Recursive delete
- Successor kullanımı
- Tree traversal algoritmaları
- Inorder traversal
- Preorder traversal
- Postorder traversal
- Tree height
- AVL Balance Factor
- Self-balancing tree mantığı
- Left Rotation
- Right Rotation
- LL Rotation
- RR Rotation
- LR Rotation
- RL Rotation
- Delete sonrası rebalance
- JUnit ile ağaç algoritmalarının test edilmesi

---

# 27. Sonuç

Bu bölüm ile birlikte projede iki önemli Tree veri yapısı başarıyla tamamlanmıştır.

```text
Tree
├── Binary Search Tree ✅
│   ├── Insert ✅
│   ├── Search ✅
│   ├── Delete ✅
│   ├── Minimum / Maximum ✅
│   ├── Inorder ✅
│   ├── Preorder ✅
│   ├── Postorder ✅
│   ├── Demo ✅
│   └── 16 JUnit Test ✅
│
└── AVL Tree ✅
    ├── Insert ✅
    ├── Search ✅
    ├── Delete ✅
    ├── Minimum / Maximum ✅
    ├── Height ✅
    ├── Balance Factor ✅
    ├── LL Rotation ✅
    ├── RR Rotation ✅
    ├── LR Rotation ✅
    ├── RL Rotation ✅
    ├── Delete Rebalance ✅
    ├── Demo ✅
    └── 23 JUnit Test ✅
```

Toplam:

```text
39 / 39 TEST PASSED
```

Tree bölümü başarıyla tamamlanmıştır.

---

# Sonraki Bölüm

Bir sonraki veri yapısı:

```text
06 - Heap
```

Planlanan yapılar:

```text
Heap
├── Min Heap
└── Max Heap
```
