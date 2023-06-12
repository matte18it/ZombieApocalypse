package ZombieApocalypse.Controller;
import ZombieApocalypse.Loop.GameLoop;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.ThreadPool;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.GraphicPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.awt.event.KeyEvent.VK_ESCAPE;

public class PlayerController implements KeyListener, MouseMotionListener, MouseListener {
    //Gestisce i movimenti del player

    private final GraphicPanel panel;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public PlayerController(GraphicPanel panel) {
        this.panel = panel;
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //setto il cursore personalizzato
                panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GameData.mancino) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_I -> Game.getInstance().startMovementUp();
                case KeyEvent.VK_L -> Game.getInstance().startMovementRight();
                case KeyEvent.VK_J -> Game.getInstance().startMovementLeft();
                case KeyEvent.VK_K -> Game.getInstance().startMovementDown();
                case KeyEvent.VK_U -> Game.getInstance().useLeftItem();
                case KeyEvent.VK_O -> Game.getInstance().useRightItem();
                case KeyEvent.VK_SPACE -> Game.getInstance().dropItem();
            }
        }else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> Game.getInstance().startMovementUp();
                case KeyEvent.VK_D -> Game.getInstance().startMovementRight();
                case KeyEvent.VK_A -> Game.getInstance().startMovementLeft();
                case KeyEvent.VK_S -> Game.getInstance().startMovementDown();
                case KeyEvent.VK_Q -> Game.getInstance().useLeftItem();
                case KeyEvent.VK_E -> Game.getInstance().useRightItem();
                case KeyEvent.VK_SPACE -> Game.getInstance().dropItem();
            }
        }
        if( e.getKeyCode()== VK_ESCAPE)
            if(!Game.getInstance().getPause()){
                Game.getInstance().setPause(true);
                setPause();
            }
    }

    private void setPause() {
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
                Game.getInstance().refresh();
                Game.getInstance().setPause(false);
                Game.getInstance().setBackMenu(true);
                if(PlayWav.getInstance().isPlay())
                    PlayWav.getInstance().stop();
                GameFrame.menuLaunch();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try { saveData(); } catch (IOException ex) { throw new RuntimeException(ex); }
                    }
                });
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

    private void soundButton() {
        PlayWav.getInstance().playButtonSound();
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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if(GameData.mancino) {
            if (e.getKeyCode() == KeyEvent.VK_I || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_K || e.getKeyCode() == KeyEvent.VK_L)
                Game.getInstance().stopMovement();
        }else {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A)
                Game.getInstance().stopMovement();
        }
    }
    private int count=0;
    private int countZombie = 0;
    private int randomZombie = new Random().nextInt(300, 600);
    private int cdd=0;
    Future<?> f1;

    public void update(){
        try{
        if(f1!=null)
          f1.get();
        if(!Game.getInstance().getPause()&& !Game.getInstance().getBackMenu()){
            Game.getInstance().update();
            f1= ThreadPool.getExecutor().submit(()->panel.update());
            count=0;
        }if( Game.getInstance().getBackMenu() && count==0){
            Game.getInstance().update();
            f1= ThreadPool.getExecutor().submit(()->panel.update());
            count++;
        }
        if(count==1){
            count=0;
            Game.getInstance().setBackMenu(false);
        }

        if(countZombie == randomZombie){
            countZombie = 0;
            if(GameData.sound)
                PlayWav.getInstance().playZombie();
        }
        else
            countZombie++;

    }catch (ExecutionException | InterruptedException e){

    }}

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Game.getInstance().hasPistol)
            panel.getPistolView().update(e.getPoint());
        if(Game.getInstance().hasShotgun)
            panel.getShotgunView().update(e.getPoint());
        if(Game.getInstance().hasGrenade)
            panel.getGrenadeView().update(e.getPoint());

        Game.getInstance().setLastMousePosition(e.getPoint());

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(GameData.mancino && e.getButton()==2)
            Game.getInstance().attack();
        if(!GameData.mancino && e.getButton()==1)
            Game.getInstance().attack();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

