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
    private static final SkinnyEnemyView skinnyEnemyView = new SkinnyEnemyView();
    public  GunView getGunView(){
        return gunView;
    }
    //immagini ordinate fino ad adesso
    public final int numeroImmagini=1;
    private final Image[] images=new Image[numeroImmagini];
    public GraphicPanel()  {
            //setto il cursore personalizzato
            this.setCursor(ResourcesLoader.getInstance().getCursor("/GameGeneral/crosshair.png", this));
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

    if(Game.getInstance().getGunModel().isUp()){
        g.drawImage(gunView.getCurrentImage(), gunView.imagePosition.x, gunView.imagePosition.y, Game.getInstance().getGunModel().getHeight(), Game.getInstance().getGunModel().getWidth(), null);
        Game.getInstance().getGunModel().hitBox.x=gunView.imagePosition.x;
        Game.getInstance().getGunModel().hitBox.y=gunView.imagePosition.y;
        Game.getInstance().getGunModel().hitBox.width=Game.getInstance().getGunModel().getHeight();
        Game.getInstance().getGunModel().hitBox.height=Game.getInstance().getGunModel().getWidth();


    }else{
        Game.getInstance().getGunModel().hitBox.x=gunView.imagePosition.x;
        Game.getInstance().getGunModel().hitBox.y=gunView.imagePosition.y;
        Game.getInstance().getGunModel().hitBox.width=Game.getInstance().getGunModel().getWidth();
        Game.getInstance().getGunModel().hitBox.height=Game.getInstance().getGunModel().getHeight();

        g.drawImage(gunView.getCurrentImage(), gunView.imagePosition.x, gunView.imagePosition.y, Game.getInstance().getGunModel().getWidth(), Game.getInstance().getGunModel().getHeight(), null);

    }


        /* For debugging
        g.setColor(Color.red);
        g.drawRect(Game.getInstance().getPlayerCharacter().hitBox.x, Game.getInstance().getPlayerCharacter().hitBox.y, Game.getInstance().getPlayerCharacter().hitBox.width, Game.getInstance().getPlayerCharacter().hitBox.height);
        g.drawRect(Game.getInstance().getEnemyCharacter().hitBox.x, Game.getInstance().getEnemyCharacter().hitBox.y, Game.getInstance().getEnemyCharacter().hitBox.width, Game.getInstance().getEnemyCharacter().hitBox.height);
         */

        g.drawImage(characterView.getCurrentImage(), Game.getInstance().getPlayerCharacter().getX(), Game.getInstance().getPlayerCharacter().getY(), characterView.width, characterView.height, null);

        g.drawImage(skinnyEnemyView.getCurrentImage(), Game.getInstance().getEnemyCharacter().getX(), Game.getInstance().getEnemyCharacter().getY(), skinnyEnemyView.width, skinnyEnemyView.height, null);


    }
    public void update() {
        characterView.update();
        //Sposto l'arma dove è il character senza girarla
        gunView.update(null);
        skinnyEnemyView.update();
        repaint();
    }}

