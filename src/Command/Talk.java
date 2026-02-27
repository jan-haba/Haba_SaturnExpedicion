package Command;

import Logic.GameData;
import Objects.Character;
import Objects.Player;

/**
 * Command for initiating a conversation with characters in the game.
 * Switches the game state to DIALOG if the specified character is present in the current room.
 */

public class Talk implements Command{
    private Player player;
    private GameData gameData;

    public Talk(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Executes the talk command.
     * Checks if the specified character is in the same room as the player.
     * If found, sets the game state to DIALOG and starts the conversation.
     * * @param command the name of the character the player wants to talk to (e.g., "lucy")
     * @return the available dialogue options, or an error message if the character isn't found
     */
    @Override
    public String execute(String command) {
        if (player.getCurrRoom().getCharacters() != null) {
            for (Character character : player.getCurrRoom().getCharacters()) {
                if (character.getName().equalsIgnoreCase(command)) {
                    gameData.setGameState("DIALOG");
                    gameData.setActiveCharacter(character);
                    return character.printDialogues();
                }
            }
        }
        return "This character isn't here.";
    }

    /**
     * Determines if this command exits the game.
     * @return false
     */
    @Override
    public boolean exit() {
        return false;
    }
}
