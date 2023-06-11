package ZombieApocalypse.View;

import ZombieApocalypse.Controller.UserMapController;
import ZombieApocalypse.Model.UserMapModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UserMapView extends JPanel {
    //variabili utili a gestire la mappa dell'utente
    public static int indice = 0;
    public static int maxIndice = 0;

    //variabili per interfaccia
    private UserMapController controller;
    private UserMapModel model;
    private Font font;              //Variabile in cui si carica il font
    private JPanel pannelloPrincipale, imagePanel, panelButton;
    private JButton btnSu, btnGiu, exitButton, loadButton, deleteButton, playButton, btnEasy, btnMedium, btnHard;
    private JLabel label, titolo, labelTitoloTxtArea, lblName, lblDifficulty, lblDescrizione;
    private JTextArea showFile;
    private JTextField nameMap;
    private File[] nameFile;

    public UserMapView(){
        //setto il cursore
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));

        //Carico il font personalizzato
        font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 30, Font.PLAIN);

        //creo il model
        model = new UserMapModel(this);
        //creo il controller
        controller = new UserMapController(model, this);

        initTitolo();
        initComponent();

        controller.addListener();

        this.add(imagePanel);
        this.add(pannelloPrincipale);
    }

    private void initTitolo() {
        titolo = new JLabel();
        titolo.setHorizontalAlignment(JLabel.CENTER);
        //Creo la icon da inserire nella label titolo
        titolo.setIcon(ResourcesLoader.getInstance().getImageIcon("/GameTitle/title.png", 650, 138, false));
        //Setto le dimensioni della label titolo e il bordo
        titolo.setMinimumSize(new Dimension(750, 138));
        titolo.setMaximumSize(new Dimension(750, 138));
        titolo.setBorder(new EmptyBorder(20, 0, 0, 0));
        //creo il pannello dove metto la label titolo
        imagePanel = new JPanel();
        imagePanel.add(titolo);
        imagePanel.setOpaque(false);
        imagePanel.setMaximumSize(new Dimension(800, 200));
    }

    private void initComponent() {
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        pannelloPrincipale = new JPanel();
        pannelloPrincipale.setOpaque(false);
        pannelloPrincipale.setMaximumSize(new Dimension(996, 500));

        label = new JLabel();
        label.setLayout(new GridBagLayout());
        label.setIcon(ResourcesLoader.getInstance().getImageIcon("/UserMap/SfondoUserMap.png", 996, 500, false));
        GridBagConstraints c = new GridBagConstraints();

        if(GameData.lang.equals(GameData.Language.EN))
            labelTitoloTxtArea = new JLabel("Maps of " + GameData.nick + ": ");
        else
            labelTitoloTxtArea = new JLabel("Mappe di " + GameData.nick + ": ");
        labelTitoloTxtArea.setBorder(new EmptyBorder(10, 0, 0, 0));
        labelTitoloTxtArea.setFont(font.deriveFont(Font.PLAIN, 22));
        labelTitoloTxtArea.setForeground(Color.WHITE);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.LINE_START;
        label.add(labelTitoloTxtArea, c);

        showFile = new JTextArea();
        showFile.setMargin(new Insets(25, 55, 25, 25));
        showFile.setPreferredSize(new Dimension(400, 350));
        showFile.setMaximumSize(new Dimension(400, 350));
        showFile.setMinimumSize(new Dimension(400, 350));
        showFile.setEditable(false);
        showFile.setForeground(Color.WHITE);
        showFile.setBorder(new CompoundBorder(new LineBorder(new Color(92,75,35), 5, true), new EmptyBorder(5, 5, 5, 5)));
        showFile.setBackground(new Color(18,17,15));
        showFile.setFont(font.deriveFont(Font.PLAIN, 15));
        c.gridx = 0; c.gridy = 1;
        label.add(showFile, c);

        panelButton = new JPanel();
        panelButton.setOpaque(false);
        panelButton.setMaximumSize(new Dimension(400, 70));
        panelButton.setMinimumSize(new Dimension(400, 70));
        panelButton.setPreferredSize(new Dimension(400, 70));
        panelButton.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        btnSu = new JButton();
        btnSu.setIcon(ResourcesLoader.getInstance().getImageIcon("/UserMap/BottoneFrecciaSu.png", 50, 50, false));
        btnSu.setBorderPainted(false);
        btnSu.setContentAreaFilled(false);
        btnSu.setFocusPainted(false);
        btnSu.setMinimumSize(new Dimension(50, 50));
        btnSu.setPreferredSize(new Dimension(50, 50));
        btnSu.setMaximumSize(new Dimension(50, 50));
        c1.gridx = 0; c1.gridy = 0;
        panelButton.add(btnSu, c1);
        c1.gridx = 1; c1.gridy = 0;
        panelButton.add(Box.createRigidArea(new Dimension(30, 10)), c1);
        btnGiu = new JButton();
        btnGiu.setIcon(ResourcesLoader.getInstance().getImageIcon("/UserMap/BottoneFrecciaGiu.png", 50, 50, false));
        btnGiu.setBorderPainted(false);
        btnGiu.setContentAreaFilled(false);
        btnGiu.setFocusPainted(false);
        btnGiu.setMinimumSize(new Dimension(50, 50));
        btnGiu.setPreferredSize(new Dimension(50, 50));
        btnGiu.setMaximumSize(new Dimension(50, 50));
        c1.gridx = 3; c1.gridy = 0;
        panelButton.add(btnGiu, c1);

        c.gridx = 0; c.gridy = 2;
        label.add(panelButton, c);
        
        initFile();

        JPanel panelSupport1 = new JPanel();
        panelSupport1.setOpaque(false);
        panelSupport1.setMaximumSize(new Dimension(400, 100));
        panelSupport1.setLayout(new BoxLayout(panelSupport1, BoxLayout.X_AXIS));
        if(GameData.lang.equals(GameData.Language.EN))
            lblName = new JLabel("Map Name: ");
        else
            lblName = new JLabel("Nome Mappa: ");
        lblName.setBorder(new EmptyBorder(10, 10, 0, 0));
        lblName.setFont(font.deriveFont(Font.PLAIN, 22));
        lblName.setForeground(Color.WHITE);
        panelSupport1.add(lblName);
        nameMap = new JTextField();
        JLabel label1 = new JLabel(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/txtGUI.png", 280, 40, false));
        label1.setLayout(new BorderLayout());
        label1.add(nameMap);
        label1.setBorder(new EmptyBorder(10, 0, 0, 0));
        nameMap.setOpaque(false);
        nameMap.setBorder(BorderFactory.createCompoundBorder(nameMap.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        nameMap.setBackground(new Color(0, 0, 0 ,0));
        nameMap.setFont(font.deriveFont(Font.PLAIN, 18));
        nameMap.setMinimumSize(new Dimension(280, 45));
        nameMap.setMaximumSize(new Dimension(280, 45));
        nameMap.setForeground(Color.WHITE);
        nameMap.setCaretColor(Color.RED);
        panelSupport1.add(label1);

        JPanel panelSupport2 = new JPanel();
        panelSupport2.setOpaque(false);
        panelSupport2.setMaximumSize(new Dimension(400, 100));
        panelSupport2.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        if(GameData.lang.equals(GameData.Language.EN))
            lblDifficulty = new JLabel("Difficulty: ");
        else
            lblDifficulty = new JLabel("Difficoltà: ");
        lblDifficulty.setBorder(new EmptyBorder(10, 10, 0, 0));
        lblDifficulty.setFont(font.deriveFont(Font.PLAIN, 22));
        lblDifficulty.setForeground(Color.WHITE);
        c3.gridx = 0; c3.gridy = 0; c3.anchor = GridBagConstraints.NORTHWEST;
        panelSupport2.add(panelSupport1, c3);
        c3.gridx = 0; c3.gridy = 1; c3.anchor = GridBagConstraints.NORTHWEST;
        panelSupport2.add(lblDifficulty, c3);

        JPanel panelSupport4 = new JPanel();
        panelSupport4.setOpaque(false);
        panelSupport4.setMinimumSize(new Dimension(500, 70));
        panelSupport4.setMaximumSize(new Dimension(500, 70));
        panelSupport4.setPreferredSize(new Dimension(500, 70));
        panelSupport4.setBorder(new EmptyBorder(0, 10, 0, 0));
        panelSupport4.setLayout(new GridBagLayout());
        GridBagConstraints c4 = new GridBagConstraints();
        if(GameData.lang.equals(GameData.Language.EN)){
            btnEasy = new JButton("Easy");
            btnMedium = new JButton("Medium");
            btnHard = new JButton("Hard");
        }
        else{
            btnEasy = new JButton("Facile");
            btnMedium = new JButton("Media");
            btnHard = new JButton("Difficile");
        }
        if(Settings.diff == Settings.Difficulty.EASY)
            btnEasy.setBorder(new LineBorder(Color.red));
        btnEasy.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/SendButton.png", 100, 50, false));
        btnEasy.setHorizontalTextPosition(JButton.CENTER);
        btnEasy.setVerticalTextPosition(JButton.CENTER);
        btnEasy.setFont(font.deriveFont(Font.PLAIN, 15));
        btnEasy.setForeground(Color.WHITE);
        btnEasy.setContentAreaFilled(false);
        btnEasy.setFocusPainted(false);
        btnEasy.setMinimumSize(new Dimension(100, 50));
        btnEasy.setPreferredSize(new Dimension(100, 50));
        btnEasy.setMaximumSize(new Dimension(100, 50));
        c4.gridx = 0; c4.gridy = 0;
        panelSupport4.add(btnEasy, c4);

        c4.gridx = 1; c4.gridy = 0;
        panelSupport4.add(Box.createRigidArea(new Dimension(20, 10)), c4);
        if(Settings.diff == Settings.Difficulty.MEDIUM)
            btnMedium.setBorder(new LineBorder(Color.red));
        btnMedium.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/SendButton.png", 100, 50, false));
        btnMedium.setHorizontalTextPosition(JButton.CENTER);
        btnMedium.setVerticalTextPosition(JButton.CENTER);
        btnMedium.setFont(font.deriveFont(Font.PLAIN, 15));
        btnMedium.setForeground(Color.WHITE);
        btnMedium.setContentAreaFilled(false);
        btnMedium.setFocusPainted(false);
        btnMedium.setMinimumSize(new Dimension(100, 50));
        btnMedium.setPreferredSize(new Dimension(100, 50));
        btnMedium.setMaximumSize(new Dimension(100, 50));
        c4.gridx = 2; c4.gridy = 0;
        panelSupport4.add(btnMedium, c4);

        c4.gridx = 3; c4.gridy = 0;
        panelSupport4.add(Box.createRigidArea(new Dimension(20, 10)), c4);
        if(Settings.diff == Settings.Difficulty.HARD)
            btnHard.setBorder(new LineBorder(Color.red));
        btnHard.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/SendButton.png", 100, 50, false));
        btnHard.setHorizontalTextPosition(JButton.CENTER);
        btnHard.setVerticalTextPosition(JButton.CENTER);
        btnHard.setFont(font.deriveFont(Font.PLAIN, 15));
        btnHard.setForeground(Color.WHITE);
        btnHard.setContentAreaFilled(false);
        btnHard.setFocusPainted(false);
        btnHard.setMinimumSize(new Dimension(100, 50));
        btnHard.setPreferredSize(new Dimension(100, 50));
        btnHard.setMaximumSize(new Dimension(100, 50));
        c4.gridx = 4; c4.gridy = 0;
        panelSupport4.add(btnHard, c4);


        c3.gridx = 0; c3.gridy = 2;
        panelSupport2.add(panelSupport4, c3);

        lblDescrizione = new JLabel();
        if(GameData.lang.equals(GameData.Language.EN)){
            if(Settings.diff == Settings.Difficulty.EASY)
                lblDescrizione.setText("<html>- Right difficulty for those who are new to the game.<br>" +
                        "- Zombies: random number of zombies between 1 and 15.<br>" +
                        "- Medikit: heals 3 lives at a time.<br>" +
                        "- Grenade: double damage.<br>" +
                        "- Hits: double damage.</html>");
            else if(Settings.diff == Settings.Difficulty.MEDIUM)
                lblDescrizione.setText("<html>- Right difficulty for those who want a more complex.<br>" +
                        "- Zombies: random number of zombies between 15 and 30.<br>" +
                        "- Medikit: heals 2 lives at a time.<br>" +
                        "- Grenade: normal damage.<br>" +
                        "- Hits: normal damage.</htmL>");
            else if(Settings.diff == Settings.Difficulty.HARD)
                lblDescrizione.setText("<html>- Right difficulty for those who want a complex challenge.<br>" +
                        "- Zombies: random number of zombies between 30 and 40.<br>" +
                        "- Medikit: heals 1 life at a time.<br>" +
                        "- Grenade: damage halved.<br>" +
                        "- Hits: damage halved.</html>");
        }
        else{
            if(Settings.diff == Settings.Difficulty.EASY)
                lblDescrizione.setText("<html>- Difficoltà giusta per chi è agli inizi col gioco.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 1 e 15.<br>" +
                        "- Medikit: cura 3 vite alla volta.<br>" +
                        "- Granata: danni raddoppiati.<br>" +
                        "- Colpi: danni raddoppiati.</html>");
            else if(Settings.diff == Settings.Difficulty.MEDIUM)
                lblDescrizione.setText("<html>- Difficoltà giusta per chi vuole una sfida più complessa.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 15 e 30.<br>" +
                        "- Medikit: cura 2 vite alla volta.<br>" +
                        "- Granata: danni normali.<br>" +
                        "- Colpi: danni normali.</html>");
            else if(Settings.diff == Settings.Difficulty.HARD)
                lblDescrizione.setText("<html>- Difficoltà giusta per chi vuole una sfida complessa.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 30 e 45.<br>" +
                        "- Medikit: cura 1 vita alla volta.<br>" +
                        "- Granata: danni dimezzati.<br>" +
                        "- Colpi: danni dimezzati.</html>");
        }
        lblDescrizione.setBorder(new EmptyBorder(10, 10, 0, 0));
        lblDescrizione.setFont(font.deriveFont(Font.PLAIN, 15));
        lblDescrizione.setForeground(Color.WHITE);
        c3.gridx = 0; c3.gridy = 3;
        panelSupport2.add(lblDescrizione, c3);

        JPanel panelSupport3 = new JPanel();
        panelSupport3.setOpaque(false);
        panelSupport3.setMaximumSize(new Dimension(500, 70));
        panelSupport3.setMinimumSize(new Dimension(500, 70));
        panelSupport3.setPreferredSize(new Dimension(500, 70));
        panelSupport3.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        if(GameData.lang.equals(GameData.Language.EN)){
            playButton = new JButton("Play");
            exitButton = new JButton("Exit");
            loadButton = new JButton("Modify");
            deleteButton = new JButton("Delete");
        }
        else{
            playButton = new JButton("Gioca");
            exitButton = new JButton("Esci");
            loadButton = new JButton("Modifica");
            deleteButton = new JButton("Elimina");
        }
        playButton.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/sendButton.png", 100, 50, false));
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setMinimumSize(new Dimension(100, 50));
        playButton.setPreferredSize(new Dimension(100, 50));
        playButton.setMaximumSize(new Dimension(100, 50));
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setFont(font.deriveFont(Font.PLAIN, 15));
        playButton.setForeground(Color.WHITE);
        loadButton.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/sendButton.png", 100, 50, false));
        loadButton.setBorderPainted(false);
        loadButton.setContentAreaFilled(false);
        loadButton.setFocusPainted(false);
        loadButton.setMinimumSize(new Dimension(100, 50));
        loadButton.setPreferredSize(new Dimension(100, 50));
        loadButton.setMaximumSize(new Dimension(100, 50));
        loadButton.setHorizontalTextPosition(JButton.CENTER);
        loadButton.setVerticalTextPosition(JButton.CENTER);
        loadButton.setFont(font.deriveFont(Font.PLAIN, 15));
        loadButton.setForeground(Color.WHITE);
        deleteButton.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/sendButton.png", 100, 50, false));
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setMinimumSize(new Dimension(100, 50));
        deleteButton.setPreferredSize(new Dimension(100, 50));
        deleteButton.setMaximumSize(new Dimension(100, 50));
        deleteButton.setHorizontalTextPosition(JButton.CENTER);
        deleteButton.setVerticalTextPosition(JButton.CENTER);
        deleteButton.setFont(font.deriveFont(Font.PLAIN, 15));
        deleteButton.setForeground(Color.WHITE);
        exitButton.setIcon(ResourcesLoader.getInstance().getImageIcon("/EditorImage/sendButton.png", 100, 50, false));
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setMinimumSize(new Dimension(100, 50));
        exitButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setMaximumSize(new Dimension(100, 50));
        exitButton.setHorizontalTextPosition(JButton.CENTER);
        exitButton.setVerticalTextPosition(JButton.CENTER);
        exitButton.setFont(font.deriveFont(Font.PLAIN, 15));
        exitButton.setForeground(Color.WHITE);
        c2.gridx = 0; c2.gridy = 0;
        panelSupport3.add(Box.createRigidArea(new Dimension(25, 10)), c2);
        c2.gridx = 1; c2.gridy = 0;
        panelSupport3.add(playButton, c2);
        c2.gridx = 2; c2.gridy = 0;
        panelSupport3.add(Box.createRigidArea(new Dimension(20, 10)), c2);
        c2.gridx = 3; c2.gridy = 0;
        panelSupport3.add(loadButton, c2);
        c2.gridx = 4; c2.gridy = 0;
        panelSupport3.add(Box.createRigidArea(new Dimension(20, 10)), c2);
        c2.gridx = 5; c2.gridy = 0;
        panelSupport3.add(deleteButton, c2);
        c2.gridx = 6; c2.gridy = 0;
        panelSupport3.add(Box.createRigidArea(new Dimension(20, 10)), c2);
        c2.gridx = 7; c2.gridy = 0;
        panelSupport3.add(exitButton, c2);

        c3.gridx = 0; c3.gridy = 4;
        panelSupport2.add(panelSupport3, c3);

        c.gridx = 1; c.gridy = 1;
        label.add(panelSupport2, c);

        pannelloPrincipale.add(label);
    }

    public void initFile() {
        File folder = new File("EditorMap");
        nameFile = folder.listFiles((dir, name) -> !name.equals(".DS_Store"));
        showFile.setText("");
        maxIndice = nameFile.length;
        if(maxIndice < 19){
            btnGiu.setEnabled(false);
            btnSu.setEnabled(false);
        }

        for (int i = indice; i < nameFile.length; i++){
            showFile.append(nameFile[i].getName().replaceFirst("[.][^.]+$", "") + "\t" + model.stampData(nameFile[i]) + "\n");
        }

    }

    public JButton getBtnSu() {
        return btnSu;
    }

    public JButton getBtnGiu() {
        return btnGiu;
    }

    public JTextField getNameMap(){
        return nameMap;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getBtnEasy() {
        return btnEasy;
    }

    public JButton getBtnMedium() {
        return btnMedium;
    }

    public JButton getBtnHard() {
        return btnHard;
    }

    public JLabel getLblDescrizione(){
        return lblDescrizione;
    }

    @Override
    public void paintComponent(Graphics g){
        //Con questo paintComponent vado a impostare un immagine casuale come sfondo
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
