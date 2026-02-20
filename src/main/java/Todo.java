public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // NEW METHOD FOR LEVEL-7
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