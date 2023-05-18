package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

public class World {
    //Tutti i blocchi disegnabili
    enum Block { GROUND}
    //Mondo e posizione del player
    private final Block[][] world = new Block[Settings.WORLD_SIZE][Settings.WORLD_SIZE];


    public World() {
        generateWorld();
    }
    //Per adesso, dopo caricare il mondo da file
    private void generateWorld() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                world[i][j]=Block.GROUND;
            }}
            }





     private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Settings.WORLD_SIZE && y >= 0 && y < Settings.WORLD_SIZE;
    }
    boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < Settings.WORLD_SIZE*Settings.CELL_SIZE && y >= 0 && y < Settings.WORLD_SIZE*Settings.CELL_SIZE;
    }
    private boolean isType(int x, int y, Block b) {
        if(isValidPosition(x, y))
            return world[x][y] == b;
        return false;
    }
    public boolean isGround(int x, int y) {
        return isType(x, y, Block.GROUND);
    }

    public int getSize() {
        return world.length;
    }

}
