package Command;

import Objects.GameObject;
import Objects.Player;

/**
 * Command for interacting with game objects in the current room.
 * Allows the player to use panels, reactors, escape modules, and other stationary objects.
 */
public class Interact implements Command{
    private Player player;

    public Interact(Player player) {
        this.player = player;
    }

    /**
     * Executes the interact command.
     * Searches for the specified game object in the room and triggers its custom logic.
     * @param command the name of the object to interact with
     * @return the result of the interaction or an error message if the object is missing
     */
    @Override
    public String execute(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "What do you want to interact with?";
        }
        if (player.getCurrRoom().getGameObjects() != null)
            for (GameObject gameObject: player.getCurrRoom().getGameObjects()){
                if (gameObject.getName().equalsIgnoreCase(command) ){
                    String idk = gameObject.execute(player);
                    return idk;
                }
            }
        return "I cannot see any '" + command + "' here to interact with.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
