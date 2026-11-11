# assignment2v2
CMPT225 assignment2 version2

For assignment 2, your group builds *CourseReg*, a small course registration system that runs at the command line. Students enroll in courses. When a course is full they join its waitlist, and when someone drops the course, the waitlisted student with the highest priority gets the seat.

*CourseReg* runs on three data structures that you write yourselves, each one doing the job it is best at:

| Data structure | What it stores | Why this one |
|:--|:--|:--|
| AVL tree (Part 1) | the course catalog, keyed by course code | finds a course in O(log n), and can list courses in a range such as CMPT 200 to CMPT 299 |
| Binary min-heap (Part 2) | each course's waitlist | always knows who is next in line |
| Hash table (Part 3) | the students, keyed by student number, and each course's enrolled students | finds a student in O(1) on average |

The command-line app, the `Student` and `Course` classes and the data files are given. You write the three data structures (Parts 1 to 3), the registrar that ties them together (Part 4) and a short report (Part 5). 

## The finished app

Here are some of the lines that `java CourseRegApp data/demo.txt` prints once everything works. First, a range of course codes from the catalog:

```text
> courses CMPT 200 CMPT 299
Courses from CMPT 200 to CMPT 299:
  CMPT 210   Probability and Computing  (0/3 seats)
  CMPT 213   Object Oriented Design in Java  (0/3 seats)
  CMPT 225   Data Structures and Programming  (0/3 seats)
  ...
```

Later, three students take the 3 seats in CMPT 225, and the next four go on its waitlist:

```text
> enroll 301000006 CMPT 225
CMPT 225 is full: 301000006 Ethan Nguyen is on the waitlist (1 waiting).
> enroll 301000003 CMPT 225
CMPT 225 is full: 301000003 Sofia Rossi is on the waitlist (2 waiting).
> enroll 301000012 CMPT 225
CMPT 225 is full: 301000012 Omar Haddad is on the waitlist (3 waiting).
> enroll 301000011 CMPT 225
CMPT 225 is full: 301000011 Hana Sato is on the waitlist (4 waiting).
...
> waitlist CMPT 225
CMPT 225 waitlist (4 waiting):
  1. 301000003 Sofia Rossi (90 credits)
  2. 301000012 Omar Haddad (33 credits)
  3. 301000006 Ethan Nguyen (30 credits)
  4. 301000011 Hana Sato (30 credits)
...
> drop 301000008 CMPT 225
Dropped 301000008 Lucas Martin from CMPT 225.
301000003 Sofia Rossi moved off the waitlist into the free seat.
```

Students with more credits come first on a waitlist. Ethan and Hana both have 30 credits, so Ethan, who asked first, is ahead of Hana.

The app understands these commands. A course code is a subject and a number, such as `CMPT 225`.

| Command | What it does |
|:--|:--|
| `add-student <id> <credits> <name>` | registers a student |
| `add-course <code> <seats> <title>` | adds a course to the catalog |
| `enroll <id> <code>` | enrolls a student, or puts them on the waitlist if the course is full |
| `drop <id> <code>` | drops a course; the top of its waitlist gets the seat |
| `student <id>` | shows a student |
| `course <code>` | shows a course|
| `roster <code>`  | lists a course's students |
| `waitlist <code>` | lists a course's waitlist in priority order | 
| `courses` | lists every course |
| `courses MATH` | lists one subject's courses |
| `courses CMPT 200 CMPT 299` | lists a range of codes, in code order| 
| `stats` | shows the sizes and shapes of the data structures |
| `tree`| draws the catalog's AVL tree|
| `load <file>` | runs the commands in a file |
| `help` | lists the commands |
| `quit` | exits |



## Objectives

- Implementing a binary search tree, keeping it balanced with AVL rotations, and writing the standard tree operations: search, minimum and maximum, in-order and pre-order traversals, and range queries.
- Implementing a priority queue as a binary min-heap stored in an array.
- Implementing a hash table with separate chaining, including resizing.
- Choosing the right data structure for each job, and combining several of them in one application.
- Working as a team: dividing the work, reviewing each other's code, and putting it together (**I bet this is your favourite part!!!**).

