package Command;

import Objects.Player;

/**
 * Command for checking the remaining time and the current time loop.
 * Displays the time left before the ship's reactor explodes in MM:SS format.
 */
public class Time implements Command{
    private Player player;

    public Time(Player player) {
        this.player = player;
    }

    /**
     * Executes the time command.
     * Calculates the remaining minutes and seconds and formats them into a status message.
     * * @param command any additional arguments provided by the player (ignored for this command)
     * @return a formatted string displaying the temporal status (time left and current loop)
     */
    @Override
    public String execute(String command) {
        int totalSeconds = player.getTimeRemaining();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        String formattedTime = String.format("%02d:%02d", minutes, seconds);

        return ">>> TEMPORAL STATUS <<<\n" +
                "Time remaining before explosion: " + formattedTime + "\n" +
                "Current loop: " + player.getLoopCount();
    }

    /**
     * Determines if executing this command should exit the game.
     * * @return false, as checking the time does not terminate the main game loop
     */
    @Override
    public boolean exit() {
        return false;
    }
}
