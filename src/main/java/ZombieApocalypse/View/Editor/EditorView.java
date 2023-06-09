package ZombieApocalypse.View.Editor;

import ZombieApocalypse.Controller.EditorController;
import ZombieApocalypse.Model.Editor.EditorModel;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import java.awt.*;

public class EditorView extends JPanel {
    private EditorController controller;
    private EditorModel model;

    //Variabili per disegnare il mondo di default
    private final int numeroImmagini = 33;
    private final Image[] images = new Image[numeroImmagini];
    private String path;
    public boolean line = true;

    //Gestione dei blocchi disegnabili
    enum Block {TERRENO0, TERRENO1, TERRENO2, TERRENO3, DIVISORIO1, WATER0, FLOWER1, FLOWER2, ROAD1, ROAD2, ROAD3, ROAD4, ROAD5, ROAD6, ROAD7, ROAD8, ROAD9, ROAD10, ROAD11, ROAD12, ROAD13, ROAD14, ROAD15, ROAD16, ROAD17, ROAD18, ROAD19, ROAD20, ROAD21, ROAD22, ROAD23, ROAD24, ROAD25}
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
        else if(EditorBarView.bloccoAttivo == 18)
            world[i][j] = Block.ROAD11;
        else if(EditorBarView.bloccoAttivo == 19)
            world[i][j] = Block.ROAD12;
        else if(EditorBarView.bloccoAttivo == 20)
            world[i][j] = Block.ROAD13;
        else if(EditorBarView.bloccoAttivo == 21)
            world[i][j] = Block.ROAD14;
        else if(EditorBarView.bloccoAttivo == 22)
            world[i][j] = Block.ROAD15;
        else if(EditorBarView.bloccoAttivo == 23)
            world[i][j] = Block.ROAD16;
        else if(EditorBarView.bloccoAttivo == 24)
            world[i][j] = Block.ROAD17;
        else if(EditorBarView.bloccoAttivo == 25)
            world[i][j] = Block.ROAD18;
        else if(EditorBarView.bloccoAttivo == 26)
            world[i][j] = Block.ROAD19;
        else if(EditorBarView.bloccoAttivo == 27)
            world[i][j] = Block.ROAD20;
        else if(EditorBarView.bloccoAttivo == 28)
            world[i][j] = Block.ROAD21;
        else if(EditorBarView.bloccoAttivo == 29)
            world[i][j] = Block.ROAD22;
        else if(EditorBarView.bloccoAttivo == 30)
            world[i][j] = Block.ROAD23;
        else if(EditorBarView.bloccoAttivo == 31)
            world[i][j] = Block.ROAD24;
        else if(EditorBarView.bloccoAttivo == 32)
            world[i][j] = Block.ROAD25;

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

    public Block getEnum(int i, int j){
        return world[i][j];
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
        path = "/AmbienteDiGioco/Flower/Flower1.png";
        images[6] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Flower/Flower2.png";
        images[7] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road1.png";
        images[8] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road2.png";
        images[9] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road3.png";
        images[10] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road4.png";
        images[11] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road5.png";
        images[12] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road6.png";
        images[13] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road7.png";
        images[14] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road8.png";
        images[15] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road9.png";
        images[16] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road10.png";
        images[17] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road11.png";
        images[18] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road12.png";
        images[19] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road13.png";
        images[20] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road14.png";
        images[21] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road15.png";
        images[22] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road16.png";
        images[23] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road17.png";
        images[24] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road18.png";
        images[25] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road19.png";
        images[26] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road20.png";
        images[27] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road21.png";
        images[28] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road22.png";
        images[29] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road23.png";
        images[30] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road24.png";
        images[31] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        path = "/AmbienteDiGioco/Road/Road25.png";
        images[32] = ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
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
