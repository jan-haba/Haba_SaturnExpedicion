package Items;

import java.util.Random;

public class Note extends Item {
    private int code;
    private String roomID;

    public Note(String name, String type, String id, String description, int code, String roomID) {
        super(name, type, id, description);
        this.code = code;
        this.roomID = roomID;
    }

    public int setCode() {
        Random random = new Random();
        this.code = random.nextInt(1000,9999);
        return code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Note{" +
                "code=" + code +
                ", roomID='" + roomID + '\'' +
                '}';
    }

    public String getRoomID() {
        return roomID;
    }
}
