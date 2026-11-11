import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SampleTests: quick checks for the CourseReg group project. Given: do not change this file.
 * Each check prints PASS or FAIL. Passing every check does NOT guarantee full
 * marks: the marking tests are much more thorough. If the program seems to
 * hang, the check printed last is stuck in an endless loop.
 */
public class SampleTests {

    private static int passed = 0;
    private static int total = 0;

    interface Check {
        void run() throws Exception;
    }

    public static void main(String[] args) {
        System.out.println("Part 1: AVLTree");
        check("A new tree is empty", () -> {
            AVLTree<Integer, String> t = new AVLTree<>();
            expect(0, t.size(), "size()");
            expect(true, t.isEmpty(), "isEmpty()");
            expect(-1, t.height(), "height()");
            expect(null, t.get(5), "get(5)");
        });
        check("put, get and containsKey", () -> {
            AVLTree<Integer, String> t = tree(20, 10, 30);
            expect("v10", t.get(10), "get(10)");
            expect("v30", t.get(30), "get(30)");
            expect(null, t.get(25), "get(25)");
            expect(true, t.containsKey(20), "containsKey(20)");
            expect(false, t.containsKey(5), "containsKey(5)");
            expect(3, t.size(), "size()");
        });
        check("put replaces the value of a key that is already there", () -> {
            AVLTree<Integer, String> t = new AVLTree<>();
            t.put(5, "a");
            t.put(5, "b");
            expect("b", t.get(5), "get(5)");
            expect(1, t.size(), "size()");
        });
        check("Left-Left: put 3, 2, 1 rotates right", () ->
            expect(Arrays.asList(2, 1, 3), tree(3, 2, 1).keysPreOrder(), "keysPreOrder()"));
        check("Right-Right: put 1, 2, 3 rotates left", () ->
            expect(Arrays.asList(2, 1, 3), tree(1, 2, 3).keysPreOrder(), "keysPreOrder()"));
        check("Left-Right: put 3, 1, 2 rotates twice", () ->
            expect(Arrays.asList(2, 1, 3), tree(3, 1, 2).keysPreOrder(), "keysPreOrder()"));
        check("Right-Left: put 1, 3, 2 rotates twice", () ->
            expect(Arrays.asList(2, 1, 3), tree(1, 3, 2).keysPreOrder(), "keysPreOrder()"));
        check("put 1 to 7 in order gives a perfect tree of height 2", () -> {
            AVLTree<Integer, String> t = tree(1, 2, 3, 4, 5, 6, 7);
            expect(Arrays.asList(4, 2, 1, 3, 6, 5, 7), t.keysPreOrder(), "keysPreOrder()");
            expect(2, t.height(), "height()");
        });
        check("put 1 to 100 in order gives height 6", () -> {
            AVLTree<Integer, String> t = new AVLTree<>();
            for (int k = 1; k <= 100; k++) {
                t.put(k, "v" + k);
            }
            expect(6, t.height(), "height()");
            expect(100, t.size(), "size()");
        });
        check("minKey and maxKey", () -> {
            AVLTree<Integer, String> t = tree(50, 20, 80, 10);
            expect(10, t.minKey(), "minKey()");
            expect(80, t.maxKey(), "maxKey()");
            expectThrows(NoSuchElementException.class, () -> new AVLTree<Integer, String>().minKey(), "minKey() on an empty tree");
        });
        check("valuesInOrder", () -> {
            AVLTree<Integer, String> t = new AVLTree<>();
            t.put(30, "c");
            t.put(10, "a");
            t.put(20, "b");
            expect(Arrays.asList("a", "b", "c"), t.valuesInOrder(), "valuesInOrder()");
        });
        check("valuesInRange", () -> {
            AVLTree<Integer, String> t = tree(50, 20, 80, 10, 30, 60, 90, 40, 70, 100);
            expect(Arrays.asList("v30", "v40", "v50", "v60"), t.valuesInRange(25, 60), "valuesInRange(25, 60)");
            expect(new ArrayList<String>(), t.valuesInRange(61, 69), "valuesInRange(61, 69)");
        });

        System.out.println();
        System.out.println("Part 2: MinHeap");
        check("A new heap is empty, and peek() throws", () -> {
            MinHeap<Integer> h = new MinHeap<>();
            expect(0, h.size(), "size()");
            expect(true, h.isEmpty(), "isEmpty()");
            expectThrows(NoSuchElementException.class, () -> h.peek(), "peek() on an empty heap");
        });
        check("removeMin returns the items in increasing order", () -> {
            MinHeap<Integer> h = new MinHeap<>();
            for (int x : new int[] {5, 3, 8, 1, 9, 2}) {
                h.add(x);
            }
            List<Integer> out = new ArrayList<>();
            while (!h.isEmpty()) {
                out.add(h.removeMin());
            }
            expect(Arrays.asList(1, 2, 3, 5, 8, 9), out, "items removed by removeMin()");
        });
        check("peek does not remove the item", () -> {
            MinHeap<Integer> h = new MinHeap<>();
            h.add(4);
            h.add(2);
            expect(2, h.peek(), "peek()");
            expect(2, h.size(), "size() after peek()");
        });
        check("the heap grows past its first 8 slots", () -> {
            MinHeap<Integer> h = new MinHeap<>();
            for (int x = 100; x >= 1; x--) {
                h.add(x);
            }
            expect(100, h.size(), "size()");
            for (int x = 1; x <= 100; x++) {
                expect(x, h.removeMin(), "removeMin() number " + x);
            }
        });
        check("toSortedList leaves the heap unchanged", () -> {
            MinHeap<Integer> h = new MinHeap<>();
            h.add(3);
            h.add(1);
            h.add(2);
            expect(Arrays.asList(1, 2, 3), h.toSortedList(), "toSortedList()");
            expect(3, h.size(), "size() after toSortedList()");
            expect(1, h.peek(), "peek() after toSortedList()");
        });

        System.out.println();
        System.out.println("Part 3: HashTable");
        check("A new table is empty, with 16 buckets", () -> {
            HashTable<String, Integer> t = new HashTable<>();
            expect(0, t.size(), "size()");
            expect(true, t.isEmpty(), "isEmpty()");
            expect(16, t.capacity(), "capacity()");
            expect(null, t.get("kiwi"), "get(\"kiwi\")");
        });
        check("put, get and containsKey", () -> {
            HashTable<String, Integer> t = new HashTable<>();
            t.put("apple", 1);
            t.put("fig", 2);
            t.put("kiwi", 3);
            expect(2, t.get("fig"), "get(\"fig\")");
            expect(true, t.containsKey("kiwi"), "containsKey(\"kiwi\")");
            expect(null, t.get("pear"), "get(\"pear\")");
            expect(3, t.size(), "size()");
        });
        check("put replaces the value of a key that is already there", () -> {
            HashTable<String, Integer> t = new HashTable<>();
            t.put("fig", 1);
            t.put("fig", 5);
            expect(5, t.get("fig"), "get(\"fig\")");
            expect(1, t.size(), "size()");
        });
        check("remove", () -> {
            HashTable<String, Integer> t = new HashTable<>();
            t.put("apple", 1);
            t.put("fig", 2);
            expect(1, t.remove("apple"), "remove(\"apple\")");
            expect(null, t.get("apple"), "get(\"apple\") after removing it");
            expect(1, t.size(), "size()");
            expect(null, t.remove("pear"), "remove(\"pear\"), a key that is not there");
        });
        check("keys with negative hash codes", () -> {
            HashTable<Object, String> t = new HashTable<>();
            t.put(-7, "minus seven");                   // the hash code of -7 is -7
            t.put("polygenelubricants", "odd word");    // this String's hash code is Integer.MIN_VALUE
            expect("minus seven", t.get(-7), "get(-7)");
            expect("odd word", t.get("polygenelubricants"), "get(\"polygenelubricants\")");
        });
        check("the table grows to 32 buckets at the 13th key", () -> {
            HashTable<Integer, Integer> t = new HashTable<>();
            for (int k = 1; k <= 12; k++) {
                t.put(k * 100, k);
            }
            expect(16, t.capacity(), "capacity() with 12 keys");
            t.put(1300, 13);
            expect(32, t.capacity(), "capacity() with 13 keys");
            for (int k = 1; k <= 13; k++) {
                expect(k, t.get(k * 100), "get(" + (k * 100) + ") after growing");
            }
        });
        check("values() returns every value", () -> {
            HashTable<String, Integer> t = new HashTable<>();
            t.put("apple", 1);
            t.put("fig", 2);
            t.put("kiwi", 3);
            List<Integer> values = new ArrayList<>(t.values());
            Collections.sort(values);
            expect(Arrays.asList(1, 2, 3), values, "values(), sorted");
        });

        System.out.println();
        System.out.println("Part 4: WaitlistEntry and Registrar");
        check("compareTo: more credits come first", () -> {
            WaitlistEntry senior = new WaitlistEntry(new Student("1", "A", 90), 2);
            WaitlistEntry junior = new WaitlistEntry(new Student("2", "B", 30), 1);
            expect(true, senior.compareTo(junior) < 0, "90 credits before 30 credits (compareTo < 0)");
            expect(true, junior.compareTo(senior) > 0, "30 credits after 90 credits (compareTo > 0)");
        });
        check("compareTo: same credits, the earlier request comes first", () -> {
            WaitlistEntry first = new WaitlistEntry(new Student("1", "A", 30), 1);
            WaitlistEntry second = new WaitlistEntry(new Student("2", "B", 30), 2);
            expect(true, first.compareTo(second) < 0, "request 1 before request 2 (compareTo < 0)");
        });
        check("addStudent, findStudent, addCourse and findCourse", () -> {
            Registrar r = new Registrar();
            r.addStudent("301000001", "Maya Chen", 45);
            r.addCourse("CMPT 225", "Data Structures and Programming", 3);
            expect("Maya Chen", r.findStudent("301000001").getName(), "findStudent(\"301000001\").getName()");
            expect(3, r.findCourse("CMPT 225").getCapacity(), "findCourse(\"CMPT 225\").getCapacity()");
            expect(null, r.findStudent("301999999"), "findStudent(\"301999999\")");
            expect(1, r.studentCount(), "studentCount()");
            expect(1, r.courseCount(), "courseCount()");
        });
        check("enroll gives out the seats, then uses the waitlist", () -> {
            Registrar r = new Registrar();
            r.addCourse("CMPT 225", "Data Structures and Programming", 2);
            r.addStudent("A", "Ann", 10);
            r.addStudent("B", "Bo", 20);
            r.addStudent("C", "Cy", 30);
            expect(true, r.enroll("A", "CMPT 225"), "enroll(\"A\", ...)");
            expect(true, r.enroll("B", "CMPT 225"), "enroll(\"B\", ...)");
            expect(false, r.enroll("C", "CMPT 225"), "enroll(\"C\", ...) when the course is full");
            expect(2, r.roster("CMPT 225").size(), "roster size");
            expect(1, r.waitlist("CMPT 225").size(), "waitlist size");
        });
        check("drop gives the seat to the top of the waitlist", () -> {
            Registrar r = new Registrar();
            r.addCourse("CMPT 225", "Data Structures and Programming", 1);
            r.addStudent("A", "Ann", 10);
            r.addStudent("B", "Bo", 30);
            r.addStudent("C", "Cy", 90);
            r.enroll("A", "CMPT 225");
            r.enroll("B", "CMPT 225");
            r.enroll("C", "CMPT 225");
            Student promoted = r.drop("A", "CMPT 225");
            expect("C", promoted == null ? null : promoted.getId(), "the student who got A's seat (C has the most credits)");
            expect(1, r.waitlist("CMPT 225").size(), "waitlist size after the drop");
        });
        check("enroll throws for an unknown student or a second enroll", () -> {
            Registrar r = new Registrar();
            r.addCourse("CMPT 225", "Data Structures and Programming", 2);
            r.addStudent("A", "Ann", 10);
            expectThrows(IllegalArgumentException.class, () -> r.enroll("Z", "CMPT 225"), "enroll an unknown student");
            r.enroll("A", "CMPT 225");
            expectThrows(IllegalStateException.class, () -> r.enroll("A", "CMPT 225"), "enroll the same student twice");
        });
        check("listCourses is in code order, and ranges work", () -> {
            Registrar r = new Registrar();
            r.addCourse("MATH 151", "Calculus I", 4);
            r.addCourse("CMPT 225", "Data Structures and Programming", 3);
            r.addCourse("CMPT 120", "Introduction to Computing Science and Programming I", 4);
            expect(Arrays.asList("CMPT 120", "CMPT 225", "MATH 151"), codes(r.listCourses()), "listCourses()");
            expect(Arrays.asList("CMPT 225"), codes(r.listCourses("CMPT 200", "CMPT 299")), "listCourses(\"CMPT 200\", \"CMPT 299\")");
        });

        System.out.println();
        System.out.println(passed + " of " + total + " checks passed.");
    }

