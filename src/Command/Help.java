package Command;

import Logic.GameData;

/**
 * Command for displaying the help menu.
 * Loads and prints the list of available commands and their descriptions from GameData.
 */
public class Help implements Command {
    private GameData gameData;

    public Help(GameData gameData) {
        this.gameData = gameData;
    }

    /**
     * Executes the help command.
     * Retrieves the help text lines from the game data and formats them into a single string.
     * @param command any additional arguments provided by the player (ignored for this command)
     * @return the formatted help text containing all available commands
     */
    @Override
    public String execute(String command) {
        StringBuilder sb = new StringBuilder();

        if (gameData.getCommands() != null) {
            for (String line : gameData.getCommands()) {
                sb.append(line).append("\n");
            }
        } else {
            return "Error: Help data is missing from the system.";
        }

        return sb.toString();
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as displaying help does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}