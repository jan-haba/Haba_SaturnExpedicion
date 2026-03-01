package Objects;

import Items.Item;
import java.util.ArrayList;

/**
 * Class for Storage Rack
 */
public class Storage extends GameObject{
    private ArrayList<Item> items;

    public Storage(String name, String id, String description, ArrayList<Item> items) {
        super(name, id, description);
        this.items = items;
    }

    @Override
    public String execute(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================================================").append("\n                 " + name.toUpperCase()).append("\n==================================================");
        sb.append("\nCONTENT: ");

        if (items == null || items.isEmpty()) {
            sb.append(" - [Empty]");
        } else {
            sb.append("\n");
            for (Item item : items) {
                sb.append("- ").append(item.getName()).append("\n");
            }
        }
        sb.append("==================================================");
        sb.append("\nType 'pick <item name>' if you want to get something from the rack.");
        return sb.toString();
    }

    /**
     * Method that gets Item from Storage
     * @param itemName name of the Item we wanna get
     * @return Item we wanna get
     */
    public Item findItem(String itemName){
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(itemName)){
                items.remove(i);
                return item;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "Storage{" +
                "items=" + items +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Writes the content of the storage rack
     * @return content of the storage rack
     */
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==================================================").append("\n                 " + name.toUpperCase()).append("\n==================================================");
        sb.append("\nDESCRIPTION: ").append(description).append("\n");
        sb.append("CONTENT: ");

        if (items == null || items.isEmpty()) {
            sb.append(" - [Empty]");
        } else {
            sb.append("\n");
            for (Item item : items) {
                sb.append("- ").append(item.getName()).append("\n");
            }
        }
        sb.append("==================================================");
        return sb.toString();
    }
}
