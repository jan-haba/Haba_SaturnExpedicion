package Command;

import Logic.GameData;
import Objects.Player;
import Objects.Room;

import java.util.Scanner;

/**
 * Command for player movement.
 * Allows the player to navigate between connected rooms.
 */
public class GoTo implements Command {
    private Player player;
    private GameData data;

    public GoTo(Player player,GameData data) {
        this.player = player;
        this.data = data;
    }

    /**
     * Executes the movement command.
     * Checks if the player provided a destination and initiates the movement.
     * @param command the name of the destination room provided by the player
     * @return a message indicating the result of the movement attempt
     */
    @Override
    public String execute(String command) {
        if (command.isEmpty()) {
            return "Please specify where you want to go.";
        }
        return move(command);
    }

    /**
     * Handles the logic of moving the player to a new room.
     * Validates if the destination is a valid exit from the current room.
     * @param roomName the name of the room the player wants to move to
     * @return the description of the new room if movement is successful, or an error message if not
     */
    public String move(String roomName){
        String actualExit = null;

        for (String exit : player.getCurrRoom().getExits()) {
            if (exit.equalsIgnoreCase(roomName)) {
                actualExit = exit;
                break;
            }
        }

        if (actualExit != null) {
            Room targetRoom = data.locateRoom(actualExit);

            if (!targetRoom.isAccessible()) {

                if (targetRoom.getRequiredCode() == -1) {
                    return "The door to " + targetRoom.getName() + " is completely jammed or destroyed. You cannot enter.";
                }

                System.out.println("\n[SECURITY SYSTEM]");
                System.out.println("The door to " + targetRoom.getName() + " is locked.");
                System.out.println("A keypad is waiting for a PIN code (type 'exit' to step back).");
                System.out.print("Enter code >> ");

                @SuppressWarnings("resource")
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    return "You stepped away from the door.";
                }

                try {
                    int enteredCode = Integer.parseInt(input);
                    if (enteredCode == targetRoom.getRequiredCode()) {
                        targetRoom.setAccessible(true);
                        player.setCurrRoom(targetRoom);
                        return "\n[ACCESS GRANTED] The door unlocks and opens...\n" + player.getCurrRoom().getRoom();
                    } else {
                        return "[ACCESS DENIED] Incorrect code. The door remains locked.";
                    }
                } catch (NumberFormatException e) {
                    return "[ERROR] Invalid input. The keypad only accepts numbers.";
                }
            }

            player.setCurrRoom(targetRoom);
            return player.getCurrRoom().getRoom() + "\nYou moved to " + targetRoom.getName() + ".";

        } else {
            return "Room wasn't found or it doesn't connect to your current location.";
        }
    }

    /**
     * Determines if executing this command should exit the game.
     * @return false, as moving does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}