## Files

| File | What to do |
|:--|:--|
| `AVLTree.java` | **Part 1.** Complete the methods marked `TODO`. |
| `MinHeap.java` | **Part 2.** Complete the methods marked `TODO`. |
| `HashTable.java` | **Part 3.** Complete the methods marked `TODO`. |
| `WaitlistEntry.java` | **Part 4.** Complete the methods marked `TODO`. |
| `Registrar.java` | Also **Part 4.** Complete the methods marked `TODO`. | 
| `report.md` | **Part 5.** Your group report: fill in the template. |
| `AVLNode.java` | Given. The node classes for the tree |
| `HashNode.java` | Given. The hash table | 
| `Student.java` | Given. A student |
| `Course.java` | Given. a course with its enrolled students and waitlist | 
| `CourseRegApp.java` | Given. The command-line app |
| `SampleTests.java` | Given. 31 quick checks you can run at any time |
| `data/catalog.txt` | Given. 26 courses |
| `data/students.txt` | Given. 18 students |
| `data/demo.txt` | Given. |
| `data/demo_expected.txt` | Given. |

Each method you need to write currently throws an `UnsupportedOperationException`. Replace that line with your code. The given files compile as they are, so you can work on one method at a time. A few public methods are already written and call the private helpers that you write; for example, `valuesInOrder` calls your `inOrder`.

## Assignment Rules
1. **Keep the given interface.** Do not rename the classes or methods, change any parameter or return type, replace the `package` line, or change the given files. Use the given fields. You may add `private` helper methods, but no public ones.
2. **Use your own data structures.** In `AVLTree`, `MinHeap` and `HashTable`, the only things you may use from `java.util` are `List` and `ArrayList` (to build the lists that some methods return) and `NoSuchElementException`. Do **NOT** use `TreeMap`, `HashMap`, `PriorityQueue`, `LinkedList`, `Arrays`, `Collections`, etc. If Part 1, 2, 3, or 4 classes are built on a library collection, it will score 0.
3. **Use your own data structures in Part 4.** `WaitlistEntry` and `Registrar` keep all their data in your `AVLTree`, `MinHeap` and `HashTable`. A `Registrar` that uses a library collection scores 0 for Part 4.
4. **Meet the running times.** Each method must run in the time given in its Javadoc, for example O(log n) for `AVLTree.get` and O(1) for every `size()` and `height()`. I will The include inputs of 100,000 items or more with a time limit of a few seconds when marking your submissions.
5. **No printing.** Your classes must not print anything. The app does all the printing.

## Working as a group

Parts 1, 2 and 3 do not depend on each other, so all three of you can start on the first day. Part 4 needs all three. One way to divide the work:

| Member | Starts with |
|:--|:--|
| A | Part 1: `AVLTree`, the longest data structure |
| B | Part 2: `MinHeap`, then `WaitlistEntry.compareTo` |
| C | Part 3: `HashTable`, then the simple `Registrar` methods: `addStudent`, `addCourse`, `findStudent`, `findCourse`, `studentCount` and `courseCount` |

Then write the rest of Part 4 (`enroll`, `drop` and the listings) together, and finish with testing, a code review in which each member reads a part they did NOT write, and the report.

A schedule that works for three-week deadline:

- **Week 1:** Parts 1 to 3, each passing its checks in `SampleTests`.
- **Week 2:** the rest of Part 4, together; run the demo until your output matches.
- **Week 3:** more testing, code review, and the report.

## Code quality (5 marks)

- Meaningful names and consistent indentation.
- Use the same style across all your files regardless of which team member is writing the code.
- The entire source code should read as if one person wrote it. 
- Short comments where the code is not obvious.
- No leftover debugging output or commented-out code.

## Testing

