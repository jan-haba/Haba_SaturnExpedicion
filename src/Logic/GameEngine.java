package Logic;

import Command.*;
import Objects.Player;
import java.util.HashMap;

public class GameEngine {
    private Player player;
    private GameData gameData;
    private HashMap<String, Command>commands;

    public GameEngine( ) {
        this.gameData = GameData.loadGameDataFromResources("/gamedata.json");
        this.player = new Player();
        this.commands = new HashMap<>();
    }

    /**
     * method that loads all the commands
     */
    public void putCommands(){
        commands.put("movement", new GoTo(player, gameData));
    }

    /**
     * method that starts the game
     */
    public void start(){
        putCommands();
    }

    public GameData getGameData() {
        return gameData;
    }

    public Player getPlayer() {
        return player;
    }
    /*private Room currentRoom;
    private ArrayList<Room> allRooms;
    private int timeRemaining;
    private int loopCount;
    private boolean isRunning;

    public void startGame() {}
    public void update() {}
    public void resetLoop() {}
    public void checkWinCondition() {}
    public void checkLoseCondition() {}*/
}

