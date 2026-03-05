import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws JerryException {
        super(description);
        try {
            // Parses "yyyy-MM-dd" string into a LocalDate object
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new JerryException("Please use the format yyyy-mm-dd for dates (e.g., 2026-12-01).");
        }
    }

    @Override
    public String toFileFormat() {
        // Saves in yyyy-MM-dd format for easy reloading
        return "D | " + super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        // Displays as "Mar 05 2026" in the UI
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}