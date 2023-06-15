package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.MenuBar.MenuBarView;

public class MenuBarModel {
    //Gestione della MenuBar in basso durante il gioco
    MenuBarView menuBarView;
    //numero di munizioni per item
    private final int[] ammo =new int[]{4,12};
    MenuBarModel(MenuBarView m){
        this.menuBarView=m;
    }
    //Aggiornamento del tempo nella view
     public void updateTimeLable(long time) {
        long timeElapsed=System.nanoTime()-time;
        long t=timeElapsed/1000000000;
        menuBarView.update(t);
    }
    //aggiornamento del punteggo
     public void updatePoint(int val){
        menuBarView.updateScoreLable(val);
    }
//Rimozione di un cuore "pieno"
     public void removeHeart() {
            menuBarView.removeHeart();
    }
//Aggiunta di un cuore "pieno"
     public void addHeart() {
            menuBarView.addHeart();
    }
//Aggiunta di un item nella MenuBar
     public void addItem(Items.ItemType type) {
        menuBarView.add(type);
    }
    //Controlla se uno dei due slot è libero
    public boolean collect() {
        return (menuBarView.spaceToCollect());
    }
    //Gestione dell'utilizzo di un'oggetto presente in uno slot nella menuBar
     void useItem(boolean b) {
        Items.ItemType value;
        if(b)
            value=menuBarView.gunLable1Type();
        else
            value=menuBarView.gunLable2Type();
        switch (value){
            case MEDKIT -> medKit(b);
            case SPELL -> spell(b);
            case SHOTGUN -> shotGun();
            case PISTOL -> pistol();
            case GRENADE -> grenade();
        }
    }
    private void medKit(boolean b){
        if(GameData.sound)
            PlayWav.getInstance().playMedikitSound();
        int numVite=0;
        switch (Settings.diff){
            case EASY -> numVite=3;
            case MEDIUM -> numVite=2;
            case HARD -> numVite=1;
        }
        for(int i=0; i<numVite; i++){
            Game.getInstance().getPlayer().cure();
        }
        setLabelEmpty(b);
    }
    private void grenade() {Game.getInstance().setGrenade();}
    private void pistol() {
        Game.getInstance().setPistol();
    }
    private void shotGun() {
        Game.getInstance().setShotgun();
    }
    private void spell(boolean b) {
        if(GameData.sound)
            PlayWav.getInstance().playPotionSound();
        Game.getInstance().getPlayer().speedUp();
        setLabelEmpty(b);
    }
    //Ritorna cosa è presente nello slot
     public Items.ItemType getLabel1() {
        return menuBarView.gunLable1Type();
    }
     public Items.ItemType getLabel2() {
        return menuBarView.gunLable2Type();
    }
//Setta lo slot vouto
     public void setLabelEmpty(boolean b) {
        if(b)
            menuBarView.setGunLable1(Items.ItemType.EMPTY);
        else
            menuBarView.setGunLable2(Items.ItemType.EMPTY);
    }
    //Gestione dei proiettili
     public void addAmmo(Items.ItemType type) {
        switch (type){
            case AMMO0 -> menuBarView.addAmmo(ammo[0]);
            case AMMO1 -> menuBarView.addAmmo(ammo[1]);
        }
    }
     public int numBullet() {
        return  menuBarView.getAmmo();
    }

     public void removeAmmo(int i) {
        menuBarView.useAmmo(i);
    }

     public boolean collectAmmo(Items.ItemType type) {
        int t=numBullet();
        switch (type){
            case AMMO0 -> t=t+ammo[0];
            case AMMO1 -> t=t+ammo[1];
        }
         return t <= 999;
     }
}
