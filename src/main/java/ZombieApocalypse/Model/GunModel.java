package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

import java.awt.*;

public class GunModel {


    public boolean getAttack() {
        return attack;
    }

    //Informazioni sull'arma e l'immagine
    enum gunType{KNIFE,PISTOL, SHOTGUN};
    int [] damage= new int[]{1, 2, 4};
    int width= Settings.CELL_SIZEX;
    int height=Settings.CELL_SIZEY/2;
    int radius=Settings.CELL_SIZEY;
    int centerX = width / 2;
    int centerY = height / 2;
    int xPosy;
    int yPosy;
    boolean attack=false;
    public double angle;
    public Rectangle hitBox;

    public GunModel(){
        xPosy=width+10;
        yPosy=height-5;
        angle=0;
        hitBox=new Rectangle(xPosy,yPosy,width,height);

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
    public Point update(Point point){
        if(point==null)
            return new Point(Game.getInstance().getPlayerCharacter().getX()+xPosy-20, Game.getInstance().getPlayerCharacter().getY()+yPosy);

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




        //Aggiorno posizione
        return new Point(Game.getInstance().getPlayerCharacter().getX()+xPosy-20, Game.getInstance().getPlayerCharacter().getY()+yPosy);


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
