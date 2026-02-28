package Command;

import Objects.Player;

public class Time implements Command{
    private Player player;

    public Time(Player player) {
        this.player = player;
    }

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

    @Override
    public boolean exit() {
        return false;
    }
}
