package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;


public class ShotgunModel extends GunModel{
    int radius, xPosy, yPosy;
    public double angle=0;
    public ShotgunModel(){
        super(Settings.CELL_SIZEX,Settings.CELL_SIZEY-10, 5);
        radius=height-20;
        xPosy=width;
        yPosy=height-10;
        hitBox=new Rectangle(xPosy,yPosy,width,height);
        imagePosition=new Point(xPosy,yPosy);
    }
    public void attack() {
        //posizionamento e creazione proiettili
        if(GameData.sound)
            PlayWav.getInstance().playPumpShot();

        attack=true;
        int x=Game.getInstance().getPlayerCharacter().getX()+xPosy;
        int y=Game.getInstance().getPlayerCharacter().getY()+yPosy;
        Bullet.Direction dir=checkDirection(angle);
        if(dir== Bullet.Direction.UP) {
            y=y-width+15;
            x=x+5;
        }else if (dir== Bullet.Direction.RIGHT) {
            x = x + width;
        }else if(dir== Bullet.Direction.LEFT){
            x=x-5;
        }else if(dir== Bullet.Direction.DOWN) {
            y = y + width;
            x = x + 5;

        }   if(dir== Bullet.Direction.UP || dir==Bullet.Direction.DOWN){
            Bullets.getInstance().ShotgunShot(x, y,  angle, BulletShotgun.Type.RIGHT);
            Bullets.getInstance().ShotgunShot(x, y,  angle, BulletShotgun.Type.CENTER );
            Bullets.getInstance().ShotgunShot(x, y,  angle, BulletShotgun.Type.LEFT );}
            else{
            Bullets.getInstance().ShotgunShot(x, y,  angle, BulletShotgun.Type.RIGHT);
            Bullets.getInstance().ShotgunShot(x, y,  angle, BulletShotgun.Type.CENTER);
            Bullets.getInstance().ShotgunShot(x, y,  angle, BulletShotgun.Type.LEFT);

        }

    }
    public void  update(Point point){
        //update dell'arma secondo il mouse
        int x, y;
        if(point==null){
            x=Game.getInstance().getPlayerCharacter().getX()+xPosy;
            y=Game.getInstance().getPlayerCharacter().getY()+yPosy;
            hitBox.x=x;
            hitBox.y=y;
            //Inversione per permettere al GraphicPanel di disegnare orizzondale e verticale
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
        //Calcolo della nuova poszione dell'arma, con immagini rettangolari va ribilanciata
        xPosy = Math.round((float) (centerX + Math.cos(rads) * fullLength))-20;
        yPosy = Math.round((float) (centerY - Math.sin(rads) * fullLength))-10;
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
        return !(angle < 230) || !(angle >= 140);
    }

}
