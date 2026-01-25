package Command;

import Logic.GameData;
import Objects.Player;

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
        move(command);
        return "Player has moved";
    }

    /**
     * method for movement
     * @param roomName = room where the moement is headed
     * @return
     */
    public String move(String roomName){
        if (player.getCurrRoom().getExits().contains(roomName)){
            player.setCurrRoom(data.locateRoom(roomName));
        }else{
            return "room wasnt found or it doesnt connect to that room";

        }
        return "";
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
