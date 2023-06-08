package ZombieApocalypse.View.Editor;

import ZombieApocalypse.Controller.EditorBarController;
import ZombieApocalypse.Controller.EditorController;
import ZombieApocalypse.Model.Editor.EditorModel;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import java.awt.*;

public class EditorView extends JPanel {
    private EditorController controller;
    private EditorModel model;

    //Variabili per disegnare il mondo di default
    private final int numeroImmagini = 8;
    private final Image[] images = new Image[numeroImmagini];
    private String path;

    //Gestione dei blocchi disegnabili
    enum Block { TERRENO0, TERRENO1, TERRENO2, TERRENO3, DIVISORIO1, WATER0, FLOWER1, FLOWER2, ROAD1, ROAD2, ROAD3, ROAD4, ROAD5, ROAD6, ROAD7, ROAD8, ROAD9, ROAD10, ROAD11, ROAD12, ROAD13, ROAD14, ROAD15, ROAD16, ROAD17, ROAD18, ROAD19, ROAD20, ROAD21, ROAD22, ROAD23, ROAD24, ROAD25, ROAD26}
    private final Block[][] world = new Block[Settings.WORLD_SIZEX][Settings.WORLD_SIZEY];

    public EditorView(){
        //setto il cursore
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));

        //creo il model
        model = new EditorModel(this);
        //creo il controller
        controller = new EditorController(this, model);

        this.setBackground(Color.BLACK);
        initWorld();
        initImage();
    }

    private void initWorld() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                world[i][j] = Block.TERRENO0;
            }
        }
    }

    public void initImage(){
        //caricamento immagini
        path = "/AmbienteDiGioco/Terreno/Terreno0.png";
        images[0] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Terreno/Terreno1.png";
        images[1] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Terreno/Terreno2.png";
        images[2] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Terreno/Terreno3.png";
        images[3] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/DivisorioAcquaTerra/Divisorio1.png";
        images[4] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Water/Water0.png";
        images[5] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Water/Water1.png";
        images[6] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Water/Water2.png";
        images[7] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Inizializzo la mappa dell'editor
        for(int i = 0; i < world.length; i++) {
            int x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < world.length; j++) {
                int y = j * Settings.CELL_SIZEY;
                g.drawImage(images[0], x, y, null);
            }
        }
    }

}
