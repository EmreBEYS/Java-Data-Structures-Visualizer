# Java Data Structures Visualizer

A Java-based educational project that implements fundamental and advanced data structures from scratch.

The main goal of this project is to understand how commonly used data structures work internally instead of relying only on Java Collection Framework implementations.

Each data structure includes:

* Clean Java implementation
* Generic type support
* Demonstration classes
* JUnit 5 tests
* Edge-case coverage
* Technical documentation
* Practical usage examples

---

## Project Status

**Completed**

The core data structures planned for this project have been implemented and tested successfully.

---

## Implemented Data Structures

### 01 — Dynamic Array

Custom dynamic array implementation with automatic capacity management.

Features:

* Generic type support
* Automatic resizing
* Add
* Get
* Set
* Remove
* Search
* Clear
* Size and capacity management

Documentation:

`docs/01-array.md`

---

### 02 — Linked List Family

Multiple linked-list implementations were developed to demonstrate different node-linking strategies.

Implemented structures:

* Singly Linked List
* Doubly Linked List
* Circular Singly Linked List
* Circular Doubly Linked List
* Skip List
* Unrolled Linked List

Features include:

* Insertion
* Removal
* Search
* Traversal
* Indexed operations
* Circular traversal
* Multi-level search structures

Documentation:

`docs/02-linked-list.md`

---

### 03 — Stack

LIFO-based stack implementation.

Features:

* Push
* Pop
* Peek
* Size
* Clear
* Empty-state validation
* Generic element support

Documentation:

`docs/03-stack.md`

---

### 04 — Queue

FIFO-based queue implementations.

Implemented structures:

* Queue
* Circular Queue

Features:

* Enqueue
* Dequeue
* Peek
* Circular index management
* Capacity control
* Empty/full state validation

Documentation:

`docs/04-queue.md`

---

### 05 — Tree

Tree-based data structures for hierarchical storage and ordered searching.

Implemented structures:

* Binary Search Tree
* AVL Tree

Features:

* Insert
* Search
* Delete
* Minimum / Maximum
* Tree height
* Traversals

    * Inorder
    * Preorder
    * Postorder
* AVL balancing
* Left rotation
* Right rotation
* Double rotations

Documentation:

`docs/05-tree.md`

---

### 06 — Heap

Binary heap implementations.

Implemented structures:

* Min Heap
* Max Heap

Features:

* Insert
* Peek
* Extract
* Heapify
* Parent/child index management
* Dynamic heap operations

Documentation:

`docs/06-heap.md`

---

### 07 — Hash Map

Custom hash table implementation using **Separate Chaining**.

Features:

* Generic key/value support
* `put()`
* `get()`
* `remove()`
* `containsKey()`
* `containsValue()`
* Collision handling
* Automatic resizing
* Load factor management
* Null key support
* Null value support
* Custom initial capacity
* Clear operation

Collision resolution is implemented using linked nodes inside each bucket.

Documentation:

`docs/07-hash-map.md`

---

### 08 — Graph

Adjacency-list-based graph implementation.

Supported graph types:

* Directed Graph
* Undirected Graph

Features:

* Vertex insertion
* Vertex removal
* Edge insertion
* Edge removal
* Weighted edges
* Self-loops
* Neighbor retrieval
* Vertex count
* Edge count
* Breadth-First Search
* Depth-First Search
* Cycle-safe traversal
* Disconnected graph handling

Traversal algorithms:

* BFS — Breadth-First Search
* DFS — Depth-First Search

Documentation:

`docs/08-graph.md`

---

## Project Structure

```text
Java-Data-Structures-Visualizer/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── array/
│   │       ├── linkedlist/
│   │       ├── stack/
│   │       ├── queue/
│   │       ├── tree/
│   │       ├── heap/
│   │       ├── hashmap/
│   │       └── graph/
│   │
│   └── test/
│       └── java/
│           ├── array/
│           ├── linkedlist/
│           ├── stack/
│           ├── queue/
│           ├── tree/
│           ├── heap/
│           ├── hashmap/
│           └── graph/
│
├── docs/
│   ├── 01-array.md
│   ├── 02-linked-list.md
│   ├── 03-stack.md
│   ├── 04-queue.md
│   ├── 05-tree.md
│   ├── 06-heap.md
│   ├── 07-hash-map.md
│   └── 08-graph.md
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## Technologies

* Java
* JDK 21
* Maven
* JUnit 5
* IntelliJ IDEA
* Git
* GitHub

---

## Testing

The project contains dedicated JUnit tests for each implemented data structure.

Tests cover:

* Normal operations
* Boundary conditions
* Empty structures
* Duplicate values
* Invalid operations
* Resizing
* Collision handling
* Tree balancing
* Graph cycles
* Directed and undirected graph behavior
* Null handling where supported

Recent implementations include:

* Hash Map — **24 passing tests**
* Graph — **33 passing tests**

---

## Hash Map Architecture

The custom Hash Map uses an array of buckets.

Each bucket can contain multiple nodes when different keys produce the same bucket index.

```text
Bucket Array

[0] -> null

[1] -> Node(A)
          |
          v
       Node(B)
          |
          v
       Node(C)

[2] -> Node(D)

[3] -> null
```

This technique is known as:

**Separate Chaining**

When the configured load factor is exceeded, the bucket array is resized and existing entries are redistributed.

---

## Graph Architecture

The Graph implementation uses an adjacency list.

Example:

```text
A -> B, C
B -> A, D
C -> A, D
D -> B, C, E
E -> D
```

Conceptually:

```text
A ----- B
|       |
|       |
C ----- D ----- E
```

The same graph structure can operate as either:

```text
DIRECTED
```

or:

```text
UNDIRECTED
```

---

## BFS

Breadth-First Search explores vertices level by level.

Example:

```text
A -> B -> C -> D -> E
```

Main structures used internally:

* Queue
* Visited Set

Typical complexity:

```text
O(V + E)
```

---

## DFS

Depth-First Search explores one branch as deeply as possible before backtracking.

Example traversal:

```text
A -> B -> D -> C -> E
```

The implementation uses recursive traversal and a visited set.

Typical complexity:

```text
O(V + E)
```

---

## Learning Objectives

This project was created to strengthen understanding of:

* Memory-oriented data structure design
* Generic Java programming
* Algorithm implementation
* Node-based structures
* Dynamic resizing
* Collision resolution
* Recursive algorithms
* Tree balancing
* Graph modeling
* BFS and DFS
* Unit testing
* Object-oriented design
* Clean project architecture

---

## Running the Project

Clone the repository:

```bash
git clone https://github.com/EmreBEYS/Java-Data-Structures-Visualizer.git
```

Enter the project directory:

```bash
cd Java-Data-Structures-Visualizer
```

Run the test suite:

```bash
mvn test
```

Or open the project with IntelliJ IDEA and run individual demo or test classes.

---

## Repository

GitHub:

`EmreBEYS/Java-Data-Structures-Visualizer`

---

## License

This project is intended primarily for educational and portfolio purposes.

---

## Final Notes

Java Data Structures Visualizer demonstrates the internal implementation of several fundamental computer science data structures without depending on Java's built-in collection implementations for the core algorithms.

The project covers structures ranging from dynamic arrays and linked lists to balanced trees, hash tables and graphs, with dedicated tests and documentation for each major module.

**Project Status: Completed**
