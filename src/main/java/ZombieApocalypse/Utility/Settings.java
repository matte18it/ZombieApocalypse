package ZombieApocalypse.Utility;

import java.awt.*;

public class Settings {



    //Grandezza della finestra di Gioco
    public static enum movementDirection{RIGHT, LEFT, UP, DOWN};

    public  static int WORLD_SIZEX=32;
    public  static int WORLD_SIZEY=20;
    public  static int WINDOW_SIZEX=1280;
    public  static int WINDOW_SIZEY=660;
    public  static int MENU_BAR_HEIGHT=60;
    public static int EDITOR_BAR_HEIGHT = 60;
    public  static int CELL_SIZEX = WINDOW_SIZEX / WORLD_SIZEX;
    public  static int CELL_SIZEY = WINDOW_SIZEY / WORLD_SIZEY;


}
