package ZombieApocalypse.Model.Enemy;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.CountPoint;
import ZombieApocalypse.Utility.Settings;
import java.awt.*;
public class TurretZombie extends Enemy{

    public TurretZombie(int x, int y) {super(x,y, Enemies.EnemiesType.TURRETZOMBIE);}
    //calcolo dell'angolo fra player e nemico
    double angle;
    public boolean update() {
        //Gestione della morte
        if(dying)
            return false;
        if (healt <= 0 ) {
            CountPoint.getInstance().setPoint(Enemies.EnemiesType.TURRETZOMBIE);
            dying = true;
            int c = random.nextInt(4, 9);
            Items.getInstance().dropItem(x, y, Items.ItemType.values()[c]);
            return true;}
        //Gestione del ritorno al menu
        if (Game.getInstance().getBackMenu()) {
            return false;
        }
        //Gestione delle hit
        if (hit) {
            if (countHit < 30) {
                countHit++;
            } else
                stopHit();
        }
        //Gestione delle hit col player
        if (hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox))
            Game.getInstance().getPlayerCharacter().hit();

        Point player = getPlayerPosition();
        Point enemy = getEnemyPosition();
        //Gestione dei Pattern
        if(enemy.distance(player)<300){
            //Calcolo dell'angolo per lo spostamento dello zombie
        float xDistance = player.x- enemy.x ;
        float yDistance = player.y- enemy.y ;
        angle = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        if (angle < 0)
            angle = angle + 360;
        xDistance = enemy.x-player.x ;
        yDistance =enemy.y-player.y ;
        //Calcolo dell'angolo fra nemico e player per lo sparo
            double angle2 = -Math.toDegrees(Math.atan2(yDistance, xDistance));
        if (angle2 < 0)
            angle2 = angle2 + 360;
        turretCount+=1;
        if(turretCount==15){
            Bullets.getInstance().zombieShot(x+centerX,y+centerY-5 , angle);
            turretCount=-1;}
        //Spostamento di direzione dello zombie
        int i=checkDirection(angle2);
        switch (i){
            case 0-> moveLeft();
            case 1-> moveDown();
            case 2-> moveRight();
            case 3 -> moveUp();
        }}
                else
            isMoving=false; return true;
        }
    private int checkDirection(double angle) {
        if((angle<60 && angle>=0) || (angle>=320)){return 0;}
        if(angle<140 && angle>=60){return 1;}
        if(angle<230 && angle>=140){return 2;}
        return 3;
    }
    //il nemico non si muove quindi non serve aggiornare la posizione
     void moveRight() {
            isMoving=true;
            dir=Settings.movementDirection.RIGHT;}


     void moveLeft() {
            isMoving=true;
            dir=Settings.movementDirection.LEFT;}


     void moveDown() {
            isMoving=true;
            dir=Settings.movementDirection.DOWN;
    }

     void moveUp() {
            isMoving=true;
            dir=Settings.movementDirection.UP;

    }
}
