package ZombieApocalypse.Model.Guns;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;

public class BulletGrenadeBandit extends Bullet{
    private int count=0;
    BulletGrenadeBandit(int x, int y,  Direction d, int tot){
        super(x,y, BulletType.GRENADE, d);
        totalFrame=tot/15;
    }
    boolean update(){
        //Gestione del ritorno al menu
        if(Game.getInstance().getBackMenu()){
            return false;
        }
        if(!ending) {
            count++; //Gestione del movimento del proiettile
            if (this.getX() > 0 && this.getX() < Settings.WINDOW_SIZEX && this.y > 0 && this.y < Settings.WINDOW_SIZEY && count<totalFrame) {
                if (bulletDir == Direction.DOWN)
                    this.y += this.velocity;
                if (bulletDir == Direction.LEFT)
                    this.x -= this.velocity;
                if (bulletDir == Direction.UP)
                    this.y -= this.velocity;
                if (bulletDir == Direction.RIGHT)
                    this.x += this.velocity;

            } else{
                ending=true;
                numFrame=0;
            }
            return true;
        }else{
            checkcollision();
            if(numFrame<24){ //Gestione dell'esplosione
                numFrame++;
                if(numFrame==10 ) {
                    if(GameData.sound)
                        PlayWav.getInstance().playGrenadeSound();

                    dimension = dimension + 10;
                    x=x-5;
                    y=y-5;
                }
                if(numFrame==12 ){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;
                }
                if(numFrame==14 ){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(numFrame==16){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(numFrame==18){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                if(numFrame==20){
                    dimension=dimension+10;y=y-5;
                    x=x-5;}
                if(numFrame>22){
                    dimension=dimension+10;
                    y=y-5;
                    x=x-5;}
                return true;}
            else
                return false;
        }
    }
//Controllo della collisione con il player (perchè è la granata del nemico)
    private void checkcollision() {
        Point explosion=new Point(x+dimension/2,y+dimension/2);
        Point player=new Point(Game.getInstance().getPlayerCharacter().getX()+Game.getInstance().getPlayerCharacter().centerX, Game.getInstance().getPlayerCharacter().getY()+Game.getInstance().getPlayerCharacter().centerY);
        if(numFrame<22 && numFrame>5){
        if(explosion.distance(player)<dimension ){
            Game.getInstance().getPlayerCharacter().hit();
        }
    }}
}










