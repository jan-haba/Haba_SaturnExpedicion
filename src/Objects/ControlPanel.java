package Objects;

/**
 * Class representing the Control Panel in the Electrical room.
 */
public class ControlPanel extends GameObject {
    private boolean isActivated;

    public ControlPanel(String name, String id, String description, boolean isActivated) {
        super(name, id, description);
        this.isActivated = isActivated;
    }

    /**
     * Executes interaction with the Control Panel.
     * @param player the player interacting with the object
     * @return message describing the result of the interaction
     */
    @Override
    public String execute(Player player) {
        if (!isActivated) {
            isActivated = true;
            return "You flipped the heavy switches. The Control Panel hums to life.\n" +
                    "*** Power has been restored to Escape Module 2! ***";
        } else {
            return "The Control Panel is already fully operational. All lights are green.";
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

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
