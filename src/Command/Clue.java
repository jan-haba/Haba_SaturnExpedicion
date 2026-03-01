package Command;

import Logic.GameData;
import Objects.Player;

public class Clue implements Command{
    private Player player;
    private GameData data;

    public Clue(Player player, GameData data) {
        this.player = player;
        this.data = data;
    }

    @Override
    public String execute(String command) {
        if (data.getClues() == null) {
            return "Error: Clue database is currently offline.";
        }

        String path = player.getChoosenPath();

        if ("reactor".equals(path)) {
            return data.getClues().get("reactor");
        } else if ("escape".equals(path)) {
            return data.getClues().get("escape");
        } else {
            return "System Error: Unknown path detected.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}