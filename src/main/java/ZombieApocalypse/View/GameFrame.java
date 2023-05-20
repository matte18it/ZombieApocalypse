package ZombieApocalypse.View;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.GameLoop;
import ZombieApocalypse.Settings;
import ZombieApocalypse.LoginMenu.LoginLoop;
import ZombieApocalypse.LoginMenu.LoginPanel;
import ZombieApocalypse.TimeLoop;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel{
    private static JFrame frameGame;
    private static GameLoop gameLoopObject;
    private static LoginPanel panel;
    public static LoginLoop loop;

    public static void loginLaunch(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        frameGame = new JFrame("Login");
        frameGame.setSize(Settings.WINDOW_SIZEX, Settings.WINDOW_SIZEY+Settings.MENU_BAR_HEIGHT);
        frameGame.setUndecorated(true);

        //Creo un loginPaint, parte interna della mia cornice
        panel = new LoginPanel();
        //Inserisco il panel appena creato all'intrerno del mio frame
        frameGame.add(panel);

        //Creo un oggetto loginLoop per usare un thread che gestisca l'animazione del titolo
        loop = new LoginLoop(panel);
        loop.start();

        // Mettiamo la finestra al centro dello schermo
        int x = (screenDimension.width - frameGame.getWidth())/2;
        int y = (screenDimension.height - frameGame.getHeight())/2;
        frameGame.setLocation(x, y);
        frameGame.setVisible(true);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void gameLaunch(){
        frameGame.remove(panel);
        frameGame.repaint();
        frameGame.setTitle("Game");

        MenuBarView menuBarView=new MenuBarView();
        GraphicPanel graphicPanel=new GraphicPanel();
        frameGame.setLayout(new BoxLayout(frameGame.getContentPane(), BoxLayout.PAGE_AXIS));

        frameGame.add(graphicPanel);
        frameGame.add(menuBarView);

        graphicPanel.setFocusable(true);
        graphicPanel.requestFocus();
        PlayerController playerController=new PlayerController(graphicPanel);
        graphicPanel.addKeyListener(playerController);
        gameLoopObject=new GameLoop(playerController);
        menuBarView.setBar();
        gameLoopObject.start();
    }

    public static void close() {
        frameGame.dispose();
        gameLoopObject.stop();
        TimeLoop.stop();
        System.exit(0);
    }
}
