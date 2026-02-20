package Logic;

import Command.*;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        System.out.println(gameEngine.getGameData().getGameObjects());
        gameEngine.start();


    }
}
