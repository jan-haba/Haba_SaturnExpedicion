package Command;

import Objects.GameObject;
import Objects.Player;

public class Interact implements Command{
    private Player player;

    public Interact(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String command) {
        if(player.getCurrRoom().getItems() != null){
        }
        if (player.getCurrRoom().getGameObjects() != null)
            for (GameObject gameObject: player.getCurrRoom().getGameObjects()){
                if (gameObject.getName().equalsIgnoreCase(command) ){
                    String idk = gameObject.execute(player);
                    return "interaction with " + gameObject.getName() + " started " + "\n" + idk;
                }
            }
        return "Room doesnt have a interactive Game Object";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
