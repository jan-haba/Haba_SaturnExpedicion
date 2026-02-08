package Command;

import Logic.Console;

public class Help implements Command {
    private Console console = new Console();

    @Override
    public String execute(String command) {
        System.out.println("=== AVAILABLE COMMANDS ===");
        System.out.println(console.getKeySet());
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
