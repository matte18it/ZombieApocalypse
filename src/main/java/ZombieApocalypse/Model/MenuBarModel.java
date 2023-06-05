package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.PlayWav;
import ZombieApocalypse.View.MenuBar.MenuBarView;

public class MenuBarModel {
    MenuBarView menuBarView;
    private  int ammo[]=new int[]{4,12};
    MenuBarModel(MenuBarView m){
        this.menuBarView=m;
    }
    public void updateTimeLable(long time) {
        long timeElapsed=System.nanoTime()-time;
        long t=timeElapsed/1000000000;
        menuBarView.updateTimeLable(t);
    }

    public void removeHeart() {
        if(Game.getInstance().getPlayerLife()>0)
            menuBarView.removeHeart();
    }

    public void addHeart() {
        if(Game.getInstance().getPlayerLife()<Game.getInstance().getPlayerMaxLife()){

            menuBarView.addHeart();
    }}

    public void addItem(Items.ItemType type) {
        menuBarView.add(type);

    }

    public boolean collect() {
        return (menuBarView.spaceToCollect());
    }
    private void medKit(boolean b){
        if(GameData.sound) {
            PlayWav.getInstance().loadSound("/Audio/MedikitSound.wav");
            PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
            PlayWav.getInstance().playSound();
        }

        for(int i=0; i<2; i++){
            Game.getInstance().getPlayerCharacter().cure();
        }

        setLabelEmpty(b);
    }

    public void useItem(boolean b) {
        Items.ItemType value;
        if(b)
            value=menuBarView.gunLable1Type();
        else
            value=menuBarView.gunLable2Type();
        switch (value){
            case MEDKIT -> medKit(b);
            case SPELL -> spell(b);
            case RADIO -> radio(b);
            case SHOTGUN -> shotGun(b);
            case PISTOL -> pistol(b);
            case GRENADE -> grenade(b);
        }

    }

    private void grenade(boolean b) {
        Game.getInstance().setGrenade();
    }

    private void pistol(boolean b) {
        Game.getInstance().setPistol();
    }

    private void shotGun(boolean b) {
        Game.getInstance().setShotgun();
    }

    private void radio(boolean b) {
        if(GameData.sound) {
            PlayWav.getInstance().loadSound("/Audio/WinSound.wav");
            PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
            PlayWav.getInstance().playSound();
        }

        setLabelEmpty(b);   //Da implementare la logica
    }

    private void spell(boolean b) {
        if(GameData.sound) {
            PlayWav.getInstance().loadSound("/Audio/PotionDrink.wav");
            PlayWav.getInstance().setVolumeSound(GameData.soundVolume);
            PlayWav.getInstance().playSound();
        }

        Game.getInstance().speedUpPlayer();
        setLabelEmpty(b);
    }

    public Items.ItemType getLabel1() {
        return menuBarView.gunLable1Type();
    }
    public Items.ItemType getLabel2() {
        return menuBarView.gunLable2Type();
    }

    public void setLabelEmpty(boolean b) {
        if(b)
            menuBarView.setGunLable1(Items.ItemType.EMPTY);
        else
            menuBarView.setGunLable2(Items.ItemType.EMPTY);


    }


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
        if(t>99)
            return false;
        return true;
    }
}
