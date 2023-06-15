package ZombieApocalypse.View.MenuBar;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

import javax.swing.*;
import java.awt.*;

import static ZombieApocalypse.Utility.GameData.nick;

public class MenuBarView extends JPanel {
    private final MenuBarAnimation menuBarAnimation=new MenuBarAnimation();
    private final JLabel ammoLabel; //munizioni
    private final JLabel addAmmoLabel;  //numero di munizioni aggiunte
    private Items.ItemType typeGunLabel1=Items.ItemType.EMPTY; //valore presente nello slot di sinistra
    private Items.ItemType typeGunLabel2=Items.ItemType.EMPTY; //valore presente nello slot di destra
     private final JLabel [] healthLabel;  //array di cuori
    private final JLabel pointLabel;  //punteggio
     private final JLabel timeLabel; //tempo
     private final JLabel gunLabel1; //immagine presente nello slot di sinistra
    private final JLabel gunLabel2; //immagine presente nello slot di sinistra
    private boolean addAmmo=false; //gestione dell'animazione di aggiunta munizioni
    private int count=0; //gestione dell'animazione di aggiunta munizioni
    public MenuBarView(){  //Creazione della MenuBar
        //setto il cursore personalizzato
        this.setCursor(ResourcesLoader.getInstance().getCursor("/GameGeneral/crosshair.png", this));
        //Crea tre Jpanel
        //Nel primo metto Vita e Munizioni con quattro label
        //nel secondo lo Score
        //nel terzo il tempo
        JLabel playerName = new JLabel(nick);
        //Gestione della barra in basso presente in game
        Font font = ResourcesLoader.getInstance().getFont("/Font/PixelFont.otf", 20, Font.PLAIN);
        playerName.setFont(font);
        playerName.setForeground(Color.WHITE);
        setMaximumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setPreferredSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setMinimumSize(new Dimension(Settings.WINDOW_SIZEX, Settings.MENU_BAR_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        //Pannello della Salute
        healthLabel=new JLabel[Game.getInstance().getPlayer().maxHealth];
        JPanel healthAmmoPanel=new JPanel();
        healthAmmoPanel.setLayout(new GridBagLayout());
        healthAmmoPanel.setMaximumSize(new Dimension(300, Settings.MENU_BAR_HEIGHT));
        healthAmmoPanel.setMinimumSize(new Dimension(300, Settings.MENU_BAR_HEIGHT));
        GridBagConstraints c=new GridBagConstraints();
        healthAmmoPanel.setBackground(Color.BLACK);
        JLabel jLabelH;
        if(GameData.lang== GameData.Language.IT)
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
        for(int i=0; i<Game.getInstance().getPlayer().maxHealth; i++){
            healthLabel[i]=new JLabel();
            array.add(healthLabel[i]);
        }
        c.gridx=1;
        c.gridy=0;
        c.insets=new Insets(0,5,0,0);
        healthAmmoPanel.add(array, c);


        //Pannello delle Munizioni

        JLabel jLabelA;
        if(GameData.lang== GameData.Language.IT)
            jLabelA = new JLabel("Colpi");
        else
            jLabelA = new JLabel("Ammo");
        jLabelA.setFont(font);
        jLabelA.setForeground(Color.GRAY);
        addAmmoLabel=new JLabel("");
        addAmmoLabel.setForeground(Color.WHITE);
        addAmmoLabel.setFont(font);
        ammoLabel=new JLabel("45");
        ammoLabel.setForeground(Color.WHITE);
        ammoLabel.setFont(font);
        c.gridx=0;
        c.gridy=1;

        healthAmmoPanel.add(jLabelA, c);
        c.gridx=1;
        c.gridy=1;
        c.fill=GridBagConstraints.HORIZONTAL;

        healthAmmoPanel.add(ammoLabel, c);
        c.gridx=1;
        c.gridy=1;
        c.fill=GridBagConstraints.LINE_END;

        healthAmmoPanel.add(addAmmoLabel, c);

        //Pannello delle Armi primo
        JPanel gunPanel=new JPanel();
        if(GameData.lang== GameData.Language.EN)
            gunPanel.setMaximumSize(new Dimension(130, Settings.MENU_BAR_HEIGHT));
        else
            gunPanel.setMaximumSize(new Dimension(160, Settings.MENU_BAR_HEIGHT));

        gunPanel.setBackground(Color.BLACK);
        gunPanel.setLayout(new GridBagLayout());
        JLabel jLabelG;
        if(GameData.lang== GameData.Language.IT)
            jLabelG = new JLabel("usa");
        else
            jLabelG = new JLabel("use");
        jLabelG.setFont(font);
        jLabelG.setForeground(Color.GRAY);
        JLabel jLabelW;
            if(GameData.mancino)
                jLabelW=new JLabel("U");
            else
                jLabelW=new JLabel("Q");
        jLabelW.setFont(font);
        jLabelW.setForeground(Color.WHITE);
        gunLabel1=new JLabel();
        JLabel jLabelD;
        if(GameData.lang== GameData.Language.IT)
            jLabelD = new JLabel("lascia");
        else
            jLabelD = new JLabel("drop");
        jLabelD.setFont(font);
        jLabelD.setForeground(Color.GRAY);
        JLabel jLabelS;
        if(GameData.lang== GameData.Language.IT)
            jLabelS=new JLabel("Spazio");
        else
            jLabelS=new JLabel("Space");
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
        c.anchor=GridBagConstraints.LINE_END;
        gunPanel.add(jLabelW, c);
        c.gridy=1;
        c.gridx=1;
        gunPanel.add(jLabelS, c);
        //Pannello delle armi 2
        gunLabel2=new JLabel();
        JPanel gunPanel1=new JPanel();
        if(GameData.lang== GameData.Language.EN)
            gunPanel1.setMaximumSize(new Dimension(130, Settings.MENU_BAR_HEIGHT));
        else
            gunPanel1.setMaximumSize(new Dimension(160, Settings.MENU_BAR_HEIGHT));
       gunPanel1.setBackground(Color.BLACK);
        gunPanel1.setLayout(new GridBagLayout());
        JLabel jLabelE;
        if(GameData.mancino)
            jLabelE=new JLabel("O");
        else
            jLabelE=new JLabel("E");
        jLabelE.setFont(font);
        jLabelE.setForeground(Color.WHITE);
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.LINE_END;
        gunPanel1.add(jLabelE, c);

        c.gridx=0;
        c.gridy=1;
        JLabel jLabelZ;
        if(GameData.lang== GameData.Language.IT)
            jLabelZ=new JLabel("Spazio");
        else
            jLabelZ=new JLabel("Space");
        jLabelZ.setFont(font);
        jLabelZ.setForeground(Color.WHITE);
        gunPanel1.add(jLabelZ, c);
        c.gridy=0;
        c.gridx=1;
        JLabel jLabelGg;
        if(GameData.lang== GameData.Language.IT)
            jLabelGg = new JLabel("usa");
        else
            jLabelGg = new JLabel("use");
        jLabelGg.setFont(font);
        jLabelGg.setForeground(Color.GRAY);
        gunPanel1.add(jLabelGg, c);
        c.gridy=1;
        c.gridx=1;
        JLabel jLabelz;
        if(GameData.lang== GameData.Language.IT)
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
        if(GameData.lang== GameData.Language.IT)
            jLabelP = new JLabel("Punti");
        else
            jLabelP = new JLabel("Points");
        jLabelP.setFont(font);
        jLabelP.setForeground(Color.GRAY);
        pointLabel=new JLabel(String.valueOf(GameData.punti));
        pointLabel.setFont(font);
        pointLabel.setForeground(Color.WHITE);
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
        if(GameData.lang== GameData.Language.IT)
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
        c.insets=new Insets(0,60,0,0);
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
    //aggiunta e rimozione di un cuore
    public void addHeart(){
        healthLabel[Game.getInstance().getPlayer().getHealth()-1].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.FULLHEART, 30, 25));
    }
    public void removeHeart(){
        healthLabel[Game.getInstance().getPlayer().getHealth()].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.EMPTYHEART, 30, 25));
    }
    public void setBar() { //set cuori e i due slot vuoti
        for(int i=0; i<Game.getInstance().getPlayer().maxHealth; i++){
            if(i<Game.getInstance().getPlayer().getHealth())
            healthLabel[i].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.FULLHEART, 30, 25));
            else
                healthLabel[i].setIcon(menuBarAnimation.setIcon(MenuBarAnimation.Icon.EMPTYHEART, 30, 25));
        }
        gunLabel1.setIcon(menuBarAnimation.setIcon(Items.ItemType.EMPTY, 65, 55));
        gunLabel2.setIcon(menuBarAnimation.setIcon(Items.ItemType.EMPTY, 65, 55));
        timeLabel.setForeground(Color.WHITE);
    }
    public  void update(long t){ //update del tempo e dell'aggiunta di munizioni
        if(addAmmo){
        if(count!=2)
            count++;
        if(count==2){
            count=0;
            addAmmo=false;
            addAmmoLabel.setText("");
        }}

        timeLabel.setText(String.valueOf(t));
    }
    //update del punteggio
    public  void updateScoreLable(long t){
        pointLabel.setText(String.valueOf(t));
    }
