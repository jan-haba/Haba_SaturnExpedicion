package Command;

import Logic.GameData;
import Objects.Player;
import Objects.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GoToTest {
    Player player;
    Room room1;
    Room room2;
    GoTo goTo;

    private static class FakeGameData extends GameData{
        private final ArrayList<Room>fakeRooms = new ArrayList<>();

        void add (Room room){
            fakeRooms.add(room);
        }
    }
    FakeGameData fakeGameData;

    @BeforeEach
    void setUp() {
        player = new Player("Marcus", null,true);
        room1 = new Room("A","a","a",true,0);
        room2 = new Room("B","b","b",true,0);
        FakeGameData fakeGameData = new FakeGameData();
        room1.getExits().add("B");
        room2.getExits().add("A");
        fakeGameData.add(room1);
        fakeGameData.add(room2);

        player.setCurrRoom(room1);
        goTo = new GoTo(player,fakeGameData);
    }

    @Test
    void execute() {
        goTo.execute("A");
        assertEquals("A",player.getCurrRoom().getName());
    }
}