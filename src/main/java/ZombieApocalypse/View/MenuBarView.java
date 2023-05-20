package ZombieApocalypse.View;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

public class MenuBarView extends JPanel {
    Font font;
    JLabel ammoLabel;
    JLabel [] healthLabel;
    JLabel pointLabel;
    JLabel timeLabel;
    public MenuBarView(){
        //Crea tre Jpanel
        //Nel primo metto Vita e Munizioni con quattro label
        //nel secondo lo Score
        //nel terzo il tempo
        loadFont();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));


        //Pannello della Salute
        healthLabel=new JLabel[10];
        JPanel healthAmmoPanel=new JPanel();
        healthAmmoPanel.setLayout(new GridLayout(2,2,1,1));
        healthAmmoPanel.setBackground(Color.BLACK);
        JLabel jLabelH=new JLabel("Health");
        jLabelH.setFont(font);
        jLabelH.setForeground(Color.WHITE);

        healthAmmoPanel.add(jLabelH);

        JPanel array=new JPanel();
        array.setLayout(new BoxLayout(array, BoxLayout.X_AXIS));
        array.setBackground(Color.BLACK);
        for(int i=0; i<6; i++){
            healthLabel[i]=new JLabel();
            array.add(healthLabel[i]);
        }
        healthAmmoPanel.add(array);

        //Pannello delle Munizioni

        JLabel jLabelA=new JLabel("Ammo");
        jLabelA.setFont(font);
        jLabelA.setForeground(Color.WHITE);
        ammoLabel=new JLabel();
        healthAmmoPanel.add(jLabelA);
        healthAmmoPanel.add(ammoLabel);
        //Pannello del Punteggio
        JPanel pointPanel=new JPanel();
        pointPanel.setLayout(new GridLayout(2,1,2,2));
        pointPanel.setBackground(Color.BLACK);
        JLabel jLabelP=new JLabel("Points");
        jLabelP.setFont(font);
        jLabelP.setForeground(Color.WHITE);
        pointLabel=new JLabel();
        pointPanel.add(jLabelP);
        pointPanel.add(pointLabel);
        //Pannello del tempo
        JPanel timePanel=new JPanel();
        timePanel.setLayout(new GridLayout(2,1,2,2));
        timePanel.setBackground(Color.BLACK);
        JLabel jLabelT=new JLabel("Time");
        jLabelT.setFont(font);
        jLabelT.setForeground(Color.WHITE);
        timeLabel=new JLabel();
        timePanel.add(jLabelT);
        timePanel.add(timeLabel);


        JPanel spazioPanel=new JPanel();
        spazioPanel.setBackground(Color.BLACK);

        add(healthAmmoPanel);

        add(pointPanel);
        add(timePanel);
        add(spazioPanel);
        add(spazioPanel);
        add(spazioPanel);





    }
    private Font loadFont(){
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("/PixelFont.otf"))).deriveFont(Font.PLAIN,20);


        }catch (IOException | FontFormatException e){
            e.printStackTrace();}
        return font;
    }

    public void update()  {
        ImageIcon logo=null;

        logo=new ImageIcon(getClass().getResource("/BarraDistatoeMenù/BarraVita.png"));
        Image im=logo.getImage();
        Image logoS=im.getScaledInstance(30,25,Image.SCALE_SMOOTH);
        logo=new ImageIcon(logoS);

        for(int i=0; i<6; i++){
            healthLabel[i].setIcon(logo);

        }






    }
}
