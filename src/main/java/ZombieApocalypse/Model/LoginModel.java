package ZombieApocalypse.Model;

import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.Utility.PlayerData;
import ZombieApocalypse.View.LoginView;

import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginModel {

    LoginView view;
    public LoginModel(LoginView view) {
        //Creo la view
        this.view = view;
    }

    public void controlloCredenziali() throws SQLException, NoSuchAlgorithmException {
        //effettuo la conessione col db
        String url = "jdbc:sqlite:player.db";
        Connection con = DriverManager.getConnection(url);

        //con questa query vado a contare il numero di utenti nel db che hanno lo stesso nick e la stessa pass di quelli inseriti
        String query = "select count(*) from player where Nickname=? AND Password=?;";
        PreparedStatement stmt = con.prepareStatement(query);
        //setto il primo parametro della query 'Nickname' a playerData.nick
        stmt.setString(1, PlayerData.nick);
        //setto il secondo parametro della query 'Password' a playerData.pass
        stmt.setString(2, PlayerData.pass);
        //eseguo la query e metto il risultato in rs
        ResultSet rs = stmt.executeQuery();

        //se il numero nel db di utenti è uguale a 1 effettuo il login
        if(rs.getInt(1) == 1){
            GameFrame.gameLaunch();
            GameFrame.loop.stop();
        }
        else{
            //Altrimenti verifico se il nickname è già stato usato
            query = "select count(*) from player where Nickname=?;";
            stmt = con.prepareStatement(query);
            stmt.setString(1, PlayerData.nick);
            rs = stmt.executeQuery();
            if(rs.getInt(1) == 1){
                //Se è già stato usato comunico con una scritta all'utente che il nickname non è disponibilen (essendo chiave primaria devono essere tutti diversi)
                view.getNickname().setText("Nickname not available!");
                view.getNickname().setForeground(Color.RED);
                view.getNickname().setText("");
            } else{
                //Altrimenti se non è stato usato registro il player e lo faccio accedere al gioco
                GameFrame.gameLaunch();
                query = "insert into Player(Nickname, Password) values(?, ?)";
                stmt = con.prepareStatement(query);
                stmt.setString(1, PlayerData.nick);
                stmt.setString(2, PlayerData.pass);
                stmt.execute();
                GameFrame.loop.stop();
            }
        }

        //chiudo la connessione col db
        stmt.close();
        con.close();
    }

    public String crittografia(String pass) throws NoSuchAlgorithmException {
        //per crittografare uso la funzione id hash MD5, quindi creo la stringa criptata e la restituisco al programma
        MessageDigest m = MessageDigest.getInstance("MD5");
        //aggiorna il digest usando la password
        m.update(pass.getBytes());
        byte[] bytes = m.digest();
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < bytes.length; i++)
            //creo la stringa
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        return s.toString();
    }

}
