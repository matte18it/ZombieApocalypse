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
    private final GunAnimation gunAnimation;
    int width=Settings.CELL_SIZEX;
    int height=Settings.CELL_SIZEY/2;
    int radius=Settings.CELL_SIZEY;
    int centerX = width / 2;
    int centerY = height / 2;
    int xPosy=width+10;
    int yPosy=height-5;
     public Point imagePosition;

    public Image currentImage;

    public GunView() {
        gunAnimation= new GunAnimation("Coltello", 4);
    }
    double angle=0;
    public void update(Point point) {
        if(point==null){
            imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+xPosy-20, Game.getInstance().getPlayerCharacter().getY()+yPosy);
            currentImage=gunAnimation.update(angle);
            return;
        }

        float xDistance = point.x - Game.getInstance().getPlayerCharacter().getX()-20;   //Distanza punto x
        float yDistance = point.y - Game.getInstance().getPlayerCharacter().getY();     //Distanza punto y
        //Questo metodo converte le coordinate rettangolari (x,y) in coordinate polari (r,theta) e ritorna theta
        angle = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        //Le coordinate sotto lo zero diventano negative, a noi ci servono sempre positive
        if(angle<0)
            angle=angle+360;




        double rads=Math.toRadians(angle-180);  //transformo angolo in radianti
        //Calcolo il raggio del cerchio
        //il radius(diametro) è l'altezza del personaggio
        // radius/2f significa dividi per 2 transformandolo in float
        int fullLength=Math.round((radius/2f))-width-10;
        //Calcolo della nuova poszione dell'arma
        xPosy = Math.round((float) (centerX + Math.cos(rads) * fullLength));
        yPosy = Math.round((float) (centerY - Math.sin(rads) * fullLength));


        //Aggiorno immagine
        currentImage=gunAnimation.update(angle);
        //Aggiorno posizione
        imagePosition = new Point(Game.getInstance().getPlayerCharacter().getX()+xPosy-20, Game.getInstance().getPlayerCharacter().getY()+yPosy);


    }
    //Ho bisogno di sapere se l'arma è in verticale o in orizzondale
    //Perchè wight e height si invertono nel Graphic Panel
    public boolean isUp(){
        if((angle<60 && angle>=0) || (angle>=320)){
            return false;
        }
        if(angle<140 && angle>=60){
            return true;

        }
        if(angle<230 && angle>=140){
            return false;

        }


         return true;
    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
