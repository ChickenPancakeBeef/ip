public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // REMOVED toFileFormat() because it belongs to Level 7

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}