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
    public String execute(Player player, String string) {
        if (string == null || string.isEmpty()) {
            return "You need to specify what you want to take from the toolkit.\n" + this.getDescription();
        }
        Item foundTool = null;
        for (Item tool : this.tools) {
            if (tool.getName().equalsIgnoreCase(string) || tool.getId().equalsIgnoreCase(string)) {
                foundTool = tool;
                break;
            }
        }
        if (foundTool != null) {
            player.addItem(foundTool);
            this.tools.remove(foundTool);
            return "You took the " + foundTool.getName() + " from the toolkit.";
        }
        return "There is no item named '" + string + "' in the toolkit.";
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
            if (item.getName().equals(name)) {
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

