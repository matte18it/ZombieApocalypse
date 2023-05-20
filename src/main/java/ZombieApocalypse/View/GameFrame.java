package ZombieApocalypse.View;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.GameLoop;
import ZombieApocalypse.Settings;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private static JFrame frameGame;
    private static GameLoop gameLoopObject;

    public static void lauch(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        frameGame = new JFrame();
        frameGame.setSize(Settings.WINDOW_SIZEX, Settings.WINDOW_SIZEY+Settings.MENU_BAR_HEIGHT);

        MenuBarView menuBarView=new MenuBarView();
        GraphicPanel graphicPanel=new GraphicPanel(menuBarView);
        frameGame.setLayout(new BoxLayout(frameGame.getContentPane(), BoxLayout.PAGE_AXIS));



        frameGame.add(graphicPanel);
        frameGame.add(menuBarView);

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
