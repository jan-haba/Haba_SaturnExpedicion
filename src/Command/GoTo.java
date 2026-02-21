package Command;

import Logic.GameData;
import Objects.Player;
import Objects.Room;

import java.util.Scanner;

/**
 * class = command for movement
 */
public class GoTo implements Command {
    private Player player;
    private GameData data;

    public GoTo(Player player,GameData data) {
        this.player = player;
        this.data = data;
    }
    /**
     * execute method
     * @param command players command
     * @return message that indicates players movement
     */
    @Override
    public String execute(String command) {
        if (command.isEmpty()) {
            return "write where zou wanna go";
        }
        return move(command);
    }

    /**
     * method for movement
     * @param roomName = room where the moement is headed
     * @return
     */
    public String move(String roomName){
        String actualExit = null;
        Scanner scanner = new Scanner(System.in);
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
            return "Room wasn't found or it doesn't connect to that room.";
        }

    }

    /**
     * exit method
     * @return
     */
    @Override
    public boolean exit() {
        return false;
    }
}
