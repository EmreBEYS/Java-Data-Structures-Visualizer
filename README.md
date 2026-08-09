# Java Data Structures Visualizer

An educational project focused on implementing, testing, and documenting fundamental and advanced data structures from scratch in Java.

Instead of only relying on the Java Collections Framework, this project aims to explore the **internal logic**, algorithms, and time complexities behind common data structures through hands-on implementations.

---

## Project Goals

- Implement data structures from scratch
- Understand how core algorithms work internally
- Improve Java and OOP skills
- Write automated tests with JUnit
- Validate edge-case scenarios
- Strengthen algorithmic thinking
- Document each data structure clearly
- Expand the project with a visualization layer in later stages

---

## Technologies Used

```text
Java 21
Maven
JUnit 5
IntelliJ IDEA
Git
GitHub
```

---

# Project Status

| Section | Data Structure | Status |
|---|---|---|
| 01 | Array | ✅ Completed |
| 02 | Linked List Family | ✅ Completed |
| 03 | Stack | ✅ Completed |
| 04 | Queue | ✅ Completed |
| 05 | Tree | ✅ Completed |
| 06 | Heap | ✅ Completed |
| 07 | Hash Map | ⏳ Next |
| 08 | Graph | ⏳ Planned |

---

# Implemented Data Structures

## 01 - Array

A dynamic array implementation was developed from scratch.

```text
Array
└── Dynamic Array
```

Main topics:

- Dynamic capacity
- Automatic resizing
- Insert
- Remove
- Get / Set
- Search
- Size management

Detailed documentation:

```text
docs/01-array.md
```

---

## 02 - Linked List Family

Different types of linked lists were implemented.

```text
Linked List
├── Singly Linked List
├── Doubly Linked List
├── Circular Singly Linked List
├── Circular Doubly Linked List
├── Skip List
└── Unrolled Linked List
```

This section covers:

- Node-based data organization
- Forward and backward links
- Circular references
- Skip-level logic
- Block-based linked list design
- Insert / remove / search
- Traversal

Detailed documentation:

```text
docs/02-Linked-list.md
```

---

## 03 - Stack

A Stack data structure based on the LIFO principle was implemented.

```text
Last In
First Out
```

Main operations:

```text
push
pop
peek
size
isEmpty
clear
```

The implementation was verified with demo scenarios and JUnit tests.

Detailed documentation:

```text
docs/03-stack.md
```

---

## 04 - Queue

Queue structures based on the FIFO principle were implemented.

```text
First In
First Out
```

Implemented structures:

```text
Queue
├── Queue
└── Circular Queue
```

Main topics:

- Enqueue
- Dequeue
- Peek
- Circular indexing
- Overflow / underflow checks
- Size management

Detailed documentation:

```text
docs/04-queue.md
```

---

## 05 - Tree

The Tree section contains two important search tree implementations.

```text
Tree
├── Binary Search Tree
└── AVL Tree
```

### Binary Search Tree

Implemented features:

- Insert
- Search
- Delete
- Minimum
- Maximum
- Inorder Traversal
- Preorder Traversal
- Postorder Traversal
- Duplicate prevention
- Clear

JUnit tests:

```text
16 Tests
```

### AVL Tree

The AVL Tree implementation introduces self-balancing tree behavior.

```text
AVL Tree
├── Height
├── Balance Factor
├── LL Rotation
├── RR Rotation
├── LR Rotation
└── RL Rotation
```

Additional features:

- Rebalancing after insertion
- Rebalancing after deletion
- Search
- Min / Max
- Traversal
- Height validation

JUnit tests:

```text
23 Tests
```

Total Tree tests:

```text
39 Tests
```

Detailed documentation:

```text
docs/05-tree.md
```

---

## 06 - Heap

The Heap section includes Min Heap and Max Heap implementations.

```text
Heap
├── Min Heap
└── Max Heap
```

### Min Heap

Rule:

```text
Parent <= Children
```

Main operations:

- Insert
- Peek
- Extract Min
- Heapify Up
- Heapify Down
- Contains
- Clear

### Max Heap

Rule:

