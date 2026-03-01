package Logic;

import Command.*;
import Objects.Player;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The main controller class of the game (Game Engine).
 * It handles the initialization of the map, execution of the main game loop,
 * management of the user menu, and the real-time background timer.
 */
public class GameEngine {
    private Player player;
    private GameData gameData;
    private Console console;
    private Timer timer;

    /**
     * Constructor for GameEngine.
     * Loads and links game data from the JSON file and creates an instance
     * of the player (Markus Hayers), placing him in the starting room (Reactor).
     */
    public GameEngine( ) {
        loadAndLinkData();
        this.player = new Player("Markus Hayers",gameData.locateRoom("Reactor"),false,0,0,1,"",false, false,false);
        this.console = new Console(player,gameData);
    }

    /**
     * Loads all data from the gamedata.json file and interconnects the game elements.
     * For example, it assigns items and characters to their respective rooms
     * and sets required codes for locked doors.
     */
    private void loadAndLinkData() {
        this.gameData = GameData.loadGameDataFromResources("/gamedata.json");
        gameData.convertItems();
        gameData.convertObjects();
        gameData.linkToolsToToolkits();
        gameData.linkItemsToRooms();
        gameData.linkObjectsToRooms();
        gameData.linkItemsToCharacters();
        gameData.linkCharactersToRooms();
        gameData.setCodesToRooms();
    }

    /**
     * The entry point for starting the game.
     * Opens the main text menu.
     */
    public void start(){
        startMenu();
    }

    /**
     * Displays the introductory text menu.
     * Provides options to start the game, read story information, or exit the application.
     */
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean inMenu = true;

        while (inMenu) {
            System.out.println(gameData.logo());
            System.out.println(gameData.getUI().get("menu"));

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println(gameData.getUI().get("story"));

                    System.out.println("Will you try to SAVE THE SHIP (type 'reactor') or ESCAPE THE SHIP (type 'escape')?");
                    System.out.print("Make your choice >> ");

                    String pathChoice = scanner.nextLine().trim().toLowerCase();
                    while (!pathChoice.equals("reactor") && !pathChoice.equals("escape")) {
                        System.out.print("Invalid choice. Please type 'reactor' or 'escape' >> ");
                        pathChoice = scanner.nextLine().trim().toLowerCase();
                    }
                    player.setChoosenPath(pathChoice);

                    startGameLoop();
                    inMenu = false;
                    break;
                case "2":
                    printAbout();
                    break;
                case "3":
                    System.out.println(gameData.getUI().get("exit"));
                    inMenu = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please type 1, 2, or 3.");
            }
        }
    }

    /**
     * The main game loop.
     * Runs continuously until the player wins or manually exits the game.
     * Starts the real-time timer, hands over control to the console for command inputs,
     * and checks the win/loss state after the console session ends.
     */
    public void startGameLoop() {
        while (true) {

            System.out.println("\nLucy is standing right in front of you, looking worried.");
            System.out.println("\nWrite help to show all of the available commands (write description to learn more about your current room).");

            console.setExit(false);
            player.setTimeRemaining(3600);
            startRealTimeTimer();

            console.start();


            stopTimer();

            int state = player.getWinState();
            if (state == 1 || state == 2) {
                break;
            } else if (state == -1) {
                resetTimeLoop();
            } else {
                break;
            }
        }
    }

    /**
     * Starts a background thread that deducts the player's remaining time every second.
     * If the time runs out, it sets the player's state to a loss (-1) and prompts
     * the player to initiate the next time loop.
     */
    private void startRealTimeTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (player.getWinState() != 0) {
                    timer.cancel();
                    return;
                }

                player.setTimeRemaining(player.getTimeRemaining() - 1);

                if (player.getTimeRemaining() <= 0) {
                    player.setWinState(-1);
                    System.out.println("\n\n=======================================================");
                    System.out.println("                 !!! OUT OF TIME !!!                   ");
                    System.out.println(" The reactor exploded! The ship is falling apart!      ");
                    System.out.println("=======================================================");
                    System.out.println(">>> PRESS 'ENTER' TO INITIATE TIME LOOP <<<");
                    System.out.print(">> ");
                    timer.cancel();
                }
            }
        }, 1000, 1000); // 1-second delay, repeating every 1 second
    }

    /**
     * Safely stops and destroys the running background timer, if it exists.
     */
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Handles the Time Loop logic after the time runs out.
     * Increments the loop counter, generates a fresh map from the JSON file
     * (locking doors, resetting items/characters), moves the player back
     * to the start (Reactor), but preserves the player's current inventory.
     */
    private void resetTimeLoop() {
        player.incrementLoop();
        System.out.println("\n*************************************************************************");
        System.out.println("Everything goes dark... You feel a strange pulling sensation.");
        System.out.println("...");
        System.out.println("You wake up with a gasp. Lucy is shaking you.");
        System.out.println("LOOP " + player.getLoopCount() + " INITIATED.");
        System.out.println("*************************************************************************\n");

        loadAndLinkData();

        player.setCurrRoom(gameData.locateRoom("Reactor"));
        player.setWinState(0);

        console.setGameData(gameData);
    }

    /**
     * Prints the game's background story and author information.
     * Waits for the user to press Enter before returning to the main menu.
     */
    private void printAbout() {
        System.out.println(gameData.getUI().get("about"));
        new Scanner(System.in).nextLine();
    }

    public Player getPlayer() {
        return player;
    }
}

