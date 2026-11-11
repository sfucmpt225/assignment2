/**
 * Part 4: one student waiting for a seat in one course.
 *
 * The waitlist is a MinHeap, and a MinHeap removes the SMALLEST item first,
 * so compareTo must make the entry with the HIGHEST priority the smallest:
 *
 *   1. the student with more credits comes first;
 *   2. if both students have the same credits, the earlier request
 *      (the smaller request number) comes first.
 */
public class WaitlistEntry implements Comparable<WaitlistEntry> {

    private final Student student;
    private final int requestNumber;   // 1 for the first waitlist request, 2 for the next, and so on

    public WaitlistEntry(Student student, int requestNumber) {
        this.student = student;
        this.requestNumber = requestNumber;
    }

    public Student getStudent() {
        return student;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    /**
     * Returns a negative number if this entry comes before other on the waitlist,
     * a positive number if it comes after, and 0 if neither comes first.
     */
    @Override
    public int compareTo(WaitlistEntry other) {
        // TODO
        throw new UnsupportedOperationException("TODO: WaitlistEntry.compareTo");
    }

    /** For example "301000003 Sofia Rossi (90 credits, request 2)". */
    @Override
    public String toString() {
        return student + " (" + student.getCredits() + " credits, request " + requestNumber + ")";
    }
}
