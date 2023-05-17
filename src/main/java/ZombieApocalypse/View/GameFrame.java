package ZombieApocalypse.View;

import ZombieApocalypse.Settings;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel{


    public static void lauch(){
        JFrame frame = new JFrame();
        frame.setSize(Settings.WINDOW_SIZE, Settings.WINDOW_SIZE);
        GraphicPanel graphicPanel=new GraphicPanel();
        graphicPanel.repaint();
        frame.add(graphicPanel);

        //view.addKeyListener(controller);
        graphicPanel.setFocusable(true);
        graphicPanel.requestFocus();
        frame.setUndecorated(true);
        // Mettiamo la finestra al centro dello schermo
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - frame.getWidth())/2;
        int y = (screenDimension.height - frame.getHeight())/2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

}
