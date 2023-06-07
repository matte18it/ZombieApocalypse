package ZombieApocalypse.View.Editor;

import ZombieApocalypse.Controller.EditorBarController;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditorBarView extends JPanel {
    public static int bloccoAttivo = 1;
    private EditorBarController controller;

    //Bottoni per rappresentare i blocchi della mappa
    private JButton dirt0, dirt1, dirt2, dirt3, water0, water1, water2, divisorio;

    public EditorBarView(){
        //setto il cursore personalizzato
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ResourcesLoader.getInstance().getBufferedImage("/GameGeneral/crosshair.png", 32, 32, false), new Point(20, 20), "Cursor"));

        //Dimensione barra e layout
        setMaximumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.EDITOR_BAR_HEIGHT));
        setPreferredSize(new Dimension(Settings.WINDOW_SIZEX, Settings.EDITOR_BAR_HEIGHT));
        setMinimumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.EDITOR_BAR_HEIGHT));
        setBackground(new Color(130, 130, 130));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        //creo il controller
        controller = new EditorBarController(this);
        initComponent(c);
    }

    private void initComponent(GridBagConstraints c) {
        dirt0 = new JButton();
        dirt0.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno/Terreno0.png", 48, 48, false));
        dirt0.setFocusPainted(false);
        dirt0.setContentAreaFilled(false);
        dirt0.setMinimumSize(new Dimension(48, 48));
        dirt0.setPreferredSize(new Dimension(48, 48));
        dirt0.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 1;
        add(dirt0, c);

        c.gridx = 0; c.gridy = 2;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt1 = new JButton();
        dirt1.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno/Terreno1.png", 48, 48, false));
        dirt1.setFocusPainted(false);
        dirt1.setContentAreaFilled(false);
        dirt1.setMinimumSize(new Dimension(48, 48));
        dirt1.setPreferredSize(new Dimension(48, 48));
        dirt1.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 3;
        add(dirt1, c);

        c.gridx = 0; c.gridy = 4;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt2 = new JButton();
        dirt2.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno/Terreno2.png", 48, 48, false));
        dirt2.setFocusPainted(false);
        dirt2.setContentAreaFilled(false);
        dirt2.setMinimumSize(new Dimension(48, 48));
        dirt2.setPreferredSize(new Dimension(48, 48));
        dirt2.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 5;
        add(dirt2, c);

        c.gridx = 0; c.gridy = 6;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        dirt3 = new JButton();
        dirt3.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Terreno/Terreno3.png", 48, 48, false));
        dirt3.setFocusPainted(false);
        dirt3.setContentAreaFilled(false);
        dirt3.setMinimumSize(new Dimension(48, 48));
        dirt3.setPreferredSize(new Dimension(48, 48));
        dirt3.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 7;
        add(dirt3, c);

        c.gridx = 0; c.gridy = 8;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        divisorio = new JButton();
        divisorio.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/DivisorioAcquaTerra/Divisorio1.png", 48, 48, false));
        divisorio.setFocusPainted(false);
        divisorio.setContentAreaFilled(false);
        divisorio.setMinimumSize(new Dimension(48, 48));
        divisorio.setPreferredSize(new Dimension(48, 48));
        divisorio.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 9;
        add(divisorio, c);

        c.gridx = 0; c.gridy = 10;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        water0 = new JButton();
        water0.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Water/Water0.png", 48, 48, false));
        water0.setFocusPainted(false);
        water0.setContentAreaFilled(false);
        water0.setMinimumSize(new Dimension(48, 48));
        water0.setPreferredSize(new Dimension(48, 48));
        water0.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 11;
        add(water0, c);

        c.gridx = 0; c.gridy = 12;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        water1 = new JButton();
        water1.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Water/Water1.png", 48, 48, false));
        water1.setFocusPainted(false);
        water1.setContentAreaFilled(false);
        water1.setMinimumSize(new Dimension(48, 48));
        water1.setPreferredSize(new Dimension(48, 48));
        water1.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 13;
        add(water1, c);

        c.gridx = 0; c.gridy = 14;
        add(Box.createRigidArea(new Dimension(10, 10)), c);

        water2 = new JButton();
        water2.setIcon(ResourcesLoader.getInstance().getImageIcon("/AmbienteDiGioco/Water/Water2.png", 48, 48, false));
        water2.setFocusPainted(false);
        water2.setContentAreaFilled(false);
        water2.setMinimumSize(new Dimension(48, 48));
        water2.setPreferredSize(new Dimension(48, 48));
        water2.setMaximumSize(new Dimension(48, 48));
        c.gridx = 0; c.gridy = 15;
        add(water2, c);
    }
}
