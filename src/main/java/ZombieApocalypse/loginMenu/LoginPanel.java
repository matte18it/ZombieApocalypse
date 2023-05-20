package ZombieApocalypse.loginMenu;

import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.utility.PlayerData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;

public class LoginPanel extends JPanel {
    private final int setBg;       //Variabile che mi serve per settare lo sfondo della schermata di login
    private Font font;              //Variabile in cui si carica il font
    private JLabel titolo;       //Label per visualizzare il titolo
    private JButton btnSend, showPassword;     //Bottone per inviare i dati e l'altro per visualizzare la password
    private JTextField txtNickname;  //Per inserire il nickname
    private JPasswordField txtPassword; //Per inserire la password
    private JLabel lblNickame;   //Label per nickname
    private JLabel lblPassword;  //Label per password
    private JPanel panelNickname, panelPasswod, imagePanel, pannelloBottone;    //Pannelli per contenere gli elementi
    private int borderValueRight, borderValueLeft;  //Variabili che contengono il bordo destro e sinistro dell'animazione
    private boolean cambio = false, show = false;   //la variabile cambio serve per cambiare il verso dell'animazione del titolo, show invece per mostrare o nascondere la password

    public LoginPanel() {
        //Carico il font personalizzato
        loadFont();

        //setBg contiene un numero casuale da 1 a 4, utile per settare un immagine casuale a ogni avvio
        setBg = new Random().nextInt(1, 4);

        //Inizializzo i componenti da inserire nel pannello principale
        initComponent();

        //Settando il valore di borderValueLeft a 100 dico all'animazione del titolo di iniziare andando verso sinistra
        borderValueLeft = 100;
        borderValueRight = titolo.getBorder().getBorderInsets(titolo).right;

        //Setto il layout del pannello principale
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Inserisco ora i componenti nel pannello principale
        this.add(imagePanel);
        this.add(panelNickname);
        this.add(panelPasswod);
        this.add(pannelloBottone);
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("/font/PixelFont.otf"))).deriveFont(Font.PLAIN, 30);
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void initComponent() {
        //Creo la label titolo
        titolo = new JLabel();
        //Creo la icon da inserire nella label titolo
        titolo.setIcon(new ImageIcon(this.getClass().getResource("/gameTitle/title.png")));
        //Setto le dimensioni della label titolo e il bordo
        titolo.setMinimumSize(new Dimension(750, 138));
        titolo.setMaximumSize(new Dimension(750, 138));
        titolo.setBorder(new EmptyBorder(20, 0, 0, 0));
        //creo il pannello dove metto la label titolo
        imagePanel = new JPanel();
        imagePanel.add(titolo);
        imagePanel.setOpaque(false);
        imagePanel.setMaximumSize(new Dimension(800, 200));

        //Creo la label per il nickname
        lblNickame = new JLabel("Nickname: ");
        lblNickame.setFont(font);
        lblNickame.setMinimumSize(new Dimension(180, 30));
        lblNickame.setMaximumSize(new Dimension(180, 30));
        //Ora creo il campo di input dove inserire il nickname
        txtNickname = new JTextField();
        //Qui vado a creare una label in cui andrò ad inserire il campo di input per dargli una grafica
        JLabel label1 = new JLabel(new ImageIcon(getClass().getResource("/loginInterface/txtGUI.png")));
        label1.setLayout(new BorderLayout());
        label1.add(txtNickname);
        txtNickname.setOpaque(false);
        //Con questa istruzione vado a far iniziare il testo del campo di input spostato leggermente a sinistra, tutto per un fattore estetico
        txtNickname.setBorder(BorderFactory.createCompoundBorder(txtNickname.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        txtNickname.setBackground(new Color(0, 0, 0 ,0));
        txtNickname.setFont(font.deriveFont(Font.PLAIN, 18));
        txtNickname.setMinimumSize(new Dimension(300, 45));
        txtNickname.setMaximumSize(new Dimension(350, 45));
        txtNickname.setForeground(Color.WHITE);
        //vado ad impostare il focus di default sul campo di input del nickname
        txtNickname.setFocusable(true);
        txtNickname.requestFocus();
        //Inserisco tutto nel pannello
        panelNickname = new JPanel();
        panelNickname.setOpaque(false);
        panelNickname.setLayout(new BoxLayout(panelNickname, BoxLayout.X_AXIS));
        panelNickname.add(lblNickame);
        panelNickname.add(label1);

        //Creo la label per il campo password
        lblPassword = new JLabel("Password: ");
        lblPassword.setFont(font);
        lblPassword.setMinimumSize(new Dimension(230, 50));
        lblPassword.setMaximumSize(new Dimension(230, 50));
        //ora creo il campo password
        txtPassword = new JPasswordField();
        //Qui vado a dare un bordo al campo password per far si che sia allineato al campo sopra. Infatti senza questo bordo sarebbero disallineati in quanto è presente un bottone in più affianco al campo password
        lblPassword.setBorder(new EmptyBorder(0, 57, 0, 0));
        //Creo la label come ho fatto anche sopra
        JLabel label2 = new JLabel(new ImageIcon(getClass().getResource("/loginInterface/txtGUI.png")));
        label2.setLayout(new BorderLayout());
        label2.add(txtPassword);
        txtPassword.setOpaque(false);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(txtPassword.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        txtPassword.setBackground(new Color(0,0, 0 ,0));
        txtPassword.setFont(font.deriveFont(Font.PLAIN, 18));
        txtPassword.setMinimumSize(new Dimension(300, 45));
        txtPassword.setMaximumSize(new Dimension(355, 45));
        //faccio in modo che la password venga rimpiazzata dal carattere '-'
        txtPassword.setEchoChar('-');
        txtPassword.setForeground(Color.white);
        //qua creo il pulsante per mostare/nascondere la password
        showPassword = new JButton("", new ImageIcon(getClass().getResource("/loginInterface/show.png")));
        showPassword.setBorderPainted(false);
        showPassword.setBackground(new Color(37, 40, 80));
        showPassword.setOpaque(false);
        showPassword.setFocusPainted(false);
        //Qua vado ad assegnare la bottone un mouse listener per far si che quando la password è nascosta venga mostrata e viceversa
        showPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(!show){
                    show = true;
                    //Carattere per visualizzare la password
                    txtPassword.setEchoChar('\u0000');
                    showPassword.setIcon(new ImageIcon(getClass().getResource("/loginInterface/hide.png")));
                }
                else{
                    show = false;
                    txtPassword.setEchoChar('-');
                    showPassword.setIcon(new ImageIcon(getClass().getResource("/loginInterface/show.png")));
                }
            }
        });
        //Metto tutto nel pannello
        panelPasswod = new JPanel();
        panelPasswod.setOpaque(false);
        panelPasswod.setLayout(new BoxLayout(panelPasswod, BoxLayout.X_AXIS));
        panelPasswod.add(lblPassword);
        panelPasswod.add(label2);
        panelPasswod.add(showPassword);
        panelPasswod.setBorder(new EmptyBorder(30, 0, 0, 0));

        //Creo il pannello per contenere il bottone
        btnSend = new JButton("Send", new ImageIcon(getClass().getResource("/loginInterface/sendButton.png")));
        btnSend.setHorizontalTextPosition(JButton.CENTER);
        btnSend.setVerticalTextPosition(JButton.CENTER);
        btnSend.setFont(font);
        btnSend.setForeground(Color.WHITE);
        btnSend.setMinimumSize(new Dimension(197, 60));
        btnSend.setMaximumSize(new Dimension(197, 60));
        //Do un evento al pulsante 'SEND', quando viene premuto testa le credenziali di accesso
        btnSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    //Chiamata funzione
                    controlloCredenziali();
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Metto il bottone nel suo pannello
        pannelloBottone = new JPanel();
        pannelloBottone.setOpaque(false);
        pannelloBottone.setLayout(new BoxLayout(pannelloBottone, BoxLayout.X_AXIS));
        pannelloBottone.add(btnSend);
        pannelloBottone.setBorder(new EmptyBorder(30, 0, 0, 0));

        //Se lo sfondo è impostato sul secondo o quarto (sfondi scuri) rendo le scritte più chiare, per una questione di legibilità
        if(setBg == 2 || setBg == 4){
            lblNickame.setForeground(Color.WHITE);
            lblPassword.setForeground(Color.WHITE);
        }
    }

    private void controlloCredenziali() throws SQLException, NoSuchAlgorithmException {
        //come prima cosa mi prendo i valori dal campo password e nickname
        PlayerData.nick = txtNickname.getText();
        PlayerData.pass = new String(txtPassword.getPassword());

        //chiamo la funzione di crittografia
        PlayerData.pass = crittografia(PlayerData.pass);

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
                txtNickname.setText("Nickname not available!");
                txtNickname.setForeground(Color.RED);
                txtPassword.setText("");

                txtNickname.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        if(txtNickname.getText().equals("Nickname not available!")){
                            txtNickname.setText("");
                            txtPassword.setText("");
                            txtNickname.setForeground(Color.white);
                        }
                    }
                });
                txtPassword.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        if(txtNickname.getText().equals("Nickname not available!")){
                            txtNickname.setText("");
                            txtPassword.setText("");
                            txtNickname.setForeground(Color.white);
                        }
                    }
                });
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

    private String crittografia(String pass) throws NoSuchAlgorithmException {
        //per crittografare uso la funzione id hash MD5, quindi creo la stringa criptata e la restituisco al programma
        MessageDigest m = MessageDigest.getInstance("MD5");
        //aggiorna il digest usando la password
        m.update(pass.getBytes());
        byte[] bytes = m.digest();
        StringBuilder s = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
            //creo la stringa
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        return s.toString();
    }

    public void updatePosition(){
        //Questa funzione serve per l'animazione del titolo

        //se è verificata questa condizione vuol dire che l'animazione deve spostarsi verso sinistra
        if(borderValueRight < 100 && !cambio){
            //Quindi incremento il bordo destro per farlo spostare verso sinistra
            borderValueRight += 3;
            //se il bordo di sinistra è maggiore di 0 devo decrementarlo per evitare di farlo uscire dallo schermo
            if(borderValueLeft > 0)
                borderValueLeft -= 3;
            //Qua setto i nuovi bordi
            titolo.setBorder(new EmptyBorder(20, borderValueLeft, 0, borderValueRight));
        }
        else if(borderValueRight >= 100)
            //quando il bordo destro arriva sopra il valore 100 inverto il senso dell'animazione
            cambio = true;

        //se è verificata questa condizione vuol dire che l'animazione deve spostarsi verso destra
        if(borderValueLeft < 100 && cambio){
            //incremento il bordo sinistro per farlo spostare verso destra
            borderValueLeft += 3;
            //se il bordo di destra è maggiore di 0 devo decrementarlo per evitare di farlo uscire dallo schermo
            if(borderValueRight > 0)
                borderValueRight -= 3;
            //setto i nuovi bordi
            titolo.setBorder(new EmptyBorder(20, borderValueLeft, 0, borderValueRight));
        }
        else if(borderValueLeft >= 100)
            //quando il bordo sinistro arriva sopra il valore 100 inverto il senso dell'animazione
            cambio = false;
    }

    @Override
    public void paintComponent(Graphics g){
            //Con questo paintComponent vado a impostare un immagine casuale come sfondo al login
            super.paintComponent(g);
            Image bgImage = null;       //Immagine da disegnare

            try{
                //Qui vado a leggere una sola immagine casuale tra le quattro disponibili
                bgImage = ImageIO.read(getClass().getResourceAsStream("/loginBackground/War" + setBg + ".png"));
            } catch(IOException e){ e.printStackTrace(); }

            //Disegno l'immagine come sfondo del panel
            g.drawImage(bgImage, 0, 0, null);
    }

}
