package ZombieApocalypse.View;

import ZombieApocalypse.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

public class MenuBarView extends JPanel {
    Font font;
    JLabel healthLabel;
    JLabel ammoLabel;
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
        JPanel healthAmmoPanel=new JPanel();
        healthAmmoPanel.setLayout(new GridLayout(2,2,2,2));
        healthAmmoPanel.setBackground(Color.BLACK);
        JLabel jLabelH=new JLabel("Health");
        jLabelH.setFont(font);
        jLabelH.setForeground(Color.WHITE);
        healthLabel=new JLabel();
        healthAmmoPanel.add(jLabelH);
        healthAmmoPanel.add(healthLabel);
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

    public void update() {   //Aggiornamento della vita
        try{    //non so perchè in questo metodo setText e setIcon non funzionano, ma in public MenuBarView funzionano
            Image image= ImageIO.read(getClass().getResourceAsStream("/BarraDistatoeMenù/BarraVita.png"));
            ImageIcon icon=new ImageIcon(image);
            healthLabel.setIcon(icon);
            healthLabel.setText("ciao prova ");
            healthLabel.repaint();





        } catch (IOException e){
            System.exit(1);
        }

    }
}
