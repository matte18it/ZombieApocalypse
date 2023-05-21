package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Utility.PlayerData;
import ZombieApocalypse.View.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
                if(view.getNickname().getText().length() > 20)
                    e.consume();
            }
        });


        //Qua vado ad assegnare la bottone un mouse listener per far si che quando la password è nascosta venga mostrata e viceversa
        view.getShowPassword().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!view.isShow()){
                    view.setShow(true);
                    //Carattere per visualizzare la password
                    view.getPassword().setEchoChar('\u0000');
                    view.getShowPassword().setIcon(loader.getImageIcon("/LoginInterface/hide.png", 40, 40, false));
                }
                else{
                    view.setShow(false);
                    view.getPassword().setEchoChar('-');
                    view.getShowPassword().setIcon(loader.getImageIcon("/LoginInterface/show.png", 40, 40, false));
                }
            }
        });

        //Do un evento al pulsante 'SEND', quando viene premuto testa le credenziali di accesso
        view.getBtnSend().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    if((view.getNickname().getText().length() > 0) && (view.getPassword().getPassword().length > 0))
                        //Chiamata funzione
                        getCredenziali();
                } catch (NoSuchAlgorithmException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        view.getNickname().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(view.getNickname().getText().equals("Nickname not available!")){
                    view.getNickname().setText("");
                    view.getPassword().setText("");
                    view.getNickname().setForeground(Color.white);
                }
            }
        });

        view.getPassword().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(view.getNickname().getText().equals("Nickname not available!")){
                    view.getNickname().setText("");
                    view.getPassword().setText("");
                    view.getNickname().setForeground(Color.white);
                }
            }
        });
    }

    public void getCredenziali() throws NoSuchAlgorithmException, SQLException {
        //come prima cosa mi prendo i valori dal campo password e nickname
        PlayerData.nick = view.getNickname().getText();
        PlayerData.pass = new String(view.getPassword().getPassword());
        //chiamo la funzione di crittografia
        PlayerData.pass = login.crittografia(PlayerData.pass);
        //Chiamo la funzione del model per controllare le credenziali
        login.controlloCredenziali();
    }
}
