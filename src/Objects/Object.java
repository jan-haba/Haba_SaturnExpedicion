package Objects;

import Items.Item;
import java.util.ArrayList;

public class Object {
    private String name;
    private String description;
    private boolean isLocked;
    private int requiredCode;
    private ArrayList<Item> requiredItems;
    private boolean isCompleted;
    private int progressStatus;

    public String interact(Player player) {
        return null;
    }
    public boolean unlock(int code) {
        return false;
    }
    public String useItem(Item item) {
        return null;
    }
    public void applyAction(int actionID) {
    }
    public void resetProgress() {
    }
    public boolean isActionValid(Item item) {
        return false;
    }
}
