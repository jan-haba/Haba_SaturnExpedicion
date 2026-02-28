package Command;

/**
 * Interface for commands.
 */
public interface Command {
     String execute(String command);
     boolean exit();
}