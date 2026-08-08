# Java Data Structures Lab

> A comprehensive Java project for learning, implementing and analyzing fundamental data structures from scratch.

---

## 📖 About The Project

**Java Data Structures Lab** is an educational project that implements fundamental data structures from scratch using **Java 21**.

The purpose of this repository is not only to provide working implementations but also to explain how each data structure works, its advantages, disadvantages, memory behavior, and time complexity.

Each data structure includes:

- 📚 Theoretical documentation
- 💻 Java implementation
- 🧪 Unit tests
- ▶️ Interactive console demonstrations
- 📊 Time & Space complexity analysis
- 📝 Detailed documentation

---

## 🚀 Technologies

- Java 21
- Maven
- JUnit 5
- IntelliJ IDEA

---

# 📂 Project Structure

```text
java-data-structures-lab
│
├── docs
│   ├── 01-array.md
│   └── 02-linked-list.md
│
├── images
│
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── emrebeys
│   │               └── datastructures
│   │                   ├── array
│   │                   │   └── DynamicArray.java
│   │                   │
│   │                   ├── linkedlist
│   │                   │   ├── singly
│   │                   │   │   ├── LinkedList.java
│   │                   │   │   ├── LinkedListDemo.java
│   │                   │   │   └── Node.java
│   │                   │   │
│   │                   │   ├── doubly
│   │                   │   │   ├── DoublyLinkedList.java
│   │                   │   │   ├── DoublyLinkedListDemo.java
│   │                   │   │   └── DoublyNode.java
│   │                   │   │
│   │                   │   ├── circularsingly
│   │                   │   │   ├── CircularSinglyLinkedList.java
│   │                   │   │   ├── CircularSinglyLinkedListDemo.java
│   │                   │   │   └── CircularSinglyNode.java
│   │                   │   │
│   │                   │   ├── circulardoubly
│   │                   │   │   ├── CircularDoublyLinkedList.java
│   │                   │   │   ├── CircularDoublyLinkedListDemo.java
│   │                   │   │   └── CircularDoublyNode.java
│   │                   │   │
│   │                   │   ├── skiplist
│   │                   │   │   ├── SkipList.java
│   │                   │   │   ├── SkipListDemo.java
│   │                   │   │   └── SkipListNode.java
│   │                   │   │
│   │                   │   └── unrolled
│   │                   │       ├── UnrolledLinkedList.java
│   │                   │       ├── UnrolledLinkedListDemo.java
│   │                   │       └── UnrolledNode.java
│   │                   │
│   │                   ├── stack
│   │                   ├── queue
│   │                   ├── tree
│   │                   ├── hashmap
│   │                   ├── heap
│   │                   └── graph
│   │
│   └── test
│       └── java
│           └── com
│               └── emrebeys
│                   └── datastructures
│                       ├── array
│                       │   └── DynamicArrayTest.java
│                       │
│                       └── linkedlist
│                           ├── singly
│                           │   └── LinkedListTest.java
│                           │
│                           ├── doubly
│                           │   └── DoublyLinkedListTest.java
│                           │
│                           ├── circularsingly
│                           │   └── CircularSinglyLinkedListTest.java
│                           │
│                           ├── circulardoubly
│                           │   └── CircularDoublyLinkedListTest.java
│                           │
│                           ├── skiplist
│                           │   └── SkipListTest.java
│                           │
│                           └── unrolled
│                               └── UnrolledLinkedListTest.java
│
├── pom.xml
└── README.md
```

---

# 📚 Implemented Data Structures

| Data Structure | Status |
|---|:---:|
| Dynamic Array | ✅ |
| Singly Linked List | ✅ |
| Doubly Linked List | ✅ |
| Circular Singly Linked List | ✅ |
| Circular Doubly Linked List | ✅ |
| Skip List | ✅ |
| Unrolled Linked List | ✅ |
| Stack | ✅ |
| Queue | ✅ |
| Circular Queue | ✅ |
| Binary Search Tree | ⏳ |
| AVL Tree | ⏳ |
| Heap | ⏳ |
| Hash Map | ⏳ |
| Graph | ⏳ |

