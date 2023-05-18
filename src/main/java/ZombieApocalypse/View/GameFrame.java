package ZombieApocalypse.View;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.GameLoop;
import ZombieApocalypse.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameFrame extends JPanel{


    public static void lauch(){
        JFrame frame = new JFrame();
        frame.setSize(Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);
        GraphicPanel graphicPanel=new GraphicPanel();
        frame.add(graphicPanel);

        //view.addKeyListener(controller);
        graphicPanel.setFocusable(true);
        graphicPanel.requestFocus();
        frame.setUndecorated(true);
        PlayerController playerController=new PlayerController(graphicPanel);
        graphicPanel.addKeyListener(playerController);
        GameLoop gameLoop=new GameLoop(playerController);

        /* Mettiamo la finestra al centro dello schermo
        Non utile in questo momento
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - frame.getWidth())/2;
        int y = (screenDimension.height - frame.getHeight())/2;
        //frame.setLocation(x, y); */
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameLoop.start();
    }

}
