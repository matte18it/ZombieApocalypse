package ZombieApocalypse.Controller;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.*;
import ZombieApocalypse.View.GraphicPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PlayerController implements KeyListener, MouseMotionListener ,MouseListener {
    private final GraphicPanel panel;
    //Run in più per aggiornare il panel al ritorno nel menu
    private boolean lastRun=false;
    private int countZombie = 0;
    private final Random randomVariable=new Random();
    private final int minTimeSoundZombie=300;
    private final int maxTimeSoundZombie=600;
    //Gestione random del suono degli zombie
    private int randomZombie = randomVariable.nextInt(minTimeSoundZombie,maxTimeSoundZombie);
    Thread graphicUpdatethread;
    public PlayerController(GraphicPanel panel) {
        this.panel = panel;
        graphicUpdatethread =new Thread(panel::update);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //setto il cursore personalizzato
                panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));}
        });}
    public void update(){
        if(graphicUpdatethread.isAlive()){
        try{
            graphicUpdatethread.join();
        }catch (InterruptedException e){
            ResultsPanel.getInstance().showError("Errore nel aggiornamento della grafica", 80, e);
        }}
            if(Game.getInstance().getPause() && !Game.getInstance().getBackMenu()){
                Game.getInstance().update();
                //lancio dell'update del GraphicPanel con un thread
                graphicUpdatethread =new Thread(panel::update);
                graphicUpdatethread.start();
                if(lastRun)
                    lastRun=false;
                if(countZombie == randomZombie ){
                    countZombie = 0;
                    randomZombie=randomVariable.nextInt(minTimeSoundZombie, maxTimeSoundZombie);
                    if(GameData.sound)
                        PlayWav.getInstance().playZombie();
                }
                else
                    countZombie++;
                //Gestione del ritorno al menù
            }if( Game.getInstance().getBackMenu() && !lastRun){
                Game.getInstance().update();
                graphicUpdatethread =new Thread(panel::update);
                graphicUpdatethread.start();
                lastRun=true;
                return;
            }
            if(lastRun){
                Game.getInstance().setBackMenu(false);
            }
        }
//Gestione degli eventi: tastiera e movimenti del mouse
    @Override
    public void keyPressed(KeyEvent e) {
        //Gestione del Mancinismo
        if(GameData.mancino) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_I -> Game.getInstance().getPlayer().startMovementUp();
                case KeyEvent.VK_L -> Game.getInstance().getPlayer().startMovementRight();
                case KeyEvent.VK_J -> Game.getInstance().getPlayer().startMovementLeft();
                case KeyEvent.VK_K -> Game.getInstance().getPlayer().startMovementDown();
                case KeyEvent.VK_U -> Game.getInstance().useLeftItem();
                case KeyEvent.VK_O -> Game.getInstance().useRightItem();
                case KeyEvent.VK_SPACE -> Game.getInstance().dropItem();
            }
        }else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> Game.getInstance().getPlayer().startMovementUp();
                case KeyEvent.VK_D -> Game.getInstance().getPlayer().startMovementRight();
                case KeyEvent.VK_A -> Game.getInstance().getPlayer().startMovementLeft();
                case KeyEvent.VK_S -> Game.getInstance().getPlayer().startMovementDown();
                case KeyEvent.VK_Q -> Game.getInstance().useLeftItem();
                case KeyEvent.VK_E -> Game.getInstance().useRightItem();
                case KeyEvent.VK_SPACE -> Game.getInstance().dropItem();
            }
        }
        if( e.getKeyCode()== KeyEvent.VK_ESCAPE)
            if(Game.getInstance().getPause()){
                Game.getInstance().setPause(true);
                ResultsPanel.getInstance().showPause();
            }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(GameData.mancino) {
            if (e.getKeyCode() == KeyEvent.VK_I || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K || e.getKeyCode() == KeyEvent.VK_L)
                Game.getInstance().getPlayer().stopMovement();
        }else {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A)
                Game.getInstance().getPlayer().stopMovement();
        }
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
    public void mousePressed(MouseEvent e) {
        if(GameData.mancino && e.getButton()==MouseEvent.BUTTON3)
            Game.getInstance().attack();
        if(!GameData.mancino && e.getButton()==MouseEvent.BUTTON1)
            Game.getInstance().attack();
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
}