---

# 🔗 Linked List Family

Version **0.2.0** expands the project with six different Linked List implementations.

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

The Linked List module demonstrates different approaches to node-based data organization.

Covered concepts include:

- Single-direction traversal
- Bidirectional traversal
- Circular structures
- Node references
- Dynamic memory organization
- Generic programming
- Probabilistic multi-level searching
- Block-based node storage
- Node splitting
- Boundary handling
- Exception handling
- Algorithm analysis

---

# 1️⃣ Singly Linked List

Singly Linked List is the simplest node-based linked data structure.

Each node stores:

```text
Value
Next
```

Example:

```text
HEAD
 │
 ▼
[10] -> [20] -> [30] -> null
```

The structure supports traversal in a single direction.

### Implemented Features

- Add elements
- Add first
- Add last
- Insert by index
- Remove elements
- Search
- Access by index
- Clear list
- Size control
- Empty list control

---

# 2️⃣ Doubly Linked List

Doubly Linked List stores references to both the previous and next nodes.

```text
null <- [10] <-> [20] <-> [30] -> null
```

Each node contains:

```text
Previous
Value
Next
```

This allows traversal in both directions.

### Implemented Features

- Forward traversal
- Backward traversal
- Add operations
- Remove operations
- Search operations
- Index access
- Head and tail management
- Generic type support

---

# 3️⃣ Circular Singly Linked List

Circular Singly Linked List connects the last node back to the first node.

```text
      ┌─────────────────┐
      │                 │
      ▼                 │
    [10] -> [20] -> [30]
```

Instead of:

```text
tail.next = null
```

the structure uses:

```text
tail.next = head
```

This structure is useful for cyclic processes such as:

- Round-Robin scheduling
- Turn-based systems
- Circular buffers
- Repeating task lists

---

# 4️⃣ Circular Doubly Linked List

Circular Doubly Linked List combines circular and doubly linked structures.

```text
      ┌─────────────────────────┐
      │                         │
      ▼                         │
    [10] <-> [20] <-> [30]
      ▲                         │
      └─────────────────────────┘
```

The structure maintains:

```text
tail.next = head
head.previous = tail
```

This allows bidirectional circular traversal.

---

# 5️⃣ Skip List

Skip List is a probabilistic data structure that improves search performance by creating multiple traversal levels.

Example:

```text
Level 2: 10 ----------------------> 50
           |                        |
Level 1: 10 --------> 30 --------> 50
           |           |            |
Level 0: 10 -> 20 -> 30 -> 40 -> 50 -> 60
```

Higher levels allow the algorithm to skip multiple nodes during searching.

Average search complexity:

```text
O(log n)
```

### Implemented Features

- `add()`
- `contains()`
- `remove()`
- `first()`
- `last()`
- `size()`
- `currentLevel()`
- `isEmpty()`
- `clear()`

Duplicate values are not allowed.

Default configuration:

```text
Maximum Level = 16
Probability   = 0.5
```

---

# 6️⃣ Unrolled Linked List

Unrolled Linked List stores multiple elements inside each node instead of storing only one element.

Traditional Linked List:

```text
[10] -> [20] -> [30] -> [40]
```

Unrolled Linked List:

```text
Node 1                  Node 2

+----+----+             +----+----+
| 10 | 20 | ----------> | 30 | 40 |
+----+----+             +----+----+
```

This approach can reduce node overhead and improve cache locality.

The default node capacity used in this project is:

```text
4
```

Example:

```text
Node Capacity = 4

+----+----+----+----+
| 10 | 20 | 30 | 40 |
+----+----+----+----+
```

When more space is required, another node can be created.

```text
+----+----+----+----+       +----+----+----+----+
| 10 | 20 | 30 | 40 | ----> | 50 |    |    |    |
+----+----+----+----+       +----+----+----+----+
```

### Implemented Features

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

# 📊 Complexity Analysis

Each implementation includes analysis of:

- Time Complexity
- Space Complexity
- Advantages
- Disadvantages
- Real-world usage examples

## Linked List Complexity Overview

