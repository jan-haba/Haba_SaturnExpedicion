package Command;

import Objects.Player;

public class Description implements Command{
    private Player player;

    public Description(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        String roomDescription = player.getCurrRoom().getRoom();
        return roomDescription;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
