package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class World {


    public boolean isPlayer(int x, int i, int ceX, int ceY) {
        Point enemy=new Point(x+ceX, i+ceY);
        Point player=new Point(Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().centerX, Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().centerY);
        if(player.distance(enemy)<20)
            return false;
        return true;
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
    public boolean isEnemy(int x, int y, int cX, int cY){
        return Enemies.getInstance().checkCollision(x,y, cX, cY) && isBoss(x,y, cX, cY);
    }

    private boolean isBoss(int x, int y, int cX, int cY) {
        Point p=new Point(x+ cX,y+cY);
        Point boss=new Point(Game.getInstance().getBoss().x+Game.getInstance().getBoss().centerX, Game.getInstance().getBoss().y+Game.getInstance().getBoss().centerY);
        return !(boss.distance(p) < 50);

    }


    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Settings.WORLD_SIZEX && y >= 0 && y < Settings.WORLD_SIZEY;
    }

    private boolean isType(int x, int y, Block b) {
        if(x<0 || y<0)  //viene utilizzato perchè nel passaggio da coordinata
            return false; //a posizone sulla matrice un numero negativo
                            //potrebbe essere approssimato a 0, facendo uscire il player
        x=(x * Settings.WORLD_SIZEX) /Settings.WINDOW_SIZEX;
        y= (y * Settings.WORLD_SIZEY) /Settings.WINDOW_SIZEY;


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
