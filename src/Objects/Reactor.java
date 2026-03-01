package Objects;

import java.util.Scanner;

/**
 * Class for Reactor
 */
public class  Reactor extends GameObject{
    private boolean isFixed;
    private boolean isFireExtinguished ;
    private boolean isCardInserted;
    private boolean isCalibrated;
    private boolean isWelded ;

    public Reactor(String name, String id, String description, boolean isFixed, boolean isFireExtinguished, boolean isCardInserted, boolean isCalibrated, boolean isWelded) {
        super(name, id, description);
        this.isFixed = isFixed;
        this.isFireExtinguished = isFireExtinguished;
        this.isCardInserted = isCardInserted;
        this.isCalibrated = isCalibrated;
        this.isWelded = isWelded;
    }

    /**
     * Method for fixing of the Reactor
     * @param player gives method a way to interact with player
     * @return state of the Reactor if it is fixed or not and what you need to fix it.
     */
    @Override
    public String execute(Player player) {
        if (isFixed) {
            return "The reactor is fully stabilized and humming quietly. Good job!";
        }

        if (!player.isSuitEquiped()) {
            return "WARNING: Lethal radiation levels detected! You cannot approach the reactor without a Suit equipped.";
        }

        System.out.println("\n[INITIATING REACTOR REPAIR SEQUENCE...]");

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        while (!isFixed) {
            String problem = "";
            String requiredItem = "";

            if (!isFireExtinguished) {
                problem = "Flames are engulfing the main console! You need to put out the fire.";
                requiredItem = "fire extinguisher";
            } else if (!isCardInserted) {
                problem = "The core access panel is locked. A blinking slot waits for authorization.";
                requiredItem = "reactor card";
            } else if (!isCalibrated) {
                problem = "The energy output is completely unstable! The core needs calibration.";
                requiredItem = "calibrator";
            } else if (!isWelded) {
                problem = "Coolant pipes are breached and leaking! They need to be sealed immediately.";
                requiredItem = "welding machine";
            } else {
                this.isFixed = true;
            }

            System.out.println("\n[REACTOR STATUS: CRITICAL]");
            System.out.println(problem);
            System.out.println("What item do you want to use? (Type the item name or 'exit' to step back)");
            System.out.print(">> ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit") || input.equals("0")) {
                return "You stepped away from the reactor. The repair sequence is paused.";
            }

            if (input.equalsIgnoreCase(requiredItem)) {

                if (player.getItem(requiredItem) != null) {
                    player.removeItem(requiredItem);

                    if (!isFireExtinguished) {
                        isFireExtinguished = true;
                        System.out.println(">>> SUCCESS: You sprayed the Fire Extinguisher. The flames are completely gone!");
                    } else if (!isCardInserted) {
                        isCardInserted = true;
                        System.out.println(">>> SUCCESS: You swiped the Reactor Card. Access granted!");
                    } else if (!isCalibrated) {
                        isCalibrated = true;
                        System.out.println(">>> SUCCESS: You hooked up the Calibrator. Energy levels are stabilizing.");
                    } else if (!isWelded) {
                        isWelded = true;
                        System.out.println(">>> SUCCESS: You used the Welding Machine to seal the pipes.");

                        this.isFixed = true;
                        player.setWinState(1);
                        return "\n==================================================" +
                                "\n[SYSTEM ALERT: ALL REACTOR SYSTEMS STABILIZED!]" +
                                "\n==================================================";
                    }
                } else {
                    return "You don't have '" + input + "' in your inventory! Repair sequence aborted.";
                }

            } else {
                if (player.getItem(input) != null) {
                    return "You try to use '" + input + "', but it has no effect here. Repair sequence aborted.";
                } else {
                    return "You don't have '" + input + "' in your inventory. Repair sequence aborted.";
                }
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "Reactor{" +
                "isFixed=" + isFixed +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================================================\n");
        sb.append("                 ").append(name.toUpperCase()).append("\n");
        sb.append("==================================================\n");
        sb.append("DESCRIPTION: ").append(description).append("\n");

        sb.append("STATUS: ");
        if (isFixed) {
            sb.append("FIXED\n");
        } else {
            sb.append("BROKEN\n");
        }
        sb.append("==========================================\n");

        return sb.toString();
    }
}