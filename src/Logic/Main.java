package Logic;

import Command.*;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.start();
        System.out.println(gameEngine.getGameData().getRooms());
        System.out.println();

        GoTo goTo = new GoTo(gameEngine.getPlayer(), gameEngine.getGameData());
        //vypada to hnusne ale ted jsem jenom nastavoval mistnost pro playera
        gameEngine.getPlayer().setCurrRoom(gameEngine.getGameData().locateRoom("Hall"));
        //pohyb do mistnosti ktera existuje a je napojena na mistnost
        goTo.move("Electrical");
        System.out.println(gameEngine.getPlayer().getCurrRoom());
        goTo.move("Hall");
        System.out.println(gameEngine.getPlayer().getCurrRoom());
        goTo.move("Electrical");
        //pohyb do mistnosti ktera existuje ale neni napojena na Electrical
        System.out.println(gameEngine.getPlayer().getCurrRoom());
        goTo.move("Admin");
        System.out.println(gameEngine.getPlayer().getCurrRoom());
        //pohyb do mistnosti ktera neexistuje
        goTo.move("Livning Room");
        System.out.println(gameEngine.getPlayer().getCurrRoom());
    }
}
