package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

public class World {
    //Tutti i blocchi disegnabili
    enum Block { TERRAIN}
    //Mondo e posizione del player
    private final Block[][] world = new Block[Settings.WORLD_SIZE][Settings.WORLD_SIZE];
    private int playerPositionX = 0;
    private int playerPositionY = 0;

    public World() {
        generateWorld();
    }
    //Per adesso, dopo caricare il mondo da file
    private void generateWorld() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                world[i][j]=Block.TERRAIN;
            }}
            }


    int getPlayerPositionX() {
        return playerPositionX;
    }

    int getPlayerPositionY() {
        return playerPositionY;
    }
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Settings.WORLD_SIZE && y >= 0 && y < Settings.WORLD_SIZE;
    }
    private boolean isType(int x, int y, Block b) {
        if(isValidPosition(x, y))
            return world[x][y] == b;
        return false;
    }
    public boolean isWall(int x, int y) {
        return isType(x, y, Block.TERRAIN);
    }

    public int getSize() {
        return world.length;
    }
}
