package Items;

import java.util.Random;

/**
 * Represents a note item containing a generated access code.
 * Notes are typically used by the player to find codes needed to unlock specific doors or systems.
 */
public class Note extends Item {
    private int code;
    private String roomID;

    public Note(String name, String type, String id, String description, int code, String roomID) {
        super(name, type, id, description);
        this.code = code;
        this.roomID = roomID;
    }

    /**
     * Generates a random 4-digit code (from 1000 to 9999) and assigns it to this note.
     * @return the newly generated code
     */
    public int setCode() {
        Random random = new Random();
        this.code = random.nextInt(1000,10000);
        return code;
    }

    @Override
    public String toString() {
        return "Note{" +
                "code=" + code +
                ", roomID='" + roomID + '\'' +
                '}';
    }

    @Override
    public String getDescription() {
        return "\n[ WRITTEN PIN CODE: " + this.code + " ]";
    }

    public String getRoomID() {
        return roomID;
    }

    public int getCode() {
        return code;
    }
}
