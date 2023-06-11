package ZombieApocalypse.View;

import ZombieApocalypse.Controller.MenuController;
import ZombieApocalypse.Model.MenuModel;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;

public class MenuView extends JPanel implements Runnable{
    //variabili utili per la leaderboard
    public static boolean complimentato = false;
    private Thread t;
    ArrayList<String> nickname = new ArrayList<String>();
    ArrayList<Integer> punti = new ArrayList<Integer>();
    JLabel bg, sfondo1, sfondo2, sfondo3, sfondo4, sfondo5, sfondo6;
    //Variabili per i menu
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    private int borderValueRight, borderValueLeft;
    private boolean cambio = false;
    private Font font;
    private JLabel titolo, sfondoAbout, developerLabel, gameVersionLabel, graphicsLabel, musicLabel, creditsLabel, soundLabel, controls, sfondoSettings, settingsLabel;
    //label per la rappresentazione dei comandi pt1
    private JLabel lblUp, lblDown, lblLeft, lblRight, lblQ, lblE, lblESC, lblBar, lblMouse1, lblMouse2;
    //label per la rappresentazione dei comandi pt2
    private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
    private MenuModel model;
    private MenuController controller;
    private JPanel panelMenu, imagePanel, aboutPanel, settingsPanel;
    private JButton btnPlay, btnSettings, btnAbout, btnEditor, btnExit, btnExitAbout, btnEditorMap;
    //componenti per le settings
    private JLabel music, sound, language, mancino, logout, skin, skinAttiva;
    private JSlider musica = new JSlider(-74, 6), suoni = new JSlider(-74, 6);
    private JButton muteMusic, muteSound, it, en, exitSettings, btnMancino, btnLogout, avantiSkin, dietroSkin;

