package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;

import java.awt.*;
import java.util.Random;

public class TurretZombie extends Enemy{

    public TurretZombie(int x, int y) {
        super(x, y);

        type= Enemies.EnemiesType.TURRETZOMBIE;
        healt=5;
        super.setSize();
    }

    //Per prova
    double angle, angle2;
    Random m=new Random();
    public boolean update() {
        if(dying)
            return false;
        if (healt <= 0 ) {
            dying = true;
            int c = m.nextInt(4, 9);
            Items.getInstance().dropItem(x, y, Items.ItemType.values()[c]);
            return true;}
        if (Game.getInstance().getBackMenu()) {
            stopAll = true;
            return false;
        }
        if (hit) {
            if (countHit < 30) {
                countHit++;
            } else
                stopHit();
        }
        if (hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();


        Point player = Game.getInstance().getPlayerPosition();
        Point turret = new Point(x + centerX, y + centerY);
        if(turret.distance(player)<300){
        float xDistance = player.x- turret.x ;
        float yDistance = player.y- turret.y ;

        angle = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        if (angle < 0)
            angle = angle + 360;
        xDistance = turret.x-player.x ;
        yDistance =turret.y-player.y ;
        angle2 = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        if (angle2 < 0)
            angle2 = angle2 + 360;


        turretCount+=1;
        if(turretCount==15){
            Bullets.getInstance().zombieShot(x+centerX,y+centerY-5 , angle);
            turretCount=-1;}

        int i=checkDirection(angle2);
        switch (i){
            case 0-> moveLeft();
            case 1-> moveDown();
            case 2-> moveRight();
            case 3 -> moveUp();
        }}
                else
            isMoving=false;


                return true;
        }

    private boolean checkAngle() {
        if(angle>320 || angle<40)
            return true;
        if(angle>50 && angle<130)
            return true;
        if(angle>140 && angle<220)
            return true;
        if(angle>230 && angle<310)
            return true;
        return false;
    }

    private int checkDirection(double angle) {
        if((angle<60 && angle>=0) || (angle>=320)){
            return 0;
        }
        if(angle<140 && angle>=60){
            return 1;

        }
        if(angle<230 && angle>=140){
            return 2;

        }

        return 3;
    }


    private void moveRight() {
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;}


    private void moveLeft() {
            isMoving=true;
            dir=Settings.movementDirection.LEFT;}


    private void moveDown() {
            isMoving=true;
            dir=Settings.movementDirection.DOWN;
    }

    private void moveUp() {
            isMoving=true;
        dir=Settings.movementDirection.UP;

    }
}