Run `SampleTests`. It prints `PASS` or `FAIL` for 31 quick checks: 12 for Part 1, 5 for Part 2, 7 for Part 3 and 7 for Part 4. Passing all of them does not guarantee full marks: I will run tests that are much more thorough, and they include large inputs with a time limit.

Run the demo. Once all four parts work, run the demo script and compare its output with the expected output. Every line should match.

Test these cases yourselves:

- Tree: keys put in increasing and in decreasing order; a key put twice; `valuesInRange` with bounds that are not in the tree, with `low > high`, and covering the whole tree; `minKey` on an empty tree.
- Heap: include duplicates; `peek` and `removeMin` on a heap that has just been emptied.
- Hash table: negative hash codes; several keys in the same bucket; removing the first, a middle and the last node of a chain; `capacity()` after 12, 13 and 25 keys.
- Registrar: `drop` when nobody is waiting; two waiting students with the same credits; one student enrolled in several courses.

Also, test the following before submission for each class:
- `AVLTree`: a linked tree of `AVLNode`s, heights kept in the nodes
- `MinHeap`: an array heap that sifts up and down
- `HashTable`: separate chaining, one chain per operation
- `WaitlistEntry`: the right data structure for each job
- `Registrar`: the right data structure for each job

## Marking

| Part | Marks|
|:--|:--:|
| 1. `AVLTree` | 30 |
| 2. `MinHeap` | 15 |
| 3. `HashTable` | 15 |
| 4. `WaitlistEntry` and `Registrar`  | 25 |
| 5. Group report | 10 |
| Code quality | 5 |
| **Total** | x/100 |

## Submission
One member submits for the whole group. Zip the project directory and submit the ZIP file to Canvas.
**Please do not remove any build artifacts or manifest files generated by NetBeans, and do not modify the directory structure. Zip the project directory exactly as it is.**

**Your program must compile and execute successfully. If the program fails to compile or crashes during execution, the submission will receive a grade of zero.**

## Deadline
Sunday, December 7, 2026, at 11:59 PM PDT

!@#

## Part 1: `AVLTree` (30 marks)

`AVLTree<K, V>` is an **ordered map**: it stores values of type `V` under keys of type `K`, kept in key order. In CourseReg the keys are course codes and the values are `Course` objects. Keys are compared with `compareTo` (that is what `K extends Comparable<K>` promises), and you may assume that values are never `null`.

The tree is a **binary search tree**: every key in a node's left subtree is smaller than the node's key, and every key in its right subtree is larger. An AVL tree is also **balanced**: at every node, the heights of the left and right subtrees differ by at most 1.

Why balance matters: `data/catalog.txt` lists its 26 courses in sorted order. A plain binary search tree built from that file would be a single chain, each node the right child of the one before, with height 25, so finding `STAT 270` would take 26 steps. The AVL tree has height 4. For 100,000 sorted keys the difference is 99,999 against 16.

The fields are given:

```java
private AVLNode<K, V> root;   // the root node (null when the tree is empty)
private int size;             // number of keys in the tree
```

Each `AVLNode` has a `key`, a `value`, `left` and `right` links, and a `height`: the height of the subtree rooted at that node. **A leaf has height 0, and an empty subtree (`null`) counts as −1.** Three helpers are given: `height(node)` returns a node's stored height (−1 for `null`), `updateHeight(node)` recomputes it from its children's heights, and `balanceFactor(node)` returns height(left) − height(right).

| Method | What it does |
|:--|:--|
| `int size()`, `boolean isEmpty()` | Returns the number of keys, or whether there are none. |
| `int height()` | Returns the height of the tree: −1 if it is empty, 0 if it has one node. Use the root's stored height. |
| `V get(K key)` | Returns the value stored with `key`, or `null` if `key` is not in the tree. |
| `boolean containsKey(K key)` | Returns `true` if `key` is in the tree. |
| `void put(K key, V value)` | Adds `key` with `value`, or replaces the value if `key` is already there. **Given:** it calls your private `put(node, key, value)`. |
| `K minKey()`, `K maxKey()` | Returns the smallest or largest key. Throws `NoSuchElementException` if the tree is empty. |
| `List<V> valuesInOrder()` | Returns every value in increasing order of their keys. **Given:** it calls your private `inOrder`. |
| `List<K> keysPreOrder()` | Returns every key in pre-order. **Given:** it calls your private `preOrder`. |
| `List<V> valuesInRange(K low, K high)` | Returns the values of the keys from `low` to `high` inclusive, in key order. **Given:** it calls your private `range`. |

