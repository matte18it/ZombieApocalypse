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
import java.io.File;

public class MenuController {
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
}
