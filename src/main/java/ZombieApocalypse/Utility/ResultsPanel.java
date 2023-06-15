package ZombieApocalypse.Utility;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.View.GameFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

public class ResultsPanel {
    //classe per la gestione delle schermate di vittoria, sconfitta e pausa
    public static ResultsPanel results = null;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private ResultsPanel(){}
    public static ResultsPanel getInstance(){
        if(results == null)
            results = new ResultsPanel();

        return results;
    }

    public void showContinue() {
        //Schermata per livello completato
        if(GameData.sound)
            PlayWav.getInstance().playMissionComplete();

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
        label.setFont(font.deriveFont(Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(315, 100));
        label.setPreferredSize(new Dimension(315, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(ResourcesLoader.getInstance().getImageIcon("/VictoryLose/YouWin.png", 287, 113, false));
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton btnMenu;
        if(GameData.lang.equals(GameData.Language.EN))
            btnMenu = new JButton("Continue");
        else
            btnMenu = new JButton("Continua");

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu});
        JDialog dialog = new JDialog();

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
                Game.getInstance().setPause(false);
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().stop();
                Game.getInstance().setBackMenu(true);
                Game.getInstance().reloadWorld();
                GameFrame.gameLaunch();
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
    public void showFinal() {
        //Schermata di vittoria
        if(GameData.sound)
            PlayWav.getInstance().playYouWin();

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
        label.setFont(font.deriveFont(Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(315, 100));
        label.setPreferredSize(new Dimension(315, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(ResourcesLoader.getInstance().getImageIcon("/VictoryLose/YouWin.png", 287, 113, false));
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton btnMenu = new JButton("Menu");

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu});
        JDialog dialog = new JDialog();

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
                Game.getInstance().setPause(false);
                Game.getInstance().setBackMenu(true);
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().stop();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try { saveData(); } catch (IOException ex) { throw new RuntimeException(ex); } finally { GameData.punti = 0; }
                    }
                });
                GameFrame.menuLaunch();
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
    public void showPause() {
        //schermata di pausa
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
        label.setFont(font.deriveFont(Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(100, 100));
        label.setPreferredSize(new Dimension(100, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        //label.setVerticalAlignment(JLabel.TOP);
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton btnMenu = new JButton("Menu");
        JButton btnGo;
        if(GameData.lang.equals(GameData.Language.IT)) {
            btnGo = new JButton("Riprendi");
            label.setText("PAUSA");
        }
        else {
            btnGo = new JButton("Resume");
            label.setText("PAUSE");
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
                    soundButton();
                Game.getInstance().setPause(false);
                dialog.dispose();
            }
        });
        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    soundButton();
                dialog.dispose();
                Game.getInstance().setPause(false);
                Game.getInstance().setBackMenu(true);
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().stop();
                CountPoint.getInstance().malusPoint();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try { saveData(); } catch (IOException ex) { throw new RuntimeException(ex); } finally { GameData.punti = 0; }
                    }
                });
                GameFrame.menuLaunch();
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
    public void showGameOver() {
        //schermata di sconfitta (game over)
        if(GameData.sound)
            PlayWav.getInstance().playGameOverSound();

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
        label.setFont(font.deriveFont(Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setMinimumSize(new Dimension(315, 100));
        label.setPreferredSize(new Dimension(315, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(ResourcesLoader.getInstance().getImageIcon("/VictoryLose/YouLose.png", 315, 113, false));
        label.setBorder(new EmptyBorder(10, 0, 0, 0));
        JButton btnMenu = new JButton("Menu");

        btnMenu.setIcon(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/sendButton.png", 230, 60, false));
        btnMenu.setHorizontalTextPosition(JButton.CENTER);
        btnMenu.setVerticalTextPosition(JButton.CENTER);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 25, Font.PLAIN));

        //creo il joptionpane e gli assegno la label, poi creo il dialog e lo mostro
        JOptionPane pane = new JOptionPane(label,  JOptionPane.PLAIN_MESSAGE,  JOptionPane.DEFAULT_OPTION,null,  new JButton[] {btnMenu});
        JDialog dialog = new JDialog();

        btnMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                dialog.dispose();
                Game.getInstance().setPause(false);
                Game.getInstance().setBackMenu(true);
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().stop();
                CountPoint.getInstance().malusPoint();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try { saveData(); } catch (IOException ex) { throw new RuntimeException(ex); } finally { GameData.punti = 0; }
                    }
                });
                GameFrame.menuLaunch();
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

    private void saveData() throws IOException {
        //script per salvare i dati
        int val1, val2, val3, val4;

        //incremento i punti
        GameData.recordPunti += GameData.punti;

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
    private void soundButton() {
        PlayWav.getInstance().playButtonSound();
    }
    public void showError(String error, int exitStatus, Exception err){
        JOptionPane.showMessageDialog(null , error, "Error!", JOptionPane.ERROR_MESSAGE);
        err.printStackTrace();
        System.exit(exitStatus);
    }
}
