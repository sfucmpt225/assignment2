/**
 * A course in the catalog, with its enrolled students and its waitlist.
 * Given: do not change this file.
 *
 * A course keeps three data structures, all built from your own classes:
 *
 *   enrolled    the students who have a seat, by student id     (HashTable)
 *   waitlist    the students waiting for a seat, best first     (MinHeap)
 *   waitlisted  the same waiting students, by student id        (HashTable)
 *
 * The heap gives the student with the highest priority quickly, but it cannot
 * tell quickly whether a given student is waiting; the waitlisted table can.
 * The Registrar keeps waitlist and waitlisted in step: every student who is in
 * one of them is also in the other.
 */
public class Course {

    private final String code;       // for example "CMPT 225"
    private final String title;      // for example "Data Structures and Programming"
    private final int capacity;      // number of seats

    private final HashTable<String, Student> enrolled = new HashTable<>();
    private final MinHeap<WaitlistEntry> waitlist = new MinHeap<>();
    private final HashTable<String, Student> waitlisted = new HashTable<>();

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    /** The enrolled students, by student id. */
    public HashTable<String, Student> getEnrolled() {
        return enrolled;
    }

    /** The waitlist: the entry with the highest priority is at the top of the heap. */
    public MinHeap<WaitlistEntry> getWaitlist() {
        return waitlist;
    }

    /** The students on the waitlist, by student id. */
    public HashTable<String, Student> getWaitlisted() {
        return waitlisted;
    }

    /** Returns the number of free seats. */
    public int seatsLeft() {
        return capacity - enrolled.size();
    }

    /** Returns true if every seat is taken. */
    public boolean isFull() {
        return enrolled.size() >= capacity;
    }

    /** For example "CMPT 225 Data Structures and Programming". */
    @Override
    public String toString() {
        return code + " " + title;
    }
}
