 package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.MenuBarView;

import java.awt.*;

public class Game {
    //Gestisce gli aspetti del gioco
    private final World world = new World();
    private final PlayerCharacter character = new PlayerCharacter();
    private final EnemyCharacter enemy = new EnemyCharacter();
    private final GunModel gun = new GunModel();

    private static final Game instance = new Game();
    private Game() {
    }
    public static Game getInstance() {
        return instance;
    }

    public void startMovementRight() {
        character.startMovementRight();}
    public void startMovementUp() {
        character.startMovementUp();

    }

    public void startMovementDown() {
        character.startMovementDown();
    }
    public void startMovementLeft() {
        character.startMovementLeft();
    }

    public void stopMovement() {
        character.stopMovement();
    }


    //Da fare nel Game Loop
    public void update() {
        if(character.isMoving())
            character.move();}



    public PlayerCharacter getPlayerCharacter() {
        return character;}
    public GunModel getGunModel() {
        return gun;}


    public int getPlayerLife() {
        return character.health;}
    public int getPlayerMaxLife() {
        return character.getMaxHealth();}

    public PlayerCharacter.movementDirection getPlayerDirection() {
        return character.dir;}

    public void closeGame() {
        GameFrame.close();
    }




    public void updateTime(long start) {
        long timeElapsed=System.nanoTime()-start;
        long t=timeElapsed/1000000000;
        MenuBarView.updateTimeLable(t);
    }


    public void attack() {
        gun.attack();

    }

    public Point coordinateToIJ(int x, int y) {
        int x1=x* Settings.WORLD_SIZEX/Settings.WINDOW_SIZEX;
        int y1=y* Settings.WORLD_SIZEY/Settings.WINDOW_SIZEY;
        return new Point(x1,y1);
    }

    public Character getEnemyCharacter() {
        return enemy;
    }
}
