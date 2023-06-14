package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;
import ZombieApocalypse.View.SplashScreenView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class SplashScreenModel {
    public static SplashScreenView view;
    public SplashScreenModel(SplashScreenView view) {
        //Creo la view
        this.view = view;
    }

    public static void checkFile() {
        //qua faccio un check del file player.txt. Infatti se esiste faccio login automatico, sennò vado al login
        File file = new File("player.txt");
        Scanner myReader = null;

        if(file.exists()){

            try { myReader = new Scanner(file); }
            catch (FileNotFoundException e) { throw new RuntimeException(e); }

            GameData.nick = myReader.nextLine();
            GameData.pass = myReader.nextLine();
            System.out.println(GameData.nick + " " + GameData.pass);
            myReader.close();
            try { if(getData("https://progettouid.altervista.org/ZombieApocalypse/getData.php?nickname=" + GameData.nick)); } catch (IOException e) { showDialog(); }
        }

    }

    private static boolean getData(String path) throws  IOException{

        //faccio un check per verificare correttezza credenziali
        URL check = new URL("https://progettouid.altervista.org/ZombieApocalypse/checkPlayer.php?nickname=" + GameData.nick + "&password=" + GameData.pass);
        URLConnection connCheck = check.openConnection();
        BufferedReader incheck = new BufferedReader(new InputStreamReader(connCheck.getInputStream()));
        if(incheck.readLine().equals("0")){
            GameData.nick = null; GameData.pass = null;
            return false;
        }

        //chiamo script per fare get dei dati dal db se il file player.txt esiste e se le credenziali sono corrette
        URL sript = new URL(path);
        URLConnection conn = sript.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;

        //Aggiorno i dati
        if ((inputLine = in.readLine()) != null)
            GameData.musicVolume = Integer.parseInt(inputLine);
        if ((inputLine = in.readLine()) != null)
            GameData.soundVolume = Integer.parseInt(inputLine);
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.music = false;
            else
                GameData.music = true;
        }
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.sound = false;
            else
                GameData.sound = true;
        }
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.mancino = false;
            else
                GameData.mancino = true;
        }
        if ((inputLine = in.readLine()) != null)
            GameData.skinAttiva = Integer.parseInt(inputLine);
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.lang = GameData.Language.EN;
            else
                GameData.lang = GameData.Language.IT;
        }
        if ((inputLine = in.readLine()) != null)
            GameData.recordPunti = Integer.parseInt(inputLine);

        in.close();

        return true;
    }

    public static void loadInterface() {
        if(GameData.nick != null){
            //Creo un menuView, parte interna della mia cornice
            view.bar.setIcon(ResourcesLoader.getInstance().getImageIcon("/SplashScreen/bar.png", 550, 7, false));
            GameFrame.menu = new MenuView();
            GameFrame.menuLaunch();
        }
        else{
            //Creo un loginView, parte interna della mia cornice
            view.bar.setIcon(ResourcesLoader.getInstance().getImageIcon("/SplashScreen/bar.png", 550, 7, false));
            GameFrame.panel = new LoginView();
            GameFrame.loginLaunch();
        }
    }

    private static void showDialog() {
        //dialog personalizzato a tema Zombie Apocalypse utilissimo. Infatti se il player non è connesso a internet (requisito fondamentale dato che il db è online) lo comunica al player

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
            btnMenu = new JButton("Riprova");
            label.setText("<html>Nessuna connessione ad internet!<br>Per giocare a 'Zombie Apocalypse' serve una rete internet stabile. Connettiti ad una rete internet stabile e riprova!</html>");
        }
        else {
            btnMenu = new JButton("Try Again");
            label.setText("<html>No internet connection at all!<br>To play 'Zombie Apocalypse' you need a stable internet network. Connect to a stable internet network and try again!</html>");
        }

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
                try { if(getData("https://progettouid.altervista.org/ZombieApocalypse/getData.php?nickname=" + GameData.nick)) GameFrame.launch(); } catch (IOException e1) { showDialog(); }
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
