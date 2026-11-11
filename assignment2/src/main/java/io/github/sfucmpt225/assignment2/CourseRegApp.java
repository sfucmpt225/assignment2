import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * CourseReg: a command-line course registration system. Given: do not change this file.
 *
 *   java CourseRegApp                  start the app and type commands ("help" lists them)
 *   java CourseRegApp data/demo.txt    run the commands in a file, showing each command
 *
 * This class only reads commands and prints the results. All the real work is
 * done by the Registrar and the data structures you write.
 */
public class CourseRegApp {

    private static final String HELP =
          "Commands (a course code is a subject and a number, like CMPT 225):\n"
        + "  add-student <id> <credits> <name>   register a student\n"
        + "  add-course <code> <seats> <title>   add a course to the catalog\n"
        + "  enroll <id> <code>                  enroll a student, or put them on the waitlist\n"
        + "  drop <id> <code>                    drop a course; the top of its waitlist gets the seat\n"
        + "  student <id>                        show a student and their courses\n"
        + "  course <code>                       show a course\n"
        + "  roster <code>                       list the students enrolled in a course\n"
        + "  waitlist <code>                     list a course's waitlist, next in line first\n"
        + "  courses                             list every course, in code order\n"
        + "  courses <subject>                   list one subject's courses, e.g. courses MATH\n"
        + "  courses <code> <code>               list a range of courses, e.g. courses CMPT 200 CMPT 299\n"
        + "  stats                               show the sizes and shapes of the data structures\n"
        + "  tree                                draw the catalog's AVL tree\n"
        + "  load <file>                         run the commands in a file, showing only errors\n"
        + "  quit                                exit\n";

    /** The classes students write: error messages point to the line in these files. */
    private static final String[] STUDENT_CLASSES = {"AVLTree", "MinHeap", "HashTable", "WaitlistEntry", "Registrar"};

    private final Registrar registrar = new Registrar();
    private boolean quiet = false;      // true while the load command runs a file
    private String errorPrefix = "";    // where the current command came from, e.g. "data/x.txt line 3: "
    private int errorCount = 0;         // errors printed so far

    public static void main(String[] args) {
        CourseRegApp app = new CourseRegApp();
        if (args.length == 0) {
            app.runInteractive();
        } else {
            for (String file : args) {
                if (!app.runFile(file, true)) {
                    break;                          // the file said quit
                }
            }
        }
        System.out.flush();
    }