You also write the private helpers `put`, `rebalance`, `rotateRight`, `rotateLeft`, `inOrder`, `preOrder` and `range`. The Javadoc of each one says exactly what it must do.

### `put` and `rebalance`

The private `put` is the usual recursive insert into a binary search tree. The only new part is its last line: as the recursion returns, each node on the path from the new leaf back up to the root goes through `rebalance`, which updates its height and fixes it if it has become unbalanced.

```text
put(node, key, value):
    if node is null:
        size++
        return a new AVLNode(key, value)
    if key < node.key:        node.left = put(node.left, key, value)
    else if key > node.key:   node.right = put(node.right, key, value)
    else:                     node.value = value, and return node     (same key: nothing else changes)
    return rebalance(node)

rebalance(node):
    updateHeight(node)
    if balanceFactor(node) > 1:                    the left side is too tall
        if balanceFactor(node.left) < 0:           Left-Right case: first turn it into Left-Left
            node.left = rotateLeft(node.left)
        return rotateRight(node)                   Left-Left case
    if balanceFactor(node) < -1:                   the right side is too tall
        if balanceFactor(node.right) > 0:          Right-Left case: first turn it into Right-Right
            node.right = rotateRight(node.right)
        return rotateLeft(node)                    Right-Right case
    return node
```

Here are the four cases in their smallest form. In each, the third `put` makes the root unbalanced, and the rotations give the same balanced tree:

```text
Left-Left: put 3, 2, 1                 Right-Right: put 1, 2, 3

      3                                    1
     /                                      \
    2     rotateRight(3)      2              2     rotateLeft(1)      2
   /      ------------->     / \              \    ------------>     / \
  1                         1   3              3                    1   3
```

```text
Left-Right: put 3, 1, 2

    3                      3
   /    rotateLeft(1)     /    rotateRight(3)       2
  1     ------------>    2     ------------->      / \
   \                    /                         1   3
    2                  1

Right-Left: put 1, 3, 2

  1                        1
   \     rotateRight(3)     \     rotateLeft(1)     2
    3    ------------->      2    ------------>    / \
   /                          \                   1   3
  2                            3
```

A rotation changes three links; the Javadoc of `rotateRight` and `rotateLeft` has a picture. After relinking, call `updateHeight` on the old root first (it is now lower down) and then on the new root, and return the new root.

### Traversals and ranges

- `inOrder` visits the left subtree, then the node, then the right subtree, so the values come out in key order.
- `preOrder` visits the node, then its left subtree, then its right subtree. The pre-order shows the tree's shape: after putting 1 to 7 in order, it is `[4, 2, 1, 3, 6, 5, 7]`.
- `range(node, low, high, result)` is like `inOrder`, but it skips subtrees that cannot hold a key in the range. If `node.key <= low`, every key in the left subtree is smaller than `low`, so skip it; if `node.key >= high`, skip the right subtree. Add `node.value` only if `low <= node.key <= high`. This visits O(log n + m) nodes when m values are returned. A range that walks the whole tree gets the right answer but loses marks.

> **Hint:** Draw small trees on paper. While debugging, `System.out.print(tree.toTreeString())` draws a tree on its side, with the root at the left (remove the print before you submit). The app's `tree` command shows the same drawing for the catalog.

## Part 2: `MinHeap` (15 marks)

