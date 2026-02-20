package Objects;

public class Reactor extends GameObject{
    private boolean isFixed;

    public Reactor(String name, String id, String description, boolean isFixed) {
        super(name, id, description);
        this.isFixed = isFixed;
    }

    @Override
    public String execute(Player player) {


        return super.execute(player);
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
}
