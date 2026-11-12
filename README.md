# assignment2
CMPT225 assignment2

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
| `appendix.md` | Explains each of the five parts of assignment 2 in detail and what is expected for each part|
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
Sunday, December 4, 2026, at 11:59 PM PDT

## Resources
- [AVL trees](https://www.geeksforgeeks.org/dsa/introduction-to-avl-tree/)
- [Min-heap](https://www.geeksforgeeks.org/dsa/introduction-to-min-heap-data-structure/)
- [Hash table](https://www.geeksforgeeks.org/dsa/hash-table-data-structure/)