package Logic;

import Command.*;
import Objects.Player;
import Objects.Character;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private HashMap<String, Command>commands;
    private boolean exit;
    private Player player;
    private GameData data;

    private void putCommands(){
        commands.put("go",new GoTo(this.player,this.data));
        commands.put("help", new Help());
        commands.put("inventory", new Inventory(player));
        commands.put("pick", new PickUp(player));
        commands.put("drop", new DropDown(player));
        commands.put("end", new End());
        commands.put("equip", new Equip(player));
        commands.put("interact",new Interact(player));
        commands.put("examine", new Examine(player));
        commands.put("talk", new Talk(player, data));
        commands.put("time", new Time(player));
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
    public void start(){
        putCommands();
        do {
            execute();
            checkWinConditions();
        }while (!exit);
    }

    public void setGameData(GameData data) {
        this.data = data;
        putCommands();
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    private void checkWinConditions() {
        int state = player.getWinState();

        if (state == 1) {
            System.out.println("\n=======================================================================");
            System.out.println("                         HERO ENDING ACHIEVED                          ");
            System.out.println("=======================================================================");
            System.out.println("The alarm stops ringing. The ship's systems return to normal.");
            System.out.println("You wiped the sweat from your forehead. You just saved the entire crew!");
            System.out.println("Mission will continue and everyone will celebrate.");
            System.out.println("=======================================================================\n");
            this.exit = true;
        }
        else if (state == 2) {
            System.out.println("\n=======================================================================");
            System.out.println("                       SURVIVOR ENDING ACHIEVED                        ");
            System.out.println("=======================================================================");
            System.out.println("The engine ignites with a massive roar, and you shoot out into space,");
            System.out.println("leaving the doomed ship Mi Bomdo TX670 behind.");
            System.out.println("You survived... but was it the right choice?");
            System.out.println("=======================================================================\n");
            this.exit = true;
        }
    }

    public String getKeySet(){
        putCommands();
        return commands.keySet().toString();
    }

    /**
     * method that handles dialogues and their different outcomes
     * @param input name of the character
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
                            System.out.println("Lucy: 'Okay, I'll come with you. Don't leave me alone.'");
                            player.getCurrRoom().getCharacters().clear();
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
                            //TODO: CAPTAIN runs to EM

                        } else if (choice == 3) {
                            System.out.println("Captain: 'Finally a reasonable idea. See ya, Hayers!'");
                            // TODO: Captain runs away logic
                        } else if (choice == 4) {
                            System.out.println("You left the Captain alone.");
                        }
                        break;

                    case "caroline":
                        if (choice == 1) {
                            System.out.println("Caroline: 'Fine! I wasn't talking to you anyway.'");
                            // TODO: Caroline runs away logic
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
}
