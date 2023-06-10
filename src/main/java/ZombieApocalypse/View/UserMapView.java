package ZombieApocalypse.View;

import ZombieApocalypse.Controller.UserMapController;
import ZombieApocalypse.Model.UserMapModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
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
    private JButton btnSu, btnGiu, exitButton, loadButton, deleteButton, playButton;
    private JLabel label, titolo, labelTitoloTxtArea, lblName, lblDifficulty;
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
        pannelloPrincipale = new JPanel();
        pannelloPrincipale.setOpaque(false);
        pannelloPrincipale.setMaximumSize(new Dimension(996, 500));

        label = new JLabel();
        label.setLayout(new GridBagLayout());
        label.setIcon(ResourcesLoader.getInstance().getImageIcon("/UserMap/SfondoUserMap.png", 996, 500, false));
        GridBagConstraints c = new GridBagConstraints();

        if(GameData.lang.equals(GameData.Language.EN))
            labelTitoloTxtArea = new JLabel("Maps created by you:");
        else
            labelTitoloTxtArea = new JLabel("Mappe create da te:");
        labelTitoloTxtArea.setBorder(new EmptyBorder(10, 0, 0, 0));
        labelTitoloTxtArea.setFont(font.deriveFont(Font.PLAIN, 28));
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

        if(GameData.lang.equals(GameData.Language.EN))
            lblName = new JLabel("Insert map name: ");
        else
            lblName = new JLabel("Inserisci il nome della mappa: ");
        lblName.setBorder(new EmptyBorder(10, 10, 0, 0));
        lblName.setFont(font.deriveFont(Font.PLAIN, 25));
        lblName.setForeground(Color.WHITE);
        c.gridx = 1; c.gridy = 1; c.anchor = GridBagConstraints.NORTHEAST;
        label.add(lblName, c);

        nameMap = new JTextField();
        JLabel label1 = new JLabel(ResourcesLoader.getInstance().getImageIcon("/Login&Menu/txtGUI.png", 280, 45, false));
        label1.setLayout(new BorderLayout());
        label1.add(nameMap);
        nameMap.setOpaque(false);
        nameMap.setBorder(BorderFactory.createCompoundBorder(nameMap.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        nameMap.setBackground(new Color(0, 0, 0 ,0));
        nameMap.setFont(font.deriveFont(Font.PLAIN, 18));
        nameMap.setMinimumSize(new Dimension(280, 45));
        nameMap.setMaximumSize(new Dimension(280, 45));
        nameMap.setForeground(Color.WHITE);
        nameMap.setCaretColor(Color.RED);
        c.gridx = 2; c.gridy = 1;
        label.add(label1, c);

        pannelloPrincipale.add(label);
    }

    public void initFile() {
        File folder = new File("EditorMap");
        nameFile = folder.listFiles((dir, name) -> !name.equals(".DS_Store"));
        showFile.setText("");
        maxIndice = nameFile.length;

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
