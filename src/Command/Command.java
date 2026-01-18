package Command;

public abstract class Command {
    protected String command;
    public void setCommand(String command) {}
    public abstract String execute();
    public abstract boolean exit();

}
