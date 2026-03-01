package Objects;

import java.util.*;

/**
 * Class representing the Control Panel in the Electrical room.
 */
public class ControlPanel extends GameObject {
    private boolean isActivated;
    private HashMap<String,String>puzzles;

    public ControlPanel(String name, String id, String description, boolean isActivated, HashMap<String, String> puzzles) {
        super(name, id, description);
        this.isActivated = isActivated;
        this.puzzles = puzzles;
    }

    /**
     * Executes interaction with the Control Panel.
     * @param player the player interacting with the object
     * @return message describing the result of the interaction
     */
    @Override
    public String execute(Player player) {
        if (isActivated) {
            return "The Control Panel is already online and humming with energy. The power grid is stable.";
        }

        if (puzzles == null || puzzles.isEmpty()) {
            this.isActivated = true;
            return "SYSTEM OVERRIDE: The Control Panel booted up automatically. Power restored.";
        }

        List<String> questions = new ArrayList<>(puzzles.keySet());
        Random random = new Random();
        String randomQuestion = questions.get(random.nextInt(questions.size()));
        String expectedAnswer = puzzles.get(randomQuestion);

        System.out.println("==================================================");
        System.out.println("                 CONTROL PANEL                           ");
        System.out.println("==================================================");
        System.out.println("[SYSTEM ALERT: MANUAL OVERRIDE REQUIRED]");
        System.out.println("To restore the power grid, solve the security sequence:");
        System.out.println(randomQuestion);
        System.out.println("==================================================");
        System.out.print("Enter override code >> ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase(expectedAnswer)) {
            this.isActivated = true;
            return "Override accepted. Power grid restored. The Control Panel is now ONLINE.\nContinue exploring by typing another command\n==================================================";
        } else {
            return "ERROR: Incorrect override code. Access denied.\nContinue exploring by typing another command or try again.\n==================================================";
        }
    }

    @Override
    public String toString() {
        return "ControlPanel{" +
                "isActivated=" + isActivated +
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
        if (isActivated()) {
            sb.append("[ONLINE]\n");
        } else {
            sb.append("[OFFLINE]\n");
        }
        sb.append("==========================================\n");

        return sb.toString();
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
