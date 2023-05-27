package ZombieApocalypse.View;

import ZombieApocalypse.Model.SplashScreenModel;
import ZombieApocalypse.Utility.ResourcesLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class SplashScreenView extends JPanel {
    private SplashScreenModel model;
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    public JLabel bar, bgBar;       //Progress Bar
    private JLabel titolo;          //Label per visualizzare il titolo
    private JPanel panel1, panel2;

    public SplashScreenView() {
        //setto il cursore personalizzato
        this.setCursor(loader.getCursor("/GameGeneral/crosshair.png", this));

        //Creo un model e un controller
        model = new SplashScreenModel(this);
        //Assegno i listener ai componenti

        initComponent();

        //Setto il layout del pannello principale
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        //Inserisco ora i componenti nel pannello principale
        this.add(panel1, c);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        this.add(panel2, c);
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

        bgBar = new JLabel();
        bgBar.setIcon(loader.getImageIcon("/SplashScreen/bgBar.png", 559, 15, false));
        bgBar.setBorder(new EmptyBorder(15, 0, 0, 0));
        bgBar.setLayout(new BoxLayout(bgBar, BoxLayout.X_AXIS));

        //creo la bar
        bar = new JLabel();
        bar.setIcon(loader.getImageIcon("/SplashScreen/bar.png", 1, 7, false));
        bar.setBorder(new EmptyBorder(0, 5, 0, 0));

        bgBar.add(bar);

        //creo il pannello per la progress bar
        panel2 = new JPanel();
        panel2.setOpaque(false);
        panel2.setPreferredSize(new Dimension(559, 200));
        panel2.setMaximumSize(new Dimension(559, 200));
        panel2.add(bgBar);
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
