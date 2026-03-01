package Objects;

import java.util.Scanner;

/**
 * Represents the ship's Escape Module.
 * This is a critical game object that allows the player to achieve the "Survivor Ending".
 * To use it, the player must meet a series of strict requirements (power, fuel, kit, and keycard).
 */
public class EscapeModule extends GameObject {
    private ControlPanel controlPanel;

    public EscapeModule(String name, String id, String description) {
        super(name, id, description);
    }

    /**
     * Executes the interaction logic for attempting to launch the escape module.
     * Checks all necessary conditions sequentially. If all are met, sets the player's
     * win state to 2 (Survivor Ending).
     * @param player the player attempting to use the module
     * @return a message describing what is missing, or a success message if launching
     */
    @Override
    public String execute(Player player) {
        if (!controlPanel.isActivated()) {
            return "The module is completely dead. It looks like it needs power from the Control Panel in Electrical.";
        }

        if (player.getWinState() == 2) {
            return "The escape module is ready for launch. Get in!";
        }

        System.out.println("\n[INITIATING ESCAPE MODULE PREPARATION...]");
        Scanner scanner = new Scanner(System.in);

        boolean fuelAdded = false;
        boolean kitAdded = false;
        boolean cardInserted = false;

        while (true) {
            String problem = "";
            String requiredItem = "";

            if (!fuelAdded) {
                problem = "The fuel tank is empty. The engines won't prime without Fuel.";
                requiredItem = "Fuel";
            } else if (!kitAdded) {
                problem = "Safety protocols: The pod is missing an Emergency kit.";
                requiredItem = "Emergency kit";
            } else if (!cardInserted) {
                problem = "The hatch is locked. You need E.M.Card 2 to gain access.";
                requiredItem = "E.M.Card 2";
            } else {
                player.setWinState(2);
                return "\n==================================================" +
                        "\n[SYSTEM READY: ESCAPE POD FULLY PREPARED!]" +
                        "\n==================================================";
            }

            System.out.println("\n[MODULE STATUS: INCOMPLETE]");
            System.out.println(problem);
            System.out.println("What item do you want to use? (Type the item name or 'exit')");
            System.out.print(">> ");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit") || input.equals("0")) {
                return "You stepped away from the module. Preparation paused.";
            }
            if (input.equalsIgnoreCase(requiredItem)) {

                if (player.hasItemName(requiredItem)) {
                    player.removeItem(requiredItem);

                    if (requiredItem.equals("Fuel")) {
                        fuelAdded = true;
                        System.out.println(">>> SUCCESS: You fueled the escape pod.");
                    } else if (requiredItem.equals("Emergency kit")) {
                        kitAdded = true;
                        System.out.println(">>> SUCCESS: Emergency kit secured inside.");
                    } else if (requiredItem.equals("E.M.Card 2")) {
                        cardInserted = true;
                        System.out.println(">>> SUCCESS: Card accepted! Hatch is now open.");
                    }
                } else {
                    System.out.println(">>> ERROR: You don't have '" + requiredItem + "' in your inventory!");
                }
            } else {
                System.out.println(">>> ERROR: Using '" + input + "' here doesn't help.");
            }
        }
    }

    @Override
    public String toString() {
        return "EscapeModule{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
}