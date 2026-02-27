package Command;

import Logic.GameData;
import Objects.Character;
import Objects.Player;

/**
 * Command for interaction with characters in the game
 */

public class Talk implements Command{
    private Player player;
    private GameData gameData;

    public Talk(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
    }

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
        return "Character doesnt exist.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
