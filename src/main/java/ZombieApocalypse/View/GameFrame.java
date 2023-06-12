package ZombieApocalypse.View;
import ZombieApocalypse.Controller.UserMapController;
import ZombieApocalypse.Loop.*;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Model.PlayerCharacter;
import ZombieApocalypse.Model.SplashScreenModel;
import ZombieApocalypse.Model.World;
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
import java.net.URL;

public class GameFrame extends JPanel {
    private static PlayWav playMenuMusic = PlayWav.getInstance();   //variabile per la musica
    public static JFrame frameGame = new JFrame("Splash Screen");       //frame principale del gioco
    public static GameLoop gameLoopObject;          //loop di gioco
    private static LeaderboardLoop leaderboardLoop; //loop che gestisce l'aggiornamento della classifica
    public static LoginView panel;
    public static SplashScreenView splashScreen;    //oggetto splashscreen
    private static MenuLoop menuLoop;               //loop per far muovere il titolo nel menu
    public static LoginLoop loop;                   //loop per far muovere il titolo nel login
    public static MenuView menu;                    //interfaccia del menu
    public static MenuBarView menuBarView;          //barra inferiore del graphics panel
    public static GraphicPanel graphicPanel;        //pannello di gioco
    public static TimeLoop timeLoop;                //loop che scandisce il tempo di gioco
    public static EditorView editor;                //interfaccia dell'editor
    public static EditorBarView editorBar;          //barra inferiore dell'editor
    public static UserMapView userView;             //interfaccia delle mappe utente

    public static void loadingLaunch(){
        //setto il logo
        frameGame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameFrame.class.getClassLoader().getResource("LogoGame/LogoGame.png")));

        //setto le dimensioni
        dimension();

        //creo la splash screen
        splashScreen = new SplashScreenView();

        //aggiungo al frame tutti i componenti della splash screen
        frameGame.add(splashScreen);
        frameGame.setUndecorated(true);
        frameGame.setVisible(true);
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //faccio un check del file 'player.txt' per effettuare il login automatico e avvio la splash screen
        if(SplashScreenModel.checkFile())
            launch();
    }

    public static void launch() {
        splashScreen.bar.setIcon(ResourcesLoader.getInstance().getImageIcon("/SplashScreen/bar.png", 276, 7, false));
        SplashScreenModel.loadInterface();
    }

    public static void loginLaunch(){
        //qui ci arrivo se il file 'player.txt' non è presente e non posso effettuare il login automatico
        frameGame.remove(splashScreen);
        frameGame.setTitle("Login");

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

        //Inserisco il panel appena creato all'interno del mio frame
        frameGame.add(panel);

        //Creo un oggetto loginLoop per usare un thread che gestisca l'animazione del titolo
        loop = new LoginLoop(panel);
        loop.start();
        frameGame.repaint();
        frameGame.revalidate();
    }

    public static void menuLaunch(){
        //reset variabili game
        Settings.campainMapIndex = 1;
        Settings.diff = Settings.Difficulty.EASY;

        if(splashScreen != null && splashScreen.isShowing())
            frameGame.remove(splashScreen);

        //se il pannello è nullo lo creo
        if(menu == null)
            menu = new MenuView();

        //se il menu di gioco è visibile lo elimino
        if(graphicPanel != null && graphicPanel.isShowing()){
            frameGame.remove(graphicPanel);
            frameGame.remove(menuBarView);
            timeLoop.stop();
            gameLoopObject.stop();
            frameGame.setLayout(new BorderLayout());
        }

        //se il menu delle user map è visibile lo elimino
        if(userView != null && userView.isShowing()){
            frameGame.remove(userView);
        }

        //se il menu dell'editor è visibile lo elimino
        if(editor != null && editor.isShowing()){
            frameGame.remove(editor);
            frameGame.remove(editorBar);
            frameGame.setLayout(new BorderLayout());
        }

        //Prendo l'ora corrente
        GameData.setBg = ResourcesLoader.getInstance().getHours();

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
        //se il menu utente è attivo lo elimino
        if(userView != null && userView.isShowing())
            frameGame.remove(userView);
        //se il menu di gioco è attivo lo elimino, mi è utile nel cambio livello
        if(graphicPanel != null && graphicPanel.isShowing()){
            frameGame.remove(graphicPanel);
            frameGame.remove(menuBarView);
            timeLoop.stop();
            gameLoopObject.stop();
        }
        //se il menu è attivo lo elimino
        if(menu != null && menu.isShowing()){
            leaderboardLoop.stop();
            menuLoop.stop();
            frameGame.remove(menu);
        }

        //da qui in poi setto il menu del gioco
        frameGame.setTitle("Game");

        menuBarView = new MenuBarView();
        Game.getInstance().setMenuBar(menuBarView);
        graphicPanel = new GraphicPanel();
        frameGame.setLayout(new BoxLayout(frameGame.getContentPane(), BoxLayout.PAGE_AXIS));

        frameGame.add(graphicPanel);
        frameGame.add(menuBarView);
        frameGame.repaint();
        frameGame.revalidate();

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
        //se il menu è attivo lo stoppo
        if(menu != null && menu.isShowing()){
            frameGame.remove(menu);
            menuLoop.stop();
            leaderboardLoop.stop();
        }
        //se il menu mappe utente è attivo lo stoppo
        if(userView != null && userView.isShowing()){
            frameGame.remove(userView);
        }

        //da qui in poi setto il menu dell'editor
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
        //rimuovo il menu stoppando tutti i loop
        frameGame.remove(menu);
        menuLoop.stop();
        leaderboardLoop.stop();

        //da qui in poi setto il menu mappe utente
        frameGame.setTitle("User Maps");

        userView = new UserMapView();
        frameGame.add(userView);

        frameGame.repaint();
        frameGame.revalidate();
    }

    public static void close() {
        //chiudo il programma
        frameGame.dispose();
        gameLoopObject.stop();
        System.exit(0);
    }
    public static void closeGame() {
        gameLoopObject.stop();
    }

    private static void dimension() {
        //Con questo setto la dimensione e la posizione della finestra di gioco
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        frameGame.setSize(Settings.WINDOW_SIZEX, Settings.WINDOW_SIZEY+Settings.MENU_BAR_HEIGHT);
        // Mettiamo la finestra al centro dello schermo
        int x = (screenDimension.width - frameGame.getWidth())/2;
        int y = (screenDimension.height - frameGame.getHeight())/2;
        frameGame.setLocation(x, y);
    }
}
