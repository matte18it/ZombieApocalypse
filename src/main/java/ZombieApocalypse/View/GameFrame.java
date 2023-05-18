package ZombieApocalypse.View;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.GameLoop;
import ZombieApocalypse.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameFrame {
private static JFrame frameGame;
private static GameLoop gameLoopObject;

    public static void lauch(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        frameGame = new JFrame();
        frameGame.setSize(Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);
        GraphicPanel graphicPanel=new GraphicPanel();
        frameGame.add(graphicPanel);



        //view.addKeyListener(controller);
        graphicPanel.setFocusable(true);
        graphicPanel.requestFocus();
        frameGame.setUndecorated(true);
        PlayerController playerController=new PlayerController(graphicPanel);
        graphicPanel.addKeyListener(playerController);
        gameLoopObject=new GameLoop(playerController);

        // Mettiamo la finestra al centro dello schermo


        int x = (screenDimension.width - frameGame.getWidth())/2;
        int y = (screenDimension.height - frameGame.getHeight())/2;
        frameGame.setLocation(x, y);
        frameGame.setVisible(true);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameLoopObject.start();


    }

    public static void close() {
        frameGame.dispose();
        gameLoopObject.stop();
        //System.exit(0);

    }
}
