package Logic;

import Command.*;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.start();
        System.out.println(gameEngine.getGameData().getItems());
        System.out.println(gameEngine.getGameData().getItems().size());
        System.out.println(gameEngine.getGameData().getRooms());
        System.out.println(gameEngine.getPlayer());
    }
}
