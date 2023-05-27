package ZombieApocalypse.View;

import ZombieApocalypse.Utility.GameData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SplashScreen extends JPanel {

    public void launch(){

    }

    @Override
    public void paintComponent(Graphics g){
        //Disegno lo sfondo
        super.paintComponent(g);
        Image bgImage = null;       //Immagine da disegnare

        try{
            //Qui vado a leggere lo sfondo
            bgImage = ImageIO.read(getClass().getResourceAsStream("/SplashScreen/Background.png"));
        } catch(IOException e){ e.printStackTrace(); }

        //Disegno l'immagine come sfondo del panel
        g.drawImage(bgImage, 0, 0, null);
    }
}
