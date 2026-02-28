package Command;

import Logic.GameData;
import Objects.Player;

/**
 * Command for player movement.
 * Allows the player to navigate between connected rooms.
 */
public class GoTo implements Command {
    private Player player;
    private GameData data;

    public GoTo(Player player,GameData data) {
        this.player = player;
        this.data = data;
    }

    /**
     * Executes the movement command.
     * Checks if the player provided a destination and initiates the movement.
     * @param command the name of the destination room provided by the player
     * @return a message indicating the result of the movement attempt
     */
    @Override
    public String execute(String command) {
        if (command.isEmpty()) {
            return "Please specify where you want to go.";
        }
        return move(command);
    }

    /**
     * Handles the logic of moving the player to a new room.
     * Validates if the destination is a valid exit from the current room.
     * @param roomName the name of the room the player wants to move to
     * @return the description of the new room if movement is successful, or an error message if not
     */
    public String move(String roomName){
        String actualExit = null;
        for (String exit : player.getCurrRoom().getExits()) {
            if (exit.equalsIgnoreCase(roomName)) {
                actualExit = exit;
                break;
            }
        }
        if (actualExit != null) {
            player.setCurrRoom(data.locateRoom(actualExit));
            return player.getCurrRoom().getRoom() + "\nPlayer has moved to " + actualExit;
        } else {
            return "Room wasn't found or it doesn't connect to your current location.";
        }

    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as moving does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}