`MinHeap<T>` is a **priority queue**: `removeMin` always removes the smallest item, as decided by `compareTo`. In CourseReg, each course's waitlist is a `MinHeap<WaitlistEntry>`, and `WaitlistEntry.compareTo` (Part 4) makes the student with the highest priority the "smallest".

The heap is a complete binary tree stored in an array, level by level, with no gaps. Every node is smaller than or equal to its children, so the smallest item is always at index 0:

```text
            1                 index:  0   1   2   3   4   5
          /   \               heap:  [1] [3] [2] [7] [4] [9]
         3     2
        / \   /               The children of index i are at 2i + 1 and 2i + 2,
       7   4 9                and its parent is at (i - 1) / 2.
```

The fields are given:

```java
private T[] heap;   // heap[0 .. size-1] hold the items; heap[0] is the smallest
private int size;   // number of items in the heap
```

The array starts with 8 slots. The helpers `parent(i)`, `left(i)`, `right(i)` and `swap(i, j)` are given.

| Method | What it does |
|:--|:--|
| `int size()`, `boolean isEmpty()` | Returns the number of items, or whether there are none. |
| `void add(T item)` | Adds `item`. Throws `IllegalArgumentException` if `item` is `null`. |
| `T peek()` | Returns the smallest item **without removing it**. Throws `NoSuchElementException` if the heap is empty. |
| `T removeMin()` | Removes and returns the smallest item. Throws `NoSuchElementException` if the heap is empty. |
| `List<T> toSortedList()` | Returns every item in increasing order, **without changing the heap**. |

You also write the private helpers `siftUp`, `siftDown` and `grow`.

- **`add`:** if the array is full, call `grow` first. Put the item in the first free slot, `heap[size]`, add 1 to `size`, then sift it up: while it is smaller than its parent, swap the two.
- **`removeMin`:** remember `heap[0]`. Move the last item to index 0, set its old slot to `null` and subtract 1 from `size`. Then sift the root down: while it has a child that is smaller, swap it with the **smaller** of its children.
- **`grow`:** make an array twice as long, the same way the constructor does, and copy the items into it. Doubling, rather than adding a few slots, keeps `add` fast.
- **`toSortedList`:** add every item to a second, new `MinHeap`, then call `removeMin` on that heap until it is empty, collecting the results. This leaves the original heap untouched.

**Example:**

| Operation | Returns | `heap[0 .. size-1]` | What happened |
|:--|:--:|:--|:--|
| `add(5)`, `add(3)`, `add(8)` | | `[3, 5, 8]` | 3 was swapped with its parent 5 |
| `add(1)` | | `[1, 3, 8, 5]` | 1 went in at index 3, then moved up past 5 and then past 3 |
| `removeMin()` | 1 | `[3, 5, 8]` | 5 moved to the root, then swapped with its smaller child 3 |

> **Hint:** In `siftDown`, a node can have two children, only a left child, or none. Check `left(i) < size` and `right(i) < size` before you look at a child.

## Part 3: `HashTable` (15 marks)

`HashTable<K, V>` is a map with no order: it finds the value for a key in O(1) time on average. It uses **separate chaining**: an array of buckets, where each bucket holds a linked list (a "chain") of the `HashNode`s whose keys belong in that bucket. Keys that land in the same bucket simply share a chain:

```text
buckets[0]  ->  null
buckets[1]  ->  ["ann" | 7]  ->  ["kim" | 2]  ->  null     two keys collide here
buckets[2]  ->  ["bo" | 4]  ->  null
   ...
```

The fields are given:

```java
private HashNode<K, V>[] buckets;   // buckets[i] is the first node of chain i (null if empty)
private int size;                   // number of keys in the table
```

A new table has 16 buckets. Each `HashNode` has a `key`, a `value` and a `next` link.

**Which bucket?** Your `indexFor(key)` returns `(key.hashCode() & 0x7fffffff) % buckets.length`. A hash code can be negative (for example, the hash code of the `Integer` −7 is −7), and in Java `-7 % 16` is −7, which is not an array index. The `& 0x7fffffff` clears the sign bit first, so the result is never negative.

