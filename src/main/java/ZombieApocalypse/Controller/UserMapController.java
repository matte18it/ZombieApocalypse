package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.UserMapModel;
import ZombieApocalypse.Model.World;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.Editor.EditorBarView;
import ZombieApocalypse.View.Editor.EditorView;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.UserMapView;

import javax.sound.sampled.Line;
import javax.swing.*;
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
                Settings.diff = Settings.Difficulty.EASY;
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
                Settings.diff = Settings.Difficulty.MEDIUM;
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
                Settings.diff = Settings.Difficulty.HARD;
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
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
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
        view.getDeleteButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                File f = new File("EditorMap/" + view.getNameMap().getText() + ".txt");
                if(f.exists() && !f.isDirectory())
                    showDialog();
                else
                    view.getNameMap().setForeground(Color.red);
            }
        });
        view.getPlayButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                File f = new File("EditorMap/" + view.getNameMap().getText() + ".txt");
                if(f.exists() && !f.isDirectory()){
                    nomeFile = view.getNameMap().getText();
                    Settings.loadEditorMap(nomeFile);
                    Game.getInstance().reloadWorld();
                    GameFrame.gameLaunch();

                }
                else{
                    view.getNameMap().setForeground(Color.red);
                }
            }
        });
    }

    private void showDialog() {

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
            btnMenu = new JButton("Elimina");
            btnGo = new JButton("Annulla");
            label.setText("<html>Se confermi il mondo sarà eliminato per sempre.<br>Sei sicuro di voler continuare?</html>");
        }
        else {
            btnMenu = new JButton("Delete");
            btnGo = new JButton("Cancel");
            label.setText("<html>If you confirm the world will be eliminated forever.<br>Are you sure you want to continue?</html>");
        }

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        btnGo.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnGo.setHorizontalTextPosition(JButton.CENTER);
        btnGo.setVerticalTextPosition(JButton.CENTER);
        btnGo.setBorderPainted(false);
        btnGo.setFocusPainted(false);
        btnGo.setContentAreaFilled(false);
        btnGo.setForeground(Color.WHITE);
        btnGo.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu, btnGo});
        JDialog dialog = new JDialog();
        btnGo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
            }
        });
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
                File file = new File("EditorMap/" + view.getNameMap().getText() + ".txt");
                file.delete();
                view.initFile();
                view.getNameMap().setText("");
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
}
