package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.UserMapModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.View.Editor.EditorView;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.UserMapView;

import javax.sound.sampled.Line;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class UserMapController {
    private UserMapModel model;
    private UserMapView view;
    public static String nomeFile = "";

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
                model.changeDifficulty();
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
                model.changeDifficulty();
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
                model.changeDifficulty();
            }
        });
        view.getNameMap().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                view.getNameMap().setForeground(Color.white);
            }
        });
        view.getLoadButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                File f = new File("EditorMap/" + view.getNameMap().getText() + ".txt");
                if(f.exists() && !f.isDirectory()){
                    EditorView.init = 1;
                    nomeFile = view.getNameMap().getText();
                    GameFrame.editorLaunch();
                }
                else{
                    view.getNameMap().setForeground(Color.red);
                }
            }
        });
    }
}