    // ================================ Helpers ================================

    private static void check(String name, Check body) {
        total++;
        System.out.print("  " + name + " ... ");
        System.out.flush();
        try {
            body.run();
            passed++;
            System.out.println("PASS");
        } catch (Throwable t) {
            System.out.println("FAIL: " + describe(t));
        }
    }

    private static void expect(Object expected, Object actual, String what) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(what + ": expected " + expected + " but got " + actual);
        }
    }

    private static void expectThrows(Class<? extends Throwable> type, Check body, String what) throws Exception {
        try {
            body.run();
        } catch (Throwable t) {
            if (type.isInstance(t)) {
                return;
            }
            throw new AssertionError(what + ": expected " + type.getSimpleName() + " but got " + describe(t));
        }
        throw new AssertionError(what + ": expected " + type.getSimpleName() + " but nothing was thrown");
    }

    private static String describe(Throwable t) {
        if (t instanceof AssertionError) {
            return t.getMessage();
        }
        if (t instanceof UnsupportedOperationException) {
            return "not written yet (" + t.getMessage() + ")";
        }
        String text = t.getClass().getSimpleName() + (t.getMessage() == null ? "" : ": " + t.getMessage());
        for (StackTraceElement frame : t.getStackTrace()) {
            String c = frame.getClassName();
            if (c.equals("AVLTree") || c.equals("MinHeap") || c.equals("HashTable")
                    || c.equals("WaitlistEntry") || c.equals("Registrar")) {
                return text + " (at " + frame.getFileName() + ":" + frame.getLineNumber() + ")";
            }
        }
        return text;
    }

    private static AVLTree<Integer, String> tree(int... keys) {
        AVLTree<Integer, String> t = new AVLTree<>();
        for (int k : keys) {
            t.put(k, "v" + k);
        }
        return t;
    }

    private static List<String> codes(List<Course> courses) {
        List<String> result = new ArrayList<>();
        for (Course c : courses) {
            result.add(c.getCode());
        }
        return result;
    }
}
