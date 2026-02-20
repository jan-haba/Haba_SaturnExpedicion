package Objects;

public class Reactor extends GameObject{
    private boolean isFixed;
    private int phase;

    public Reactor(String name, String id, String description, boolean isFixed, int phase) {
        super(name, id, description);
        this.isFixed = isFixed;
        this.phase = phase;
    }

    @Override
    public String execute(Player player) {
        if (!player.hasItem("fire_extinguisher")){
            return "zou need to get rid of the fire";
        }
        if (!player.isSuitEquiped()){
            return "equip suit there is radation";
        }
        if (!player.hasItem("reactor_card")){
            return "you need reactor card";
        }
        if (!player.hasItem("marks_toolkit"))
            return "you need toolkit to fix this";
        isFixed = true;
        return "reactor is fixed";
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
