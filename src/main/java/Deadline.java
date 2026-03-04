public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // REMOVED toFileFormat() for Level 6 branch

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}