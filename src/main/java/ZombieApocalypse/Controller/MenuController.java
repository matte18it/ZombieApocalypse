package ZombieApocalypse.Controller;

import ZombieApocalypse.Loop.MenuLoop;
import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.MenuModel;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
                try {
                    view.setAbout();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
    }
}
