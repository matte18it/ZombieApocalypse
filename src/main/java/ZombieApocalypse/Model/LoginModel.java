package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

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
            case 0:{
                try {
                    File myObj = new File("player.txt");
                    myObj.createNewFile();
                    FileWriter myWriter = new FileWriter("player.txt");
                    myWriter.write(GameData.nick);
                    myWriter.close();
                } catch (IOException e) { e.printStackTrace(); }
                getData("https://progettouid.altervista.org/ZombieApocalypse/getData.php?nickname=" + GameData.nick);
                GameFrame.loop.stop();
                GameFrame.frameGame.remove(GameFrame.panel);
                GameFrame.menuLaunch();
                break;
            }
            case 1:{
                if(GameData.lang== GameData.Language.IT)
                    view.getNickname().setText("Nickname non disponibile!");
                else
                    view.getNickname().setText("Nickname not available!");
                view.getNickname().setForeground(Color.RED);
                view.getPassword().setText("");
                break;
            }
            case 2:{
                try {
                    File myObj = new File("player.txt");
                    myObj.createNewFile();
                    FileWriter myWriter = new FileWriter("player.txt");
                    myWriter.write(GameData.nick);
                    myWriter.close();
                } catch (IOException e) { e.printStackTrace(); }
                GameFrame.loop.stop();
                GameFrame.frameGame.remove(GameFrame.panel);
                GameFrame.menuLaunch();
                break;
            }
            default:{
                //se non è nessun caso precedente potrebbe esserci stato un errore nella lettura dei dati
                System.out.println("Errore nella lettura dei dati!");
                break;
            }
        }
    }

    private static void getData(String path) throws IOException {
        //chiamo script per fare get dei dati
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
