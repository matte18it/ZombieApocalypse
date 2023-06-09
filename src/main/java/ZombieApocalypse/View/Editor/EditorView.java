package ZombieApocalypse.View.Editor;

import ZombieApocalypse.Controller.EditorBarController;
import ZombieApocalypse.Controller.EditorController;
import ZombieApocalypse.Model.Editor.EditorModel;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorView extends JPanel {
    private EditorController controller;
    private EditorModel model;

    //Variabili per disegnare il mondo di default
    private final int numeroImmagini = 33;
    private final Image[] images = new Image[numeroImmagini];
    private String path;
    public boolean line = true;

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

    public void setTassello(int i, int j) {
        if(EditorBarView.bloccoAttivo == 0)
            world[i][j] = Block.TERRENO0;
        else if(EditorBarView.bloccoAttivo == 1)
            world[i][j] = Block.TERRENO1;
        else if(EditorBarView.bloccoAttivo == 2)
            world[i][j] = Block.TERRENO2;
        else if(EditorBarView.bloccoAttivo == 3)
            world[i][j] = Block.TERRENO3;
        else if(EditorBarView.bloccoAttivo == 4)
            world[i][j] = Block.DIVISORIO1;
        else if(EditorBarView.bloccoAttivo == 5)
            world[i][j] = Block.WATER0;
        else if(EditorBarView.bloccoAttivo == 6)
            world[i][j] = Block.FLOWER1;
        else if(EditorBarView.bloccoAttivo == 7)
            world[i][j] = Block.FLOWER2;
        else if(EditorBarView.bloccoAttivo == 8)
            world[i][j] = Block.ROAD1;
        else if(EditorBarView.bloccoAttivo == 9)
            world[i][j] = Block.ROAD2;
        else if(EditorBarView.bloccoAttivo == 10)
            world[i][j] = Block.ROAD3;
        else if(EditorBarView.bloccoAttivo == 11)
            world[i][j] = Block.ROAD4;
        else if(EditorBarView.bloccoAttivo == 12)
            world[i][j] = Block.ROAD5;
        else if(EditorBarView.bloccoAttivo == 13)
            world[i][j] = Block.ROAD6;
        else if(EditorBarView.bloccoAttivo == 14)
            world[i][j] = Block.ROAD7;
        else if(EditorBarView.bloccoAttivo == 15)
            world[i][j] = Block.ROAD8;
        else if(EditorBarView.bloccoAttivo == 16)
            world[i][j] = Block.ROAD9;
        else if(EditorBarView.bloccoAttivo == 17)
            world[i][j] = Block.ROAD10;

        repaint();
    }

    public void initWorld() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                world[i][j] = Block.TERRENO0;
            }
        }
    }

    public int getWorldLength(){
        return world.length;
    }

    public int getWorldLengthRow(int i){
        return world[i].length;
    }

    public int getEnum(int i, int j){
        return world[i][j].ordinal();
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
        g.clearRect(0, 1280, 0, 1280);

        int x = 0, y = 0;
        g.setColor(Color.RED);

        //Inizializzo la mappa dell'editor
        for(int i = 0; i < world.length+1; i++) {
            x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < world.length+1; j++) {
                y = j * Settings.CELL_SIZEY;

                if(i < world.length && j < world[i].length)
                    g.drawImage(images[world[i][j].ordinal()], x, y, null);

                if(line){
                    g.drawLine(x-Settings.CELL_SIZEX, y-Settings.CELL_SIZEY, x, y-Settings.CELL_SIZEY);
                    g.drawLine(x-Settings.CELL_SIZEX, y-Settings.CELL_SIZEY, x-Settings.CELL_SIZEX, y);
                }
            }
        }
        if(line)
            g.drawLine(1280, 0, 1280, 1280);
    }

    public boolean getLine(){
        return line;
    }

    public void setLine(boolean l){
        line = l;
    }

}
