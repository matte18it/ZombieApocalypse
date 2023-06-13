package ZombieApocalypse.Controller;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.*;
import ZombieApocalypse.View.GraphicPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
public class PlayerController implements KeyListener, MouseMotionListener ,MouseListener {
    private final GraphicPanel panel;
    private boolean lastRun=false;
    private int countZombie = 0;
    private final Random randomVariable=new Random();
    private final int minTimeSoundZombie=300;
    private final int maxTimeSoundZombie=600;
    private int randomZombie = randomVariable.nextInt(minTimeSoundZombie,maxTimeSoundZombie);
    Future<?> f1;
    public PlayerController(GraphicPanel panel) {
        this.panel = panel;
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //setto il cursore personalizzato
                panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));}
        });}
    public void update(){
        try{
            if(f1!=null)
                f1.get();
            if(!Game.getInstance().getPause()&& !Game.getInstance().getBackMenu()){
                Game.getInstance().update();
                f1= ThreadPool.getExecutor().submit(()->panel.update());
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
            }if( Game.getInstance().getBackMenu() && !lastRun){
                Game.getInstance().update();
                f1= ThreadPool.getExecutor().submit(()->panel.update());
                lastRun=true;
                return;
            }
            if(lastRun){
                Game.getInstance().setBackMenu(false);
            }



        }catch (ExecutionException | InterruptedException e){

        }}
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
        if( e.getKeyCode()== KeyEvent.VK_ESCAPE)
            if(!Game.getInstance().getPause()){
                Game.getInstance().setPause(true);
                ResultsPanel.getInstance().showPause();
            }
    }
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
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}


}

