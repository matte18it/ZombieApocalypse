package ZombieApocalypse;

import java.awt.*;

public class Settings {

    //Gestione del full-screen, in questo caso è sempre full-screen
    public static int getActualWindowSize(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        return screenDimension.width;

    }

    //Grandezza della finestra
    public  static int WORLD_SIZE = 20;
    public  static int WINDOW_SIZE= getActualWindowSize();
    public  static int CELL_SIZE = WINDOW_SIZE / WORLD_SIZE;

    //Gestione dei movimenti del player
    public final static int NOT_MOVING = -1;
    public final static int MOVE_LEFT = 0;
    public final static int MOVE_RIGHT = 1;
    public final static int MOVE_UP = 2;
    public final static int MOVE_DOWN = 3;

    public final static int EXIT = 4;
}
