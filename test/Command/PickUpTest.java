package Command;

import Items.Item;
import Logic.GameData;
import Objects.Player;
import Objects.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PickUpTest {
    Player player;
    Room room;
    Item item;
    GameData gameData;
    PickUp pickUp;

    @BeforeEach
    void setUp() {
        player = new Player("Marcus", null, true);
        room = new Room("A", "a", "a", true, 0);
        item = new Item("Item","Item","item","item of the item the itemest item of the all");

        gameData = new GameData();

        room.addItem(item);
        gameData.getRooms().add(room);
        player.setCurrRoom(room);

        pickUp = new PickUp(player);
    }

    @Test
    void execute() {
        pickUp.execute("item");
        assertTrue(player.hasItem("item"));
        assertFalse(room.getItems().contains(item));
    }
}