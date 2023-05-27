package ZombieApocalypse.View;

import ZombieApocalypse.Controller.LoginController;
import ZombieApocalypse.Controller.SplashScreenController;
import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.SplashScreenModel;
import ZombieApocalypse.ResourcesLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SplashScreenView extends JPanel {
    private SplashScreenModel model;
    private SplashScreenController controller;
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    public JProgressBar bar;  //Progress Bar
    private JLabel titolo;       //Label per visualizzare il titolo
    private JPanel panel1, panel2;

    public SplashScreenView() {
        //setto il cursore personalizzato
        this.setCursor(loader.getCursor("/GameGeneral/crosshair.png", this));

        //Creo un model e un controller
        model = new SplashScreenModel(this);
        controller = new SplashScreenController(model, this);
        //Assegno i listener ai componenti
        controller.addListener();

        initComponent();

        //Setto il layout del pannello principale
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Inserisco ora i componenti nel pannello principale
        this.add(Box.createGlue());
        this.add(panel1);
        this.add(panel2);
        this.add(Box.createGlue());
    }

    private void initComponent() {
        //Creo la label titolo
        titolo = new JLabel();
        titolo.setForeground(Color.WHITE);
        //Creo la icon da inserire nella label titolo
        titolo.setIcon(loader.getImageIcon("/GameTitle/title.png", 650, 138, false));
        //Setto le dimensioni della label titolo e il bordo
        titolo.setMinimumSize(new Dimension(750, 138));
        titolo.setMaximumSize(new Dimension(750, 138));
        titolo.setBorder(new EmptyBorder(20, 0, 0, 0));

        //creo il pannello dove metto la label titolo
        panel1 = new JPanel();
        panel1.add(titolo);
        panel1.setOpaque(false);
        panel1.setMaximumSize(new Dimension(800, 200));

        //creo la splash screen
        bar = new JProgressBar();
        bar.setMaximum(100);
        bar.setMinimum(0);

        bar.setStringPainted(true);

        //creo il pannello per la progress bar
        panel2 = new JPanel();
        panel2.setOpaque(false);
        panel2.add(bar);
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
