package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginController {
    private final LoginModel login;
    private final LoginView view;
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    public LoginController(LoginModel login, LoginView view){
        //Creo il model e la view
        this.login = login;
        this.view = view;
    }

    public void addListener() {
        //Evento per settare il numero massimo di caratteri nel campo di input
        view.getNickname().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(view.getNickname().getText().length() > 19)
                    e.consume();
            }
        });


        //Qua vado ad assegnare la bottone un mouse listener per far si che quando la password è nascosta venga mostrata e viceversa
        view.getShowPassword().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                if(!view.isShow()){
                    view.setShow(true);
                    //Carattere per visualizzare la password
                    view.getPassword().setEchoChar('\u0000');
                    view.getShowPassword().setIcon(loader.getImageIcon("/Login&Menu/hide.png", 40, 40, false));
                }
                else{
                    view.setShow(false);
                    view.getPassword().setEchoChar('-');
                    view.getShowPassword().setIcon(loader.getImageIcon("/Login&Menu/show.png", 40, 40, false));
                }
            }
        });

        //Do un evento al pulsante 'SEND', quando viene premuto testa le credenziali di accesso
        view.getBtnSend().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(GameData.sound)
                    PlayWav.getInstance().playButtonSound();
                try {
                    if((view.getNickname().getText().length() > 0) && (view.getPassword().getPassword().length > 0))
                        //Chiamata funzione
                        getCredenziali();
                } catch (NoSuchAlgorithmException | SQLException | IOException ex) {
                    showDialog();
                }
            }
        });

        //quando la barra del nick prende il focus ed è presente l'errore del nick si elimina
        view.getNickname().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(view.getNickname().getText().equals("Nickname not available!") || view.getNickname().getText().equals("Nickname non disponibile!")){
                    view.getNickname().setText("");
                    view.getPassword().setText("");
                    view.getNickname().setForeground(Color.white);
                }
            }
        });

        //stessa cosa succede se prende il focus il campo apssword
        view.getPassword().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(view.getNickname().getText().equals("Nickname not available!") || view.getNickname().getText().equals("Nickname non disponibile!")){
                    view.getNickname().setText("");
                    view.getPassword().setText("");
                    view.getNickname().setForeground(Color.white);
                }
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
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                view.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));
            }
        });
    }

    public void getCredenziali() throws NoSuchAlgorithmException, SQLException, IOException {
        //come prima cosa mi prendo i valori dal campo password e nickname
        GameData.nick = view.getNickname().getText();
        GameData.pass = new String(view.getPassword().getPassword());
        //chiamo la funzione di crittografia
        GameData.pass = login.crittografia(GameData.pass);

        //Chiamo la funzione del model per controllare le credenziali e gli passo il path del file php
        login.controlloCredenzialiOnline("https://progettouid.altervista.org/ZombieApocalypse/playerUID.php?nickname=" + GameData.nick + "&password=" + GameData.pass);
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
