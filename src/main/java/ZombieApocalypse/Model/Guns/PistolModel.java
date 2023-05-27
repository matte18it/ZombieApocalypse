package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class PistolModel {


    public boolean getAttack() {
        return attack;
    }

    //Informazioni sull'arma e l'immagine

    public int damage=2;
    int width= Settings.CELL_SIZEX-15;
    int height=Settings.CELL_SIZEY-10;
    //Raggio del cerchio, più è piccolo più è grande il cerchio
    int radius=height-30;
    int centerX = width / 2;
    int centerY = height / 2;
    int xPosy;
    int yPosy;
    boolean attack=false;
    public double angle;
    public Point imagePosition;
    public Rectangle hitBox;

    public PistolModel(){
        xPosy=width+10;
        yPosy=height-10;
        angle=0;
        hitBox=new Rectangle(xPosy,yPosy,width,height);
        imagePosition=new Point(xPosy,yPosy);


    }
    public void attack() {
        attack=true;

        if(hitBox.intersects(Game.getInstance().getEnemyCharacter().hitBox)){
            Game.getInstance().getEnemyCharacter().hit();
        }

    }
    public void stopAttack() {
        attack=false;

    }
    public double getAngle(){
        return angle;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public void  update(Point point){
        int x, y;
        if(point==null){
            x=Game.getInstance().getPlayerCharacter().getX()+xPosy;
            y=Game.getInstance().getPlayerCharacter().getY()+yPosy;
            hitBox.x=x;
            hitBox.y=y;
            if(isUp()){
                hitBox.height=width;
                hitBox.width=height;
            }else{
                hitBox.height=height;
                hitBox.width=width;
            }

            imagePosition=new Point(x, y);
            return ;}

        float xDistance = point.x - Game.getInstance().getPlayerCharacter().getX();   //Distanza punto x
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
        int fullLength=Math.round((radius/2f))-width;
        //Calcolo della nuova poszione dell'arma
        xPosy = Math.round((float) (centerX + Math.cos(rads) * fullLength));
        yPosy = Math.round((float) (centerY - Math.sin(rads) * fullLength));




        //Aggiorno posizione
        imagePosition=new Point(Game.getInstance().getPlayerCharacter().getX()+xPosy, Game.getInstance().getPlayerCharacter().getY()+yPosy);


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



}
