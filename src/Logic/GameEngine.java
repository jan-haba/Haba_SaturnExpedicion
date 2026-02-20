package Logic;

import Command.*;
import Objects.Player;

public class GameEngine {
    private Player player;
    private GameData gameData;
    private Console console;

    public GameEngine( ) {
        this.gameData = GameData.loadGameDataFromResources("/gamedata.json");
        gameData.convertItems();
        gameData.convertObjects();
        gameData.linkToolsToToolkits();
        gameData.linkItemsToRooms();
        gameData.linkObjectsToRooms();
        gameData.linkItemsToCharacters();
        gameData.linkCharactersToRooms();
        this.player = new Player("Markus Hayers",gameData.locateRoom("Reactor"),false);
        this.console = new Console(player,gameData);

    }
    /**
     * method that starts the game
     */
    public void start(){
        console.start();
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

