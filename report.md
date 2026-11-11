# Report

Fill in every section, then submit this file with your code. Keep the whole report to one or two pages.

## Group members and contributions

| Name | Student number | Code they wrote | What they tested or reviewed |
|:--|:--|:--|:--|
| Joe Doe| 123456789 | AVL class | program output and HashTable class |
| | | | |
| | | | |


## Running times

Give each running time in Big-O. For the data structures, *n* is the number of items stored. For `enroll` and `drop`, use *c* for the number of courses and *w* for the number of students on that course's waitlist.

| Operation | Running time |
|:--|:--|
| `AVLTree.put` | |
| `AVLTree.get` | | 
| `AVLTree.containsKey` | |
| `AVLTree.valuesInOrder` | |
| `AVLTree.valuesInRange`| |
| `MinHeap.add` | |
| `MinHeap.removeMin` | |
| `MinHeap.peek` | |
| `MinHeap.toSortedList` | |
| `HashTable.put` | |
| `HashTable.get` | |
| `HashTable.remove` | |
| `HashTable.get`  | |
| `Registrar.enroll` | |
| `Registrar.drop` | |

## 3. Design questions

Answer each question in two to four sentences.

1. The catalog is an AVL tree, but the students are kept in a hash table. Why is each one the better choice for its job? Name one thing the `courses` command does that a hash table could not do efficiently.

2. `data/catalog.txt` lists the courses in sorted order. What height would a plain (unbalanced) binary search tree have after loading it? What height does your AVL tree have (run `stats`)? Why does the difference matter for `enroll`?

3. Each course keeps its waitlist in a heap and also in a hash table (`waitlisted`). What does each of the two do efficiently that the other cannot?

## 4. Testing and known problems

- What did you test beyond `SampleTests` and the demo? Describe at least three specific cases.
- Does anything not work, or work only partly? Say so here.

## 5. Outside help

Say whether you used AI tools or any other outside help, and how.
