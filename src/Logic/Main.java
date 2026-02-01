package Logic;

import Command.*;

public class Main {
    public static void main(String[] args) {
        //na zjisteni commandu napsat "help"
        //na vyzkouseni inventare "pick"/"drop" Fire Extinguisher a "inventory" na zobrazeni invetare
        //nejakz UI dodelam pozdeji tak takhle at se to da alespon vyskouset
        GameEngine gameEngine = new GameEngine();
        gameEngine.start();
    }
}
