package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.UserMapModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.UserMapView;

import javax.sound.sampled.Line;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMapController {
    private UserMapModel model;
    private UserMapView view;

    public UserMapController(UserMapModel model, UserMapView view){
        //Creo il model e la view
        this.model = model;
        this.view = view;
    }

    public void addListener(){
        view.getBtnGiu().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound && view.getBtnGiu().isEnabled())
                    PlayWav.getInstance().playButtonSound();
                if(UserMapView.indice < UserMapView.maxIndice && UserMapView.maxIndice > 19){
                    UserMapView.indice ++;
                    view.initFile();
                }
            }
        });
        view.getBtnSu().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound && view.getBtnSu().isEnabled())
                    PlayWav.getInstance().playButtonSound();
                if(UserMapView.indice > 0 && UserMapView.maxIndice > 19){
                    UserMapView.indice --;
                    view.initFile();
                }
            }
        });
        view.getNameMap().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(view.getNameMap().getText().length() > 11)
                    e.consume();
            }
        });
        view.getExitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                GameFrame.menuLaunch();
            }
        });
        view.getBtnEasy().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                UserMapView.difficulty = 0;
                view.getBtnMedium().setBorder(null);
                view.getBtnHard().setBorder(null);
                view.getBtnEasy().setBorder(new LineBorder(Color.red));
                changeDifficulty();
            }
        });
        view.getBtnMedium().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                UserMapView.difficulty = 1;
                view.getBtnMedium().setBorder(new LineBorder(Color.red));
                view.getBtnHard().setBorder(null);
                view.getBtnEasy().setBorder(null);
                changeDifficulty();
            }
        });
        view.getBtnHard().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                UserMapView.difficulty = 2;
                view.getBtnMedium().setBorder(null);
                view.getBtnHard().setBorder(new LineBorder(Color.red));
                view.getBtnEasy().setBorder(null);
                changeDifficulty();
            }
        });
    }

    private void changeDifficulty() {
        if(GameData.lang.equals(GameData.Language.EN)){
            if(UserMapView.difficulty == 0)
                view.getLblDescrizione().setText("<html>- Right difficulty for those who are new to the game.<br>" +
                        "- Zombies: random number of zombies between 1 and 15.<br>" +
                        "- Medikit: heals 3 lives at a time.<br>" +
                        "- Grenade: double damage.<br>" +
                        "- Hits: double damage.</html>");
            else if(UserMapView.difficulty == 1)
                view.getLblDescrizione().setText("<html>- Right difficulty for those who want a more complex.<br>" +
                        "- Zombies: random number of zombies between 15 and 30.<br>" +
                        "- Medikit: heals 2 lives at a time.<br>" +
                        "- Grenade: normal damage.<br>" +
                        "- Hits: normal damage.</htmL>");
            else if(UserMapView.difficulty == 2)
                view.getLblDescrizione().setText("<html>- Right difficulty for those who want a complex challenge.<br>" +
                        "- Zombies: random number of zombies between 30 and 45.<br>" +
                        "- Medikit: heals 1 life at a time.<br>" +
                        "- Grenade: damage halved.<br>" +
                        "- Hits: damage halved.</html>");
        }
        else{
            if(UserMapView.difficulty == 0)
                view.getLblDescrizione().setText("<html>- Difficoltà giusta per chi è agli inizi col gioco.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 1 e 15.<br>" +
                        "- Medikit: cura 3 vite alla volta.<br>" +
                        "- Granata: danni raddoppiati.<br>" +
                        "- Colpi: danni raddoppiati.</html>");
            else if(UserMapView.difficulty == 1)
                view.getLblDescrizione().setText("<html>- Difficoltà giusta per chi vuole una sfida più complessa.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 15 e 30.<br>" +
                        "- Medikit: cura 2 vite alla volta.<br>" +
                        "- Granata: danni normali.<br>" +
                        "- Colpi: danni normali.</html>");
            else if(UserMapView.difficulty == 2)
                view.getLblDescrizione().setText("<html>- Difficoltà giusta per chi vuole una sfida complessa.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 30 e 45.<br>" +
                        "- Medikit: cura 1 vita alla volta.<br>" +
                        "- Granata: danni dimezzati.<br>" +
                        "- Colpi: danni dimezzati.</html>");
        }
    }
}
