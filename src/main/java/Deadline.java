public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // RESTORED for Level 7 functionality in the final merged master
    @Override
    public String toFileFormat() {
        // Returns "D | 0 | return book | June 6th"
        return "D | " + super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}