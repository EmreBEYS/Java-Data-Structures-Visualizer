# 08 — Graph

## Overview

This module implements a generic graph data structure using an **Adjacency List** architecture.

The implementation supports:

* Directed graphs
* Undirected graphs
* Weighted edges
* Vertices
* Edge management
* Self-loops
* BFS
* DFS
* Cyclic graphs
* Disconnected graphs

---

## Core Classes

```text
graph/
├── Graph.java
├── Vertex.java
├── Edge.java
├── GraphType.java
└── GraphDemo.java
```

Test:

```text
GraphTest.java
```

---

## GraphType

`GraphType` determines how edges behave.

Supported types:

```java
DIRECTED
UNDIRECTED
```

---

# Directed Graph

In a directed graph:

```text
A -> B
```

does not automatically mean:

```text
B -> A
```

Example:

```text
A ----> B
       /
      v
      C
```

Connections have explicit directions.

---

# Undirected Graph

In an undirected graph:

```text
A -- B
```

means both vertices are connected.

Internally this can be represented as:

```text
A -> B
B -> A
```

but it represents one logical edge.

---

## Vertex

`Vertex<T>` represents a graph node.

Structure:

```text
+----------------+
|     Vertex     |
+----------------+
| value          |
+----------------+
```

The vertex can contain any generic Java type.

Examples:

```text
String
Integer
Custom Object
```

---

## Edge

`Edge<T>` represents a connection between two vertices.

Structure:

```text
+----------------------+
|        Edge          |
+----------------------+
| source               |
| destination          |
| weight               |
+----------------------+
```

Example:

```text
A ----5.0----> B
```

Where:

```text
source      = A
destination = B
weight      = 5.0
```

---

## Default Edge Weight

When no weight is specified:

```java
graph.addEdge("A", "B");
```

the default weight is:

```text
1.0
```

Weighted insertion:

```java
graph.addEdge("A", "B", 25.5);
```

---

# Adjacency List

The graph uses an adjacency list rather than an adjacency matrix.

Example graph:

```text
A ----- B
|       |
|       |
C ----- D ----- E
```

Adjacency representation:

```text
A -> B, C
B -> A, D
C -> A, D
D -> B, C, E
E -> D
```

Internally:

```text
Map<Vertex, List<Edge>>
```

Conceptually:

```text
A -> [B, C]
B -> [A, D]
C -> [A, D]
D -> [B, C, E]
E -> [D]
```

---

## Why Adjacency List?

Adjacency lists are particularly effective for sparse graphs.

Space complexity:

```text
O(V + E)
```

Where:

```text
V = number of vertices
E = number of edges
```

An adjacency matrix would require:

```text
O(V²)
```

memory.

---

# Vertex Operations

## Add Vertex

```java
graph.addVertex("A");
```

Duplicate vertices are rejected.

Example:

```java
graph.addVertex("A"); // true
graph.addVertex("A"); // false
```

---

## Remove Vertex

```java
graph.removeVertex("A");
```

Removing a vertex also removes edges connected to that vertex.

Before:

```text
A --- B --- C
```

Remove `B`:

```text
A     C
```

All edges involving `B` are removed.

---

## containsVertex

```java
graph.containsVertex("A");
```

Returns:

```text
true / false
```

---

# Edge Operations

## Add Edge

```java
graph.addEdge("A", "B");
```

Weighted:

```java
graph.addEdge("A", "B", 10.5);
```

Duplicate edges are rejected.

---

## Remove Edge

```java
graph.removeEdge("A", "B");
```

For a directed graph:

```text
A -> B
```

only the specified direction is removed.

For an undirected graph:

```text
A -- B
```

both internal directions are removed.

---

## containsEdge

```java
graph.containsEdge("A", "B");
```

Checks whether a connection exists.

---

## getEdgeWeight

```java
graph.getEdgeWeight("A", "B");
```

Returns the edge's weight.

If no edge exists:

```text
null
```

is returned.

---

# Neighbors

Direct neighbors can be retrieved using:

```java
graph.getNeighbors("A");
```

Example:

```text
A -> B
A -> C
```

Result:

```text
[B, C]
```

---

# Breadth-First Search

Breadth-First Search explores the graph **level by level**.

Abbreviation:

```text
BFS
```

Example:

```text
        A
       / \
      B   C
      |   |
      D---+
      |
      E
```

Possible BFS traversal from `A`:

```text
A -> B -> C -> D -> E
```

---

## BFS Algorithm

BFS uses a queue.

Conceptually:

```text
Add start vertex to queue
          |
          v
Take first vertex
          |
          v
Visit vertex
          |
          v
Add unvisited neighbors
          |
          v
Repeat
```

Data structures:

```text
Queue
Visited Set
Traversal List
```

Pseudo-code:

```text
queue.add(start)
visited.add(start)

while queue is not empty:
    current = queue.remove()

    visit current

    for each neighbor:
        if neighbor is not visited:
            visited.add(neighbor)
            queue.add(neighbor)
```

---

## BFS Complexity

Time:

```text
O(V + E)
```

Space:

```text
O(V)
```

---

# Depth-First Search

Depth-First Search explores one path as deeply as possible before backtracking.

Abbreviation:

```text
DFS
```

Possible traversal:

```text
A -> B -> D -> C -> E
```

---

## DFS Algorithm

The project uses recursive DFS.

