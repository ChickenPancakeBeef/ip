public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // You MUST include this for Level 7 to work!
    @Override
    public String toFileFormat() {
        // Returns "T | 1 | description"
        return "T | " + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}