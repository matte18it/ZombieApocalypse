package ZombieApocalypse.View.MenuBar;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import java.awt.*;

import static ZombieApocalypse.Utility.GameData.nick;

public class MenuBarView extends JPanel {
    Font font= ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN);

     MenuBarAnimation menuBarAnimation=new MenuBarAnimation();
     int heart;
    JLabel playerName;
    JLabel ammoLabel;
     JLabel [] healthLabel;
    JLabel pointLabel;
     JLabel timeLabel;
     JLabel gunLabel1;
    JLabel gunLabel2;
    public MenuBarView(){
        //setto il cursore personalizzato
        this.setCursor(ResourcesLoader.getInstance().getCursor("/GameGeneral/crosshair.png", this));

        //Crea tre Jpanel
        //Nel primo metto Vita e Munizioni con quattro label
        //nel secondo lo Score
        //nel terzo il tempo
        playerName=new JLabel(nick);
        playerName.setFont(font);
        playerName.setForeground(Color.WHITE);
        setMaximumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setPreferredSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setMinimumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));


        //Pannello della Salute
        healthLabel=new JLabel[Game.getInstance().getPlayerMaxLife()];
        JPanel healthAmmoPanel=new JPanel();
        healthAmmoPanel.setLayout(new GridBagLayout());
        healthAmmoPanel.setMaximumSize(new Dimension(300, Settings.MENU_BAR_HEIGHT));
        healthAmmoPanel.setMinimumSize(new Dimension(300, Settings.MENU_BAR_HEIGHT));

        GridBagConstraints c=new GridBagConstraints();
        healthAmmoPanel.setBackground(Color.BLACK);
        JLabel jLabelH;
        if(GameData.lang.equals("it"))
            jLabelH = new JLabel("Vita");
        else
            jLabelH = new JLabel("Health");
        jLabelH.setFont(font);
        jLabelH.setForeground(Color.GRAY);
        c.gridx=0;
        c.gridy=0;
        healthAmmoPanel.add(jLabelH, c);

        JPanel array=new JPanel();
        array.setLayout(new BoxLayout(array, BoxLayout.X_AXIS));
        array.setBackground(Color.BLACK);
        for(int i=0; i<Game.getInstance().getPlayerMaxLife(); i++){
            healthLabel[i]=new JLabel();
            array.add(healthLabel[i]);
        }
        c.gridx=1;
        c.gridy=0;
        c.insets=new Insets(0,5,0,0);
        healthAmmoPanel.add(array, c);


        //Pannello delle Munizioni

        JLabel jLabelA;
        if(GameData.lang.equals("it"))
            jLabelA = new JLabel("Munizioni");
        else
            jLabelA = new JLabel("Ammo");
        jLabelA.setFont(font);
        jLabelA.setForeground(Color.GRAY);
        ammoLabel=new JLabel();
        c.gridx=0;
        c.gridy=1;
        healthAmmoPanel.add(jLabelA, c);
        c.gridx=1;
        c.gridy=1;

        healthAmmoPanel.add(ammoLabel, c);
        //Pannello delle Armi primo
        JPanel gunPanel=new JPanel();
        gunPanel.setMaximumSize(new Dimension(90, Settings.MENU_BAR_HEIGHT));

        gunPanel.setBackground(Color.BLACK);
        gunPanel.setLayout(new GridBagLayout());
        JLabel jLabelG;
        if(GameData.lang.equals("it"))
            jLabelG = new JLabel("usa");
        else
            jLabelG = new JLabel("use");
        jLabelG.setFont(font);
        jLabelG.setForeground(Color.GRAY);
        JLabel jLabelW=new JLabel("W");
        jLabelW.setFont(font);
        jLabelW.setForeground(Color.WHITE);
        gunLabel1=new JLabel();
        JLabel jLabelD;
        if(GameData.lang.equals("it"))
            jLabelD = new JLabel("lascia");
        else
            jLabelD = new JLabel("drop");
        jLabelD.setFont(font);
        jLabelD.setForeground(Color.GRAY);
        JLabel jLabelS=new JLabel("S");
        jLabelS.setFont(font);
        jLabelS.setForeground(Color.WHITE);
        c.gridx=0;
        c.gridy=0;
        gunPanel.add(jLabelG, c);
        c.gridx=0;
        c.gridy=1;
        gunPanel.add(jLabelD, c);
        c.gridy=0;
        c.gridx=1;
        gunPanel.add(jLabelW, c);
        c.gridy=1;
        c.gridx=1;
        gunPanel.add(jLabelS, c);
        //Pannello delle armi 2
        gunLabel2=new JLabel();
        JPanel gunPanel1=new JPanel();
        gunPanel1.setMaximumSize(new Dimension(100, Settings.MENU_BAR_HEIGHT));
        gunPanel1.setBackground(Color.BLACK);
        gunPanel1.setLayout(new GridBagLayout());
        JLabel jLabelE=new JLabel("E");
        jLabelE.setFont(font);
        jLabelE.setForeground(Color.WHITE);
        c.gridx=0;
        c.gridy=0;
        gunPanel1.add(jLabelE, c);
        JLabel jLabelDd=new JLabel("D");
        jLabelDd.setFont(font);
        jLabelDd.setForeground(Color.WHITE);
        c.gridx=0;
        c.gridy=1;
        gunPanel1.add(jLabelDd, c);
        c.gridy=0;
        c.gridx=1;
        JLabel jLabelGg;
        if(GameData.lang.equals("it"))
            jLabelGg = new JLabel("usa");
        else
            jLabelGg = new JLabel("use");
        jLabelGg.setFont(font);
        jLabelGg.setForeground(Color.GRAY);
        gunPanel1.add(jLabelGg, c);
        c.gridy=1;
        c.gridx=1;
        JLabel jLabelz;
        if(GameData.lang.equals("it"))
            jLabelz = new JLabel("lascia");
        else
            jLabelz = new JLabel("drop");
        jLabelz.setFont(font);
        jLabelz.setForeground(Color.GRAY);
        gunPanel1.add(jLabelz, c);

        //Pannello del Punteggio
        JPanel pointPanel=new JPanel();
        pointPanel.setMaximumSize(new Dimension(300, Settings.MENU_BAR_HEIGHT));

        pointPanel.setLayout(new GridBagLayout());
        pointPanel.setBackground(Color.BLACK);
        JLabel jLabelP;
        if(GameData.lang.equals("it"))
            jLabelP = new JLabel("Punti");
        else
            jLabelP = new JLabel("Points");
        jLabelP.setFont(font);
        jLabelP.setForeground(Color.GRAY);
        pointLabel=new JLabel();
        c.insets=new Insets(0,40,0,0);
        c.gridx = 0;
        c.gridy = 0;
        pointPanel.add(jLabelP, c);
        c.gridx = 0;
        c.gridy = 1;
        pointPanel.add(pointLabel,c);
        //Pannello del tempo
        JPanel timePanel=new JPanel();
        timePanel.setMaximumSize(new Dimension(100, Settings.MENU_BAR_HEIGHT));

        timePanel.setLayout(new GridBagLayout());
        timePanel.setBackground(Color.BLACK);
        JLabel jLabelT;
        if(GameData.lang.equals("it"))
            jLabelT = new JLabel("Tempo");
        else
            jLabelT = new JLabel("Time");
        jLabelT.setFont(font);
        jLabelT.setForeground(Color.GRAY);
        timeLabel=new JLabel();
        timeLabel.setFont(font);
        c.insets=new Insets(0,40,0,0);
        c.gridx = 0;
        c.gridy = 0;
        timePanel.add(jLabelT, c);
        c.gridx = 0;
        c.gridy = 1;
        timePanel.add(timeLabel,c);
        add(playerName);
        add(healthAmmoPanel);
        add(gunPanel);
        add(gunLabel1);
        add(new JLabel("  "));
        add(gunLabel2);
        add(gunPanel1);
        add(pointPanel);
        add(timePanel);
    }
    public void addHeart(){
        healthLabel[Game.getInstance().getPlayerLife()].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.FULLHEART, 30, 30));
    }
    public void removeHeart(){
        healthLabel[Game.getInstance().getPlayerLife()].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.EMPTYHEART, 30, 30));
    }



    public void setBar() {
        heart=Game.getInstance().getPlayerLife();
        for(int i=0; i<Game.getInstance().getPlayerMaxLife(); i++){
            if(i<Game.getInstance().getPlayerLife())
            healthLabel[i].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.FULLHEART, 30, 30));
            else
                healthLabel[i].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.EMPTYHEART, 30, 30));
        }
        gunLabel1.setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.EMPTYSLOT, 55, 55));
        gunLabel2.setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.EMPTYSLOT, 55, 55));
        timeLabel.setForeground(Color.WHITE);
    }
    public  void updateTimeLable(long t){
        timeLabel.setText(String.valueOf(t));
    }
    public  void updateScoreLable(long t){
        pointLabel.setText(String.valueOf(t));
    }

}
