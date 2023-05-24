package ZombieApocalypse.View;

import ZombieApocalypse.Controller.MenuController;
import ZombieApocalypse.Model.MenuModel;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class MenuView extends JPanel {
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    private int borderValueRight, borderValueLeft;
    private boolean cambio = false;
    private Font font;
    private JLabel titolo, sfondoAbout, developerLabel, gameVersionLabel, graphicsLabel, musicLabel, creditsLabel, soundLabel, controls;
    private MenuModel model;
    private MenuController controller;
    private JPanel panelMenu, imagePanel, aboutPanel;
    private JButton btnPlay, btnSettings, btnAbout, btnEditor, btnExit, btnExitAbout;

    public MenuView(){
        //Carico il font personalizzato
        font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 30, Font.PLAIN);

        //Creo un model e un controller
        model = new MenuModel(this);
        controller = new MenuController(model, this);

        setTitolo();

        //Setto il layout del pannello principale
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Inserisco ora i componenti nel pannello principale
        this.add(imagePanel);
        setMenu();
    }

    private void setTitolo() {
        //Creo la label titolo
        titolo = new JLabel();
        //Creo la icon da inserire nella label titolo
        titolo.setIcon(loader.getImageIcon("/GameTitle/title.png", 650, 138, false));
        //Setto le dimensioni della label titolo e il bordo
        titolo.setMinimumSize(new Dimension(750, 138));
        titolo.setMaximumSize(new Dimension(750, 138));
        titolo.setBorder(new EmptyBorder(20, 0, 0, 0));
        //creo il pannello dove metto la label titolo
        imagePanel = new JPanel();
        imagePanel.add(titolo);
        imagePanel.setOpaque(false);
        imagePanel.setMaximumSize(new Dimension(800, 200));
        //Settando il valore di borderValueLeft a 100 dico all'animazione del titolo di iniziare andando verso sinistra
        borderValueLeft = 100;
        borderValueRight = titolo.getBorder().getBorderInsets(titolo).right;
    }

    public void setMenu() {
        //Controllo se è attiva la schermata 'About' la elimino
        if(aboutPanel != null && aboutPanel.isShowing())
            this.remove(aboutPanel);

        //Inizializzo i componenti
        initComponent();
        controller.addListener();
        this.add(panelMenu);
        this.repaint();
    }

    private void initComponent() {
        btnExitAbout = new JButton("Exit");

        //Creo il pannello con tuti i pulsanti
        panelMenu = new JPanel();
        panelMenu.setOpaque(false);
        panelMenu.setMaximumSize(new Dimension(1280, 550));
        panelMenu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;

        btnPlay = new JButton("Play", loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnPlay.setHorizontalTextPosition(JButton.CENTER);
        btnPlay.setVerticalTextPosition(JButton.CENTER);
        btnPlay.setFont(font);
        btnPlay.setBorderPainted(false);
        btnPlay.setFocusPainted(false);
        btnPlay.setForeground(Color.WHITE);
        btnPlay.setFont(font.deriveFont(Font.PLAIN, 30));
        btnPlay.setMinimumSize(new Dimension(197, 60));
        btnPlay.setMaximumSize(new Dimension(197, 60));
        c.gridx = 0;
        c.gridy = 0;
        panelMenu.add(btnPlay, c);
        c.insets = new Insets(20, 0, 0, 0);

        btnSettings = new JButton("Settings", loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnSettings.setHorizontalTextPosition(JButton.CENTER);
        btnSettings.setVerticalTextPosition(JButton.CENTER);
        btnSettings.setFont(font);
        btnSettings.setBorderPainted(false);
        btnSettings.setFocusPainted(false);
        btnSettings.setForeground(Color.WHITE);
        btnSettings.setFont(font.deriveFont(Font.PLAIN, 30));
        btnSettings.setMinimumSize(new Dimension(197, 60));
        btnSettings.setMaximumSize(new Dimension(197, 60));
        c.gridx = 0;
        c.gridy = 1;
        panelMenu.add(btnSettings, c);

        btnEditor = new JButton("Editor", loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnEditor.setHorizontalTextPosition(JButton.CENTER);
        btnEditor.setVerticalTextPosition(JButton.CENTER);
        btnEditor.setFont(font);
        btnEditor.setBorderPainted(false);
        btnEditor.setFocusPainted(false);
        btnEditor.setForeground(Color.WHITE);
        btnEditor.setFont(font.deriveFont(Font.PLAIN, 30));
        btnEditor.setMinimumSize(new Dimension(197, 60));
        btnEditor.setMaximumSize(new Dimension(197, 60));
        c.gridx = 0;
        c.gridy = 2;
        panelMenu.add(btnEditor, c);

        btnAbout = new JButton("About", loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnAbout.setHorizontalTextPosition(JButton.CENTER);
        btnAbout.setVerticalTextPosition(JButton.CENTER);
        btnAbout.setFont(font);
        btnAbout.setBorderPainted(false);
        btnAbout.setFocusPainted(false);
        btnAbout.setForeground(Color.WHITE);
        btnAbout.setFont(font.deriveFont(Font.PLAIN, 30));
        btnAbout.setMinimumSize(new Dimension(197, 60));
        btnAbout.setMaximumSize(new Dimension(197, 60));
        c.gridx = 0;
        c.gridy = 3;
        panelMenu.add(btnAbout, c);

        btnExit = new JButton("Exit", loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnExit.setHorizontalTextPosition(JButton.CENTER);
        btnExit.setVerticalTextPosition(JButton.CENTER);
        btnExit.setFont(font);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(font.deriveFont(Font.PLAIN, 30));
        btnExit.setMinimumSize(new Dimension(197, 60));
        btnExit.setMaximumSize(new Dimension(197, 60));
        c.gridx = 0;
        c.gridy = 4;
        panelMenu.add(btnExit, c);
    }

    public void setAbout() throws IOException {
        initAbout();
        this.remove(panelMenu);
        this.add(aboutPanel);
        this.repaint();
    }

    private void initAbout() throws IOException {
        //creo il pannello about
        aboutPanel = new JPanel();
        aboutPanel.setOpaque(false);
        aboutPanel.setMaximumSize(new Dimension(747, 520));

        sfondoAbout = new JLabel();
        sfondoAbout.setIcon(loader.getImageIcon("/Login&Menu/aboutPanel.png", 747, 440, false));
        sfondoAbout.setLayout(new BoxLayout(sfondoAbout, BoxLayout.Y_AXIS));

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        creditsLabel = new JLabel("CREDITS");
        creditsLabel.setFont(font.deriveFont(Font.PLAIN, 22));
        creditsLabel.setForeground(Color.WHITE);
        creditsLabel.setBorder(new EmptyBorder(20, ((sfondoAbout.getIcon().getIconWidth()/2)-((int)(font.getStringBounds(creditsLabel.getText(), frc).getWidth())/2))+25, 0, 0));
        sfondoAbout.add(creditsLabel);

        developerLabel = new JLabel("GAME: game developed by Pierfrancesco Napoli, Matteo Canino, Sebastian Witon.");
        developerLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        developerLabel.setForeground(Color.WHITE);
        developerLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(developerLabel);

        gameVersionLabel = new JLabel("VERSION: " + GameData.version + ".");
        gameVersionLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        gameVersionLabel.setForeground(Color.WHITE);
        gameVersionLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(gameVersionLabel);

        graphicsLabel = new JLabel("GRAPHICS: graphics taken from itch.io and drawn by Ittai Manero.");
        graphicsLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        graphicsLabel.setForeground(Color.WHITE);
        graphicsLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(graphicsLabel);

        musicLabel = new JLabel("MUSIC: music taken from itch.io, is created by SzajnaWorkshop.");
        musicLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        musicLabel.setForeground(Color.white);
        musicLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(musicLabel);

        soundLabel = new JLabel("SOUND: sounds taken from https://freesound.org");
        soundLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        soundLabel.setForeground(Color.white);
        soundLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(soundLabel);

        btnExitAbout.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 197, 60, false));
        btnExitAbout.setHorizontalTextPosition(JButton.CENTER);
        btnExitAbout.setVerticalTextPosition(JButton.CENTER);
        btnExitAbout.setFont(font);
        btnExitAbout.setBorderPainted(false);
        btnExitAbout.setFocusPainted(false);
        btnExitAbout.setForeground(Color.WHITE);
        btnExitAbout.setFont(font.deriveFont(Font.PLAIN, 30));
        btnExitAbout.setMinimumSize(new Dimension(197, 60));
        btnExitAbout.setMaximumSize(new Dimension(197, 60));

        controls = new JLabel("CONTROLS");
        controls.setFont(font.deriveFont(Font.PLAIN, 22));
        controls.setForeground(Color.WHITE);
        controls.setBorder(new EmptyBorder(20, ((sfondoAbout.getIcon().getIconWidth()/2)-((int)(font.getStringBounds(controls.getText(), frc).getWidth())/2))+25, 0, 0));
        sfondoAbout.add(controls);

        aboutPanel.add(sfondoAbout);
        aboutPanel.add(btnExitAbout);
    }

    public JButton getBtnExitAbout(){
        return btnExitAbout;
    }

    public JButton getBtnPlay(){
        return btnPlay;
    }

    public JButton getBtnSettings() {
        return btnSettings;
    }

    public JButton getBtnAbout() {
        return btnAbout;
    }

    public JButton getBtnEditor() {
        return btnEditor;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public void updatePosition(){
        //Questa funzione serve per l'animazione del titolo

        //se è verificata questa condizione vuol dire che l'animazione deve spostarsi verso sinistra
        if(borderValueRight < 100 && !cambio){
            //Quindi incremento il bordo destro per farlo spostare verso sinistra
            borderValueRight += 3;
            //se il bordo di sinistra è maggiore di 0 devo decrementarlo per evitare di farlo uscire dallo schermo
            if(borderValueLeft > 0)
                borderValueLeft -= 3;
            //Qua setto i nuovi bordi
            titolo.setBorder(new EmptyBorder(20, borderValueLeft, 0, borderValueRight));
        }
        else if(borderValueRight >= 100)
            //quando il bordo destro arriva sopra il valore 100 inverto il senso dell'animazione
            cambio = true;

        //se è verificata questa condizione vuol dire che l'animazione deve spostarsi verso destra
        if(borderValueLeft < 100 && cambio){
            //incremento il bordo sinistro per farlo spostare verso destra
            borderValueLeft += 3;
            //se il bordo di destra è maggiore di 0 devo decrementarlo per evitare di farlo uscire dallo schermo
            if(borderValueRight > 0)
                borderValueRight -= 3;
            //setto i nuovi bordi
            titolo.setBorder(new EmptyBorder(20, borderValueLeft, 0, borderValueRight));
        }
        else if(borderValueLeft >= 100)
            //quando il bordo sinistro arriva sopra il valore 100 inverto il senso dell'animazione
            cambio = false;
    }

    @Override
    public void paintComponent(Graphics g){
        //Con questo paintComponent vado a impostare un immagine casuale come sfondo al login
        super.paintComponent(g);
        Image bgImage = null;       //Immagine da disegnare

        try{
            //Qui vado a leggere una sola immagine casuale tra le quattro disponibili
            bgImage = ImageIO.read(getClass().getResourceAsStream("/LoginBackground/War" + GameData.setBg + ".png"));
        } catch(IOException e){ e.printStackTrace(); }

        //Disegno l'immagine come sfondo del panel
        g.drawImage(bgImage, 0, 0, null);
    }
}
