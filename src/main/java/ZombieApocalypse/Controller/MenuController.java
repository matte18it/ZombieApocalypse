package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.MenuModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.MenuView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuController {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private MenuModel model;
    private MenuView view;
    public MenuController(MenuModel model, MenuView view){
        //Creo il model e la view
        this.model = model;
        this.view = view;
    }

    public void addListener(){
        //Evento per avviare il gioco
        view.getBtnPlay().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                GameFrame.gameLaunch();
            }
        });

        //Evento per impostazioni
        view.getBtnSettings().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.setSettings();
            }
        });

        //Evento per gestione editor
        view.getBtnEditor().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound){
                    PlayWav.getInstance().loadSound("/Audio/ButtonSound.wav");
                    PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
                    PlayWav.getInstance().playSound();
                }
            }
        });

        //Evento per crediti
        view.getBtnAbout().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.setAbout();
            }
        });

        //Evento chiusura game
        view.getBtnExit().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        view.getBtnExitAbout().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.setMenu();
            }
        });

        view.getMusica().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GameData.musicVolume = view.getMusica().getValue();
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().setVolume(GameData.musicVolume);
            }
        });

        view.getSuoni().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GameData.soundVolume = view.getSuoni().getValue();
            }
        });

        view.getMuteMusic().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.music){
                    GameData.music = false;
                    view.getMuteMusic().setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/AudioOff.png", 32, 32, false));
                    if(PlayWav.getInstance().isPlay())
                        PlayWav.getInstance().stop();
                    view.getMusica().setEnabled(false);
                }
                else{
                    GameData.music = true;
                    view.getMuteMusic().setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/AudioOn.png", 32, 32, false));
                    view.getMusica().setEnabled(true);
                    PlayWav.getInstance().play("/Music/MenuMusic.wav");
                    PlayWav.getInstance().setVolume(GameData.musicVolume);
                }
            }
        });

        view.getMuteSound().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound){
                    GameData.sound = false;
                    view.getMuteSound().setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/SoundOff.png", 32, 32, false));
                    view.getSuoni().setEnabled(false);
                }
                else{
                    GameData.sound = true;
                    view.getMuteSound().setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/SoundOn.png", 32, 32, false));
                    view.getSuoni().setEnabled(true);
                }
            }
        });

        view.getIt().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                GameData.lang = GameData.Language.IT;
                model.translateSettings();
            }
        });

        view.getEn().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                GameData.lang = GameData.Language.EN;
                model.translateSettings();
            }
        });

        view.getExitSettings().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                view.setMenu();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try { saveData(); } catch (IOException ex) { throw new RuntimeException(ex); }
                    }
                });
            }
        });

        view.getBtnMancino().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!GameData.mancino){
                    GameData.mancino = true;
                    view.getBtnMancino().setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/Slider2.png", 48, 48, false));
                }
                else{
                    GameData.mancino = false;
                    view.getBtnMancino().setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/Slider1.png", 48, 48, false));
                }
            }
        });

        view.getBtnLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                model.showDialog();
            }
        });

        view.getAvantiSkin().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.skinAttiva < 4)
                    GameData.skinAttiva += 1;
                view.getSkinAttiva().setIcon(ResourcesLoader.getInstance().getImageIcon("/Player/Skin" + GameData.skinAttiva + "/PlayerAvanti0.png", 42, 48, false));
            }
        });

        view.getDietroSkin().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.skinAttiva > 1)
                    GameData.skinAttiva -= 1;
                view.getSkinAttiva().setIcon(ResourcesLoader.getInstance().getImageIcon("/Player/Skin" + GameData.skinAttiva + "/PlayerAvanti0.png", 42, 48, false));
            }
        });

        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //setto il cursore personalizzato
                view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
            }
        });
    }

    private void saveData() throws IOException {
        int val1, val2, val3, val4;
        //se i punti della partita sono maggiori del suo record, aggiorno
        if(GameData.punti > GameData.recordPunti)
            GameData.recordPunti = GameData.punti;

        //gestisco i dati
        if(GameData.music) val1 = 1; else val1 = 0;
        if(GameData.sound) val2 = 1; else val2 = 0;
        if(GameData.mancino) val3 = 1; else val3 = 0;
        if(GameData.lang == GameData.Language.EN) val4 = 0; else val4 = 1;

        //salvo i dati
        String path = "https://progettouid.altervista.org/ZombieApocalypse/saveData.php?nickname=" + GameData.nick + "&volumeMusica=" + GameData.musicVolume + "&volumeSuoni=" + GameData.soundVolume + "&musicaAttiva=" + val1 + "&suoniAttivi=" + val2 + "&mancino=" + val3 + "&lingua=" + val4 + "&skinAttiva=" + GameData.skinAttiva + "&punti=" + GameData.recordPunti;
        URL sript = new URL(path);
        URLConnection conn = sript.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;

        if ((inputLine = in.readLine()) != null)
            val1 = Integer.parseInt(inputLine);
    }
}
