package ZombieApocalypse.View;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.Loop.GameLoop;
import ZombieApocalypse.Loop.MenuLoop;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Settings;
import ZombieApocalypse.Loop.LoginLoop;
import ZombieApocalypse.Loop.TimeLoop;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayMusic;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel {
    private static PlayMusic playMenuMusic = new PlayMusic("/Music/MenuMusic.wav");
    public static JFrame frameGame = new JFrame("Login");
    private static GameLoop gameLoopObject;
    public static LoginView panel;
    private static MenuLoop menuLoop;
    public static LoginLoop loop;
    public static MenuView menu;
    public static TimeLoop timeLoop;

    public static void loginLaunch(){
        //Controllo che la traccia non sia già attiva
        if(!playMenuMusic.isMusic() && GameData.music)
            //Faccio partire la traccia
            playMenuMusic.playMusic();

        //Prendo l'ora corrente
        GameData.setBg = ResourcesLoader.getInstance().getHours();

        //Setto le dimensioni
        dimension();

        //Creo un loginPaint, parte interna della mia cornice
        panel = new LoginView();
        //Inserisco il panel appena creato all'interno del mio frame
        frameGame.add(panel);

        //Creo un oggetto loginLoop per usare un thread che gestisca l'animazione del titolo
        loop = new LoginLoop(panel);
        loop.start();

        frameGame.setUndecorated(true);
        frameGame.setVisible(true);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void menuLaunch(){
        //Prendo l'ora corrente
        GameData.setBg = ResourcesLoader.getInstance().getHours();

        dimension();

        //Controllo che la traccia non sia già attiva
        if(!playMenuMusic.isMusic() && GameData.music)
            //Faccio partire la traccia
            playMenuMusic.playMusic();
        frameGame.setTitle("Menu");
        menu = new MenuView();

        frameGame.add(menu);
        frameGame.repaint();
        menuLoop = new MenuLoop(menu);
        menuLoop.start();

        if(!frameGame.isUndecorated())
            frameGame.setUndecorated(true);

        frameGame.setVisible(true);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void gameLaunch(){
        //controllo che la musica sia attiva
        if(playMenuMusic.isMusic() && GameData.music){
            //se è attiva la stoppo...
            playMenuMusic.stopMusic();
            //...e attivo la nuova
            playMenuMusic.changeMusic("/Music/GameMusic.wav");
            playMenuMusic.playMusic();
        }

        menuLoop.stop();
        frameGame.remove(menu);
        frameGame.setTitle("Game");

        MenuBarView menuBarView=new MenuBarView();
        GraphicPanel graphicPanel=new GraphicPanel();
        frameGame.setLayout(new BoxLayout(frameGame.getContentPane(), BoxLayout.PAGE_AXIS));

        frameGame.add(graphicPanel);
        frameGame.add(menuBarView);
        frameGame.repaint();

        graphicPanel.setFocusable(true);
        graphicPanel.requestFocus();
        PlayerController playerController=new PlayerController(graphicPanel);
        graphicPanel.addMouseMotionListener(playerController);
        graphicPanel.addKeyListener(playerController);
        gameLoopObject=new GameLoop(playerController);
        menuBarView.setBar();
        timeLoop=new TimeLoop();

        gameLoopObject.start();
        timeLoop.start();
    }

    public static void close() {
        frameGame.dispose();
        gameLoopObject.stop();
        System.exit(0);
    }

    private static void dimension() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        frameGame.setSize(Settings.WINDOW_SIZEX, Settings.WINDOW_SIZEY+Settings.MENU_BAR_HEIGHT);
        // Mettiamo la finestra al centro dello schermo
        int x = (screenDimension.width - frameGame.getWidth())/2;
        int y = (screenDimension.height - frameGame.getHeight())/2;
        frameGame.setLocation(x, y);
    }
}
