package ZombieApocalypse.View;

import ZombieApocalypse.Loop.TimeLoop;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GunView {
    final int width=Settings.CELL_SIZEX;
    final int height=Settings.CELL_SIZEY/2;
    int centerX = width / 2;
    int centerY = height / 2;



     public Point imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+30, Game.getInstance().getPlayerCharacter().getY()+12);






    public BufferedImage currentImage=  ResourcesLoader.getInstance().getBufferedImage("/ArmieOggetti/Coltello0.png", width,height, true);

    public GunView() {

    }
    double angle=0;

    public void update(Point point) {
        int radius=Settings.CELL_SIZEY;
        double rads=Math.toRadians(angle-180);
        int fullLength=Math.round((radius/2f))-width-10;
        int xPosy = Math.round((float) (centerX + Math.cos(rads) * fullLength));
        int yPosy = Math.round((float) (centerY - Math.sin(rads) * fullLength));
        angle=angle+10;

        //imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+30, Game.getInstance().getPlayerCharacter().getY()+12);
        imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+xPosy-20, Game.getInstance().getPlayerCharacter().getY()+yPosy);






    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
