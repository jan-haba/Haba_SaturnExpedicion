package Command;

import Objects.Player;

import java.util.Arrays;

public class Inventory implements Command {
    private Player player;

    public Inventory(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        return Arrays.toString(player.openInventory());
    }

    @Override
    public boolean exit() {
        return false;
    }
}
