package Command;

import Objects.Character;
import Objects.Player;

/**
 * Command for interaction with characters in the game
 */

public class Talk implements Command{
    private Player player;

    public Talk(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        if (player.getCurrRoom().getCharacters() != null){
            for (Character character : player.getCurrRoom().getCharacters()){
                if (character.getName().equalsIgnoreCase(command)){
                    return String.valueOf(character.getDialogue());
                }
            }
        }
        return "Room does not have anybody in it.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
