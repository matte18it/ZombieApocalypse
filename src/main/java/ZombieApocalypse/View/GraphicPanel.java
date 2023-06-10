package ZombieApocalypse.View;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Enemy.Enemy;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Guns.Bullet;
import ZombieApocalypse.Model.Guns.Bullets;
import ZombieApocalypse.Model.Items.Item;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Enemy.BossView;
import ZombieApocalypse.View.Gun.*;
import ZombieApocalypse.View.Player.CharacterView;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphicPanel extends JPanel {
    int count=0;
    //Disegna il mondo
    private final CharacterView characterView = new CharacterView();
    private static final PistolView pistolView = new PistolView();
    private static final BanditPistolView pistolBandit=new BanditPistolView();
    private static final ShotgunView shotgunView=new ShotgunView();
    private static final GrenadeView grenadeView=new GrenadeView();
    private static final KnifeView knife=new KnifeView();
    public GrenadeView getGrenadeView(){return grenadeView;}

    public PistolView getPistolView(){
        return pistolView;
    }
    public ShotgunView getShotgunView() {
        return shotgunView;
    }
    World world=Game.getInstance().getWorld();
    private final int numeroImmagini=World.Block.values().length;
    private final Map<World.Block, Image> images=new Hashtable<>();
    public GraphicPanel()  {
        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
        World.Block[] enue=new World.Block[numeroImmagini];
        for(int i=0; i<numeroImmagini; i++){
            enue[i]= World.Block.values()[i];
            images.put(enue[i], ResourcesLoader.getInstance().getImage("/AmbienteDiGioco/"+enue[i]+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, true));
        }
        }



Random m=new Random();
    @Override
    protected void paintComponent(Graphics g) {
        if(count==0){
            Items.getInstance().generateRandomItems();
            Enemies.getInstance().generateRandomEnemies();
        }
        int r;

        super.paintComponent(g);
        for(int i = 0; i < world.getSizeX(); i++) {
            int x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < world.getSizeY(); j++) {
                int y = j * Settings.CELL_SIZEY;
                if(world.world[i][j]== World.Block.WATER0 || world.world[i][j]== World.Block.WATER1 || world.world[i][j]== World.Block.WATER2){
                    r=m.nextInt(0, 100);
                    if(r<5){
                        switch (world.world[i][j]){
                            case WATER0 -> world.world[i][j]= World.Block.WATER1;
                            case WATER1 -> world.world[i][j]= World.Block.WATER2;
                            case WATER2 -> world.world[i][j]= World.Block.WATER0;
                        }
                    }


                }
                    g.drawImage(images.get(world.world[i][j]), x, y, null);

                }

            }



        if(Game.getInstance().hasPistol){
        if(Game.getInstance().getPistolModel().isUp()){
            g.drawImage(pistolView.getCurrentImage(), Game.getInstance().getPistolModel().imagePosition.x, Game.getInstance().getPistolModel().imagePosition.y, Game.getInstance().getPistolModel().getHeight(), Game.getInstance().getPistolModel().getWidth(), null);
        }else{
            g.drawImage(pistolView.getCurrentImage(), Game.getInstance().getPistolModel().imagePosition.x, Game.getInstance().getPistolModel().imagePosition.y, Game.getInstance().getPistolModel().getWidth(), Game.getInstance().getPistolModel().getHeight(), null);
        }}
        if(Game.getInstance().hasShotgun){
            if(Game.getInstance().getShotgunModel().isUp()){
                g.drawImage(shotgunView.getCurrentImage(), Game.getInstance().getShotgunModel().imagePosition.x, Game.getInstance().getShotgunModel().imagePosition.y, Game.getInstance().getShotgunModel().getHeight(), Game.getInstance().getShotgunModel().getWidth(), null);
            }else{
                g.drawImage(shotgunView.getCurrentImage(), Game.getInstance().getShotgunModel().imagePosition.x, Game.getInstance().getShotgunModel().imagePosition.y, Game.getInstance().getShotgunModel().getWidth(), Game.getInstance().getShotgunModel().getHeight(), null);
            }}



        for (Item b : Items.getInstance().getItems()) {
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getWight(), b.getHeight(), null);
        }
        for (Enemy b : Enemies.getInstance().getEnemies()) {
            b.getView().update();
            if (b.type == Enemies.EnemiesType.BANDIT) {
                pistolBandit.update(b);
                g.drawImage(pistolBandit.getCurrentImage(), b.getGunX(), b.getGunY(), Game.getInstance().getPistolModel().getWidth(), Game.getInstance().getPistolModel().getHeight(), null);
            }
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getWight(), b.getHeight(), null);



        }
        if(Game.getInstance().getPlayerCharacter().speedUp ){
        g.setColor(Color.WHITE);
        float t= (float) Game.getInstance().getPlayerCharacter().countSpeed /60;
            String h = String.format("%.2f", t);
        g.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        String c="SpeedUp: "+String.valueOf(h);
        g.drawString(c, 20, 20);}


        count++;

        for(Bullet b: Bullets.getInstance().getBullets()){
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getDimension(), b.getDimension(), null);
        }





        if(Game.getInstance().hasKnife){
            if(Game.getInstance().getKnifeModel().isUp())
                g.drawImage(knife.getCurrentImage(), Game.getInstance().getKnifeModel().imagePosition.x,Game.getInstance().getKnifeModel().imagePosition.y, Game.getInstance().getKnifeModel().getHeight(), Game.getInstance().getKnifeModel().getWidth(), null);
            else
                g.drawImage(knife.getCurrentImage(), Game.getInstance().getKnifeModel().imagePosition.x,Game.getInstance().getKnifeModel().imagePosition.y, Game.getInstance().getKnifeModel().getWidth(), Game.getInstance().getKnifeModel().getHeight(), null);

        }
        if(Game.getInstance().hasGrenade){

            if(Game.getInstance().getGrenadeModel().isUp())
                g.drawImage(grenadeView.getCurrentImage(), Game.getInstance().getGrenadeModel().imagePosition.x, Game.getInstance().getGrenadeModel().imagePosition.y, Game.getInstance().getGrenadeModel().getHeight(), Game.getInstance().getGrenadeModel().getWidth(), null);
            else
                g.drawImage(grenadeView.getCurrentImage(), Game.getInstance().getGrenadeModel().imagePosition.x, Game.getInstance().getGrenadeModel().imagePosition.y, Game.getInstance().getGrenadeModel().getWidth(), Game.getInstance().getGrenadeModel().getHeight(), null);

        }

        g.drawImage(characterView.getCurrentImage(), Game.getInstance().getPlayerCharacter().getX(), Game.getInstance().getPlayerCharacter().getY(), characterView.width, characterView.height, null);



    }
    public void update() {
        characterView.update();
        //Sposto l'arma dove è il character senza girarla
        if(Game.getInstance().hasPistol)
            pistolView.update(null);
        if(Game.getInstance().hasKnife)
            knife.update();
        if(Game.getInstance().hasShotgun)
            shotgunView.update(null);
        if(Game.getInstance().hasGrenade){
            grenadeView.update();
            grenadeView.update(null);}
        Bullets.getInstance().update();
        Items.getInstance().update();
        Enemies.getInstance().update();
        repaint();
    }


}