    /** Reads commands typed by the user until quit or the end of the input. */
    private void runInteractive() {
        say("CourseReg: type 'help' for the list of commands, 'quit' to exit.");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            System.out.flush();
            String line;
            try {
                line = in.readLine();
            } catch (IOException e) {
                break;
            }
            if (line == null || !execute(line)) {
                break;
            }
        }
    }

    /**
     * Runs every command in a file. With echo, prints each command (and the
     * file's comments) before its result; without echo (the load command),
     * prints only errors and a summary. Returns false if the file said quit.
     */
    private boolean runFile(String path, boolean echo) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            error("cannot read the file " + path + ".");
            return true;
        }

        boolean oldQuiet = quiet;
        String oldPrefix = errorPrefix;
        int oldErrorCount = errorCount;
        quiet = quiet || !echo;
        int commands = 0;
        boolean keepGoing = true;
        for (int i = 0; i < lines.size() && keepGoing; i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty() || line.startsWith("#")) {
                say(line);                          // blank lines and comments, when echoing
                continue;
            }
            commands++;
            say("> " + line);
            errorPrefix = quiet ? path + " line " + (i + 1) + ": " : "";
            keepGoing = execute(line);
        }
        int errors = errorCount - oldErrorCount;
        quiet = oldQuiet;
        errorPrefix = oldPrefix;
        if (!echo) {
            say("Loaded " + plural(commands, "command") + " from " + path
                    + (errors > 0 ? ", with " + plural(errors, "error") : "") + ".");
        }
        return keepGoing;
    }

    /** Runs one command. Returns false if the command was quit. */
    private boolean execute(String line) {
        if (line.trim().isEmpty() || line.trim().startsWith("#")) {
            return true;                            // nothing to do
        }
        String[] words = line.trim().split("\\s+");
        String command = words[0].toLowerCase(Locale.ROOT);
        try {
            switch (command) {
                case "help":        sayRaw(HELP); break;
                case "add-student": addStudent(line); break;
                case "add-course":  addCourse(line); break;
                case "enroll":      enroll(words); break;
                case "drop":        drop(words); break;
                case "student":     showStudent(words); break;
                case "course":      showCourse(words); break;
                case "roster":      showRoster(words); break;
                case "waitlist":    showWaitlist(words); break;
                case "courses":     listCourses(words); break;
                case "stats":       sayRaw(registrar.stats()); break;
                case "tree":        sayRaw(registrar.catalogTree()); break;
                case "load":        return load(line);
                case "quit":
                case "exit":        return false;
                default:
                    error("unknown command '" + words[0] + "'. Type 'help' for the list of commands.");
            }
        } catch (CommandException e) {
            error(e.getMessage());
        } catch (RuntimeException | StackOverflowError e) {
            // A bug in one of the classes you wrote: say what went wrong and where.
            error("unexpected " + e.getClass().getSimpleName()
                    + (e.getMessage() == null ? "" : ": " + e.getMessage()) + where(e));
        }
        System.out.flush();
        return true;
    }

    // ================================ Commands ================================

    private void addStudent(String line) {
        String[] parts = line.trim().split("\\s+", 4);
        if (parts.length < 4) {
            throw new CommandException("usage: add-student <id> <credits> <name>");
        }
        String id = parts[1];
        int credits = number(parts[2], "credits");
        String name = parts[3];
        try {
            registrar.addStudent(id, name, credits);
        } catch (IllegalArgumentException e) {
            error(credits < 0 ? "credits must not be negative." : "a student with id " + id + " already exists.");
            return;
        }
        say("Added student " + id + " " + name + " (" + plural(credits, "credit") + ").");
    }

    private void addCourse(String line) {
        String[] parts = line.trim().split("\\s+", 5);
        if (parts.length < 5) {
            throw new CommandException("usage: add-course <subject> <number> <seats> <title>");
        }
        String code = code(parts[1], parts[2]);
        int seats = number(parts[3], "seats");
        String title = parts[4];
        try {
            registrar.addCourse(code, title, seats);
        } catch (IllegalArgumentException e) {
            error(seats < 1 ? "a course needs at least 1 seat." : "course " + code + " already exists.");
            return;
        }
        say("Added course " + code + " " + title + " (" + plural(seats, "seat") + ").");
    }

    private void enroll(String[] words) {
        if (words.length != 4) {
            throw new CommandException("usage: enroll <id> <subject> <number>");
        }
        String id = words[1];
        String code = code(words[2], words[3]);
        boolean gotSeat;
        try {
            gotSeat = registrar.enroll(id, code);
        } catch (IllegalArgumentException e) {
            error(unknownStudentOrCourse(id, code, e));
            return;
        } catch (IllegalStateException e) {
            Course course = registrar.findCourse(code);
            if (course.getEnrolled().containsKey(id)) {
                error(id + " is already enrolled in " + code + ".");
            } else if (course.getWaitlisted().containsKey(id)) {
                error(id + " is already on the waitlist for " + code + ".");
            } else {
                throw e;
            }
            return;
        }
        Student student = registrar.findStudent(id);
        Course course = registrar.findCourse(code);
        if (gotSeat) {
            say("Enrolled " + student + " in " + code + " (" + plural(course.seatsLeft(), "seat") + " left).");
        } else {
            say(code + " is full: " + student + " is on the waitlist ("
                    + course.getWaitlist().size() + " waiting).");
        }
    }

    private void drop(String[] words) {
        if (words.length != 4) {
            throw new CommandException("usage: drop <id> <subject> <number>");
        }
        String id = words[1];
        String code = code(words[2], words[3]);
        Student promoted;
        try {
            promoted = registrar.drop(id, code);
        } catch (IllegalArgumentException e) {
            error(unknownStudentOrCourse(id, code, e));
            return;
        } catch (IllegalStateException e) {
            Course course = registrar.findCourse(code);
            if (course.getEnrolled().containsKey(id)) {
                throw e;
            }
            error(id + " is not enrolled in " + code
                    + (course.getWaitlisted().containsKey(id) ? " (they are on its waitlist)." : "."));
            return;
        }
        say("Dropped " + registrar.findStudent(id) + " from " + code + ".");
        if (promoted != null) {
            say(promoted + " moved off the waitlist into the free seat.");
        }
    }

    private void showStudent(String[] words) {
        if (words.length != 2) {
            throw new CommandException("usage: student <id>");
        }
        Student student = registrar.findStudent(words[1]);
        if (student == null) {
            error("no student with id " + words[1] + ".");
            return;
        }
        List<String> enrolledIn = new ArrayList<>();
        List<String> waitingFor = new ArrayList<>();
        for (Course course : registrar.listCourses()) {
            if (course.getEnrolled().containsKey(student.getId())) {
                enrolledIn.add(course.getCode());
            }
            if (course.getWaitlisted().containsKey(student.getId())) {
                waitingFor.add(course.getCode());
            }
        }
        say(student + ", " + plural(student.getCredits(), "credit"));
        say("  Enrolled in: " + (enrolledIn.isEmpty() ? "(none)" : String.join(", ", enrolledIn)));
        say("  Waiting for: " + (waitingFor.isEmpty() ? "(none)" : String.join(", ", waitingFor)));
    }

    private void showCourse(String[] words) {
        if (words.length != 3) {
            throw new CommandException("usage: course <subject> <number>");
        }
        Course course = existingCourse(code(words[1], words[2]));
        if (course == null) {
            return;
        }
        int waiting = course.getWaitlist().size();
        say(course.toString());
        say("  " + course.getEnrolled().size() + " of " + plural(course.getCapacity(), "seat") + " taken, "
                + (waiting == 0 ? "nobody waiting"
                                : waiting + " waiting (next: " + course.getWaitlist().peek().getStudent() + ")"));
    }

    private void showRoster(String[] words) {
        if (words.length != 3) {
            throw new CommandException("usage: roster <subject> <number>");
        }
        Course course = existingCourse(code(words[1], words[2]));
        if (course == null) {
            return;
        }
        List<Student> students = new ArrayList<>(registrar.roster(course.getCode()));
        Collections.sort(students, new Comparator<Student>() {   // the table has no order: sort by id
            @Override
            public int compare(Student a, Student b) {
                return a.getId().compareTo(b.getId());
            }
        });
        say(course.getCode() + " roster (" + students.size() + " of "
                + plural(course.getCapacity(), "seat") + " taken):");
        if (students.isEmpty()) {
            say("  (nobody is enrolled)");
        }
        for (Student student : students) {
            say("  " + student);
        }
    }

    private void showWaitlist(String[] words) {
        if (words.length != 3) {
            throw new CommandException("usage: waitlist <subject> <number>");
        }
        Course course = existingCourse(code(words[1], words[2]));
        if (course == null) {
            return;
        }
        List<WaitlistEntry> entries = registrar.waitlist(course.getCode());
        say(course.getCode() + " waitlist (" + entries.size() + " waiting):");
        if (entries.isEmpty()) {
            say("  (nobody is waiting)");
        }
        int position = 1;
        for (WaitlistEntry entry : entries) {
            Student student = entry.getStudent();
            say("  " + position + ". " + student + " (" + plural(student.getCredits(), "credit") + ")");
            position++;
        }
    }

    private void listCourses(String[] words) {
        List<Course> courses;
        if (words.length == 1) {
            say("All courses:");
            courses = registrar.listCourses();
        } else if (words.length == 2) {
            // Every code that starts with "MATH " lies between "MATH " and "MATH ~",
            // because '~' comes after every digit and letter.
            String subject = words[1].toUpperCase(Locale.ROOT);
            say(subject + " courses:");
            courses = registrar.listCourses(subject + " ", subject + " ~");
        } else if (words.length == 5) {
            String from = code(words[1], words[2]);
            String to = code(words[3], words[4]);
            say("Courses from " + from + " to " + to + ":");
            courses = registrar.listCourses(from, to);
        } else {
            throw new CommandException("usage: courses   or   courses <subject>   or   courses <code> <code>");
        }
        for (Course course : courses) {
            int waiting = course.getWaitlist().size();
            say(String.format(Locale.ROOT, "  %-9s  %s  (%d/%d seats%s)", course.getCode(), course.getTitle(),
                    course.getEnrolled().size(), course.getCapacity(), waiting > 0 ? ", " + waiting + " waiting" : ""));
        }
        say("(" + (courses.isEmpty() ? "no courses" : plural(courses.size(), "course")) + ")");
    }

    private boolean load(String line) {
        String[] parts = line.trim().split("\\s+", 2);
        if (parts.length < 2) {
            throw new CommandException("usage: load <file>");
        }
        return runFile(parts[1], false);
    }

    // ================================ Helpers ================================

    /** Returns the error message for an IllegalArgumentException from enroll or drop. */
    private String unknownStudentOrCourse(String id, String code, RuntimeException e) {
        if (registrar.findStudent(id) == null) {
            return "no student with id " + id + ".";
        }
        if (registrar.findCourse(code) == null) {
            return "no course " + code + ".";
        }
        throw e;            // both exist, so the exception was a mistake
    }

    /** Returns the course with this code, or prints an error and returns null. */
    private Course existingCourse(String code) {
        Course course = registrar.findCourse(code);
        if (course == null) {
            error("no course " + code + ".");
        }
        return course;
    }

    /** Builds a course code such as "CMPT 225" from its two words. */
    private static String code(String subject, String number) {
        return subject.toUpperCase(Locale.ROOT) + " " + number.toUpperCase(Locale.ROOT);
    }

    private static int number(String text, String what) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new CommandException(what + " must be a whole number, not '" + text + "'.");
        }
    }

    /** For example plural(1, "seat") is "1 seat" and plural(3, "seat") is "3 seats". */
    private static String plural(int count, String word) {
        return count + " " + word + (count == 1 ? "" : "s");
    }

    /** Returns " (at File.java:line)" for the first line of a student class in the stack trace. */
    private static String where(Throwable e) {
        for (StackTraceElement frame : e.getStackTrace()) {
            for (String name : STUDENT_CLASSES) {
                if (name.equals(frame.getClassName()) && frame.getLineNumber() > 0) {
                    return " (at " + frame.getFileName() + ":" + frame.getLineNumber() + ")";
                }
            }
        }
        return "";
    }

    private void say(String text) {
        if (!quiet) {
            System.out.print(text + "\n");
        }
    }

    private void sayRaw(String text) {
        if (!quiet) {
            System.out.print(text);
        }
    }

    private void error(String message) {
        errorCount++;
        System.out.print(errorPrefix + "Error: " + message + "\n");
    }

    /** A mistake in a command, such as a missing word or a number that is not a number. */
    private static class CommandException extends RuntimeException {
        CommandException(String message) {
            super(message);
        }
    }
}