| Method | What it does |
|:--|:--|
| `int size()`, `boolean isEmpty()` | Returns the number of keys, or whether there are none. |
| `V get(K key)` | Returns the value stored with `key`, or `null` if `key` is not in the table. |
| `boolean containsKey(K key)` | Returns `true` if `key` is in the table. |
| `void put(K key, V value)` | Adds `key` with `value`, or replaces the value if `key` is already there. Throws `IllegalArgumentException` if `key` is `null`. |
| `V remove(K key)` | Removes `key` and returns its value, or returns `null` if `key` is not in the table. |
| `List<V> values()` | Returns every value, in any order. |
| `int capacity()`, `double loadFactor()` | **Given.** The number of buckets, and size / capacity. |

You also write the private helpers `indexFor` and `resize`.

Each of `get`, `containsKey`, `put` and `remove` looks at **one chain only**: the one in bucket `indexFor(key)`. Compare keys with `equals`, never with `==`. A new node can go at the front or the back of its chain.

**Resizing.** After `put` adds a **new** key (not when it only replaces a value), if `size > 0.75 * capacity`, call `resize`. It makes an array with twice as many buckets and moves every node into it. Each node's bucket must be recomputed with `indexFor`, because the number of buckets has changed.

| Keys in the table | 1 to 12 | 13 to 24 | 25 to 48 | 49 to 96 |
|:--|:--:|:--:|:--:|:--:|
| Buckets | 16 | 32 | 64 | 128 |

> **Hint:** In `remove`, the node may be the first one in its chain (then the bucket itself must point past it) or a later one (then the node before it must skip it). In `resize`, save `node.next` before you link the node into the new array.

## Part 4: The registrar (25 marks)

Part 4 connects your data structures. Two classes are given:

- `Student` has an id (a student number such as `"301000001"`), a name and a number of credits completed.
- `Course` has a code, a title and a number of seats (its capacity), plus three data structures built from your classes:

| `Course` method | Returns |
|:--|:--|
| `getCode()`, `getTitle()`, `getCapacity()` | the course's code, title and number of seats |
| `getEnrolled()` | a `HashTable<String, Student>`: the enrolled students, by id |
| `getWaitlist()` | a `MinHeap<WaitlistEntry>`: the waiting students, highest priority on top |
| `getWaitlisted()` | a `HashTable<String, Student>`: the same waiting students, by id |
| `seatsLeft()`, `isFull()` | the number of free seats, and whether there are none |

Why two structures for one waitlist? The heap finds the next student in line quickly, but it cannot tell quickly whether a given student is waiting; the table can. Your code must keep them in step: whenever a student joins or leaves the waitlist, update both.

### (a) `WaitlistEntry.compareTo`

A `WaitlistEntry` is one student waiting for one course, with a request number: 1 for the first student anyone puts on a waitlist, 2 for the next, and so on. The waitlist priority is:

1. the student with **more credits** comes first;
2. if two students have the same credits, the **earlier request** (smaller request number) comes first.

Since the heap removes the smallest item first, `a.compareTo(b)` must return a negative number when `a` comes before `b`, a positive number when it comes after, and 0 when neither comes first. For example, these four entries come off the waitlist in the order Sofia, Omar, Ethan, Hana:

| Student | Credits | Request number | Position |
|:--|:--:|:--:|:--:|
| Ethan Nguyen | 30 | 1 | 3 |
| Sofia Rossi | 90 | 2 | 1 |
| Omar Haddad | 33 | 3 | 2 |
| Hana Sato | 30 | 4 | 4 |

> **Hint:** `Integer.compare(x, y)` returns a negative number, 0 or a positive number, as `compareTo` should.

### (b) `Registrar`

The fields are given:

```java
private final HashTable<String, Student> students = new HashTable<>();   // every student, by id
private final AVLTree<String, Course> catalog = new AVLTree<>();        // every course, by code
private int nextRequestNumber = 1;   // the request number for the next student put on a waitlist
```

