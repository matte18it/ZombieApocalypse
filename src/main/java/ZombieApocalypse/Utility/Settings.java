package ZombieApocalypse.Utility;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.View.GameFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Settings {
    public static String mapName;
    public static boolean isEditor=true;
    public static int campainMapIndex=1;  //Mappa campagna corrente
    public static int campainMaps=5;  //numero mappe massime
    public static boolean nextCampaignMap(){  //Verrà chiamato al completamento del livello
        if(campainMapIndex>=campainMaps)
            return false;

        campainMapIndex++;
        setDifficulty();
        return true;

    }

    private static void setDifficulty() {
        if(campainMapIndex == 1 || campainMapIndex == 2)
            diff = Difficulty.EASY;
        else if(campainMapIndex == 3 || campainMapIndex == 5)
            diff = Difficulty.MEDIUM;
        else
            diff = Difficulty.HARD;
    }


    public static void loadEditorMap(String nomeFile) {
        mapName=nomeFile;
        isEditor=true;
    }
    public static void loadCampaign(){
        isEditor=false;
    }

    //Grandezza della finestra di Gioco
    public static enum Difficulty{EASY, MEDIUM, HARD};

    public static Difficulty diff = Difficulty.EASY;
    public static enum movementDirection{RIGHT, LEFT, UP, DOWN};

    public  static int WORLD_SIZEX=32;
    public  static int WORLD_SIZEY=20;
    public  static int WINDOW_SIZEX=1280;
    public  static int WINDOW_SIZEY=660;
    public  static int MENU_BAR_HEIGHT=60;
    public static int EDITOR_BAR_HEIGHT = 60;
    public  static int CELL_SIZEX = WINDOW_SIZEX / WORLD_SIZEX;
    public  static int CELL_SIZEY = WINDOW_SIZEY / WORLD_SIZEY;

    public static void showError(String error) {
        //pop up per visualizzazione errori
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
        label.setText("<html>" + error + "</html>");

        JButton btnConfirm = new JButton("Ok");

        btnConfirm.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnConfirm.setHorizontalTextPosition(JButton.CENTER);
        btnConfirm.setVerticalTextPosition(JButton.CENTER);
        btnConfirm.setBorderPainted(false);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnConfirm});
        JDialog dialog = new JDialog();

        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
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
