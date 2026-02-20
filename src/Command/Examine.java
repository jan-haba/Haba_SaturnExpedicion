package Command;

import Items.Item;
import Objects.GameObject;
import Objects.Player;

public class Examine implements Command{
    private Player player;

    public Examine(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        Item inventoryItem = player.getItem(command);
        if (inventoryItem != null) {
            return inventoryItem.getDescription();
        }
        Object target = player.getCurrRoom().findObject(command);
        if (target != null) {
            if (target instanceof Item) {
                return ((Item) target).getDescription();
            }
            if (target instanceof GameObject) {
                return ((GameObject) target).getDescription();
            }
        }
        return "I cannot see any '" + command + "' here to examine.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
