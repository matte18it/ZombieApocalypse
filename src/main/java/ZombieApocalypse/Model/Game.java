package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Guns.GrenadeModel;
import ZombieApocalypse.Model.Guns.KnifeModel;
import ZombieApocalypse.Model.Guns.PistolModel;
import ZombieApocalypse.Model.Guns.ShotgunModel;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.MenuBar.MenuBarView;

import java.awt.*;

public class Game {
    //Gestisce gli aspetti del gioco
    private  World world = new World();
    public void loadEditorMap(String name) {
        world.setFileName(name);
    }


    private final PlayerCharacter character = new PlayerCharacter();
    private final KnifeModel knife=new KnifeModel();
    private final PistolModel pistol = new PistolModel();
    private final GrenadeModel grenade=new GrenadeModel();
    private final ShotgunModel shotgun=new ShotgunModel();
    private boolean pause = false;
    private Point mousePoint=new Point(0,0);

    //Gestione delle armi, per adesso
    public boolean hasGrenade=false;
    public boolean hasPistol=false;
    public boolean hasKnife=true;
    public boolean hasShotgun=false;

    private MenuBarModel menuBar;
    public void setMenuBar(MenuBarView m){
        menuBar=new MenuBarModel(m);
    }
    public MenuBarModel getMenuBar(){
        return menuBar;
    }
    public KnifeModel getKnifeModel(){
        return knife;
    }
    public ShotgunModel getShotgunModel(){
        return shotgun;
    }


    private static Game instance = new Game();
    private Game() {}
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
            character.move();
        if(character.hit)
            character.addHit();
        if(character.speedUp)
            character.addSpeed();}

    public PlayerCharacter getPlayerCharacter() {
        return character;}
    public PistolModel getPistolModel() {
        return pistol;}


    public int getPlayerLife() {
        return character.health;}
    public int getPlayerMaxLife() {
        return character.getMaxHealth();}

    public Settings.movementDirection getPlayerDirection() {
        return character.dir;}

    public void closeGame() {
        GameFrame.close();
    }

    public void attack() {
        if(hasPistol && menuBar.numBullet()>0 ){

            pistol.attack();
            menuBar.removeAmmo(1);}
        if(hasKnife)
            knife.attack();
        if(hasShotgun && menuBar.numBullet()>2 ){
            shotgun.attack();
            menuBar.removeAmmo(3);}
        if(hasGrenade){
            grenade.attack();
            menuBar.setLabelEmpty(itemUsed);
            setKnife();}
    }

    public Point coordinateToIJ(int x, int y) {
        int x1=x* Settings.WORLD_SIZEX/Settings.WINDOW_SIZEX;
        int y1=y* Settings.WORLD_SIZEY/Settings.WINDOW_SIZEY;
        return new Point(x1,y1);
    }




    public void refresh(){
        instance = new Game();
    }
    private boolean backMenu=false;

    public void setBackMenu(boolean e){
        backMenu=e;
    }

    public void updateTime(long time) {
            menuBar.updateTimeLable(time);
    }

    public GrenadeModel getGrenadeModel() {
        return grenade;
    }

    public boolean getPause(){
        return  pause;
    }
    public void setPause(boolean pause){
        this.pause = pause;
    }

    public boolean getBackMenu() {
        return backMenu;
    }
    boolean itemUsed;

    public void useLeftItem() {
        itemUsed=true;
        menuBar.useItem(itemUsed);
    }

    public void useRightItem() {
        itemUsed=false;
        menuBar.useItem(itemUsed);
    }


    public void dropItem() {
        boolean b=true;
        Items.ItemType value=menuBar.getLabel1();
        if(value== Items.ItemType.EMPTY){
            value=menuBar.getLabel2();
        b=false;}

        if(value!= Items.ItemType.EMPTY){
            if(world.isWalkable(character.getX()+character.wight+10, character.getY())){
                Items.getInstance().dropItem(character.getX()+character.wight+10, character.getY(), value);
                menuBar.setLabelEmpty(b);
                if(value== Items.ItemType.SHOTGUN || value== Items.ItemType.PISTOL || value==Items.ItemType.GRENADE)
                    setKnife();
            }

    }}

    private void setKnife() {
        hasGrenade=false;
        hasPistol=false;
        hasKnife=true;
        hasShotgun=false;
    }

    public void speedUpPlayer() {
        character.speedUp();
    }

    public void setShotgun() {
         hasGrenade=false;
        hasPistol=false;
        hasKnife=false;
         hasShotgun=true;
         shotgun.update(mousePoint);


    }

    public void setPistol() {
        hasGrenade=false;
        hasPistol=true;
        hasKnife=false;
        hasShotgun=false;
        pistol.update(mousePoint);
    }

    public void setGrenade() {
        hasGrenade=true;
        hasPistol=false;
        hasKnife=false;
        hasShotgun=false;
        grenade.update(mousePoint);
    }


    public void setLastMousePosition(Point e) {
        mousePoint=e;
    }
    public World getWorld(){
        return world;
    }

    public Point getPlayerPosition() {
        return new Point(character.getX()+character.centerX, character.getY()+character.centerY);
    }



}
