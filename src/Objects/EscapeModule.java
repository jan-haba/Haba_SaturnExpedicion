package Objects;

public class EscapeModule extends GameObject {
    private ControlPanel controlPanel;

    public EscapeModule(String name, String id, String description) {
        super(name, id, description);
    }

    @Override
    public String execute(Player player) {

        if (!controlPanel.isActivated()) {
            return "The module is completely dead. It looks like it needs power from the Control Panel in Electrical.";
        }

        if (!player.hasItem("fuel")) {
            return "The module has power, but the fuel tank is empty. You need to find Fuel.";
        }

        if (!player.hasItem("emergency_kit")) {
            return "You can't leave without an Emergency kit! It's against the safety protocol.";
        }

        if (!player.hasItem("e_m_card_2")) {
            return "The module is ready, but the door is locked. You need E.M. Card 2 to open it.";
        }

        player.setWinState(2);

        return "escape module fixed";
    }

    @Override
    public String toString() {
        return "EscapeModule{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
}
