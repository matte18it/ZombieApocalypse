package ZombieApocalypse.View;

import ZombieApocalypse.Controller.MenuController;
import ZombieApocalypse.Model.MenuModel;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel implements Runnable{
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    private int borderValueRight, borderValueLeft;
    private boolean cambio = false;
    private Font font;
    private JLabel titolo, sfondoAbout, developerLabel, gameVersionLabel, graphicsLabel, musicLabel, creditsLabel, soundLabel, controls;
    //label per la rappresentazione dei comandi pt1
    private JLabel lblUp, lblDown, lblLeft, lblRight, lblQ, lblE, lblESC, lblBar, lblMouse1, lblMouse2;
    //label per la rappresentazione dei comandi pt2
    private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
    private MenuModel model;
    private MenuController controller;
    private JPanel panelMenu, imagePanel, aboutPanel;
    private JButton btnPlay, btnSettings, btnAbout, btnEditor, btnExit, btnExitAbout;
    public Thread t;

    public MenuView(){

        //setto il cursore personalizzato
        this.setCursor(loader.getCursor("/GameGeneral/crosshair.png", this));

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

        gameVersionLabel = new JLabel("GAME VERSION: " + GameData.version);
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
        btnExitAbout.setMinimumSize(new Dimension(197, 60));
        btnExitAbout.setMaximumSize(new Dimension(197, 60));

        controls = new JLabel("CONTROLS");
        controls.setFont(font.deriveFont(Font.PLAIN, 22));
        controls.setForeground(Color.WHITE);
        controls.setBorder(new EmptyBorder(10, ((sfondoAbout.getIcon().getIconWidth()/2)-((int)(font.getStringBounds(controls.getText(), frc).getWidth())/2))+25, 0, 0));
        sfondoAbout.add(controls);

        //Creo una label contenitore che mi permette di inserire i componenti in ordine
        JLabel layoutComands = new JLabel();
        layoutComands.setMaximumSize(new Dimension(747, 60));
        layoutComands.setLayout(new BoxLayout(layoutComands, BoxLayout.X_AXIS));

        JLabel layoutComands2 = new JLabel();
        layoutComands2.setMaximumSize(new Dimension(747, 60));
        layoutComands2.setLayout(new BoxLayout(layoutComands2, BoxLayout.X_AXIS));

        JLabel layoutComands3 = new JLabel();
        layoutComands3.setMaximumSize(new Dimension(747, 75));
        layoutComands3.setLayout(new BorderLayout());
        layoutComands3.setBorder(new EmptyBorder(10, 0, 0, 0));
        layoutComands3.add(btnExitAbout, BorderLayout.CENTER);

        setCommands(layoutComands, layoutComands2);

        sfondoAbout.add(layoutComands);
        sfondoAbout.add(layoutComands2);
        sfondoAbout.add(layoutComands3);
        aboutPanel.add(sfondoAbout);
    }

    private void setCommands(JLabel layoutComands, JLabel layoutComands2) {
        lblUp = new JLabel();
        lblUp.setIcon(loader.getImageIcon("/TutorialButton/TastoW/Tasto1.png", 64, 60, false));
        lblUp.setBorder(new EmptyBorder(0, 15, 0, 0));
        layoutComands.add(lblUp);

        lblDown = new JLabel();
        lblDown.setIcon(loader.getImageIcon("/TutorialButton/TastoS/Tasto1.png", 64, 60, false));
        lblDown.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblDown);

        lblLeft = new JLabel();
        lblLeft.setIcon(loader.getImageIcon("/TutorialButton/TastoA/Tasto1.png", 64, 60, false));
        lblLeft.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblLeft);

        lblRight = new JLabel();
        lblRight.setIcon(loader.getImageIcon("/TutorialButton/TastoD/Tasto1.png", 64, 60, false));
        lblRight.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblRight);

        lblQ = new JLabel();
        lblQ.setIcon(loader.getImageIcon("/TutorialButton/TastoQ/Tasto1.png", 64, 60, false));
        lblQ.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblQ);

        lblE = new JLabel();
        lblE.setIcon(loader.getImageIcon("/TutorialButton/TastoE/Tasto1.png", 64, 60, false));
        lblE.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblE);

        lblESC = new JLabel();
        lblESC.setIcon(loader.getImageIcon("/TutorialButton/TastoESC/Tasto1.png", 64, 60, false));
        lblESC.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblESC);

        lblBar = new JLabel();
        lblBar.setIcon(loader.getImageIcon("/TutorialButton/TastoBarra/Tasto1.png", 100, 45, false));
        lblBar.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblBar);

        lblMouse1 = new JLabel();
        lblMouse1.setIcon(loader.getImageIcon("/TutorialButton/Mouse/MouseStop.png", 64, 60, false));
        lblMouse1.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblMouse1);

        lblMouse2 = new JLabel();
        lblMouse2.setIcon(loader.getImageIcon("/TutorialButton/Mouse/Mouse1.png", 64, 60, false));
        lblMouse2.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblMouse2);

        lbl1 = new JLabel();
        lbl1.setIcon(loader.getImageIcon("/Player/PlayerIndietro0.png", 42, 48, false));
        lbl1.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl1);

        lbl2 = new JLabel();
        lbl2.setIcon(loader.getImageIcon("/Player/PlayerAvanti0.png", 42, 48, false));
        lbl2.setBorder(new EmptyBorder(0, 30, 0, 0));
        layoutComands2.add(lbl2);

        lbl3 = new JLabel();
        lbl3.setIcon(loader.getImageIcon("/Player/PlayerSinistra0.png", 42, 48, false));
        lbl3.setBorder(new EmptyBorder(0, 30, 0, 0));
        layoutComands2.add(lbl3);

        lbl4 = new JLabel();
        lbl4.setIcon(loader.getImageIcon("/Player/PlayerDestra0.png", 42, 48, false));
        lbl4.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl4);

        lbl5 = new JLabel();
        lbl5.setIcon(loader.getImageIcon("/TutorialButton/pistola.png", 40, 25, false));
        lbl5.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl5);

        lbl6 = new JLabel();
        lbl6.setIcon(loader.getImageIcon("/TutorialButton/fucile.png", 42, 25, false));
        lbl6.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl6);

        lbl7 = new JLabel();
        lbl7.setIcon(loader.getImageIcon("/TutorialButton/pause.png", 48, 48, false));
        lbl7.setBorder(new EmptyBorder(0, 30, 0, 0));
        layoutComands2.add(lbl7);

        lbl8 = new JLabel();
        lbl8.setIcon(loader.getImageIcon("/TutorialButton/pistola.png", 40, 25, false));
        lbl8.setBorder(new EmptyBorder(0, 40, 0, 0));
        layoutComands2.add(lbl8);

        lbl9 = new JLabel();
        lbl9.setIcon(loader.getImageIcon("/TutorialButton/crosshair.png", 64, 65, false));
        lbl9.setBorder(new EmptyBorder(0, 35, 0, 0));
        layoutComands2.add(lbl9);

        lbl10 = new JLabel();
        lbl10.setIcon(loader.getImageIcon("/ArmieOggetti/AnimazioneColtello0.png", 44, 40, false));
        lbl10.setBorder(new EmptyBorder(0, 13, 0, 0));
        layoutComands2.add(lbl10);

        t = new Thread(this);
        t.start();
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

    public void updateAboutButton() {
        System.out.println("PROVA");
        if(lblUp != null){
            if(true)
                lblUp.setIcon(loader.getImageIcon("/TutorialButton/TastoW/Tasto2.png", 64, 60, false));
            else
                lblUp.setIcon(loader.getImageIcon("/TutorialButton/TastoW/Tasto1.png", 64, 60, false));

            repaint();
        }
    }

    @Override
    public void run() {
        int nButton = 1;
        while(!Thread.currentThread().isInterrupted()) {
            lblUp.setIcon(loader.getImageIcon("/TutorialButton/TastoW/Tasto" + nButton + ".png", 64, 60, false));
            lblDown.setIcon(loader.getImageIcon("/TutorialButton/TastoS/Tasto" + nButton + ".png", 64, 60, false));
            lblLeft.setIcon(loader.getImageIcon("/TutorialButton/TastoA/Tasto" + nButton + ".png", 64, 60, false));
            lblRight.setIcon(loader.getImageIcon("/TutorialButton/TastoD/Tasto" + nButton + ".png", 64, 60, false));
            lblQ.setIcon(loader.getImageIcon("/TutorialButton/TastoQ/Tasto" + nButton + ".png", 64, 60, false));
            lblE.setIcon(loader.getImageIcon("/TutorialButton/TastoE/Tasto" + nButton + ".png", 64, 60, false));
            lblESC.setIcon(loader.getImageIcon("/TutorialButton/TastoESC/Tasto" + nButton + ".png", 64, 60, false));
            lblBar.setIcon(loader.getImageIcon("/TutorialButton/tastoBarra/Tasto" + nButton + ".png", 100, 45, false));
            lblMouse2.setIcon(loader.getImageIcon("/TutorialButton/Mouse/Mouse" + nButton + ".png", 64, 60, false));

            if(nButton == 1) nButton = 2; else nButton = 1;
            try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}
