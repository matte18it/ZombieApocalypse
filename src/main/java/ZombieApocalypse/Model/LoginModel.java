package ZombieApocalypse.Model;

import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginModel {

    LoginView view;
    public LoginModel(LoginView view) {
        //Creo la view
        this.view = view;
    }

    public void controlloCredenzialiOnline(String path) throws IOException {
        //ris conterrà il risultato dell'interrogazione allo script php
        int ris = -1;
        //script contiene l'indirizzo online dello script
        URL sript = new URL(path);
        //Apro la connessione con l'indirizzo passato
        URLConnection conn = sript.openConnection();
        //Questa porzione di codice serve per leggere il risultato ritornato dallo script
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            ris = Integer.parseInt(inputLine);
        in.close();

        //se ris è pari a 0 effettuo il login, se ris è pari a 1 vuol dire che c'è già un player registrato con lo stesso nome, 2 effettuo la registrazione
        switch (ris){
            case 0, 2:{
                GameFrame.menuLaunch();
                break;
            }
            case 1:{
                view.getNickname().setText("Nickname not available!");
                view.getNickname().setForeground(Color.RED);
                view.getPassword().setText("");
                break;
            }
            default:{
                //se non è nessun caso precedente potrebbe esserci stato un errore nella lettura dei dati
                System.out.println("Errore nella lettura dei dati!");
                break;
            }
        }
    }

    public String crittografia(String pass) throws NoSuchAlgorithmException {
        //per crittografare uso la funzione di hash MD5, quindi creo la stringa criptata e la restituisco al programma
        MessageDigest m = MessageDigest.getInstance("SHA-512");
        MessageDigest m1 = MessageDigest.getInstance("MD5");

        //aggiorna il digest usando la password
        m.update(pass.getBytes());
        byte[] bytes = m.digest();
        StringBuilder s = new StringBuilder();
        //Viene prima criptata con SHA-512
        for(int i = 0; i < bytes.length; i++)
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

        m1.update(s.toString().getBytes());
        byte[] bytes1 = m1.digest();
        StringBuilder s1 = new StringBuilder();
        //Viene poi criptata con MD5
        for(int i = 0; i < bytes1.length; i++)
            s1.append(Integer.toString((bytes1[i] & 0xff) + 0x100, 16).substring(1));

        return s1.toString();
    }

}
