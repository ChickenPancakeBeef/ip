import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that must be completed by a specific date.
 * Stores the deadline date using a LocalDate object for date-aware logic.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initializes a new Deadline task with a description and a date.
     *
     * @param description The name of the deadline task.
     * @param by The date string in yyyy-MM-dd format.
     * @throws JerryException If the date string provided is not in the correct format.
     */
    public Deadline(String description, String by) throws JerryException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new JerryException("Please use the format yyyy-mm-dd for dates (e.g., 2026-12-01).");
        }
    }

    /**
     * Returns a string formatted for saving the deadline task to a file.
     * The format used is "D | status | description | yyyy-MM-dd".
     *
     * @return A pipe-separated string representing the deadline task.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    /**
     * Returns a string representation of the deadline task for display.
     * Formats the date into a more readable MMM dd yyyy format.
     *
     * @return A string containing the task type, status, description, and formatted date.
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}