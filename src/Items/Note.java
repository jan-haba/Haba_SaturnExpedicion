package Items;

import java.util.Random;

public class Note extends Item {
    private int code;

    public Note(String name, String type, String id, String description, int code) {
        super(name, type, id, description);
        this.code = code;
    }

    public void setCode() {
        Random random = new Random();
        this.code = random.nextInt(1000,9999);
    }

    @Override
    public String toString() {
        return super.toString() + " | code: " + this.code;
    }
}
