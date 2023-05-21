package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GunView {
    double angleToTurn=0;
    final int width=Settings.CELL_SIZEX;
    final int height=Settings.CELL_SIZEY/2;
    int centerX = width / 2;
    int centerY = height / 2;

     double mouseY;
     double mouseX;
     public Point imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+30, Game.getInstance().getPlayerCharacter().getY()+12);






    public Image currentImage= ResourcesLoader.getInstance().getImage("/ArmieOggetti/Coltello0.png", width,height, true);

    public GunView() {

    }

    public void update(Point point) {


        imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+30, Game.getInstance().getPlayerCharacter().getY()+12);



    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
