package Items;

import Objects.Player;

import java.util.ArrayList;

public class Toolkit extends Item {
    private ArrayList<Item>tools =  new ArrayList<>();
    private ArrayList<String>toolsRaw;

    public Toolkit(String name, String type, String id, String description, ArrayList<String> toolsRaw) {
        super(name, type, id, description);
        this.toolsRaw = toolsRaw;
    }

    @Override
    public Item execute(Player player, String string) {
        return getItem(string);
    }
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(super.getDescription());
        if (this.tools == null || this.tools.isEmpty()) {
            sb.append("\nIt is currently empty.");
        } else {
            sb.append("\nInside you can see:");
            for (Item tool : this.tools) {
                sb.append("\n - ").append(tool.getName());
            }
        }
        return sb.toString();
    }

    public void addItem(Item item) {
        tools.add(item);
    }

    public Item getItem(String name) {
        for (Item item : tools) {
            if (item.getName().equalsIgnoreCase(name)) {
                Item tool = item;
                tools.remove(item);
                return item;
            }
        }
        return null;
    }

    public ArrayList<String> getToolsRaw() {
        return toolsRaw;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tools: " + this.tools;
    }
}

