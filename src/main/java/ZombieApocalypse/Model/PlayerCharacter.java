package ZombieApocalypse.Model;


import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.GameFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PlayerCharacter  {
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    //Gestisce il player e i suoi movimenti
    int x ;
    boolean muovo = true, muovo1 = true;
    int y ;
    final int maxHealth=6;
    public int countHit;

    public void addHit() {
        countHit++;
        if(countHit==30) {
            stopHit();
        }
    }

    public Settings.movementDirection dir= Settings.movementDirection.RIGHT;   //Direzione iniziale
    int health;
    int speed=1;
    boolean movement = false;
    boolean hit=false;
    public int wight;
    public int height;
    public int centerX;
    public int centerY;

    public Rectangle hitBox;

    void startMovementUp() {
        movement = true;
        dir= Settings.movementDirection.UP;
    }
    void startMovementDown() {
        movement = true;
        dir=Settings.movementDirection.DOWN;
    }
    void startMovementRight() {
        movement = true;
        dir=Settings.movementDirection.RIGHT;
    }
    void startMovementLeft() {
        movement = true;
        dir=Settings.movementDirection.LEFT;
    }

    void stopMovement() {
        movement = false;
    }

    public boolean isMoving() {
        return movement;
    }
    public boolean getHit() {
        return hit;
    }
    public void stopHit() {
        hit=false;
        countHit=0;
    }



    int getMaxHealth(){
        return maxHealth;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




    PlayerCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        centerX=wight/2;
        centerY=height/2;

        health=6;
        hitBox=new Rectangle(x,y,wight,height);
    }


    public void hit() {
        if(countHit==0){
            if(GameData.sound)
                PlayWav.getInstance().playHurt1Sound();
            hit=true;
            if(health>0){
                health--;
                Game.getInstance().getMenuBar().removeHeart();
                if(health == 0){
                    Game.getInstance().setPause(false);
                    showDialog();
                }
            }
        }
    }

    private void showDialog() {
        if(GameData.sound)
            PlayWav.getInstance().playGameOverSound();

        Font font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        UIManager.put("OptionPane.background",new Color(92,75,35));
        UIManager.put("Panel.background",new Color(18,17,15));
        UIManager.put("OptionPane.minimumSize",new Dimension(500,200));
        UIManager.put("OptionPane.border", new EmptyBorder(10, 10, 10,10));
        UIManager.put("OptionPane.font", ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        UIManager.put("OptionPane.foreground", Color.WHITE);

        //creo la label e gli setto il font personalizzato
        JLabel label = new JLabel();
        label.setFont(font.deriveFont(Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(315, 100));
        label.setPreferredSize(new Dimension(315, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(ResourcesLoader.getInstance().getImageIcon("/VictoryLose/YouLose.png", 315, 113, false));
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton btnMenu = new JButton("Menu");

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu});
        JDialog dialog = new JDialog();

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
                Game.getInstance().refresh();
                Game.getInstance().setPause(false);
                Game.getInstance().setBackMenu(true);
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().stop();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try { saveData(); } catch (IOException ex) { throw new RuntimeException(ex); }
                    }
                });
                GameFrame.menuLaunch();
            }
        });

        dialog.getContentPane().add(pane);
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setSize(new Dimension(515, 220));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void saveData() throws IOException {
        int val1, val2, val3, val4;
        //se i punti della partita sono maggiori del suo record, aggiorno
        if(GameData.punti > GameData.recordPunti)
            GameData.recordPunti = GameData.punti;

        //gestisco i dati
        if(GameData.music) val1 = 1; else val1 = 0;
        if(GameData.sound) val2 = 1; else val2 = 0;
        if(GameData.mancino) val3 = 1; else val3 = 0;
        if(GameData.lang == GameData.Language.EN) val4 = 0; else val4 = 1;

        //salvo i dati
        String path = "https://progettouid.altervista.org/ZombieApocalypse/saveData.php?nickname=" + GameData.nick + "&volumeMusica=" + GameData.musicVolume + "&volumeSuoni=" + GameData.soundVolume + "&musicaAttiva=" + val1 + "&suoniAttivi=" + val2 + "&mancino=" + val3 + "&lingua=" + val4 + "&skinAttiva=" + GameData.skinAttiva + "&punti=" + GameData.recordPunti;
        URL sript = new URL(path);
        URLConnection conn = sript.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;

        if ((inputLine = in.readLine()) != null)
            val1 = Integer.parseInt(inputLine);
    }

    public void cure(){
        if(health<maxHealth){
            health++;
        Game.getInstance().getMenuBar().addHeart();}

    }
    public void speedUp(){
        if(countSpeed==300){
            speedUp=true;
            speed=3;}
        else
            countSpeed=countSpeed+300;

    }
    public void stopSpeed(){
        speedUp=false;
        countSpeed=300;
        speed=1;
    }
    public void addSpeed(){
        countSpeed--;
        if(countSpeed==0)
            stopSpeed();
    }
    public boolean speedUp=false;
    public int countSpeed=300;


    public void move() {
        soundWalk();

        if(dir==Settings.movementDirection.RIGHT && Game.getInstance().getWorld().isWalkable(getX()+wight+(10*speed), getY()) && Game.getInstance().getWorld().isEnemy(getX()+(10*speed), getY(), centerX, centerY) )
            x += (10*speed);
        else if(dir==Settings.movementDirection.LEFT && Game.getInstance().getWorld().isWalkable(getX()-(10*speed), getY()) && Game.getInstance().getWorld().isEnemy(getX()-(10*speed), getY(),centerX,centerY))
            x -= (10*speed);
        else if(dir== Settings.movementDirection.UP && Game.getInstance().getWorld().isWalkable(getX(), getY()-(10*speed)) && Game.getInstance().getWorld().isEnemy(getX(), getY()-(10*speed),centerX,centerY))
            y -= (10*speed);
        else if(dir==Settings.movementDirection.DOWN && Game.getInstance().getWorld().isWalkable(getX(), getY()+height+(10*speed)) && Game.getInstance().getWorld().isEnemy(getX(), getY()+(10*speed),centerX,centerY))
            y += (10*speed);
        else
            movement=false;


        hitBox.x=x;
        hitBox.y=y;
    }
    private void soundWalk() {
        if(GameData.sound && muovo) {
            muovo = false;
            if(muovo1){
                muovo1 = false;
                PlayWav.getInstance().playWalkSound();
            }
            else
                muovo1 = true;
        }
        else{
            muovo = true;
        }
    }

    public void chooseCoordinate() {
        Point p=Game.getInstance().getWorld().selectPlayerPosition();
        x=p.x;
        y=p.y;
    }
}

