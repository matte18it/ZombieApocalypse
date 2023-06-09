package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.io.*;

public class World {


    public boolean isPlayer(int x, int i, int ceX, int ceY) {
        Point enemy=new Point(x+ceX, i+ceY);
        Point player=new Point(Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().centerX, Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().centerY);
        if(player.distance(enemy)<20)
            return false;
        return true;
    }

    public int getSizeX() {
        return world.length;
    }

    public int getSizeY() {

        return world[0].length;
    }


    //Tutti i blocchi disegnabili
    public enum Block {TERRENO0, TERRENO1, TERRENO2, TERRENO3, DIVISORIO1, WATER0, FLOWER1, FLOWER2, ROAD1, ROAD2, ROAD3, ROAD4, ROAD5, ROAD6, ROAD7, ROAD8, ROAD9, ROAD10, ROAD11, ROAD12, ROAD13, ROAD14, ROAD15, ROAD16, ROAD17, ROAD18, ROAD19, ROAD20, ROAD21, ROAD22, ROAD23, ROAD24, ROAD25}
    //Mondo e posizione del player
    public  Block[][] world = new Block[Settings.WORLD_SIZEX][Settings.WORLD_SIZEY];
    public boolean isEditor=false;




    public World() {
        try{
        generateWorld();}catch (IOException ex){
            System.exit(105);
        }
    }
    //Per adesso, dopo caricare il mondo da file
    private void generateWorld() throws IOException, ArrayIndexOutOfBoundsException{
        BufferedReader in = null;
        File f=new File("Resources/CampaignMap/Campagna1.txt");
        String[] builder=new String[Settings.WORLD_SIZEX];
        try {
            in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/CampaignMap/Campagna1.txt")));
                for(int i=0; i<Settings.WORLD_SIZEX; i++){
                    builder = in.readLine().split(" ");
                    for(int j=0; j<Settings.WORLD_SIZEY; j++){
                        world[i][j]=Block.valueOf(builder[j]);
                    }
                }

        }
        finally {
            if(in != null)
                in.close();


    }
    }
    public boolean isEnemy(int x, int y, int cX, int cY){
        return Enemies.getInstance().checkCollision(x,y, cX, cY) ;
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
        return isType(x, y, Block.TERRENO0);
    }

    public int getSize() {
        return world.length;
    }

}
