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


}
