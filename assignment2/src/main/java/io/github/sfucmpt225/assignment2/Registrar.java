import java.util.List;

/**
 * Part 4: the registrar's office. It keeps every student and every course,
 * and handles enrolling, dropping and the waitlists.
 *
 *   students   every student, by student id      HashTable: exact lookups in O(1)
 *   catalog    every course, by course code      AVLTree: lookups in O(log n),
 *                                                plus listings in code order
 *
 * Each Course keeps its own enrolled table, waitlist heap and waitlisted table.
 * When a method throws an exception, it must not change anything.
 */
public class Registrar {

    private final HashTable<String, Student> students = new HashTable<>();
    private final AVLTree<String, Course> catalog = new AVLTree<>();
    private int nextRequestNumber = 1;   // the request number for the next student put on a waitlist

    /**
     * Registers a new student.
     *
     * @throws IllegalArgumentException if credits < 0 or a student with this id already exists
     */
    public void addStudent(String id, String name, int credits) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.addStudent");
    }

    /**
     * Adds a new course to the catalog.
     *
     * @throws IllegalArgumentException if capacity < 1 or a course with this code already exists
     */
    public void addCourse(String code, String title, int capacity) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.addCourse");
    }

    /** Returns the student with this id, or null if there is none. */
    public Student findStudent(String id) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.findStudent");
    }

    /** Returns the course with this code, or null if there is none. */
    public Course findCourse(String code) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.findCourse");
    }

    /** Returns the number of registered students. */
    public int studentCount() {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.studentCount");
    }

    /** Returns the number of courses in the catalog. */
    public int courseCount() {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.courseCount");
    }

    /**
     * Enrolls the student in the course if it has a free seat (returns true);
     * otherwise puts the student on the course's waitlist (returns false).
     * A student put on the waitlist gets the next request number (1, 2, 3, ...)
     * and goes into both the course's waitlist heap and its waitlisted table.
     *
     * @throws IllegalArgumentException if there is no such student or no such course
     * @throws IllegalStateException if the student is already enrolled in the course or already on its waitlist
     */
    public boolean enroll(String studentId, String courseCode) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.enroll");
    }

    /**
     * Removes the student from the course. If the waitlist is not empty, the
     * waitlisted student with the highest priority takes the free seat (leaving
     * both the waitlist heap and the waitlisted table) and is returned;
     * otherwise returns null.
     *
     * @throws IllegalArgumentException if there is no such student or no such course
     * @throws IllegalStateException if the student is not enrolled in the course
     *         (a student who is only on the waitlist cannot drop it)
     */
    public Student drop(String studentId, String courseCode) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.drop");
    }

    /** Returns every course, in order of course code. */
    public List<Course> listCourses() {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.listCourses");
    }

    /** Returns the courses with fromCode <= code <= toCode, in order of course code. */
    public List<Course> listCourses(String fromCode, String toCode) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.listCourses(fromCode, toCode)");
    }

    /**
     * Returns the students enrolled in the course, in no particular order.
     *
     * @throws IllegalArgumentException if there is no such course
     */
    public List<Student> roster(String courseCode) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.roster");
    }

    /**
     * Returns the course's waitlist in priority order (the next student to get
     * a seat first), without changing it.
     *
     * @throws IllegalArgumentException if there is no such course
     */
    public List<WaitlistEntry> waitlist(String courseCode) {
        // TODO
        throw new UnsupportedOperationException("TODO: Registrar.waitlist");
    }

    // You may add private helper methods here.

    // ===================== Given methods: do not change =====================

    /** Describes the sizes and shapes of the data structures, for the app's "stats" command. */
    public String stats() {
        String text = "Students: " + students.size() + ", in a hash table with " + students.capacity()
                + " buckets (load factor " + students.loadFactor() + ")\n";
        int n = catalog.size();
        if (n == 0) {
            return text + "Courses:  0\n";
        }
        int shortest = 31 - Integer.numberOfLeadingZeros(n);   // floor(log2 n): the height of the shortest tree with n nodes
        return text + "Courses:  " + n + ", in an AVL tree of height " + catalog.height()
                + " (" + catalog.minKey() + " to " + catalog.maxKey() + ")\n"
                + "          The shortest possible tree with " + n + " nodes has height " + shortest
                + "; a plain BST could have height " + (n - 1) + ".\n";
    }

    /** Returns a drawing of the catalog's AVL tree, for the app's "tree" command. */
    public String catalogTree() {
        return catalog.toTreeString();
    }
}
