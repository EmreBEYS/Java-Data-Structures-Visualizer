# 07 — Hash Map

## Overview

This module implements a custom generic Hash Map from scratch.

The implementation demonstrates the fundamental concepts behind hash-based key-value storage, including:

* Hash functions
* Bucket indexing
* Collision resolution
* Separate chaining
* Load factors
* Dynamic resizing
* Key lookup
* Key-value updates
* Removal operations

The implementation does not depend on Java's built-in `HashMap` for its core storage mechanism.

---

## Core Classes

```text
hashmap/
├── HashNode.java
├── HashMap.java
└── HashMapDemo.java
```

Test:

```text
HashMapTest.java
```

---

## HashNode

`HashNode<K, V>` represents a single key-value pair stored inside the Hash Map.

Each node contains:

```text
key
value
next
```

Conceptually:

```text
+----------------------+
|       HashNode       |
+----------------------+
| key                  |
| value                |
| next                 |
+----------------------+
```

The `next` field is used for collision handling.

---

## Bucket Architecture

The Hash Map maintains an internal array of buckets.

Example:

```text
Index
  |
  v

[0] -> null

[1] -> [A, 100]
          |
          v
       [B, 200]
          |
          v
       [C, 300]

[2] -> [D, 400]

[3] -> null
```

Multiple nodes can exist inside the same bucket.

---

## Collision Handling

A collision happens when multiple keys map to the same bucket.

This implementation resolves collisions using:

**Separate Chaining**

Each bucket acts as the beginning of a linked node chain.

Example:

```text
Bucket 5

Key-A
  |
  v
Key-B
  |
  v
Key-C
```

Each key is still compared using `equals()` before a value is returned.

---

## Bucket Index Calculation

The key's `hashCode()` is used to determine its bucket.

Conceptually:

```text
key
 |
 v
hashCode()
 |
 v
hash spread
 |
 v
bucket index
```

A simplified representation is:

```text
index = hash % capacity
```

The implementation also protects against negative hash values.

---

## Put Operation

`put(K key, V value)` inserts a new key-value pair.

### New Key

```text
put("Java", 100)
```

If the key does not exist:

```text
New HashNode
     |
     v
Target Bucket
```

The size increases.

### Existing Key

If the key already exists:

```text
put("Java", 100)
put("Java", 200)
```

The existing node is updated.

Result:

```text
Java = 200
```

The number of entries does not increase.

---

## Get Operation

`get(K key)`:

1. Calculates the bucket index.
2. Traverses the bucket chain.
3. Compares each key.
4. Returns the matching value.

If no matching key exists:

```text
null
```

is returned.

Average expected complexity:

```text
O(1)
```

Worst case:

```text
O(n)
```

---

## Remove Operation

`remove(K key)` searches the corresponding bucket chain.

Possible cases include:

```text
Remove first node
Remove middle node
Remove last node
Key does not exist
```

Example:

Before:

```text
A -> B -> C
```

Remove `B`:

```text
A -> C
```

The removed value is returned.

---

## containsKey

Checks whether a particular key exists.

Example:

```java
map.containsKey("Java");
```

Result:

```text
true / false
```

---

## containsValue

Searches all buckets and nodes for a matching value.

Because values are not hashed directly, this requires scanning entries.

Typical complexity:

```text
O(n)
```

---

## Load Factor

The load factor represents how full the Hash Map may become before resizing.

Default:

```text
0.75
```

Formula:

```text
size / capacity
```

When:

```text
size / capacity > loadFactor
```

the Hash Map is resized.

---

## Automatic Resizing

Default capacity:

```text
16
```

When the load factor threshold is exceeded:

```text
Old Capacity
     |
     v
capacity * 2
     |
     v
New Bucket Array
```

Existing nodes must then be redistributed because the bucket index depends on capacity.

Example:

```text
Capacity 4
    |
    v
Capacity 8
    |
    v
Capacity 16
```

---

## Rehashing

During resizing, every existing node is processed again.

```text
Old Bucket Array
       |
       v
Read Node
       |
       v
Calculate New Index
       |
       v
Insert Into New Bucket
```

The logical number of entries remains unchanged.

---

## Null Key Support

The custom implementation supports a `null` key.

The null key is mapped to a deterministic bucket.

Example:

```java
map.put(null, 100);
map.get(null);
```

---

## Null Value Support

Null values are also supported.

Example:

```java
map.put("Java", null);
```

This means:

```text
get("Java") == null
```

alone cannot determine whether the key exists.

For this reason:

```java
containsKey("Java")
```

can be used.

---

## Public Operations

The implementation provides:

```text
put()
get()
remove()
containsKey()
containsValue()
size()
isEmpty()
clear()
capacity()
```

---

## Example

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("Java", 100);
map.put("Python", 95);
map.put("Kotlin", 90);

System.out.println(map.get("Java"));

map.put("Java", 110);

map.remove("Python");

System.out.println(map.containsKey("Kotlin"));
System.out.println(map.size());
```

---

## Collision Example

Two different objects can intentionally produce the same hash code.

```text
Key A -> hash 42
Key B -> hash 42
Key C -> hash 42
```

All three can be stored safely:

```text
Bucket
 |
 v
C -> B -> A
```

Their `equals()` implementation is used to distinguish them.

---

## Testing

The Hash Map module contains **24 successful JUnit tests**.

The tests cover:

* Empty Hash Map
* Insert
* Lookup
* Updating an existing key
* Unknown keys
* Key lookup
* Value lookup
* Removal
* Clear
* Null keys
* Null values
* Null-key updates
* Null-key removal
* Automatic resizing
* Data preservation after resizing
* Collision handling
* Removal during collisions
* Custom capacities
* Invalid capacities
* Invalid load factors
* Reuse after clear

---

## Complexity

| Operation     |     Average |       Worst |
| ------------- | ----------: | ----------: |
| Put           |        O(1) |        O(n) |
| Get           |        O(1) |        O(n) |
| Remove        |        O(1) |        O(n) |
| containsKey   |        O(1) |        O(n) |
| containsValue |        O(n) |        O(n) |
| Clear         | O(capacity) | O(capacity) |
| Resize        |        O(n) |        O(n) |

Worst-case behavior can occur when many keys collide into the same bucket.

---

## Concepts Demonstrated

This module demonstrates:

* Hashing
* Hash codes
* Generic key-value storage
* Collision resolution
* Separate chaining
* Linked-node structures
* Load factors
* Dynamic resizing
* Rehashing
* Equality comparison
* Null handling
* Unit testing

---

## Result

The Hash Map implementation provides a functional educational model of a modern hash-table-based key-value structure.

**Status: Completed**

**Tests: 24/24 Successful**
