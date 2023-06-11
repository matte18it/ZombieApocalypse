package ZombieApocalypse.Model;

import ZombieApocalypse.Controller.UserMapController;
import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.SkinnyZombie;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Random;

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

    public boolean isSpawnable(int x, int y) {
        Point enemy=new Point(x, y);
        Point player=Game.getInstance().getPlayerPosition();
        if(player.distance(enemy)<300)
            return false;
        return true;
    }

    public boolean isSpawnableItem(int x, int y) {
        Point enemy=new Point(x, y);
        Point player=Game.getInstance().getPlayerPosition();
        if(player.distance(enemy)<50)
            return false;
        return true;
    }

    public Point selectPlayerPosition() {
        int k, h;
        for(int i=1; i<Settings.WORLD_SIZEX; i++){
            for(int j=1; j<Settings.WORLD_SIZEY; j++){
                if(world[i][j]!=Block.WATER0 && world[i][j]!=Block.WATER1  && world[i][j]!=Block.WATER2 && world[i][j]!=Block.DIVISORIO1)
                    return new Point(i*Settings.CELL_SIZEX, j*Settings.CELL_SIZEY);

            }
        }return null;
    }


    //Tutti i blocchi disegnabili
    public enum Block {TERRENO0, TERRENO1, TERRENO2, TERRENO3, DIVISORIO1, WATER0, WATER1, WATER2, FLOWER1, FLOWER2, ROAD1, ROAD2, ROAD3, ROAD4, ROAD5, ROAD6, ROAD7, ROAD8, ROAD9, ROAD10, ROAD11, ROAD12, ROAD13, ROAD14, ROAD15, ROAD16, ROAD17, ROAD18, ROAD19, ROAD20, ROAD21, ROAD22, ROAD23, ROAD24, ROAD25}
    //Mondo e posizione del player
    public  Block[][] world = new Block[Settings.WORLD_SIZEX][Settings.WORLD_SIZEY];







    public World() {
        if(Settings.isEditor)
            generatePlayerWorld();
        else
            generateCampaignWorld();






    }

    private void generatePlayerWorld() {
        String[] builder;
        try (BufferedReader in = new BufferedReader(new FileReader("EditorMap/" +Settings.mapName + ".txt"))) {
            for (int i = 0; i < Settings.WORLD_SIZEX; i++) {
                builder = in.readLine().split(" ");
                for (int j = 0; j < Settings.WORLD_SIZEY; j++) {
                    world[i][j] = Block.valueOf(builder[j]);
                }
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.exit(107);
        }
    }

    private void generateCampaignWorld(){
        String[] builder;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/CampaignMap/Campagna" + Settings.campainMapIndex + ".txt"))))) {
            for (int i = 0; i < Settings.WORLD_SIZEX; i++) {
                builder = in.readLine().split(" ");
                for (int j = 0; j < Settings.WORLD_SIZEY; j++) {
                    world[i][j] = Block.valueOf(builder[j]);
                }
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.exit(107);

        }
    }
    public boolean isEnemy(int x, int y, int cX, int cY){
        return Enemies.getInstance().checkCollision(x,y, cX, cY) ;
    }




    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Settings.WORLD_SIZEX && y >= 0 && y < Settings.WORLD_SIZEY;
    }

    private boolean walkableType(int x, int y) {
        if(x<0 || y<0)  //viene utilizzato perchè nel passaggio da coordinata
            return false; //a posizone sulla matrice un numero negativo
                            //potrebbe essere approssimato a 0, facendo uscire il player
        x=(x * Settings.WORLD_SIZEX) /Settings.WINDOW_SIZEX;
        y= (y * Settings.WORLD_SIZEY) /Settings.WINDOW_SIZEY;


        if(isValidPosition(x, y)){
            if(world[x][y]!=Block.WATER0 && world[x][y]!=Block.WATER1 && world[x][y]!=Block.WATER2 && world[x][y]!=Block.DIVISORIO1)
                return true;
        }
        return false;
    }
    public boolean isWalkable(int x, int y) {
        return walkableType(x,y);
    }



}
