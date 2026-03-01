package Logic;

import Command.*;
import Objects.Player;
import Objects.Character;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The main input parser and game loop controller.
 * Handles reading user input, executing corresponding commands, managing the dialogue system,
 * and checking for game-ending conditions (win/loss).
 */
public class Console {
    private Scanner scanner;
    private HashMap<String, Command>commands;
    private boolean exit;
    private Player player;
    private GameData data;

    /**
     * Registers all available commands in the game and maps them to their trigger words.
     */
    private void putCommands(){
        commands.put("go",new GoTo(this.player,this.data));
        commands.put("help", new Help(data));
        commands.put("inventory", new Inventory(player));
        commands.put("pick", new PickUp(player));
        commands.put("drop", new DropDown(player));
        commands.put("end", new End());
        commands.put("equip", new Equip(player));
        commands.put("interact",new Interact(player));
        commands.put("examine", new Examine(player));
        commands.put("talk", new Talk(player, data));
        commands.put("time", new Time(player));
        commands.put("description", new Description(player));
        commands.put("clue", new Clue(player,data));
    }

    public Console(Player player, GameData data) {
        this.scanner =  new Scanner(System.in);
        this.commands = new HashMap<>();
        this.player = player;
        this.data = data;
        this.exit = false;
    }
    public Console(){
        this.scanner =  new Scanner(System.in);
        this.commands = new HashMap<>();
        this.exit = false;
    }

    /**
     * Reads the player's input from the console, parses it, and executes the corresponding logic.
     * It also handles the split between normal exploration commands and numeric dialogue choices.
     */
    private void execute(){
        System.out.print(">>");
        String command = scanner.nextLine().trim();
        if (player.getWinState() == -1) {
            this.exit = true;
            return;
        }
        if (command.isEmpty()){
            return;
        }
        if ("DIALOG".equals(data.getGameState())) {
            handleDialogInput(command);
            return;

        }
        String[] parts = command.split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        if (commands.containsKey(commandName)){
            System.out.println( commands.get(commandName).execute(argument));
            exit = commands.get(commandName).exit();
        }
        else{
            System.out.println("Command " + command +  " doesn't exist.");
        }
    }

    /**
     * Starts the main interactive loop of the console.
     * Continuously prompts for input and checks for win conditions until the exit flag is set.
     */
    public void start(){
        putCommands();
        do {
            execute();
            checkWinConditions();
        }while (!exit);
    }

    /**
     * Updates the game data reference and re-registers commands to ensure they use the new data.
     * Used mainly after a time loop reset.
     * @param data the new GameData instance
     */
    public void setGameData(GameData data) {
        this.data = data;
        putCommands();
    }

    /**
     * Checks if the player has triggered a win scenario (fixing the reactor or escaping).
     * If true, prints the respective ending text and flags the console to exit.
     */
    private void checkWinConditions() {
        int state = player.getWinState();

        if (state == 1) {
            System.out.println("\n=======================================================================");
            System.out.println("                         REACTOR ENDING ACHIEVED                          ");
            System.out.println("=======================================================================");
            System.out.println("The alarm stops ringing. The ship's systems return to normal.");
            System.out.println("You wiped the sweat from your forehead. You just saved the entire crew!");
            System.out.println("Mission will continue and everyone will celebrate.");
            System.out.println("=======================================================================\n");
            this.exit = true;
        }
        else if (state == 2) {

            if (player.isToldCaptain() || player.isToldCaroline()) {
                System.out.println(data.getUI().get("win_betrayal"));
                player.setWinState(-1);
            }
            else if (player.isLucyWithMe()) {
                System.out.println(data.getUI().get("win_with_lucy"));
                this.exit = true;
            }
            else {
                System.out.println(data.getUI().get("win_solo"));
                this.exit = true;
            }
        }
    }

    /**
     * Processes numeric input during an active dialogue with an NPC.
     * Manages item giving, follower toggling, and changing the game state back to exploration.
     * @param input the numeric choice inputted by the player
     */
    private void handleDialogInput(String input) {
        try {
            int choice = Integer.parseInt(input);
            Character npc = data.getActiveCharacter();

            if (choice == 0) {
                System.out.println("You have exited the dialogue.");
                data.setGameState("EXPLORING");
                data.setActiveCharacter(null);
                return;
            }

            if (npc.getDialogue().containsKey(choice)) {
                System.out.println("You have said: " + npc.getDialogue().get(choice));
                switch (npc.getName().toLowerCase()) {
                    case "lucy":
                        if (choice == 1) {
                            npc.setFollower(false);
                            System.out.println("Lucy: 'Alright, I'll wait for you here.'");
                        } else if (choice == 2) {
                            npc.setFollower(true);
                            player.setLucyWithMe(true);
                            System.out.println("Lucy: 'Okay, I'll come with you. Don't leave me alone.'");
                            player.getCurrRoom().getCharacters().remove(npc);
                        }
                        break;

                    case "captain":
                        if (choice == 1) {
                            Items.Item card = npc.giveItem("reactor_card");
                            if (card != null) {
                                player.pickUp(card);
                                System.out.println("Captain: 'Here is the card. And don't you dare mess this up!'");
                                System.out.println("(Reactor Card was added to your inventory)");
                            } else {
                                System.out.println("Captain: 'I already gave it to you, idiot!'");
                            }
                        } else if (choice == 2) {
                            System.out.println("I'm not giving you anything until you warn Caroline. Go tell her, then we'll talk.");
                            System.out.println("Captain: 'I'm not giving you anything until you warn Caroline. Go tell her, then we'll talk.'");
                            System.out.println("The Captain pushes past you and rushes towards Escape Module 2.");
                            player.getCurrRoom().getCharacters().remove(npc);
                            player.setToldCaptain(true);
                            data.locateRoom("Escape Module 2").addCharacter(npc);
                            npc.getDialogue().remove(2);

                        } else if (choice == 3) {
                            System.out.println("Captain: 'Finally a reasonable idea. See ya, Hayers!'");
                            System.out.println("The Captain cowardly abandons his post and sprints towards Escape Module 2.");
                            player.getCurrRoom().getCharacters().remove(npc);
                            player.setToldCaptain(true);
                            data.locateRoom("Escape Module 2").addCharacter(npc);
                            npc.getDialogue().remove(3);
                        } else if (choice == 4) {
                            System.out.println("You left the Captain alone.");
                        }
                        break;

                    case "caroline":
                        if (choice == 1) {
                            System.out.println("Caroline: 'Fine! I wasn't talking to you anyway.'");
                            System.out.println("Caroline packs her things and runs away towards Escape Module 2.");
                            player.getCurrRoom().getCharacters().remove(npc);
                            player.setToldCaroline(true);
                            data.locateRoom("Escape Module 2").addCharacter(npc);
                        } else if (choice == 2) {
                            System.out.println("You left Caroline alone.");
                        }
                        break;
                }
                data.setGameState("EXPLORING");
                data.setActiveCharacter(null);
            } else {
                System.out.println("Wrong number");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input write number");
        }
    }
    public void setExit(boolean exit) {
        this.exit = exit;
    }
}