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
        //PlayerView view = new PlayerView();
        //PlayerController controller = new PlayerController(view);
        //view.addKeyListener(controller);
        //view.setFocusable(true);
        //view.requestFocus();
        //frame.add(view);
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
