public class Parser {
    public static Command parse(String fullCommand) throws JerryException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "mark":
                return new MarkCommand(parseIndex(arguments), true);
            case "unmark":
                return new MarkCommand(parseIndex(arguments), false);
            case "delete":
                return new DeleteCommand(parseIndex(arguments));
            default:
                throw new JerryException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String args) throws JerryException {
        try {
            return Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new JerryException("Please provide a valid task number.");
        }
    }
}