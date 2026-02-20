package Objects;

/**
 * Class for Reactor
 */

public class Reactor extends GameObject{
    private boolean isFixed;

    public Reactor(String name, String id, String description, boolean isFixed) {
        super(name, id, description);
        this.isFixed = isFixed;
    }

    /**
     * Method for fixing of the Reactor
     * @param player
     * @return state of the Reactor if it is fixed or not and what you need to fix it.
     */

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
        if (!player.hasItem("calibrator")){
            return "you need calibrator to fix this";

        }
        if (!player.hasItem("welding_machine")){
            return "you need welding machine to fix this";

        }
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
