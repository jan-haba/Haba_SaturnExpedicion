package Logic;

import Objects.Room;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GameData {
    public ArrayList<Room>rooms;

    public GameData() {
        this.rooms = new ArrayList<>();
    }

    /**
     * method that loads all game data from JSON
     * @param resourcePath
     * @return
     */

    public static GameData loadGameDataFromResources(String resourcePath) {
        Gson gson = new Gson();
        try (InputStream is = Main.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Resource wasnt found: " + resourcePath);
            }
            return gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    GameData.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Trouble when loading JSON: " + e.getMessage());
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * method that helps find a room
     * @param roomName = name of that room
     * @return wanted room
     */
    public Room locateRoom(String roomName){
        for (Room room: rooms) {
            if (room.getName().equals(roomName)){
                return room;
            }
        }
        throw new IllegalArgumentException("Room wasnt found with this name");
    }
}
