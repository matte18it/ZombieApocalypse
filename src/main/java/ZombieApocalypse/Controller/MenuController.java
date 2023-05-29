package ZombieApocalypse.Controller;

import ZombieApocalypse.Loop.MenuLoop;
import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.MenuModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Optional;

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
    }
}
