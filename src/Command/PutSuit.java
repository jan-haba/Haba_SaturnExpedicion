package Command;

import Objects.Player;

public class PutSuit implements Command {
    private Player player;

    public PutSuit(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        if (command.equalsIgnoreCase("suit")) {
            if (player.hasSuit()){
                player.setSuitEquiped(true);
                player.removeItem("Suit");
                return "Suit was equiped";
            }
            return "You need suit for this action";
        }
        return "you cant equip this";
    }


    @Override
    public boolean exit() {
        return false;
    }
}
