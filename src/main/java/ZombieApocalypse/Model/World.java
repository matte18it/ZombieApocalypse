package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

public class World {
    //Tutti i blocchi disegnabili
    enum Block { GROUND0, GROUND1,GROUND2, GROUND3}
    //Mondo e posizione del player
    private final Block[][] world = new Block[Settings.WORLD_SIZEX][Settings.WORLD_SIZEY];


    public World() {
        generateWorld();
    }
    //Per adesso, dopo caricare il mondo da file
    private void generateWorld() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                world[i][j]=Block.GROUND0;
            }}
    }





    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Settings.WORLD_SIZEX && y >= 0 && y < Settings.WORLD_SIZEY;
    }
    boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < (Settings.WORLD_SIZEX*Settings.CELL_SIZEX)-(Settings.CELL_SIZEX) && y >= 0 && y < (Settings.WORLD_SIZEY*Settings.CELL_SIZEY)-Settings.MENU_BAR_HEIGHT-Settings.CELL_SIZEY;
    }
    private boolean isType(int x, int y, Block b) {
        if(isValidPosition(x, y))
            return world[x][y] == b;
        return false;
    }
    public boolean isGround0(int x, int y) {
        return isType(x, y, Block.GROUND0);
    }

    public int getSize() {
        return world.length;
    }

}
