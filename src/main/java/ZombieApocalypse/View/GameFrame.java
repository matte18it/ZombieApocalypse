package ZombieApocalypse.View;
import ZombieApocalypse.Loop.*;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Model.SplashScreenModel;
import ZombieApocalypse.View.Editor.EditorBarView;
import ZombieApocalypse.View.Editor.EditorView;
import ZombieApocalypse.View.MenuBar.MenuBarView;
import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel {
    private static PlayWav playMenuMusic = PlayWav.getInstance();
    public static JFrame frameGame = new JFrame("Login");
    public static GameLoop gameLoopObject;
    private static LeaderboardLoop leaderboardLoop;
    public static LoginView panel;
    public static SplashScreenView splashScreen;
    private static MenuLoop menuLoop;
    public static LoginLoop loop;
    public static MenuView menu;
    public static MenuBarView menuBarView;
    public static GraphicPanel graphicPanel;
    public static TimeLoop timeLoop;
    public static EditorView editor;
    public static EditorBarView editorBar;
    public static UserMapView userView;

    public static void loadingLaunch(){
        dimension();
        splashScreen = new SplashScreenView();

        frameGame.add(splashScreen);
        frameGame.setUndecorated(true);
        frameGame.setVisible(true);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SplashScreenModel.checkFile();
        splashScreen.bar.setIcon(ResourcesLoader.getInstance().getImageIcon("/SplashScreen/bar.png", 276, 7, false));
        SplashScreenModel.loadInterface();
    }

    public static void loginLaunch(){
        frameGame.remove(splashScreen);

        //se il pannello è nullo lo creo
        if(panel == null)
            panel = new LoginView();

        //Controllo che la traccia non sia già attiva
        if(!playMenuMusic.isPlay() && GameData.music){
            //Faccio partire la traccia
            playMenuMusic.play("/Music/MenuMusic.wav");
            playMenuMusic.setVolume(GameData.musicVolume);
        }

        //Prendo l'ora corrente
        GameData.setBg = ResourcesLoader.getInstance().getHours();

        //Setto le dimensioni
        dimension();

        //Inserisco il panel appena creato all'interno del mio frame
        frameGame.add(panel);

        //Creo un oggetto loginLoop per usare un thread che gestisca l'animazione del titolo
        loop = new LoginLoop(panel);
        loop.start();
        frameGame.repaint();
    }

    public static void menuLaunch(){
        if(splashScreen != null && splashScreen.isShowing())
            frameGame.remove(splashScreen);

        //se il pannello è nullo lo creo
        if(menu == null)
            menu = new MenuView();

        if(graphicPanel != null && graphicPanel.isShowing()){
            frameGame.remove(graphicPanel);
            frameGame.remove(menuBarView);
            timeLoop.stop();
            gameLoopObject.stop();
            frameGame.setLayout(null);
        }

        if(userView != null && userView.isShowing()){
            frameGame.remove(userView);
        }

        if(editor != null && editor.isShowing()){
            frameGame.remove(editor);
            frameGame.remove(editorBar);
            frameGame.setLayout(null);
        }

        //Prendo l'ora corrente
        GameData.setBg = ResourcesLoader.getInstance().getHours();

        dimension();

        //Controllo che la traccia non sia già attiva
        if(!playMenuMusic.isPlay() && GameData.music){
            //Faccio partire la traccia
            playMenuMusic.play("/Music/MenuMusic.wav");
            playMenuMusic.setVolume(GameData.musicVolume);
        }
        else if(!GameData.music && playMenuMusic.isPlay())
            playMenuMusic.stop();
        frameGame.setTitle("Menu");

        frameGame.add(menu);
        frameGame.repaint();
        frameGame.revalidate();
        menuLoop = new MenuLoop(menu);
        menuLoop.start();
        leaderboardLoop = new LeaderboardLoop(menu);
        leaderboardLoop.start();
    }

    public static void gameLaunch(){
        //controllo che la musica sia attiva
        if(playMenuMusic.isPlay() && GameData.music){
            //se è attiva la stoppo...
            playMenuMusic.stop();
            //...e attivo la nuova
            playMenuMusic.play("/Music/GameMusic.wav");
            playMenuMusic.setVolume(GameData.musicVolume);
        }

        leaderboardLoop.stop();
        menuLoop.stop();
        frameGame.remove(menu);
        frameGame.setTitle("Game");

        menuBarView = new MenuBarView();
        Game.getInstance().setMenuBar(menuBarView);
        graphicPanel = new GraphicPanel();
        frameGame.setLayout(new BoxLayout(frameGame.getContentPane(), BoxLayout.PAGE_AXIS));

        frameGame.add(graphicPanel);
        frameGame.add(menuBarView);
        frameGame.repaint();

        graphicPanel.setFocusable(true);
        graphicPanel.requestFocus();
        PlayerController playerController=new PlayerController(graphicPanel);
        graphicPanel.addMouseMotionListener(playerController);
        graphicPanel.addMouseListener(playerController);
        graphicPanel.addKeyListener(playerController);
        gameLoopObject = new GameLoop(playerController);
        menuBarView.setBar();

        timeLoop=new TimeLoop();

        gameLoopObject.start();
        timeLoop.start();
    }

    public static void editorLaunch(){
        if(menu != null && menu.isShowing()){
            frameGame.remove(menu);
            menuLoop.stop();
            leaderboardLoop.stop();
        }

        frameGame.setTitle("Editor");

        frameGame.setLayout(new BoxLayout(frameGame.getContentPane(), BoxLayout.PAGE_AXIS));

        editor = new EditorView();
        editorBar = new EditorBarView(editor);

        frameGame.add(editor);
        frameGame.add(editorBar);
        frameGame.revalidate();
        frameGame.repaint();
    }

    public static void editorMapLaunch() {
        frameGame.remove(menu);
        menuLoop.stop();
        leaderboardLoop.stop();

        frameGame.setTitle("User Map");

        userView = new UserMapView();
        frameGame.add(userView);

        frameGame.revalidate();
        frameGame.repaint();
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
