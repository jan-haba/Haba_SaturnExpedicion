package Command;

import Objects.Player;

/**
 * Command for equipping items (e.g., suit).
 */

public class Equip implements Command {
    private Player player;

    public Equip(Player player) {
        this.player = player;
    }

    /**
     * Executes the equip command.
     * @param command name of the item to equip (e.g., "suit")
     * @return message about whether the player equipped the item or not
     */
    @Override
    public String execute(String command) {
        if (command.equalsIgnoreCase("suit")) {
            if (player.hasSuit()){
                player.setSuitEquiped(true);
                player.removeItem("Suit");
                return "Suit was equipped.";
            }
            return "You don't have a suit in your inventory.";
        }
        if (command.isEmpty()) {
            return "What do you want to equip?";
        }
        return "You can't equip" + command + " this.";
    }

    /**
     * @return false
     */
    @Override
    public boolean exit() {
        return false;
    }
}
