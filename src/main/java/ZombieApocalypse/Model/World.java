package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Utility.ResultsPanel;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class World {
    World() { //generatore della mappa
        if(Settings.isEditor)
            generatePlayerWorld();
        else
            generateCampaignWorld();
    }
    //Tutti i blocchi disegnabili
    public enum Block {TERRENO0, TERRENO1, TERRENO2, TERRENO3, DIVISORIO1, WATER0, WATER1, WATER2, FLOWER1, FLOWER2, ROAD1, ROAD2, ROAD3, ROAD4, ROAD5, ROAD6, ROAD7, ROAD8, ROAD9, ROAD10, ROAD11, ROAD12, ROAD13, ROAD14, ROAD15, ROAD16, ROAD17, ROAD18, ROAD19, ROAD20, ROAD21, ROAD22, ROAD23, ROAD24, ROAD25}
    //Mondo e posizione del player
    Block[][] world = new Block[Settings.WORLD_SIZEX][Settings.WORLD_SIZEY];
    private void generatePlayerWorld() { //Genera una mappa scelta dall'utente
        String[] builder;
        try (BufferedReader in = new BufferedReader(new FileReader("EditorMap/" +Settings.mapName + ".txt"))) {
            for (int i = 0; i < Settings.WORLD_SIZEX; i++) {
                builder = in.readLine().split(" ");
                for (int j = 0; j < Settings.WORLD_SIZEY; j++) {
                    world[i][j] = Block.valueOf(builder[j]);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            ResultsPanel.getInstance().showError("Errore nel caricamento della mappa scelta dall'utente", 107, e);
        }
    }
    private void generateCampaignWorld(){//Genera le mappe predefinite in ordine
        String[] builder;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/CampaignMap/Campagna" + Settings.campainMapIndex + ".txt"))))) {
            for (int i = 0; i < Settings.WORLD_SIZEX; i++) {
                builder = in.readLine().split(" ");
                for (int j = 0; j < Settings.WORLD_SIZEY; j++) {
                    world[i][j] = Block.valueOf(builder[j]);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            ResultsPanel.getInstance().showError("Errore nel caricamento della mappa predefinita", 108, e);

        }
    }
     boolean isSpawnable(int x, int y) { //Controlla la posizione del nemico allo spawn
        Point enemy=new Point(x, y);
        Point player=Game.getInstance().getPlayerPosition();
         return !(player.distance(enemy) < 300);
     }

     boolean isSpawnableItem(int x, int y) { //controlla la posizione del Item allo spawn
        Point enemy=new Point(x, y);
        Point player=Game.getInstance().getPlayerPosition();
         return !(player.distance(enemy) < 50);
     }

     Point selectPlayerPosition() { //Trova una posizione dove il player può nascere
        for(int i=1; i<Settings.WORLD_SIZEX; i++){
            for(int j=1; j<Settings.WORLD_SIZEY; j++){
                if(world[i][j]!=Block.WATER0 && world[i][j]!=Block.WATER1  && world[i][j]!=Block.WATER2 && world[i][j]!=Block.DIVISORIO1)
                    return new Point(i*Settings.CELL_SIZEX, j*Settings.CELL_SIZEY);

            }
        }return null;
    }
     boolean isEnemy(int x, int y, int cX, int cY){ //Controlla la posizione dei nemici
        return Enemies.getInstance().checkCollisionWithEnemies(x,y, cX, cY) ;
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
            return world[x][y] != Block.WATER0 && world[x][y] != Block.WATER1 && world[x][y] != Block.WATER2 && world[x][y] != Block.DIVISORIO1;
        }
        return false;
    }
    //I blocchi Walkable sono quelli dove il player e i nemici possono transitare
     boolean isWalkable(int x, int y) {
        return walkableType(x,y);
    }



}
