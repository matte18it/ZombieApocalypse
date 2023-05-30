package ZombieApocalypse.Controller;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.GraphicPanel;

import java.awt.event.*;

public class PlayerController implements KeyListener, MouseMotionListener, MouseListener {
    //Gestisce i movimenti del player

    private final GraphicPanel panel;

    public PlayerController(GraphicPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameData.mancino) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_I -> Game.getInstance().startMovementUp();
                case KeyEvent.VK_L -> Game.getInstance().startMovementRight();
                case KeyEvent.VK_J -> Game.getInstance().startMovementLeft();
                case KeyEvent.VK_K -> Game.getInstance().startMovementDown();
            }
        }else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> Game.getInstance().startMovementUp();
                case KeyEvent.VK_D -> Game.getInstance().startMovementRight();
                case KeyEvent.VK_A -> Game.getInstance().startMovementLeft();
                case KeyEvent.VK_S -> Game.getInstance().startMovementDown();
            }
        }
        if( e.getKeyCode()==KeyEvent.VK_ESCAPE)
            Game.getInstance().closeGame();
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

    public void update() {
        Game.getInstance().update();
        Game.getInstance().checkCollision();
        panel.update();
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

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(GameData.mancino && e.getButton()==2)
            Game.getInstance().attack();
        if(!GameData.mancino && e.getButton()==1)
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

