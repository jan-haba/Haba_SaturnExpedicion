package Command;

/**
 * interface for commands
 */

public interface Command {
     String execute(String command);
     boolean exit();
}
