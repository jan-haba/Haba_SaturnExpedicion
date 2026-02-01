package Command;

public class End implements Command{
    @Override
    public String execute(String command) {
        return "Exiting game";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
