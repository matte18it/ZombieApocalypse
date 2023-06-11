package ZombieApocalypse.Utility;

import java.awt.*;

public class Settings {
    public static String mapName;
    public static boolean isEditor=true;
    public static int campainMapIndex=1;  //Mappa campagna corrente
    public static int campainMaps=5;  //numero mappe massime
    public static boolean nextCampaignMap(){  //Verrà chiamato al completamento del livello
        if(campainMapIndex>=campainMaps)
            return false;

        campainMapIndex++;
        setDifficulty();
        return true;

    }

    private static void setDifficulty() {
        if(campainMapIndex == 1 || campainMapIndex == 2)
            diff = Difficulty.EASY;
        else if(campainMapIndex == 3 || campainMapIndex == 5)
            diff = Difficulty.MEDIUM;
        else
            diff = Difficulty.HARD;
    }


    public static void loadEditorMap(String nomeFile) {
        mapName=nomeFile;
        isEditor=true;
    }
    public static void loadCampaign(){
        isEditor=false;
    }

    //Grandezza della finestra di Gioco
    public static enum Difficulty{EASY, MEDIUM, HARD};

    public static Difficulty diff = Difficulty.EASY;
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
