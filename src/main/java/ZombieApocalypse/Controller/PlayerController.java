package ZombieApocalypse.Controller;
import ZombieApocalypse.Loop.GameLoop;
import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.*;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.GraphicPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.awt.event.KeyEvent.VK_ESCAPE;

public class PlayerController implements KeyListener, MouseMotionListener, MouseListener {
    //Gestisce i movimenti del player

    private final GraphicPanel panel;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public PlayerController(GraphicPanel panel) {
        this.panel = panel;
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //setto il cursore personalizzato
                panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameData.mancino) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_I -> Game.getInstance().startMovementUp();
                case KeyEvent.VK_L -> Game.getInstance().startMovementRight();
                case KeyEvent.VK_J -> Game.getInstance().startMovementLeft();
                case KeyEvent.VK_K -> Game.getInstance().startMovementDown();
                case KeyEvent.VK_U -> Game.getInstance().useLeftItem();
                case KeyEvent.VK_O -> Game.getInstance().useRightItem();
                case KeyEvent.VK_SPACE -> Game.getInstance().dropItem();
            }
        }else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> Game.getInstance().startMovementUp();
                case KeyEvent.VK_D -> Game.getInstance().startMovementRight();
                case KeyEvent.VK_A -> Game.getInstance().startMovementLeft();
                case KeyEvent.VK_S -> Game.getInstance().startMovementDown();
                case KeyEvent.VK_Q -> Game.getInstance().useLeftItem();
                case KeyEvent.VK_E -> Game.getInstance().useRightItem();
                case KeyEvent.VK_SPACE -> Game.getInstance().dropItem();
            }
        }
        if( e.getKeyCode()== VK_ESCAPE)
            if(!Game.getInstance().getPause()){
                Game.getInstance().setPause(true);
                ResultsPanel.getInstance().setPause();
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if(GameData.mancino) {
            if (e.getKeyCode() == KeyEvent.VK_I || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K || e.getKeyCode() == KeyEvent.VK_L)
                Game.getInstance().stopMovement();
        }else {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A)
                Game.getInstance().stopMovement();
        }
    }
    private int count=0;
    private int countZombie = 0;
    private int randomZombie = new Random().nextInt(300, 600);
    private int cdd=0;
    Future<?> f1;
    private boolean firstRun=true;

    public void update(){
        try{
        if(f1!=null)
          f1.get();
        if(!Game.getInstance().getPause()&& !Game.getInstance().getBackMenu()){
            Game.getInstance().update();
            f1= ThreadPool.getExecutor().submit(()->panel.update());
            count=0;
            if(countZombie == randomZombie ){
                countZombie = 0;
                if(GameData.sound)
                    PlayWav.getInstance().playZombie();
            }
            else
                countZombie++;
        }if( Game.getInstance().getBackMenu() && count==0){
            Game.getInstance().update();
            f1= ThreadPool.getExecutor().submit(()->panel.update());
            count++;
            return;
        }
        if(count==1){
            count=0;
            Game.getInstance().setBackMenu(false);
        }



    }catch (ExecutionException | InterruptedException e){

    }}

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Game.getInstance().hasPistol)
            panel.getPistolView().update(e.getPoint());
        if(Game.getInstance().hasShotgun)
            panel.getShotgunView().update(e.getPoint());
        if(Game.getInstance().hasGrenade)
            panel.getGrenadeView().update(e.getPoint());

        Game.getInstance().setLastMousePosition(e.getPoint());

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(GameData.mancino && e.getButton()==MouseEvent.BUTTON3)
            Game.getInstance().attack();
        if(!GameData.mancino && e.getButton()==MouseEvent.BUTTON1)
            Game.getInstance().attack();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