| Operation | Singly | Doubly | Circular | Skip List | Unrolled |
|---|:---:|:---:|:---:|:---:|:---:|
| Add First | O(1) | O(1) | O(1) | - | O(1)* |
| Add Last | O(1)* | O(1)* | O(1)* | O(log n) Avg. | O(1)* |
| Search | O(n) | O(n) | O(n) | O(log n) Avg. | O(n) |
| Index Access | O(n) | O(n) | O(n) | - | O(n) |
| Remove | O(n) | O(n) | O(n) | O(log n) Avg. | O(n) |
| Space | O(n) | O(n) | O(n) | O(n) | O(n) |

> `*` Complexity can depend on implementation details such as tail references, node capacity and node splitting.

Detailed analyses are available inside the `docs/` directory.

---

# 📦 Space Complexity

The general space complexity of the Linked List family is:

```text
O(n)
```

However, memory overhead differs between implementations.

### Singly Linked List

```text
Value + Next
```

### Doubly Linked List

```text
Previous + Value + Next
```

### Skip List

Skip List nodes may contain multiple forward references.

### Unrolled Linked List

Unrolled Linked List reduces node count by storing multiple elements inside each node.

---

# 🧪 Unit Tests

Every data structure is tested using **JUnit 5**.

Tests include scenarios such as:

- Empty structure behavior
- Add element
- Add first
- Add last
- Insert by index
- Remove element
- Remove by index
- Update element
- Search element
- Contains
- IndexOf
- Clear
- Boundary conditions
- Invalid index handling
- Exception handling
- Duplicate handling
- Generic type support
- Large dataset operations

---

## 🔍 Skip List Tests

The Skip List implementation contains a comprehensive JUnit test suite.

```text
31 tests passed
```

Covered scenarios include:

- Add
- Search
- Remove
- Duplicate handling
- First element
- Last element
- Empty list behavior
- Level generation
- Level boundaries
- Comparator behavior
- Reverse comparator
- Constructor validation
- Null value validation
- Invalid probability
- Invalid maximum level
- Large dataset operations

---

## 📦 Unrolled Linked List Tests

The Unrolled Linked List implementation also contains a comprehensive JUnit test suite.

```text
40 tests passed
```

Covered scenarios include:

- Add element
- Add first
- Add last
- Add by index
- Remove by index
- Remove by value
- Get
- Set
- Contains
- IndexOf
- First element
- Last element
- Node capacity
- Node creation
- Node splitting
- Head splitting
- Empty node removal
- Clear
- Null elements
- Duplicate elements
- Invalid indexes
- Boundary conditions
- Large dataset operations

---

# ▶️ Demo Applications

Each Linked List implementation contains its own console demo.

```text
linkedlist
│
├── singly
│   └── LinkedListDemo.java
│
├── doubly
│   └── DoublyLinkedListDemo.java
│
├── circularsingly
│   └── CircularSinglyLinkedListDemo.java
│
├── circulardoubly
│   └── CircularDoublyLinkedListDemo.java
│
├── skiplist
│   └── SkipListDemo.java
│
└── unrolled
    └── UnrolledLinkedListDemo.java
```

Demo applications show how each data structure behaves internally during runtime.

---

# 📖 Documentation

