package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class World {
    public boolean isEnemy(int i, int y) {
        //Tengo il centro del player a distanza dal centro del nemico
        Point player=new Point(i+Game.getInstance().getPlayerCharacter().centerX,y+Game.getInstance().getPlayerCharacter().centerY);
        Point enemy =new Point(Game.getInstance().getEnemyCharacter().getX()+Game.getInstance().getEnemyCharacter().centerX, Game.getInstance().getEnemyCharacter().getY()+Game.getInstance().getEnemyCharacter().centerY);
        if(player.distance(enemy)<20)
            return true;

        return false;
    }

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

    private boolean isType(int x, int y, Block b) {
        x=(x*Settings.WORLD_SIZEX)/Settings.WINDOW_SIZEX;
        y=(y*Settings.WORLD_SIZEY)/Settings.WINDOW_SIZEY;
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
