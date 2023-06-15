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
import ZombieApocalypse.View.Gun.*;
import ZombieApocalypse.View.Player.CharacterView;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class GraphicPanel extends JPanel  {
    //Disegna il mondo
    private boolean firstLoad=true;
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
    private final World.Block[][] worldMatrix=Game.getInstance().getWorld().getMatrix();
    private final Map<World.Block, Image> images=new Hashtable<>();
    public GraphicPanel()  {
        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
        int numeroImmagini = World.Block.values().length;  //load delle immagini del World
        World.Block[] enue=new World.Block[numeroImmagini];
        for(int i = 0; i< numeroImmagini; i++){
            enue[i]= World.Block.values()[i];
            images.put(enue[i], ResourcesLoader.getInstance().getImage("/AmbienteDiGioco/"+enue[i]+".png", Settings.CELL_SIZEX, Settings.CELL_SIZEY, true));
        }
        }
    Random m=new Random();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(firstLoad){  //primo update di goni mappa
            Items.getInstance().generateRandomItems();
            Enemies.getInstance().generateRandomEnemies(); firstLoad=false;}
        int r;     //Update del mondo
        for(int i = 0; i < worldMatrix.length; i++) {
            int x = i * Settings.CELL_SIZEX;
            for(int j = 0; j < worldMatrix[i].length; j++) {
                int y = j * Settings.CELL_SIZEY;
                if(worldMatrix[i][j]== World.Block.WATER0 || worldMatrix[i][j]== World.Block.WATER1 || worldMatrix[i][j]== World.Block.WATER2){
                    r=m.nextInt(0, 100);
                    if(r<5){
                        switch (worldMatrix[i][j]){ //Animazione dell'acqua
                            case WATER0 -> worldMatrix[i][j]= World.Block.WATER1;
                            case WATER1 -> worldMatrix[i][j]= World.Block.WATER2;
                            case WATER2 -> worldMatrix[i][j]= World.Block.WATER0;
                        }
                    }
                }
                    g.drawImage(images.get(worldMatrix[i][j]), x, y, null);
                }
            }


        //Armi del Player
        if(Game.getInstance().hasPistol){
        if(Game.getInstance().getPistol().isUp()){
            g.drawImage(pistolView.getCurrentImage(), Game.getInstance().getPistol().getImagePosition().x, Game.getInstance().getPistol().getImagePosition().y, Game.getInstance().getPistol().getHeight(), Game.getInstance().getPistol().getWidth(), null);
        }else{
            g.drawImage(pistolView.getCurrentImage(), Game.getInstance().getPistol().getImagePosition().x, Game.getInstance().getPistol().getImagePosition().y, Game.getInstance().getPistol().getWidth(), Game.getInstance().getPistol().getHeight(), null);
        }}
        if(Game.getInstance().hasShotgun){
            if(Game.getInstance().getShotgun().isUp()){
                g.drawImage(shotgunView.getCurrentImage(), Game.getInstance().getShotgun().getImagePosition().x, Game.getInstance().getShotgun().getImagePosition().y, Game.getInstance().getShotgun().getHeight(), Game.getInstance().getShotgun().getWidth(), null);
            }else{
                g.drawImage(shotgunView.getCurrentImage(), Game.getInstance().getShotgun().getImagePosition().x, Game.getInstance().getShotgun().getImagePosition().y, Game.getInstance().getShotgun().getWidth(), Game.getInstance().getShotgun().getHeight(), null);
            }}
        //Oggetti
        synchronized (Items.getInstance().getItems()){
        for (Item b : Items.getInstance().getItems()) {
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getWight(), b.getHeight(), null);
        }}
        //Nemici
        synchronized (Enemies.getInstance().getEnemies()){
        for (Enemy b : Enemies.getInstance().getEnemies()) {
            b.getView().update();
            if (b.type == Enemies.EnemiesType.BANDIT) {
                pistolBandit.update(b);
                g.drawImage(pistolBandit.getCurrentImage(), b.getGunX(), b.getGunY(), Game.getInstance().getPistol().getWidth(), Game.getInstance().getPistol().getHeight(), null);
            }
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getWight(), b.getHeight(), null);

        }}
        if(Game.getInstance().getPlayer().getSpeedUp() ){  //Scritta dello speed-Up in alto
        g.setColor(Color.WHITE);
        float t= (float) Game.getInstance().getPlayer().getCountSpeed() /60;
            String h = String.format("%.2f", t);
        g.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        String c="SpeedUp: "+ h;
        g.drawString(c, 20, 20);}

        //Proiettili
        synchronized (Bullets.getInstance().getBullets()){
        for(Bullet b: Bullets.getInstance().getBullets()){
            b.getView().update();
            g.drawImage(b.getView().getCurrentImage(), b.getX(), b.getY(), b.getDimension(), b.getDimension(), null);
        }}
        //Oggetti del Player
        if(Game.getInstance().hasKnife){
            if(Game.getInstance().getKnife().isUp())
                g.drawImage(knife.getCurrentImage(), Game.getInstance().getKnife().getImagePosition().x,Game.getInstance().getKnife().getImagePosition().y, Game.getInstance().getKnife().getHeight(), Game.getInstance().getKnife().getWidth(), null);
            else
                g.drawImage(knife.getCurrentImage(), Game.getInstance().getKnife().getImagePosition().x,Game.getInstance().getKnife().getImagePosition().y, Game.getInstance().getKnife().getWidth(), Game.getInstance().getKnife().getHeight(), null);

        }
        if(Game.getInstance().hasGrenade){

            if(Game.getInstance().getGrenade().isUp())
                g.drawImage(grenadeView.getCurrentImage(), Game.getInstance().getGrenade().getImagePosition().x, Game.getInstance().getGrenade().getImagePosition().y, Game.getInstance().getGrenade().getHeight(), Game.getInstance().getGrenade().getWidth(), null);
            else
                g.drawImage(grenadeView.getCurrentImage(), Game.getInstance().getGrenade().getImagePosition().x, Game.getInstance().getGrenade().getImagePosition().y, Game.getInstance().getGrenade().getWidth(), Game.getInstance().getGrenade().getHeight(), null);

        }
        //Player
        g.drawImage(characterView.getCurrentImage(), Game.getInstance().getPlayer().getX(), Game.getInstance().getPlayer().getY(), characterView.width, characterView.height, null);

    }
    public void update() { //Update degli elementi a schermo
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
        Thread repaintThread=new Thread(this::repaint);
        repaintThread.start();

    }


}