Every major data structure has its own detailed documentation inside the **docs/** directory.

```text
docs/
│
├── 01-array.md
├── 02-linked-list.md
├── 03-stack.md
├── 04-queue.md
├── 05-tree.md
├── 06-heap.md
├── 07-hash-map.md
└── 08-graph.md
```

Currently completed:

- ✅ `01-array.md`
- ✅ `02-linked-list.md`

Upcoming:

- ⏳ `03-stack.md`
- ⏳ `04-queue.md`
- ⏳ `05-tree.md`
- ⏳ `06-heap.md`
- ⏳ `07-hash-map.md`
- ⏳ `08-graph.md`

---

# 🎯 Project Goals

The main goals of this project are:

- Learn data structures from scratch
- Understand how common data structures work internally
- Improve Java programming skills
- Practice Generic Programming
- Practice Object-Oriented Programming
- Understand memory and reference management
- Practice algorithm analysis
- Understand Big-O notation
- Build comprehensive JUnit test suites
- Practice Clean Code principles
- Build reusable implementations
- Build a professional GitHub portfolio project

---

# 📌 Roadmap

## Version 0.1.0 — Array

- [x] Dynamic Array
- [x] Array Demo
- [x] Unit Tests
- [x] Documentation

**Status:** Completed ✅

---

## Version 0.2.0 — Linked List Family

- [x] Singly Linked List
- [x] Doubly Linked List
- [x] Circular Singly Linked List
- [x] Circular Doubly Linked List
- [x] Skip List
- [x] Unrolled Linked List
- [x] Demo Applications
- [x] JUnit Tests
- [x] Documentation

**Status:** Completed ✅

---

## Version 0.3.0 — Stack & Queue

- [x] Stack
- [x] Stack Demo
- [x] Stack Unit Tests
- [x] Queue
- [x] Circular Queue
- [x] Queue Demo
- [x] Circular Queue Demo
- [x] Queue Unit Tests
- [x] Circular Queue Unit Tests
- [x] Documentation

**Status:** Completed ✅
Currently completed:

**Array Module:** Completed ✅  
**Linked List Family:** Completed ✅  
**Stack Module:** Completed ✅  
**Queue Family:** Completed ✅  
**Next Module:** Trees ⏳
---

## Version 0.4.0 — Trees

- [ ] Binary Search Tree
- [ ] AVL Tree
- [ ] Tree Traversal Algorithms
- [ ] Demo Applications
- [ ] Unit Tests
- [ ] Documentation

**Status:** Planned ⏳

---

## Version 0.5.0 — Advanced Structures

- [ ] Heap
- [ ] Hash Map
- [ ] Graph
- [ ] Demo Applications
- [ ] Unit Tests
- [ ] Documentation

**Status:** Planned ⏳

---

## Version 1.0.0

- [ ] Complete Data Structures Library
- [ ] Full Documentation
- [ ] Full Unit Test Suite
- [ ] Complexity Analysis
- [ ] Demo Applications
- [ ] GitHub Release

---

# 📈 Current Progress

```text
Java Data Structures Lab

Array
└── Dynamic Array                     ✅

Linked List
├── Singly Linked List                ✅
├── Doubly Linked List                ✅
├── Circular Singly Linked List       ✅
├── Circular Doubly Linked List       ✅
├── Skip List                         ✅
└── Unrolled Linked List              ✅

Stack                                   ✅
Queue                                   ✅
Tree                                    ⏳
Heap                                    ⏳
Hash Map                                ⏳
Graph                                   ⏳
```

Current development version:

```text
Version 0.3.0
```

---

# 🎓 Learning Outcomes

This project currently demonstrates practical implementation of:

- Arrays
- Dynamic resizing
- Node-based data structures
- Singly linked structures
- Doubly linked structures
- Circular structures
- Probabilistic data structures
- Multi-level searching
- Block-based linked structures
- Node splitting
- Generic programming
- Reference management
- Algorithm analysis
- Time complexity
- Space complexity
- JUnit 5 testing
- Boundary testing
- Exception handling
- Clean Code principles

---

# ✅ Current Status

### Array Family

- DynamicArray Implementation ✔️
- DynamicArray Tests ✔️
- Array Documentation ✔️

### Linked List Family

- Singly Linked List ✔️
- Doubly Linked List ✔️
- Circular Singly Linked List ✔️
- Circular Doubly Linked List ✔️
- Skip List ✔️
- Unrolled Linked List ✔️
- Demo Applications ✔️
- JUnit Tests ✔️
- Documentation ✔️

---

# 🏷️ Version

**Current Version:** `0.2.0`

**Array Module:** Completed ✅  
**Linked List Family:** Completed ✅  
**Next Module:** Stack & Queue ⏳

---

# 👨‍💻 Author

**Yunus Emre KUL**

Computer Engineering Student

Java • Data Structures • Algorithms

---

## ⭐ Support

If you find this project useful, consider giving it a ⭐ on GitHub.