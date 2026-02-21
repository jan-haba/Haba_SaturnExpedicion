package Logic;

import Command.*;
import Objects.Player;

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
        commands.put("talk", new Talk(player));
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
        if (command.isEmpty()){
            return;
        }
        if ("DIALOG".equals(data.getGameState())) {
            //handleDialogInput(input);
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
        }while (!exit);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
    public String getKeySet(){
        putCommands();
        return commands.keySet().toString();
    }

}
