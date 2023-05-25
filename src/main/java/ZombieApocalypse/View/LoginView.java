package ZombieApocalypse.View;

import ZombieApocalypse.Controller.LoginController;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Utility.GameData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class LoginView extends JPanel{
    private ResourcesLoader loader = ResourcesLoader.getInstance();
    private LoginModel model;
    private LoginController controller;
    private Font font;              //Variabile in cui si carica il font
    private JLabel titolo;       //Label per visualizzare il titolo
    private JButton btnSend, showPassword;     //Bottone per inviare i dati e l'altro per visualizzare la password
    protected JTextField txtNickname;  //Per inserire il nickname
    private JPasswordField txtPassword; //Per inserire la password
    private JLabel lblNickame;   //Label per nickname
    private JLabel lblPassword;  //Label per password
    private JPanel panelPrincipale, imagePanel;    //Pannelli per contenere gli elementi
    private int borderValueRight, borderValueLeft;  //Variabili che contengono il bordo destro e sinistro dell'animazione
    private boolean cambio = false, show = false;   //la variabile cambio serve per cambiare il verso dell'animazione del titolo, show invece per mostrare o nascondere la password

    public LoginView(){
        //setto il cursore personalizzato
        this.setCursor(loader.getCursor("/GameGeneral/crosshair.png", this));

        //Carico il font personalizzato
        font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 30, Font.PLAIN);

        //Inizializzo i componenti da inserire nel pannello principale
        initComponent();

        //Creo un model e un controller
        model = new LoginModel(this);
        controller = new LoginController(model, this);
        //Assegno i listener ai componenti
        controller.addListener();

        //Settando il valore di borderValueLeft a 100 dico all'animazione del titolo di iniziare andando verso sinistra
        borderValueLeft = 100;
        borderValueRight = titolo.getBorder().getBorderInsets(titolo).right;

        //Setto il layout del pannello principale
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Inserisco ora i componenti nel pannello principale
        this.add(imagePanel);
        this.add(panelPrincipale);
    }

    private void initComponent() {
        //creo il panello
        panelPrincipale = new JPanel();
        panelPrincipale.setOpaque(false);
        panelPrincipale.setLayout(new GridBagLayout());
        panelPrincipale.setMaximumSize(new Dimension(1280, 270));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 5, 0, 0);

        //Creo la label titolo
        titolo = new JLabel();
        //Creo la icon da inserire nella label titolo
        titolo.setIcon(loader.getImageIcon("/GameTitle/title.png", 650, 138, false));
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
        lblNickame.setFont(font.deriveFont(Font.PLAIN, 30));
        //Ora creo il campo di input dove inserire il nickname
        txtNickname = new JTextField();
        //Qui vado a creare una label in cui andrò ad inserire il campo di input per dargli una grafica
        JLabel label1 = new JLabel(loader.getImageIcon("/Login&Menu/txtGUI.png", 315, 51, false));
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
        //Funzione per cambiare colore al puntatore del mouse nel textfield
        txtNickname.setCaretColor(Color.RED);
        //Inserisco tutto nel pannello
        c.gridx = 0;                //cella con coordinate 0 0
        c.gridy = 0;
        panelPrincipale.add(lblNickame, c);
        c.gridx = 1;                //cella con coordinate 1 0
        c.gridy = 0;
        panelPrincipale.add(label1, c);

        //Creo la label per il campo password
        lblPassword = new JLabel("Password: ");
        lblPassword.setFont(font);
        lblPassword.setMinimumSize(new Dimension(180, 50));
        lblPassword.setMaximumSize(new Dimension(180, 50));
        lblPassword.setFont(font.deriveFont(Font.PLAIN, 30));
        //ora creo il campo password
        txtPassword = new JPasswordField();
        //Creo la label come ho fatto anche sopra
        JLabel label2 = new JLabel(loader.getImageIcon("/Login&Menu/txtGUI.png", 315, 51, false));
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
        //Funzione per cambiare colore al puntatore del mouse nel passwordfield
        txtPassword.setCaretColor(Color.RED);
        //qua creo il pulsante per mostare/nascondere la password
        showPassword = new JButton("", loader.getImageIcon("/Login&Menu/show.png", 40, 40, false));
        showPassword.setBorderPainted(false);
        showPassword.setBackground(new Color(37, 40, 80));
        showPassword.setOpaque(false);
        showPassword.setFocusPainted(false);
        //Metto tutto nel pannello
        c.gridx = 0;                //cella con coordinate 0 1
        c.gridy = 1;
        panelPrincipale.add(lblPassword, c);
        c.gridx = 1;                //cella con coordinate 1 1
        c.gridy = 1;
        panelPrincipale.add(label2, c);
        c.gridx = 2;                //cella con coordinate 2 1
        c.gridy = 1;
        c.insets = new Insets(20, 10, 0, 0);
        panelPrincipale.add(showPassword, c);

        //Creo il pannello per contenere il bottone
        btnSend = new JButton("Send", loader.getImageIcon("/Login&Menu/sendButton.png", 197, 60, false));
        btnSend.setHorizontalTextPosition(JButton.CENTER);
        btnSend.setVerticalTextPosition(JButton.CENTER);
        btnSend.setFont(font);
        btnSend.setBorderPainted(false);
        btnSend.setFocusPainted(false);
        btnSend.setForeground(Color.WHITE);
        btnSend.setFont(font.deriveFont(Font.PLAIN, 30));
        btnSend.setMinimumSize(new Dimension(197, 60));
        btnSend.setMaximumSize(new Dimension(197, 60));
        //Metto il bottone nel suo pannello
        c.gridx = 1;               //cella con coordinate 1 2
        c.gridy = 2;
        c.insets = new Insets(20, 0, 0, 0);
        c.fill = GridBagConstraints.CENTER;
        panelPrincipale.add(btnSend, c);

        if(GameData.setBg == 2 || GameData.setBg == 4){
            lblNickame.setForeground(Color.white);
            lblPassword.setForeground(Color.white);
        }

    }

    public JTextField getNickname(){
        return txtNickname;
    }

    public JPasswordField getPassword(){
        return txtPassword;
    }

    public JButton getShowPassword(){
        return showPassword;
    }

    public boolean isShow(){
        return show;
    }

    public void setShow(boolean show){
        this.show = show;
    }

    public JButton getBtnSend(){
        return btnSend;
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
            bgImage = ImageIO.read(getClass().getResourceAsStream("/LoginBackground/War" + GameData.setBg + ".png"));
        } catch(IOException e){ e.printStackTrace(); }

        //Disegno l'immagine come sfondo del panel
        g.drawImage(bgImage, 0, 0, null);
    }
}
