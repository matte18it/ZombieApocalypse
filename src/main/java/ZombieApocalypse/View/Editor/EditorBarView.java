package ZombieApocalypse.View.Editor;

import ZombieApocalypse.Controller.EditorBarController;
import ZombieApocalypse.Controller.UserMapController;
import ZombieApocalypse.Model.Editor.EditorBarModel;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import java.awt.*;

public class EditorBarView extends JPanel {
    public static int bloccoAttivo = -1;
    public static ResourcesLoader loader = ResourcesLoader.getInstance();
    private EditorBarController controller;
    private GridBagConstraints c;

    //Bottoni per rappresentare i blocchi della mappa
    private JButton dirt0, dirt1, dirt2, dirt3, water0, divisorio, flower1, flower2;
    private JButton road1, road2, road3, road4, road5, road6, road7, road8, road9, road10, road11, road12, road13, road14, road15, road16, road17, road18, road19, road20, road21, road22, road23, road24, road25;
    private JButton arrow1, arrow2, lineButton;

    private JButton save, exit, reset;
    private JTextField txtName;

    private Font font;

    private EditorView editorView;
    private EditorBarModel model;

    public EditorBarView(EditorView editorView){
        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));

        //Carico il font personalizzato
        font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 15, Font.PLAIN);

        this.editorView = editorView;

        //Dimensione barra e layout
        setMaximumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.EDITOR_BAR_HEIGHT));
        setPreferredSize(new Dimension(Settings.WINDOW_SIZEX, Settings.EDITOR_BAR_HEIGHT));
        setMinimumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.EDITOR_BAR_HEIGHT));
        setBackground(new Color(130, 130, 130));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        c = new GridBagConstraints();

        initComponent();

        if(!UserMapController.nomeFile.equals("")){
            txtName.setText(UserMapController.nomeFile);
            txtName.setForeground(Color.BLACK);
            UserMapController.nomeFile = "";
        }

        //creo il model
        model = new EditorBarModel(this, editorView);

        //creo il controller
        controller = new EditorBarController(this, editorView, model);
        Page1();
        controller.addListener();
        model.clearBorder();
    }

    private void initComponent() {
        //inizializzo tutti i bottoni della barra
        arrow1 = new JButton();
        arrow2 = new JButton();
        txtName = new JTextField();
        lineButton = new JButton();
        save = new JButton();
        exit = new JButton();
        reset = new JButton();
        road1 = new JButton();
        road2 = new JButton();
        road3 = new JButton();
        road4 = new JButton();
        road5 = new JButton();
        road6 = new JButton();
        road7 = new JButton();
        road8 = new JButton();
        road9 = new JButton();
        road10 = new JButton();
        road11 = new JButton();
        road12 = new JButton();
        road13 = new JButton();
        road14 = new JButton();
        road15 = new JButton();
        road16 = new JButton();
        road17 = new JButton();
        road18 = new JButton();
        road19 = new JButton();
        road20 = new JButton();
        road21 = new JButton();
        road22 = new JButton();
        road23 = new JButton();
        road24 = new JButton();
        road25 = new JButton();
        dirt0 = new JButton();
        dirt1 = new JButton();
        dirt2 = new JButton();
        dirt3 = new JButton();
        divisorio = new JButton();
        water0 = new JButton();
        flower1 = new JButton();
        flower2 = new JButton();
    }

    public void Page1() {
        //Pagina 1 della barra, contiene i primi blocchi disegnabili
        c.gridx = 0; c.gridy = 0;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt0.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno0.png", 48, 48, false));
        dirt0.setFocusPainted(false);
        dirt0.setContentAreaFilled(false);
        dirt0.setMinimumSize(new Dimension(48, 48));
        dirt0.setPreferredSize(new Dimension(48, 48));
        dirt0.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 1;
        add(dirt0, c);

        c.gridx = 0; c.gridy = 2;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt1.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno1.png", 48, 48, false));
        dirt1.setFocusPainted(false);
        dirt1.setContentAreaFilled(false);
        dirt1.setMinimumSize(new Dimension(48, 48));
        dirt1.setPreferredSize(new Dimension(48, 48));
        dirt1.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 3;
        add(dirt1, c);

        c.gridx = 0; c.gridy = 4;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt2.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno2.png", 48, 48, false));
        dirt2.setFocusPainted(false);
        dirt2.setContentAreaFilled(false);
        dirt2.setMinimumSize(new Dimension(48, 48));
        dirt2.setPreferredSize(new Dimension(48, 48));
        dirt2.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 5;
        add(dirt2, c);

        c.gridx = 0; c.gridy = 6;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt3.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno3.png", 48, 48, false));
        dirt3.setFocusPainted(false);
        dirt3.setContentAreaFilled(false);
        dirt3.setMinimumSize(new Dimension(48, 48));
        dirt3.setPreferredSize(new Dimension(48, 48));
        dirt3.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 7;
        add(dirt3, c);

        c.gridx = 0; c.gridy = 8;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        divisorio.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Divisorio1.png", 48, 48, false));
        divisorio.setFocusPainted(false);
        divisorio.setContentAreaFilled(false);
        divisorio.setMinimumSize(new Dimension(48, 48));
        divisorio.setPreferredSize(new Dimension(48, 48));
        divisorio.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 9;
        add(divisorio, c);

        c.gridx = 0; c.gridy = 10;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        water0.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Water0.png", 48, 48, false));
        water0.setFocusPainted(false);
        water0.setContentAreaFilled(false);
        water0.setMinimumSize(new Dimension(48, 48));
        water0.setPreferredSize(new Dimension(48, 48));
        water0.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 11;
        add(water0, c);

        c.gridx = 0; c.gridy = 12;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        flower1.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Flower1.png", 48, 48, false));
        flower1.setFocusPainted(false);
        flower1.setContentAreaFilled(false);
        flower1.setMinimumSize(new Dimension(48, 48));
        flower1.setPreferredSize(new Dimension(48, 48));
        flower1.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 13;
        add(flower1, c);

        c.gridx = 0; c.gridy = 14;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        flower2.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Flower2.png", 48, 48, false));
        flower2.setFocusPainted(false);
        flower2.setContentAreaFilled(false);
        flower2.setMinimumSize(new Dimension(48, 48));
        flower2.setPreferredSize(new Dimension(48, 48));
        flower2.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 15;
        add(flower2, c);

        c.gridx = 0; c.gridy = 16;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road1.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road1.png", 48, 48, false));
        road1.setFocusPainted(false);
        road1.setContentAreaFilled(false);
        road1.setMinimumSize(new Dimension(48, 48));
        road1.setPreferredSize(new Dimension(48, 48));
        road1.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 17;
        add(road1, c);

        c.gridx = 0; c.gridy = 18;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road2.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road2.png", 48, 48, false));
        road2.setFocusPainted(false);
        road2.setContentAreaFilled(false);
        road2.setMinimumSize(new Dimension(48, 48));
        road2.setPreferredSize(new Dimension(48, 48));
        road2.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 19;
        add(road2, c);

        c.gridx = 0; c.gridy = 20;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road3.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road3.png", 48, 48, false));
        road3.setFocusPainted(false);
        road3.setContentAreaFilled(false);
        road3.setMinimumSize(new Dimension(48, 48));
        road3.setPreferredSize(new Dimension(48, 48));
        road3.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 21;
        add(road3, c);

        c.gridx = 0; c.gridy = 22;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road4.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road4.png", 48, 48, false));
        road4.setFocusPainted(false);
        road4.setContentAreaFilled(false);
        road4.setMinimumSize(new Dimension(48, 48));
        road4.setPreferredSize(new Dimension(48, 48));
        road4.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 23;
        add(road4, c);

        c.gridx = 0; c.gridy = 24;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road5.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road5.png", 48, 48, false));
        road5.setFocusPainted(false);
        road5.setContentAreaFilled(false);
        road5.setMinimumSize(new Dimension(48, 48));
        road5.setPreferredSize(new Dimension(48, 48));
        road5.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 25;
        add(road5, c);

        c.gridx = 0; c.gridy = 26;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road6.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road6.png", 48, 48, false));
        road6.setFocusPainted(false);
        road6.setContentAreaFilled(false);
        road6.setMinimumSize(new Dimension(48, 48));
        road6.setPreferredSize(new Dimension(48, 48));
        road6.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 27;
        add(road6, c);

        c.gridx = 0; c.gridy = 28;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road7.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road7.png", 48, 48, false));
        road7.setFocusPainted(false);
        road7.setContentAreaFilled(false);
        road7.setMinimumSize(new Dimension(48, 48));
        road7.setPreferredSize(new Dimension(48, 48));
        road7.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 29;
        add(road7, c);

        c.gridx = 0; c.gridy = 30;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road8.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road8.png", 48, 48, false));
        road8.setFocusPainted(false);
        road8.setContentAreaFilled(false);
        road8.setMinimumSize(new Dimension(48, 48));
        road8.setPreferredSize(new Dimension(48, 48));
        road8.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 31;
        add(road8, c);

        c.gridx = 0; c.gridy = 32;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road9.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road9.png", 48, 48, false));
        road9.setFocusPainted(false);
        road9.setContentAreaFilled(false);
        road9.setMinimumSize(new Dimension(48, 48));
        road9.setPreferredSize(new Dimension(48, 48));
        road9.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 33;
        add(road9, c);

        c.gridx = 0; c.gridy = 34;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road10.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road10.png", 48, 48, false));
        road10.setFocusPainted(false);
        road10.setContentAreaFilled(false);
        road10.setMinimumSize(new Dimension(48, 48));
        road10.setPreferredSize(new Dimension(48, 48));
        road10.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 35;
        add(road10, c);

        c.gridx = 0; c.gridy = 36;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road11.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road11.png", 48, 48, false));
        road11.setFocusPainted(false);
        road11.setContentAreaFilled(false);
        road11.setMinimumSize(new Dimension(48, 48));
        road11.setPreferredSize(new Dimension(48, 48));
        road11.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 37;
        add(road11, c);

        c.gridx = 0; c.gridy = 38;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road12.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road12.png", 48, 48, false));
        road12.setFocusPainted(false);
        road12.setContentAreaFilled(false);
        road12.setMinimumSize(new Dimension(48, 48));
        road12.setPreferredSize(new Dimension(48, 48));
        road12.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 39;
        add(road12, c);

        c.gridx = 0; c.gridy = 40;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road13.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road13.png", 48, 48, false));
        road13.setFocusPainted(false);
        road13.setContentAreaFilled(false);
        road13.setMinimumSize(new Dimension(48, 48));
        road13.setPreferredSize(new Dimension(48, 48));
        road13.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 41;
        add(road13, c);

        c.gridx = 0; c.gridy = 42;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        arrow1.setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/avantiArrow.png", 48, 48, false));
        arrow1.setFocusPainted(false);
        arrow1.setContentAreaFilled(false);
        arrow1.setBorderPainted(false);
        arrow1.setMinimumSize(new Dimension(48, 48));
        arrow1.setPreferredSize(new Dimension(48, 48));
        arrow1.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 43;
        add(arrow1, c);

        c.gridx = 0; c.gridy = 44;
        add(Box.createRigidArea(new Dimension(10, 10)), c);
    }

    public void Page2() {
        //pagina 2 dell'interfaccia, contiene altri blocchi disegnabili e i bottoni per le varie azioni sulla mappa
        c.gridx = 0; c.gridy = 0;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        arrow2.setIcon(ResourcesLoader.getInstance().getImageIcon("/SettingsImage/indietroArrow.png", 48, 48, false));
        arrow2.setFocusPainted(false);
        arrow2.setBorderPainted(false);
        arrow2.setContentAreaFilled(false);
        arrow2.setMinimumSize(new Dimension(48, 48));
        arrow2.setPreferredSize(new Dimension(48, 48));
        arrow2.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 1;
        add(arrow2, c);

        c.gridx = 0; c.gridy = 2;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road14.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road14.png", 48, 48, false));
        road14.setFocusPainted(false);
        road14.setContentAreaFilled(false);
        road14.setMinimumSize(new Dimension(48, 48));
        road14.setPreferredSize(new Dimension(48, 48));
        road14.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 3;
        add(road14, c);

        c.gridx = 0; c.gridy = 4;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road15.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road15.png", 48, 48, false));
        road15.setFocusPainted(false);
        road15.setContentAreaFilled(false);
        road15.setMinimumSize(new Dimension(48, 48));
        road15.setPreferredSize(new Dimension(48, 48));
        road15.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 4;
        add(road15, c);

        c.gridx = 0; c.gridy = 5;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road16.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road16.png", 48, 48, false));
        road16.setFocusPainted(false);
        road16.setContentAreaFilled(false);
        road16.setMinimumSize(new Dimension(48, 48));
        road16.setPreferredSize(new Dimension(48, 48));
        road16.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 6;
        add(road16, c);

        c.gridx = 0; c.gridy = 7;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road17.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road17.png", 48, 48, false));
        road17.setFocusPainted(false);
        road17.setContentAreaFilled(false);
        road17.setMinimumSize(new Dimension(48, 48));
        road17.setPreferredSize(new Dimension(48, 48));
        road17.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 8;
        add(road17, c);

        c.gridx = 0; c.gridy = 9;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road18.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road18.png", 48, 48, false));
        road18.setFocusPainted(false);
        road18.setContentAreaFilled(false);
        road18.setMinimumSize(new Dimension(48, 48));
        road18.setPreferredSize(new Dimension(48, 48));
        road18.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 10;
        add(road18, c);

        c.gridx = 0; c.gridy = 11;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road19.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road19.png", 48, 48, false));
        road19.setFocusPainted(false);
        road19.setContentAreaFilled(false);
        road19.setMinimumSize(new Dimension(48, 48));
        road19.setPreferredSize(new Dimension(48, 48));
        road19.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 12;
        add(road19, c);

        c.gridx = 0; c.gridy = 13;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road20.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road20.png", 48, 48, false));
        road20.setFocusPainted(false);
        road20.setContentAreaFilled(false);
        road20.setMinimumSize(new Dimension(48, 48));
        road20.setPreferredSize(new Dimension(48, 48));
        road20.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 14;
        add(road20, c);

        c.gridx = 0; c.gridy = 15;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road21.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road21.png", 48, 48, false));
        road21.setFocusPainted(false);
        road21.setContentAreaFilled(false);
        road21.setMinimumSize(new Dimension(48, 48));
        road21.setPreferredSize(new Dimension(48, 48));
        road21.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 16;
        add(road21, c);

        c.gridx = 0; c.gridy = 17;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road22.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road22.png", 48, 48, false));
        road22.setFocusPainted(false);
        road22.setContentAreaFilled(false);
        road22.setMinimumSize(new Dimension(48, 48));
        road22.setPreferredSize(new Dimension(48, 48));
        road22.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 18;
        add(road22, c);

        c.gridx = 0; c.gridy = 19;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road23.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road23.png", 48, 48, false));
        road23.setFocusPainted(false);
        road23.setContentAreaFilled(false);
        road23.setMinimumSize(new Dimension(48, 48));
        road23.setPreferredSize(new Dimension(48, 48));
        road23.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 20;
        add(road23, c);

        c.gridx = 0; c.gridy = 21;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road24.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road24.png", 48, 48, false));
        road24.setFocusPainted(false);
        road24.setContentAreaFilled(false);
        road24.setMinimumSize(new Dimension(48, 48));
        road24.setPreferredSize(new Dimension(48, 48));
        road24.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 22;
        add(road24, c);

        c.gridx = 0; c.gridy = 23;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        road25.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Road25.png", 48, 48, false));
        road25.setFocusPainted(false);
        road25.setContentAreaFilled(false);
        road25.setMinimumSize(new Dimension(48, 48));
        road25.setPreferredSize(new Dimension(48, 48));
        road25.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 24;
        add(road25, c);

        c.gridx = 0; c.gridy = 25;
        add(Box.createRigidArea(new Dimension(25, 10)), c);

        if(GameData.lang.equals(GameData.Language.EN) && txtName.getText().equals("")) {
            txtName.setText("Insert map name:");
            txtName.setForeground(new Color(156, 156, 156));
        }
        else if(GameData.lang.equals(GameData.Language.IT) && txtName.getText().equals("")) {
            txtName.setText("Nome mappa:");
            txtName.setForeground(new Color(156, 156, 156));
        }
        txtName.setFont(font);
        txtName.setPreferredSize(new Dimension(150, 50));
        txtName.setMaximumSize(new Dimension(150, 50));
        txtName.setMinimumSize(new Dimension(150, 50));
        c.gridx = 0; c.gridy = 26;
        add(txtName, c);

        c.gridx = 0; c.gridy = 27;
        add(Box.createRigidArea(new Dimension(5, 10)), c);

        if(GameData.lang.equals(GameData.Language.EN))
            save.setText("Save");
        else
            save.setText("Salva");
        save.setFont(font);
        save.setForeground(Color.WHITE);
        save.setIcon(loader.getImageIcon("/EditorImage/sendButton.png", 80, 40, false));
        save.setHorizontalTextPosition(JButton.CENTER);
        save.setVerticalTextPosition(JButton.CENTER);
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.setPreferredSize(new Dimension(80, 40));
        save.setMaximumSize(new Dimension(80, 40));
        save.setMinimumSize(new Dimension(80, 40));
        c.gridx = 0; c.gridy = 28;
        add(save, c);

        c.gridx = 0; c.gridy = 29;
        add(Box.createRigidArea(new Dimension(5, 10)), c);

        if(GameData.lang.equals(GameData.Language.EN))
            lineButton.setText("Line");
        else
            lineButton.setText("Linee");
        lineButton.setFont(font);
        lineButton.setForeground(Color.WHITE);
        lineButton.setIcon(loader.getImageIcon("/EditorImage/sendButton.png", 80, 40, false));
        lineButton.setHorizontalTextPosition(JButton.CENTER);
        lineButton.setVerticalTextPosition(JButton.CENTER);
        lineButton.setBorderPainted(false);
        lineButton.setFocusPainted(false);
        lineButton.setPreferredSize(new Dimension(80, 40));
        lineButton.setMaximumSize(new Dimension(80, 40));
        lineButton.setMinimumSize(new Dimension(80, 40));
        c.gridx = 0; c.gridy = 30;
        add(lineButton, c);

        c.gridx = 0; c.gridy = 31;
        add(Box.createRigidArea(new Dimension(5, 10)), c);

        if(GameData.lang.equals(GameData.Language.EN))
            reset.setText("Reset");
        else
            reset.setText("Resetta");
        reset.setFont(font);
        reset.setForeground(Color.WHITE);
        reset.setIcon(loader.getImageIcon("/EditorImage/sendButton.png", 80, 40, false));
        reset.setHorizontalTextPosition(JButton.CENTER);
        reset.setVerticalTextPosition(JButton.CENTER);
        reset.setBorderPainted(false);
        reset.setFocusPainted(false);
        reset.setPreferredSize(new Dimension(80, 40));
        reset.setMaximumSize(new Dimension(80, 40));
        reset.setMinimumSize(new Dimension(80, 40));
        c.gridx = 0; c.gridy = 32;
        add(reset, c);

        c.gridx = 0; c.gridy = 33;
        add(Box.createRigidArea(new Dimension(5, 10)), c);

        if(GameData.lang.equals(GameData.Language.EN))
            exit.setText("Exit");
        else
            exit.setText("Esci");
        exit.setFont(font);
        exit.setForeground(Color.WHITE);
        exit.setPreferredSize(new Dimension(80, 40));
        exit.setMaximumSize(new Dimension(80, 40));
        exit.setMinimumSize(new Dimension(80, 40));
        exit.setIcon(loader.getImageIcon("/EditorImage/sendButton.png", 80, 40, false));
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        c.gridx = 0; c.gridy = 34;
        add(exit, c);

        c.gridx = 0; c.gridy = 35;
        add(Box.createRigidArea(new Dimension(10, 10)), c);
    }

    public JButton getArrow1() {
        return arrow1;
    }

    public JButton getArrow2() {
        return arrow2;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getReset() {
        return reset;
    }

    public JButton getSave(){
        return save;
    }

    public JButton getLine() {
        return lineButton;
    }

    public JButton getDirt0() {
        return dirt0;
    }

    public JButton getDirt1() {
        return dirt1;
    }

    public JButton getDirt2() {
        return dirt2;
    }

    public JButton getDirt3() {
        return dirt3;
    }

    public JButton getWater0() {
        return water0;
    }

    public JButton getDivisorio() {
        return divisorio;
    }

    public JButton getFlower1() {
        return flower1;
    }

    public JButton getFlower2() {
        return flower2;
    }

    public JButton getRoad1() {
        return road1;
    }

    public JButton getRoad2() {
        return road2;
    }

    public JButton getRoad3() {
        return road3;
    }

    public JButton getRoad4() {
        return road4;
    }

    public JButton getRoad5() {
        return road5;
    }

    public JButton getRoad6() {
        return road6;
    }

    public JButton getRoad7() {
        return road7;
    }

    public JButton getRoad8() {
        return road8;
    }

    public JButton getRoad9() {
        return road9;
    }

    public JButton getRoad10() {
        return road10;
    }

    public JButton getRoad11() {
        return road11;
    }

    public JButton getRoad12() {
        return road12;
    }

    public JButton getRoad13() {
        return road13;
    }

    public JButton getRoad14() {
        return road14;
    }

    public JButton getRoad15() {
        return road15;
    }

    public JButton getRoad16() {
        return road16;
    }

    public JButton getRoad17() {
        return road17;
    }

    public JButton getRoad18() {
        return road18;
    }

    public JButton getRoad19() {
        return road19;
    }

    public JButton getRoad20() {
        return road20;
    }

    public JButton getRoad21() {
        return road21;
    }

    public JButton getRoad22() {
        return road22;
    }

    public JButton getRoad23() {
        return road23;
    }

    public JButton getRoad24() {
        return road24;
    }

    public JButton getRoad25() {
        return road25;
    }
    public JTextField gettxtName(){
        return txtName;
    }
}

