package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class GraphicPanel extends JPanel {
    //Disegna il mondo
    private final CharacterView characterView = new CharacterView();
    private static final GunView gunView = new GunView();
    public  GunView getGunView(){
        return gunView;
    }
    //immagini ordinate fino ad adesso
    private final int numeroImmagini=1;
    private final Image[] images=new Image[numeroImmagini];
    public GraphicPanel()  {
            for(int i=0; i<numeroImmagini; i++){
                String c=String.valueOf(i);
                String path="/AmbienteDiGioco/Terreno/Terreno"+c+".png";
                images[i]= ResourcesLoader.getInstance().getImage(path, Settings.CELL_SIZEX, Settings.CELL_SIZEY, true);
        }

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        World world=new World();
        for(int i = 0; i < world.getSize(); i++) {
            int x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < world.getSize(); j++) {
                int y = j * Settings.CELL_SIZEY;
                if(world.isGround0(i, j)) {
                    //Random random=new Random();
                    //int value= random.nextInt(numeroImmagini);
                    g.drawImage(images[0], x, y, null);
                }

            }


        }
        if(gunView.isUp())    //Arma per orizzondale o verticale?
        g.drawImage(gunView.getCurrentImage(), gunView.imagePosition.x, gunView.imagePosition.y, gunView.height, gunView.width, null);
        else
            g.drawImage(gunView.getCurrentImage(), gunView.imagePosition.x, gunView.imagePosition.y, gunView.width, gunView.height, null);
        g.drawImage(characterView.getCurrentImage(), Game.getInstance().getPlayerCharacter().getX(), Game.getInstance().getPlayerCharacter().getY(), characterView.width, characterView.height, null);

    }
    public void update() {
        characterView.update();
        //Sposto l'arma dove è il character senza girarla
        gunView.update(null);
        repaint();
    }}