| Method | What it does |
|:--|:--|
| `void addStudent(String id, String name, int credits)` | Registers a new student. Throws `IllegalArgumentException` if `credits < 0` or a student with this id already exists. |
| `void addCourse(String code, String title, int capacity)` | Adds a new course to the catalog. Throws `IllegalArgumentException` if `capacity < 1` or a course with this code already exists. |
| `Student findStudent(String id)` | Returns the student with this id, or `null`. |
| `Course findCourse(String code)` | Returns the course with this code, or `null`. |
| `int studentCount()`, `int courseCount()` | Returns the number of students, or of courses. |
| `boolean enroll(String studentId, String courseCode)` | If the course has a free seat, enrolls the student and returns `true`. Otherwise puts the student on the waitlist, with the next request number, in both the heap and the `waitlisted` table, and returns `false`. |
| `Student drop(String studentId, String courseCode)` | Removes the student from the course. If the waitlist is not empty, the waitlisted student with the highest priority leaves the waitlist (heap and table), takes the free seat and is returned. Otherwise returns `null`. |
| `List<Course> listCourses()` | Returns every course, in order of course code. |
| `List<Course> listCourses(String fromCode, String toCode)` | Returns the courses with `fromCode <= code <= toCode`, in order of course code. |
| `List<Student> roster(String courseCode)` | Returns the students enrolled in the course, in any order. |
| `List<WaitlistEntry> waitlist(String courseCode)` | Returns the course's waitlist in priority order, **without changing it**. |

The errors that `enroll`, `drop`, `roster` and `waitlist` throw:

| Situation | Exception |
|:--|:--|
| `enroll` or `drop` with an unknown student or course; `roster` or `waitlist` with an unknown course | `IllegalArgumentException` |
| `enroll` for a student who is already enrolled in the course, or already on its waitlist | `IllegalStateException` |
| `drop` for a student who is not enrolled in the course, including a student who is only on its waitlist | `IllegalStateException` |

Two rules apply to every method:

- **Check, then change.** Do all the checks first and throw before you change anything, so a method that throws leaves everything as it was. In `enroll`, check "already enrolled" and "already waitlisted" **before** you look for a free seat.
- **Use the right structure for each job.** Find students with `students`, courses with `catalog`, check who is enrolled or waiting with the course's tables, and take the next student with the heap's `removeMin`. A loop that searches for one item loses marks, and the reference solution needs no loops at all.

**Example:** CMPT 225 has 3 seats.

| Call | Returns | Enrolled | Waitlist, in priority order |
|:--|:--|:--|:--|
| `enroll` Liam (12 credits) | `true` | Liam | |
| `enroll` Noah (30 credits) | `true` | Liam, Noah | |
| `enroll` Lucas (0 credits) | `true` | Liam, Noah, Lucas | |
| `enroll` Ethan (30 credits) | `false` | Liam, Noah, Lucas | Ethan |
| `enroll` Sofia (90 credits) | `false` | Liam, Noah, Lucas | Sofia, Ethan |
| `enroll` Noah again | throws `IllegalStateException` | (no change) | (no change) |
| `drop` Lucas | Sofia | Liam, Noah, Sofia | Ethan |
| `drop` Ethan | throws `IllegalStateException`: he is only waiting | (no change) | (no change) |

> **Hint:** Two private helpers, `requireStudent(id)` and `requireCourse(code)`, that look the item up and throw `IllegalArgumentException` if it is missing, make `enroll`, `drop`, `roster` and `waitlist` much shorter.

## Part 5: Group report (10 marks)

Fill in the template in `README.md` (one or two pages):

| Section | Marks | What to write |
|:--|:--:|:--|
| Contributions | 2 | Who wrote and who tested each part, agreed by every member |
| Running times | 3 | The Big-O running time of each operation in the table |
| Design questions | 3 | Three short answers about why each data structure suits its job |
| Testing and known problems | 2 | What you tested beyond the given checks, and anything that does not work |