    public MenuView(){
        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(loader.getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));

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
        //Controllo se è attiva la schermata 'Settings' la elimino
        if(settingsPanel != null && settingsPanel.isShowing())
            this.remove(settingsPanel);
        //Inizializzo i componenti
        initComponent();
        controller.addListener();
        this.add(panelMenu);
        repaint();
        revalidate();
    }

    private void initComponent() {
        muteSound = new JButton();
        muteMusic = new JButton();
        btnLogout = new JButton();
        en = new JButton();
        avantiSkin = new JButton();
        dietroSkin = new JButton();
        btnMancino = new JButton();
        it = new JButton();
        if(GameData.lang==GameData.Language.IT) {
            btnExitAbout = new JButton("Esci");
            exitSettings = new JButton("Esci");
        }
        else {
            btnExitAbout = new JButton("Exit");
            exitSettings = new JButton("Exit");
        }

        //Creo il pannello con tuti i pulsanti
        panelMenu = new JPanel();
        panelMenu.setOpaque(false);
        panelMenu.setMaximumSize(new Dimension(1280, 550));
        panelMenu.setLayout(new GridBagLayout());
        panelMenu.setBorder(new EmptyBorder(0, 330, 0, 0));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;

        if(GameData.lang== GameData.Language.IT)
            btnPlay = new JButton("Gioca");
        else
            btnPlay = new JButton("Play");
        btnPlay.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnPlay.setHorizontalTextPosition(JButton.CENTER);
        btnPlay.setVerticalTextPosition(JButton.CENTER);
        btnPlay.setFont(font);
        btnPlay.setBorderPainted(false);
        btnPlay.setFocusPainted(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setForeground(Color.WHITE);
        btnPlay.setFont(font.deriveFont(Font.PLAIN, 30));
        btnPlay.setMinimumSize(new Dimension(297, 70));
        btnPlay.setPreferredSize(new Dimension(297, 70));
        btnPlay.setMaximumSize(new Dimension(297, 70));
        c.gridx = 0;
        c.gridy = 0;
        panelMenu.add(btnPlay, c);
        c.insets = new Insets(20, 0, 0, 0);

        if(GameData.lang== GameData.Language.IT)
            btnSettings = new JButton("Impostazioni");
        else
            btnSettings = new JButton("Settings");
        btnSettings.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnSettings.setHorizontalTextPosition(JButton.CENTER);
        btnSettings.setVerticalTextPosition(JButton.CENTER);
        btnSettings.setFont(font);
        btnSettings.setBorderPainted(false);
        btnSettings.setFocusPainted(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.setForeground(Color.WHITE);
        btnSettings.setFont(font.deriveFont(Font.PLAIN, 30));
        btnSettings.setMinimumSize(new Dimension(297, 70));
        btnSettings.setPreferredSize(new Dimension(297, 70));
        btnSettings.setMaximumSize(new Dimension(297, 70));
        c.gridx = 0;
        c.gridy = 1;
        panelMenu.add(btnSettings, c);

        if(GameData.lang== GameData.Language.IT)
            btnEditor = new JButton("Crea");
        else
            btnEditor = new JButton("Editor");
        btnEditor.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnEditor.setHorizontalTextPosition(JButton.CENTER);
        btnEditor.setVerticalTextPosition(JButton.CENTER);
        btnEditor.setFont(font);
        btnEditor.setBorderPainted(false);
        btnEditor.setContentAreaFilled(false);
        btnEditor.setFocusPainted(false);
        btnEditor.setForeground(Color.WHITE);
        btnEditor.setFont(font.deriveFont(Font.PLAIN, 30));
        btnEditor.setMinimumSize(new Dimension(297, 70));
        btnEditor.setPreferredSize(new Dimension(297, 70));
        btnEditor.setMaximumSize(new Dimension(297, 70));
        c.gridx = 0;
        c.gridy = 2;
        panelMenu.add(btnEditor, c);

        if(GameData.lang== GameData.Language.IT)
            btnEditorMap = new JButton("Mappe Utente");
        else
            btnEditorMap = new JButton("User Maps");
        btnEditorMap.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnEditorMap.setHorizontalTextPosition(JButton.CENTER);
        btnEditorMap.setVerticalTextPosition(JButton.CENTER);
        btnEditorMap.setFont(font);
        btnEditorMap.setBorderPainted(false);
        btnEditorMap.setContentAreaFilled(false);
        btnEditorMap.setFocusPainted(false);
        btnEditorMap.setForeground(Color.WHITE);
        btnEditorMap.setFont(font.deriveFont(Font.PLAIN, 30));
        btnEditorMap.setMinimumSize(new Dimension(297, 70));
        btnEditorMap.setPreferredSize(new Dimension(297, 70));
        btnEditorMap.setMaximumSize(new Dimension(297, 70));
        c.gridx = 0; c.gridy = 3;
        panelMenu.add(btnEditorMap, c);

        if(GameData.lang== GameData.Language.IT)
            btnAbout = new JButton("Informazioni");
        else
            btnAbout = new JButton("About Game");
        btnAbout.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnAbout.setHorizontalTextPosition(JButton.CENTER);
        btnAbout.setVerticalTextPosition(JButton.CENTER);
        btnAbout.setFont(font);
        btnAbout.setContentAreaFilled(false);
        btnAbout.setBorderPainted(false);
        btnAbout.setFocusPainted(false);
        btnAbout.setForeground(Color.WHITE);
        btnAbout.setFont(font.deriveFont(Font.PLAIN, 30));
        btnAbout.setMinimumSize(new Dimension(297, 70));
        btnAbout.setPreferredSize(new Dimension(297, 70));
        btnAbout.setMaximumSize(new Dimension(297, 70));
        c.gridx = 0;
        c.gridy = 4;
        panelMenu.add(btnAbout, c);

        if(GameData.lang== GameData.Language.IT)
            btnExit = new JButton("Esci");
        else
            btnExit = new JButton("Exit");
        btnExit.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 297, 70, false));
        btnExit.setHorizontalTextPosition(JButton.CENTER);
        btnExit.setVerticalTextPosition(JButton.CENTER);
        btnExit.setFont(font);
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusPainted(false);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(font.deriveFont(Font.PLAIN, 30));
        btnExit.setMinimumSize(new Dimension(297, 70));
        btnExit.setPreferredSize(new Dimension(297, 70));
        btnExit.setMaximumSize(new Dimension(297, 70));
        c.gridx = 0;
        c.gridy = 5;
        panelMenu.add(btnExit, c);

        bg = new JLabel();
        bg.setIcon(loader.getImageIcon("/Leaderboard/bgLeaderBoard.png", 330, 400, false));
        bg.setBorder(new EmptyBorder(0, 20, 0, 0));
        bg.setLayout(new BoxLayout(bg, BoxLayout.Y_AXIS));
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 5;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;

        if(GameData.lang== GameData.Language.IT)
            sfondo1 = new JLabel("Classifica");
        else
            sfondo1 = new JLabel("Leaderboard");
        sfondo1.setFont(font.deriveFont(Font.PLAIN, 30));
        sfondo1.setForeground(Color.WHITE);
        sfondo1.setVerticalTextPosition(JLabel.CENTER);
        sfondo1.setHorizontalTextPosition(JLabel.CENTER);
        sfondo1.setIcon(loader.getImageIcon("/Leaderboard/trasparente.png", 270, 51, false));
        sfondo1.setBorder(new EmptyBorder(15, 15, 0, 0));
        bg.add(sfondo1);

        sfondo2 = new JLabel("");
        sfondo2.setForeground(Color.WHITE);
        sfondo2.setVerticalTextPosition(JLabel.CENTER);
        sfondo2.setHorizontalTextPosition(JLabel.CENTER);
        sfondo2.setIcon(loader.getImageIcon("/Login&Menu/txtGUI.png", 270, 51, false));
        sfondo2.setBorder(new EmptyBorder(5, 15, 0, 0));
        bg.add(sfondo2);

        sfondo3 = new JLabel("");
        sfondo3.setForeground(Color.WHITE);
        sfondo3.setVerticalTextPosition(JLabel.CENTER);
        sfondo3.setHorizontalTextPosition(JLabel.CENTER);
        sfondo3.setIcon(loader.getImageIcon("/Login&Menu/txtGUI.png", 270, 51, false));
        sfondo3.setBorder(new EmptyBorder(10, 15, 0, 0));
        bg.add(sfondo3);

        sfondo4 = new JLabel("");
        sfondo4.setForeground(Color.WHITE);
        sfondo4.setVerticalTextPosition(JLabel.CENTER);
        sfondo4.setHorizontalTextPosition(JLabel.CENTER);
        sfondo4.setIcon(loader.getImageIcon("/Login&Menu/txtGUI.png", 270, 51, false));
        sfondo4.setBorder(new EmptyBorder(10, 15, 0, 0));
        bg.add(sfondo4);

        sfondo5 = new JLabel("");
        sfondo5.setForeground(Color.WHITE);
        sfondo5.setVerticalTextPosition(JLabel.CENTER);
        sfondo5.setHorizontalTextPosition(JLabel.CENTER);
        sfondo5.setIcon(loader.getImageIcon("/Login&Menu/txtGUI.png", 270, 51, false));
        sfondo5.setBorder(new EmptyBorder(10, 15, 0, 0));
        bg.add(sfondo5);

        sfondo6 = new JLabel("");
        sfondo6.setForeground(Color.WHITE);
        sfondo6.setVerticalTextPosition(JLabel.CENTER);
        sfondo6.setHorizontalTextPosition(JLabel.CENTER);
        sfondo6.setIcon(loader.getImageIcon("/Login&Menu/txtGUI.png", 270, 51, false));
        sfondo6.setBorder(new EmptyBorder(30, 15, 0, 0));
        bg.add(sfondo6);

        panelMenu.add(bg, c);
        try { updateLeaderboard(); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public void setAbout() {
        initAbout();
        this.remove(panelMenu);
        this.add(aboutPanel);
        repaint();
        revalidate();
    }

    private void initAbout() {
        //creo il pannello about
        aboutPanel = new JPanel();
        aboutPanel.setOpaque(false);
        aboutPanel.setMaximumSize(new Dimension(747, 520));

        sfondoAbout = new JLabel();
        sfondoAbout.setIcon(loader.getImageIcon("/Login&Menu/aboutPanel.png", 747, 440, false));
        sfondoAbout.setLayout(new BoxLayout(sfondoAbout, BoxLayout.Y_AXIS));

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        if(GameData.lang== GameData.Language.IT)
            creditsLabel = new JLabel("CREDITI");
        else
            creditsLabel = new JLabel("CREDITS");
        creditsLabel.setFont(font.deriveFont(Font.PLAIN, 22));
        creditsLabel.setForeground(Color.WHITE);
        creditsLabel.setBorder(new EmptyBorder(20, ((sfondoAbout.getIcon().getIconWidth()/2)-((int)(font.getStringBounds(creditsLabel.getText(), frc).getWidth())/2))+25, 0, 0));
        sfondoAbout.add(creditsLabel);

        if(GameData.lang== GameData.Language.IT)
            developerLabel = new JLabel("GIOCO: gioco sviluppato da Pierfrancesco Napoli, Matteo Canino, Sebastian Witon.");
        else
            developerLabel = new JLabel("GAME: game developed by Pierfrancesco Napoli, Matteo Canino, Sebastian Witon.");
        developerLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        developerLabel.setForeground(Color.WHITE);
        developerLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(developerLabel);

        if(GameData.lang== GameData.Language.IT)
            gameVersionLabel = new JLabel("VERSIONE GIOCO: " + GameData.version);
        else
            gameVersionLabel = new JLabel("GAME VERSION: " + GameData.version);
        gameVersionLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        gameVersionLabel.setForeground(Color.WHITE);
        gameVersionLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(gameVersionLabel);

        if(GameData.lang== GameData.Language.IT)
            graphicsLabel = new JLabel("GRAFICA: grafica scaricata dal sito 'itch.io', disegnata da Ittai Manero.");
        else
            graphicsLabel = new JLabel("GRAPHICS: graphics taken from 'itch.io' and drawn by Ittai Manero.");
        graphicsLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        graphicsLabel.setForeground(Color.WHITE);
        graphicsLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(graphicsLabel);

        if(GameData.lang== GameData.Language.IT)
            musicLabel = new JLabel("MUSICA: musica scaricata dal sito 'itch.io', composta da SzajnaWorkshop.");
        else
            musicLabel = new JLabel("MUSIC: music taken from 'itch.io', is created by SzajnaWorkshop.");
        musicLabel.setFont(font.deriveFont(Font.PLAIN, 15));
        musicLabel.setForeground(Color.white);
        musicLabel.setBorder(new EmptyBorder(3, 25, 0, 0));
        sfondoAbout.add(musicLabel);

        if(GameData.lang== GameData.Language.IT)
            soundLabel = new JLabel("SUONI: suoni scaricati dal sito 'https://freesound.org'");
        else
            soundLabel = new JLabel("SOUND: sounds taken from 'https://freesound.org'");
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
        btnExitAbout.setContentAreaFilled(false);
        btnExitAbout.setForeground(Color.WHITE);
        btnExitAbout.setMinimumSize(new Dimension(197, 60));
        btnExitAbout.setPreferredSize(new Dimension(197, 60));
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
        layoutComands3.setLayout(new GridBagLayout());
        layoutComands3.setBorder(new EmptyBorder(10, 0, 0, 0));
        layoutComands3.add(btnExitAbout);

        setCommands(layoutComands, layoutComands2);

        sfondoAbout.add(layoutComands);
        sfondoAbout.add(layoutComands2);
        sfondoAbout.add(layoutComands3);
        aboutPanel.add(sfondoAbout);
    }

    private void setCommands(JLabel layoutComands, JLabel layoutComands2) {
        lblUp = new JLabel();
        if(!GameData.mancino)
            lblUp.setIcon(loader.getImageIcon("/TutorialButton/TastoW/TastoW.gif", 64, 60, false));
        else
            lblUp.setIcon(loader.getImageIcon("/TutorialButton/TastoI/TastoI.gif", 64, 60, false));

        lblUp.setBorder(new EmptyBorder(0, 15, 0, 0));
        layoutComands.add(lblUp);

        lblDown = new JLabel();
        if(!GameData.mancino)
            lblDown.setIcon(loader.getImageIcon("/TutorialButton/TastoS/TastoS.gif", 64, 60, false));
        else
            lblDown.setIcon(loader.getImageIcon("/TutorialButton/TastoK/TastoK.gif", 64, 60, false));
        lblDown.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblDown);

        lblLeft = new JLabel();
        if(!GameData.mancino)
            lblLeft.setIcon(loader.getImageIcon("/TutorialButton/TastoA/TastoA.gif", 64, 60, false));
        else
            lblLeft.setIcon(loader.getImageIcon("/TutorialButton/TastoJ/TastoJ.gif", 64, 60, false));
        lblLeft.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblLeft);

        lblRight = new JLabel();
        if(!GameData.mancino)
            lblRight.setIcon(loader.getImageIcon("/TutorialButton/TastoD/TastoD.gif", 64, 60, false));
        else
            lblRight.setIcon(loader.getImageIcon("/TutorialButton/TastoL/TastoL.gif", 64, 60, false));
        lblRight.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblRight);

        lblQ = new JLabel();
        if(!GameData.mancino)
            lblQ.setIcon(loader.getImageIcon("/TutorialButton/TastoQ/TastoQ.gif", 64, 60, false));
        else
            lblQ.setIcon(loader.getImageIcon("/TutorialButton/TastoU/TastoU.gif", 64, 60, false));
        lblQ.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblQ);

        lblE = new JLabel();
        if(!GameData.mancino)
            lblE.setIcon(loader.getImageIcon("/TutorialButton/TastoE/TastoE.gif", 64, 60, false));
        else
            lblE.setIcon(loader.getImageIcon("/TutorialButton/TastoO/TastoO.gif", 64, 60, false));
        lblE.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblE);

        lblESC = new JLabel();
        lblESC.setIcon(loader.getImageIcon("/TutorialButton/TastoESC/TastoESC.gif", 64, 60, false));
        lblESC.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblESC);

        lblBar = new JLabel();
        lblBar.setIcon(loader.getImageIcon("/TutorialButton/TastoBarra/Barra.gif", 100, 45, false));
        lblBar.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblBar);

        lblMouse1 = new JLabel();
        lblMouse1.setIcon(loader.getImageIcon("/TutorialButton/Mouse/MouseStop.png", 64, 60, false));
        lblMouse1.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblMouse1);

        lblMouse2 = new JLabel();
        if(!GameData.mancino)
            lblMouse2.setIcon(loader.getImageIcon("/TutorialButton/Mouse/Mouse.gif", 64, 60, false));
        else
            lblMouse2.setIcon(loader.getImageIcon("/TutorialButton/Mouse/MouseMancino.gif", 64, 60, false));
        lblMouse2.setBorder(new EmptyBorder(0, 5, 0, 0));
        layoutComands.add(lblMouse2);

        lbl1 = new JLabel();
        lbl1.setIcon(loader.getImageIcon("/Player/PlayerIndietro.gif", 42, 48, false));
        lbl1.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl1);

        lbl2 = new JLabel();
        lbl2.setIcon(loader.getImageIcon("/Player/PlayerAvanti.gif", 42, 48, false));
        lbl2.setBorder(new EmptyBorder(0, 30, 0, 0));
        layoutComands2.add(lbl2);

        lbl3 = new JLabel();
        lbl3.setIcon(loader.getImageIcon("/Player/PlayerSinistra.gif", 42, 48, false));
        lbl3.setBorder(new EmptyBorder(0, 30, 0, 0));
        layoutComands2.add(lbl3);

        lbl4 = new JLabel();
        lbl4.setIcon(loader.getImageIcon("/Player/PlayerDestra.gif", 42, 48, false));
        lbl4.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl4);

        if(GameData.lang == GameData.Language.IT)
            lbl5 = new JLabel("Usa");
        else
            lbl5 = new JLabel("Use");
        lbl5.setFont(font.deriveFont(Font.PLAIN, 25));
        lbl5.setHorizontalTextPosition(JLabel.CENTER);
        lbl5.setForeground(Color.WHITE);
        lbl5.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl5);

        if(GameData.lang == GameData.Language.IT)
            lbl6 = new JLabel("Usa");
        else
            lbl6 = new JLabel("Use");
        lbl6.setFont(font.deriveFont(Font.PLAIN, 25));
        lbl6.setHorizontalTextPosition(JLabel.CENTER);
        lbl6.setForeground(Color.WHITE);
        lbl6.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl6);

        lbl7 = new JLabel();
        lbl7.setIcon(loader.getImageIcon("/TutorialButton/pause.png", 48, 48, false));
        lbl7.setBorder(new EmptyBorder(0, 30, 0, 0));
        layoutComands2.add(lbl7);

        if(GameData.lang == GameData.Language.IT)
            lbl8 = new JLabel("Lascia");
        else
            lbl8 = new JLabel("Drop");
        lbl8.setFont(font.deriveFont(Font.PLAIN, 25));
        lbl8.setHorizontalTextPosition(JLabel.CENTER);
        lbl8.setForeground(Color.WHITE);
        lbl8.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl8);

        lbl9 = new JLabel();
        lbl9.setIcon(loader.getImageIcon("/TutorialButton/crosshair.png", 64, 65, false));
        lbl9.setBorder(new EmptyBorder(0, 25, 0, 0));
        layoutComands2.add(lbl9);

        lbl10 = new JLabel();
        lbl10.setIcon(loader.getImageIcon("/TutorialButton/shot.png", 48, 48, false));
        //lbl10.setBorder(new EmptyBorder(0, 13, 0, 0));
        layoutComands2.add(lbl10);
    }

    public void setSettings(){
        initSettings();
        this.remove(panelMenu);
        this.add(settingsPanel);
        repaint();
        revalidate();
    }

    private void initSettings() {
        //creo il pannello settings
        settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setMaximumSize(new Dimension(830, 550));

        sfondoSettings = new JLabel();
        sfondoSettings.setIcon(loader.getImageIcon("/Login&Menu/aboutPanel.png", 830, 550, false));
        sfondoSettings.setLayout(new BoxLayout(sfondoSettings, BoxLayout.Y_AXIS));

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        settingsLabel = new JLabel("SETTINGS");
        settingsLabel.setFont(font.deriveFont(Font.PLAIN, 30));
        settingsLabel.setForeground(Color.WHITE);
        settingsLabel.setBorder(new EmptyBorder(25, ((sfondoSettings.getIcon().getIconWidth()/2)-((int)(font.getStringBounds(settingsLabel.getText(), frc).getWidth())/2))+25, 0, 0));
        sfondoSettings.add(settingsLabel);

        muteMusic.setPreferredSize(new Dimension(32, 32));
        muteMusic.setMaximumSize(new Dimension(32, 32));
        muteMusic.setMinimumSize(new Dimension(32, 32));
        muteMusic.setBorderPainted(false);
        muteMusic.setFocusPainted(false);
        muteMusic.setContentAreaFilled(false);
        if(GameData.music) {
            muteMusic.setIcon(loader.getImageIcon("/SettingsImage/AudioOn.png", 32, 32, false));
            musica.setEnabled(true);
        }
        else {
            muteMusic.setIcon(loader.getImageIcon("/SettingsImage/AudioOff.png", 32, 32, false));
            musica.setEnabled(false);
        }

        JLabel gestioneMusica = new JLabel();
        gestioneMusica.setMaximumSize(new Dimension(800, 50));
        gestioneMusica.setLayout(new BoxLayout(gestioneMusica, BoxLayout.X_AXIS));
        music = new JLabel("Music Volume: ");
        music.setFont(font.deriveFont(Font.PLAIN, 20));
        music.setForeground(Color.WHITE);
        music.setBorder(new EmptyBorder(3, 25, 0, 0));
        music.setPreferredSize(new Dimension(200, 50));
        music.setMaximumSize(new Dimension(200, 50));
        music.setMinimumSize(new Dimension(200, 50));

        musica.setMinorTickSpacing(4);
        musica.setMajorTickSpacing(16);
        musica.setPreferredSize(new Dimension(500, 50));
        musica.setMaximumSize(new Dimension(500, 50));
        musica.setMinimumSize(new Dimension(500, 50));
        musica.setOpaque(false);
        musica.setPaintTicks(true);
        musica.setLabelTable(setTable());
        musica.setPaintLabels(true);
        musica.setValue(GameData.musicVolume);
        gestioneMusica.add(music);
        gestioneMusica.add(musica);
        gestioneMusica.add(Box.createRigidArea(new Dimension(30, 10)));
        gestioneMusica.add(muteMusic);

        muteSound.setPreferredSize(new Dimension(32, 32));
        muteSound.setMaximumSize(new Dimension(32, 32));
        muteSound.setMinimumSize(new Dimension(32, 32));
        muteSound.setBorderPainted(false);
        muteSound.setFocusPainted(false);
        muteSound.setContentAreaFilled(false);
        if(GameData.sound) {
            muteSound.setIcon(loader.getImageIcon("/SettingsImage/SoundOn.png", 32, 32, false));
            suoni.setEnabled(true);
        }
        else {
            muteSound.setIcon(loader.getImageIcon("/SettingsImage/SoundOff.png", 32, 32, false));
            suoni.setEnabled(false);
        }

        JLabel gestioneSuoni = new JLabel();
        gestioneSuoni.setMaximumSize(new Dimension(800, 50));
        gestioneSuoni.setLayout(new BoxLayout(gestioneSuoni, BoxLayout.X_AXIS));
        sound = new JLabel("Sound Volume: ");
        sound.setFont(font.deriveFont(Font.PLAIN, 20));
        sound.setForeground(Color.WHITE);
        sound.setBorder(new EmptyBorder(3, 25, 0, 0));
        sound.setPreferredSize(new Dimension(200, 50));
        sound.setMaximumSize(new Dimension(200, 50));
        sound.setMinimumSize(new Dimension(200, 50));

        suoni.setPreferredSize(new Dimension(500, 50));
        suoni.setMaximumSize(new Dimension(500, 50));
        suoni.setMinimumSize(new Dimension(500, 50));
        suoni.setOpaque(false);
        suoni.setMinorTickSpacing(4);
        suoni.setMajorTickSpacing(16);
        suoni.setPaintTicks(true);
        suoni.setLabelTable(setTable());
        suoni.setPaintLabels(true);
        suoni.setValue(GameData.soundVolume);
        gestioneSuoni.add(sound);
        gestioneSuoni.add(suoni);
        gestioneSuoni.add(Box.createRigidArea(new Dimension(30, 10)));
        gestioneSuoni.add(muteSound);
        sfondoSettings.add(gestioneMusica);
        sfondoSettings.add(gestioneSuoni);

        JLabel gestioneLingua = new JLabel();
        gestioneLingua.setMaximumSize(new Dimension(800, 50));
        gestioneLingua.setLayout(new BoxLayout(gestioneLingua, BoxLayout.X_AXIS));
        language = new JLabel("Language: ");
        language.setFont(font.deriveFont(Font.PLAIN, 20));
        language.setForeground(Color.WHITE);
        language.setBorder(new EmptyBorder(3, 25, 0, 0));
        language.setPreferredSize(new Dimension(150, 50));
        language.setMaximumSize(new Dimension(150, 50));
        language.setMinimumSize(new Dimension(150, 50));

        it.setPreferredSize(new Dimension(32, 24));
        it.setMaximumSize(new Dimension(32, 24));
        it.setMinimumSize(new Dimension(32, 24));
        it.setBorderPainted(false);
        it.setFocusPainted(false);
        it.setContentAreaFilled(false);
        it.setIcon(loader.getImageIcon("/SettingsImage/it.png", 32, 24, false));
        en.setPreferredSize(new Dimension(32, 24));
        en.setMaximumSize(new Dimension(32, 24));
        en.setMinimumSize(new Dimension(32, 24));
        en.setBorderPainted(false);
        en.setFocusPainted(false);
        en.setContentAreaFilled(false);
        en.setIcon(loader.getImageIcon("/SettingsImage/en.png", 32, 24, false));
        gestioneLingua.add(language);
        gestioneLingua.add(it);
        gestioneLingua.add(Box.createRigidArea(new Dimension(30, 10)));
        gestioneLingua.add(en);
        sfondoSettings.add(gestioneLingua);

        JLabel gestioneMancino = new JLabel();
        gestioneMancino.setMaximumSize(new Dimension(800, 50));
        gestioneMancino.setLayout(new BoxLayout(gestioneMancino, BoxLayout.X_AXIS));
        mancino = new JLabel("Left Handed: ");
        mancino.setFont(font.deriveFont(Font.PLAIN, 20));
        mancino.setForeground(Color.WHITE);
        mancino.setBorder(new EmptyBorder(3, 25, 0, 0));
        mancino.setPreferredSize(new Dimension(180, 50));
        mancino.setMaximumSize(new Dimension(180, 50));
        mancino.setMinimumSize(new Dimension(180, 50));
        btnMancino.setPreferredSize(new Dimension(48, 48));
        btnMancino.setMaximumSize(new Dimension(48, 48));
        btnMancino.setMinimumSize(new Dimension(48, 48));
        btnMancino.setBorderPainted(false);
        btnMancino.setFocusPainted(false);
        btnMancino.setContentAreaFilled(false);
        if(!GameData.mancino)
            btnMancino.setIcon(loader.getImageIcon("/SettingsImage/Slider1.png", 48, 48, false));
        else
            btnMancino.setIcon(loader.getImageIcon("/SettingsImage/Slider2.png", 48, 48, false));
        gestioneMancino.add(mancino);
        gestioneMancino.add(btnMancino);
        sfondoSettings.add(gestioneMancino);

        JLabel gestioneLogout = new JLabel();
        gestioneLogout.setMaximumSize(new Dimension(800, 50));
        gestioneLogout.setLayout(new BoxLayout(gestioneLogout, BoxLayout.X_AXIS));
        logout = new JLabel("Logout: ");
        logout.setFont(font.deriveFont(Font.PLAIN, 20));
        logout.setForeground(Color.WHITE);
        logout.setBorder(new EmptyBorder(3, 25, 0, 0));
        logout.setPreferredSize(new Dimension(180, 50));
        logout.setMaximumSize(new Dimension(180, 50));
        logout.setMinimumSize(new Dimension(180, 50));
        btnLogout.setPreferredSize(new Dimension(48, 48));
        btnLogout.setMaximumSize(new Dimension(48, 48));
        btnLogout.setMinimumSize(new Dimension(48, 48));
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setIcon(loader.getImageIcon("/SettingsImage/logout.png", 48, 48, false));
        gestioneLogout.add(logout);
        gestioneLogout.add(btnLogout);
        sfondoSettings.add(gestioneLogout);

        skin = new JLabel("Skin: ");
        skin.setFont(font.deriveFont(Font.PLAIN, 20));
        skin.setForeground(Color.WHITE);
        skin.setBorder(new EmptyBorder(3, 25, 0, 0));
        skin.setPreferredSize(new Dimension(180, 50));
        skin.setMaximumSize(new Dimension(180, 50));
        skin.setMinimumSize(new Dimension(180, 50));

        JLabel gestioneSkin = new JLabel();
        gestioneSkin.setMaximumSize(new Dimension(800, 90));
        gestioneSkin.setLayout(new BoxLayout(gestioneSkin, BoxLayout.X_AXIS));
        avantiSkin.setPreferredSize(new Dimension(50, 45));
        avantiSkin.setMaximumSize(new Dimension(50, 45));
        avantiSkin.setMinimumSize(new Dimension(50, 45));
        avantiSkin.setBorderPainted(false);
        avantiSkin.setFocusPainted(false);
        avantiSkin.setContentAreaFilled(false);
        avantiSkin.setIcon(loader.getImageIcon("/SettingsImage/avantiArrow.png", 50, 45, false));

        dietroSkin.setPreferredSize(new Dimension(50, 45));
        dietroSkin.setMaximumSize(new Dimension(50, 45));
        dietroSkin.setMinimumSize(new Dimension(50, 45));
        dietroSkin.setBorderPainted(false);
        dietroSkin.setFocusPainted(false);
        dietroSkin.setContentAreaFilled(false);
        dietroSkin.setIcon(loader.getImageIcon("/SettingsImage/indietroArrow.png", 50, 45, false));

        skinAttiva = new JLabel();
        skinAttiva.setIcon(loader.getImageIcon("/Player/Skin" + GameData.skinAttiva + "/PlayerAvanti0.png", 42, 48, false));

        gestioneSkin.add(skin);
        gestioneSkin.add(dietroSkin);
        gestioneSkin.add(Box.createRigidArea(new Dimension(50, 10)));
        gestioneSkin.add(skinAttiva);
        gestioneSkin.add(Box.createRigidArea(new Dimension(50, 10)));
        gestioneSkin.add(avantiSkin);
        sfondoSettings.add(gestioneSkin);

        exitSettings.setIcon(loader.getImageIcon("/Login&Menu/sendButton.png", 197, 60, false));
        exitSettings.setHorizontalTextPosition(JButton.CENTER);
        exitSettings.setVerticalTextPosition(JButton.CENTER);
        exitSettings.setFont(font);
        exitSettings.setBorderPainted(false);
        exitSettings.setFocusPainted(false);
        exitSettings.setContentAreaFilled(false);
        exitSettings.setForeground(Color.WHITE);
        exitSettings.setMinimumSize(new Dimension(197, 60));
        exitSettings.setPreferredSize(new Dimension(197, 60));
        exitSettings.setMaximumSize(new Dimension(197, 60));
        //label per settare il pulsante al centro
        JLabel layoutComands3 = new JLabel();
        layoutComands3.setMaximumSize(new Dimension(800, 70));
        layoutComands3.setBorder(new EmptyBorder(10, 0, 0 ,0));
        layoutComands3.setLayout(new GridBagLayout());
        layoutComands3.add(exitSettings);
        sfondoSettings.add(layoutComands3);

        //traduce l'interfaccia
        model.translateSettings();

        settingsPanel.add(sfondoSettings);

        try { updateLeaderboard(); } catch (IOException e) { throw new RuntimeException(e); }
    }

    private Hashtable<Integer, JLabel> setTable() {
        //con questa sezione creo un label per la jslide personalizzata
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(-74, new JLabel("0"));
        labels.put(-58, new JLabel("20"));
        labels.put(-42, new JLabel("40"));
        labels.put(-26, new JLabel("60"));
        labels.put(-10, new JLabel("80"));
        labels.put(6, new JLabel("100"));
        labels.get(-74).setForeground(Color.white);
        labels.get(-58).setForeground(Color.white);
        labels.get(-42).setForeground(Color.white);
        labels.get(-26).setForeground(Color.white);
        labels.get(-10).setForeground(Color.white);
        labels.get(6).setForeground(Color.white);
        labels.get(-74).setFont(font.deriveFont(Font.PLAIN, 15));
        labels.get(-58).setFont(font.deriveFont(Font.PLAIN, 15));
        labels.get(-42).setFont(font.deriveFont(Font.PLAIN, 15));
        labels.get(-26).setFont(font.deriveFont(Font.PLAIN, 15));
        labels.get(-10).setFont(font.deriveFont(Font.PLAIN, 15));
        labels.get(6).setFont(font.deriveFont(Font.PLAIN, 15));
        return labels;
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

    public JSlider getMusica(){
        return musica;
    }

    public JSlider getSuoni(){
        return suoni;
    }

    public JButton getMuteMusic(){
        return muteMusic;
    }

    public JButton getMuteSound(){
        return muteSound;
    }

    public JButton getIt(){
        return it;
    }

    public JButton getEn(){
        return en;
    }

    public JLabel getMusic() {
        return music;
    }

    public JLabel getSound() {
        return sound;
    }

    public JLabel getLanguage() {
        return language;
    }

    public JLabel getSettingsLabel(){
        return settingsLabel;
    }

    public JLabel getMancino(){
        return mancino;
    }

    public JButton getBtnMancino(){
        return btnMancino;
    }

    public JButton getExitSettings(){
        return exitSettings;
    }

    public JButton getBtnLogout(){
        return btnLogout;
    }

    public JButton getAvantiSkin(){
        return avantiSkin;
    }

    public JButton getDietroSkin(){
        return dietroSkin;
    }

    public JLabel getSkinAttiva(){
        return skinAttiva;
    }

    public JButton getBtnEditorMap() {
        return btnEditorMap;
    }

    public void updatePosition(){
        //Questa funzione serve per l'animazione del titolo

        if(panelMenu.isShowing()){
            //se è verificata questa condizione vuol dire che l'animazione deve spostarsi verso sinistra
            if (borderValueRight < 100 && !cambio) {
                //Quindi incremento il bordo destro per farlo spostare verso sinistra
                borderValueRight += 3;
                //se il bordo di sinistra è maggiore di 0 devo decrementarlo per evitare di farlo uscire dallo schermo
                if (borderValueLeft > 0)
                    borderValueLeft -= 3;
                //Qua setto i nuovi bordi
                titolo.setBorder(new EmptyBorder(20, borderValueLeft, 0, borderValueRight));
            } else if (borderValueRight >= 100)
                //quando il bordo destro arriva sopra il valore 100 inverto il senso dell'animazione
                cambio = true;

            //se è verificata questa condizione vuol dire che l'animazione deve spostarsi verso destra
            if (borderValueLeft < 100 && cambio) {
                //incremento il bordo sinistro per farlo spostare verso destra
                borderValueLeft += 3;
                //se il bordo di destra è maggiore di 0 devo decrementarlo per evitare di farlo uscire dallo schermo
                if (borderValueRight > 0)
                    borderValueRight -= 3;
                //setto i nuovi bordi
                titolo.setBorder(new EmptyBorder(20, borderValueLeft, 0, borderValueRight));
            } else if (borderValueLeft >= 100)
                //quando il bordo sinistro arriva sopra il valore 100 inverto il senso dell'animazione
                cambio = false;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        //Con questo paintComponent vado a impostare un immagine casuale come sfondo
        super.paintComponent(g);
        Image bgImage = null;       //Immagine da disegnare

        try{
            //Qui vado a leggere una sola immagine casuale tra le quattro disponibili
            bgImage = ImageIO.read(getClass().getResourceAsStream("/LoginBackground/War" + GameData.setBg + ".png"));
        } catch(IOException e){ e.printStackTrace(); }

        //Disegno l'immagine come sfondo del panel
        g.drawImage(bgImage, 0, 0, null);
    }

    public void updateLeaderboard() throws IOException {
        String ris = "";
        boolean supp = true;

        //chiamata script per leggere la classifica
        URL script = new URL("https://progettouid.altervista.org/ZombieApocalypse/getLeaderboard.php?nickname=" + GameData.nick);
        URLConnection conn = script.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;

        //smistamento classifica
        while ((inputLine = in.readLine()) != null) {
            ris = inputLine;
            if(supp){
                supp = false;
                nickname.add(ris);
            }
            else{
                supp = true;
                punti.add(Integer.parseInt(ris));
            }
        }

        if(nickname.size() == 1){
            setPrimo();
        }
        else if(nickname.size() == 2){
            setPrimo();
            setSecondo();
        }
        else if(nickname.size() == 3){
            setPrimo();
            setSecondo();
            setTerzo();
        } else if(nickname.size() == 4) {
            setPrimo();
            setSecondo();
            setTerzo();
            setQuarto();
        } else{
            setNull();
        }

        sfondo6.setText("" + GameData.nick + "(" + GameData.punti + " pt)");
        if(GameData.nick.length() < 14)
            sfondo6.setFont(font.deriveFont(Font.PLAIN, 15));
        else
            sfondo6.setFont(font.deriveFont(Font.PLAIN, 11));

        //svuoto gli array
        nickname.clear();
        punti.clear();

        in.close();
    }

    public void setPrimo(){
        sfondo2.setText("1) " + nickname.get(0) + " (" + punti.get(0) + " pt)");
        sfondo2.setForeground(new Color(255, 215, 0));
        if(nickname.get(0).length() < 14)
            sfondo2.setFont(font.deriveFont(Font.PLAIN, 15));
        else
            sfondo2.setFont(font.deriveFont(Font.PLAIN, 11));

        if(nickname.get(0).equals(GameData.nick) && !complimentato){
            complimentato = true;
            t = new Thread(this);
            t.start();
        }
        else if(!nickname.get(0).equals(GameData.nick)){
            complimentato = false;
            if(GameData.skinAttiva == 5)
                GameData.skinAttiva = 4;
        }
    }
    private void setSecondo() {
        sfondo3.setText("2) " + nickname.get(1) + " (" + punti.get(1) + " pt)");
        sfondo3.setForeground(new Color(192, 192, 192));
        if(nickname.get(1).length() < 14)
            sfondo3.setFont(font.deriveFont(Font.PLAIN, 15));
        else
            sfondo3.setFont(font.deriveFont(Font.PLAIN, 11));
    }
    public void setTerzo(){
        sfondo4.setText("3) " + nickname.get(2) + " (" + punti.get(2) + " pt)");
        sfondo4.setForeground(new Color(205, 127, 50));
        if(nickname.get(2).length() < 14)
            sfondo4.setFont(font.deriveFont(Font.PLAIN, 15));
        else
            sfondo4.setFont(font.deriveFont(Font.PLAIN, 11));
    }
    public void setQuarto(){
        sfondo5.setText("4) " + nickname.get(3) + " (" + punti.get(3) + " pt)");
        if(nickname.get(3).length() < 14)
            sfondo5.setFont(font.deriveFont(Font.PLAIN, 15));
        else
            sfondo5.setFont(font.deriveFont(Font.PLAIN, 11));
    }
    public void setNull(){
        sfondo2.setText("-");
        sfondo2.setForeground(Color.WHITE);
        sfondo2.setFont(font.deriveFont(Font.PLAIN, 15));

        sfondo3.setText("-");
        sfondo3.setForeground(Color.WHITE);
        sfondo3.setFont(font.deriveFont(Font.PLAIN, 15));

        sfondo4.setText("-");
        sfondo4.setForeground(Color.WHITE);
        sfondo4.setFont(font.deriveFont(Font.PLAIN, 15));
    }

    private static void showDialog() {

        Font font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        UIManager.put("OptionPane.background",new Color(92,75,35));
        UIManager.put("Panel.background",new Color(18,17,15));
        UIManager.put("OptionPane.minimumSize",new Dimension(500,200));
        UIManager.put("OptionPane.border", new EmptyBorder(10, 10, 10,10));
        UIManager.put("OptionPane.font", ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN));
        UIManager.put("OptionPane.foreground", Color.WHITE);

        //creo la label e gli setto il font personalizzato
        JLabel label = new JLabel();
        label.setFont(font.deriveFont(Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(100, 100));
        label.setPreferredSize(new Dimension(100, 100));
        label.setBorder(new EmptyBorder(10, 10, 0, 0));

        JButton btnMenu;
        JButton btnGo;
        if(GameData.lang.equals(GameData.Language.IT)) {
            btnMenu = new JButton("Conferma");
            label.setText("<html>Complimenti, ti trovi al primo posto della classifica!<br>Ti aspetta una sorpresa, controlla tra le skin! Continua a giocare per mantenere il primo posto!</html>");
        }
        else {
            btnMenu = new JButton("Confirm");
            label.setText("<html>Congratulations, you are in first place in the ranking!<br>A surprise awaits you, check between the skins! Continue playing to keep the first place!</html>");
        }

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu});
        JDialog dialog = new JDialog();

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
            }
        });

        dialog.getContentPane().add(pane);
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setSize(new Dimension(515, 220));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void run() {
        showDialog();
    }
}
