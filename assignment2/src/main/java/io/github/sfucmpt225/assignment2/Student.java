/**
 * A student registered with the Registrar. Given: do not change this file.
 */
public class Student {

    private final String id;       // student number, for example "301000001"
    private final String name;     // for example "Maya Chen"
    private final int credits;     // credits completed so far: more credits = higher waitlist priority

    public Student(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    /** For example "301000001 Maya Chen". */
    @Override
    public String toString() {
        return id + " " + name;
    }
}
