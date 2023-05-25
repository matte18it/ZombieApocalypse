package ZombieApocalypse.Controller;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.View.GraphicPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PlayerController implements KeyListener, MouseMotionListener {
    //Gestisce i movimenti del player

    private final GraphicPanel panel;

    public PlayerController(GraphicPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP  -> Game.getInstance().startMovementUp();
            case KeyEvent.VK_RIGHT -> Game.getInstance().startMovementRight();
            case KeyEvent.VK_LEFT -> Game.getInstance().startMovementLeft();
            case KeyEvent.VK_DOWN -> Game.getInstance().startMovementDown();

            case KeyEvent.VK_SPACE -> Game.getInstance().attack();

            case KeyEvent.VK_Q -> Game.getInstance().closeGame();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT ||e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_UP ||e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_A)
            Game.getInstance().stopMovement();
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
        panel.getGunView().update(e.getPoint());

    }
}