//verifica se è possibile raccogliere l'item
    public boolean spaceToCollect() {
        return typeGunLabel1==Items.ItemType.EMPTY || typeGunLabel2==Items.ItemType.EMPTY;
    }
//aggiunge l'item ad uno slot libero
    public void add(Items.ItemType i) {
        if(typeGunLabel1==Items.ItemType.EMPTY){
            gunLabel1.setIcon(menuBarAnimation.setIcon(i, 65, 55));
            typeGunLabel1=i; return;}
        if(typeGunLabel2==Items.ItemType.EMPTY){
            gunLabel2.setIcon(menuBarAnimation.setIcon(i, 65, 55));
        typeGunLabel2=i;}
    }
    //tipi presenti negli slot
    public Items.ItemType gunLable1Type() {
        return typeGunLabel1;
    }

    public Items.ItemType gunLable2Type() {
        return typeGunLabel2;
    }

    public void setGunLable1(Items.ItemType itemType) {
        typeGunLabel1=itemType;
        gunLabel1.setIcon(menuBarAnimation.setIcon(typeGunLabel1, 65, 55));
    }
    public void setGunLable2(Items.ItemType itemType) {
        typeGunLabel2=itemType;
        gunLabel2.setIcon(menuBarAnimation.setIcon(typeGunLabel2, 65, 55));
    }
    //Gestione delle munizioni
    public int getAmmo(){
        return Integer.parseInt(ammoLabel.getText());
    }
    public void addAmmo(int i) {
        if(GameData.sound)
            PlayWav.getInstance().playAmmoSound();

        int t= Integer.parseInt(ammoLabel.getText());
        t=t+i;
        ammoLabel.setText(String.valueOf(t));
        addAmmoLabel.setText("+" + i);
        addAmmo=true;
        count=0;
    }
    public void useAmmo(int i) {
        int t= Integer.parseInt(ammoLabel.getText());
        t=t-i;
        ammoLabel.setText(String.valueOf(t));
    }
}
