package ZombieApocalypse;

import java.awt.*;

public class Settings {

    //Grandezza della finestra
    public final static int WORLD_SIZE = 20;
    public final static int WINDOW_SIZE =500 ;
    public final static int CELL_SIZE = WINDOW_SIZE / WORLD_SIZE;

    //Gestione dei movimenti del player
    public final static int NOT_MOVING = -1;
    public final static int MOVE_LEFT = 0;
    public final static int MOVE_RIGHT = 1;
    public final static int MOVE_UP = 2;
    public final static int MOVE_DOWN = 3;

    public final static int EXIT = 4;
}