Conceptually:

```text
Visit vertex
     |
     v
Mark visited
     |
     v
Take first unvisited neighbor
     |
     v
Recursive DFS
     |
     v
Backtrack
```

Pseudo-code:

```text
dfs(vertex):

    visited.add(vertex)

    visit(vertex)

    for each neighbor:
        if neighbor not visited:
            dfs(neighbor)
```

---

## DFS Complexity

Time:

```text
O(V + E)
```

Space:

```text
O(V)
```

The recursive call stack can also use up to:

```text
O(V)
```

space.

---

# Cycle Handling

Graphs can contain cycles.

Example:

```text
A -> B
^    |
|    v
C <--
```

Without tracking visited vertices, graph traversal could continue forever.

Both BFS and DFS use a visited set.

Example:

```text
visited = {A, B, C}
```

This prevents infinite loops.

---

# Disconnected Graphs

A graph does not need to be fully connected.

Example:

```text
A --- B

C --- D

E
```

Running:

```java
graph.bfs("A");
```

only visits vertices reachable from `A`.

Result:

```text
[A, B]
```

Vertices `C`, `D` and `E` are not visited.

---

# Self-Loops

The implementation supports edges where the source and destination are the same vertex.

Example:

```text
  +---+
  |   |
  v   |
  A---+
```

Created using:

```java
graph.addEdge("A", "A");
```

The self-loop counts as one logical edge.

---

# Edge Count

Directed graph:

```text
A -> B
B -> A
```

contains:

```text
2 edges
```

Undirected graph:

```text
A -- B
```

is physically represented internally by two directions but logically counts as:

```text
1 edge
```

---

# Vertex Count

The number of vertices is available through:

```java
graph.vertexCount();
```

---

# Clear

All vertices and edges can be removed:

```java
graph.clear();
```

After clearing:

```text
vertexCount = 0
edgeCount   = 0
isEmpty     = true
```

The same Graph instance can then be reused.

---

# Example

```java
Graph<String> graph =
        new Graph<>(GraphType.UNDIRECTED);

graph.addVertex("A");
graph.addVertex("B");
graph.addVertex("C");
graph.addVertex("D");

graph.addEdge("A", "B");
graph.addEdge("A", "C");
graph.addEdge("B", "D");

System.out.println(graph.bfs("A"));
System.out.println(graph.dfs("A"));
```

Possible result:

```text
BFS:
[A, B, C, D]

DFS:
[A, B, D, C]
```

---

# Weighted Graph Example

```java
Graph<String> graph =
        new Graph<>(GraphType.DIRECTED);

graph.addVertex("Malatya");
graph.addVertex("Elazığ");
graph.addVertex("Adıyaman");

graph.addEdge(
        "Malatya",
        "Elazığ",
        100.0
);

graph.addEdge(
        "Malatya",
        "Adıyaman",
        110.0
);
```

Adjacency representation:

```text
Malatya -> Elazığ(100.0), Adıyaman(110.0)
Elazığ -> []
Adıyaman -> []
```

---

# Public Operations

The Graph provides:

```text
addVertex()
removeVertex()
containsVertex()

addEdge()
removeEdge()
containsEdge()
getEdgeWeight()

getNeighbors()

bfs()
dfs()

vertexCount()
edgeCount()

isEmpty()
clear()

getGraphType()
```

---

# Testing

The Graph implementation contains **33 successful JUnit tests**.

The test suite covers:

* Empty graph
* Graph type
* Invalid graph type
* Vertex insertion
* Duplicate vertices
* Null vertices
* Vertex removal
* Unknown vertices
* Directed edges
* Undirected edges
* Missing source vertices
* Missing destination vertices
* Duplicate edges
* Weighted edges
* Default edge weights
* Missing edge weights
* Directed edge removal
* Undirected edge removal
* Neighbor retrieval
* BFS
* DFS
* Unknown traversal starting points
* Cyclic graphs
* Disconnected graphs
* Vertex removal with connected edges
* Incoming directed edges
* Self-loops
* Self-loop removal
* Clear
* Reuse after clear

---

# Complexity Summary

| Operation     | Typical Complexity |
| ------------- | -----------------: |
| Add Vertex    |               O(1) |
| Find Vertex   |               O(V) |
| Add Edge      |      O(V + degree) |
| Remove Edge   |      O(V + degree) |
| Remove Vertex |           O(V + E) |
| Get Neighbors |      O(V + degree) |
| BFS           |           O(V + E) |
| DFS           |           O(V + E) |
| Clear         |           O(V + E) |

Because the current educational implementation searches for matching `Vertex` objects, some operations include a linear vertex-search component.

---

# Concepts Demonstrated

This module demonstrates:

* Graph theory fundamentals
* Directed graphs
* Undirected graphs
* Weighted graphs
* Adjacency lists
* Generic graph structures
* Vertex modeling
* Edge modeling
* Graph traversal
* BFS
* DFS
* Queues
* Recursion
* Visited sets
* Cycle detection during traversal
* Self-loops
* Disconnected graphs
* Unit testing

---

# Result

The Graph module completes the main data structure implementation phase of the project.

It provides a reusable educational graph structure capable of representing directed, undirected and weighted relationships while supporting the two fundamental traversal algorithms.

**Status: Completed**

**Demo: Successful**

**Tests: 33/33 Successful**