```text
Parent >= Children
```

Main operations:

- Insert
- Peek
- Extract Max
- Heapify Up
- Heapify Down
- Contains
- Clear

The heap implementations are based on `ArrayList<Integer>`.

Index relationships:

```text
Parent      = (index - 1) / 2
Left Child  = (2 * index) + 1
Right Child = (2 * index) + 2
```

JUnit test classes:

```text
MinHeapTest -> 13 Tests
MaxHeapTest -> 13 Tests
```

Detailed documentation:

```text
docs/06-heap.md
```

---

# Project Structure

```text
Java-Data-Structures-Visualizer
│
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── emrebeys
│   │               └── datastructures
│   │                   ├── array
│   │                   ├── linkedlist
│   │                   ├── stack
│   │                   ├── queue
│   │                   ├── tree
│   │                   └── heap
│   │
│   └── test
│       └── java
│           └── com
│               └── emrebeys
│                   └── datastructures
│                       ├── array
│                       ├── linkedlist
│                       ├── stack
│                       ├── queue
│                       ├── tree
│                       └── heap
│
├── docs
│   ├── 01-array.md
│   ├── 02-Linked-list.md
│   ├── 03-stack.md
│   ├── 04-queue.md
│   ├── 05-tree.md
│   └── 06-heap.md
│
├── pom.xml
├── README.md
└── LICENSE
```

---

# Algorithmic Approach

The project avoids treating built-in Java collections as black boxes. Instead, the core logic behind each structure is implemented manually whenever appropriate.

Example: AVL Tree insertion

```text
Insert
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
Rotation
```

Example: Heap insertion

```text
Insert
   |
   v
Append to array
   |
   v
heapifyUp
```

Example: Heap extraction

```text
Remove root
   |
   v
Move last element to root
   |
   v
heapifyDown
```

This approach is intended to make the internal behavior of each data structure easier to understand.

---

# Testing Strategy

JUnit tests are written for each data structure.

The tests cover not only normal usage scenarios but also edge cases.

Example scenarios:

- Empty structure
- Single element
- Multiple elements
- Duplicate values
- Negative values
- Invalid operations
- Delete edge cases
- Clear
- Size validation
- Tree rotations
- Tree rebalancing
- Heap ordering

---

# Time Complexity Examples

| Data Structure | Operation | Complexity |
|---|---|---:|
| BST | Average Search | O(log n) |
| BST | Worst Case Search | O(n) |
| AVL | Search | O(log n) |
| AVL | Insert | O(log n) |
| AVL | Delete | O(log n) |
| Heap | Peek | O(1) |
| Heap | Insert | O(log n) |
| Heap | Extract | O(log n) |
| Heap | Contains | O(n) |

---

# Roadmap

Completed sections:

```text
Array        ✅
Linked List  ✅
Stack        ✅
Queue        ✅
Tree         ✅
Heap         ✅
```

Upcoming sections:

```text
Hash Map     ⏳
Graph        ⏳
```

After the main data structure implementations are completed, the visualization layer will be expanded further.

---

# Learning Outcomes

Throughout the project, the following topics are practiced:

- Java OOP
- Data structure design
- Array management
- Node-based structures
- Recursive algorithms
- Tree traversal
- Binary Search Trees
- Self-balancing trees
- AVL rotations
- Complete Binary Trees
- Heap algorithms
- Time complexity analysis
- Edge-case analysis
- Unit testing
- Maven project management
- Git and GitHub workflow

---

# Documentation

Each main data structure is documented in a separate Markdown file.

```text
docs/
├── 01-array.md
├── 02-Linked-list.md
├── 03-stack.md
├── 04-queue.md
├── 05-tree.md
└── 06-heap.md
```

These files include:

- How each data structure works
- Core algorithms
- Example structures
- Implemented methods
- Time complexities
- Demo scenarios
- Test coverage

---

# Next Goal

The next section is:

```text
07 - Hash Map
```

After that:

```text
08 - Graph
```

Once these are completed, the core data structure implementation phase of the project will be finished.

---

## Author

**Yunus Emre KUL**

GitHub: `EmreBEYS`
