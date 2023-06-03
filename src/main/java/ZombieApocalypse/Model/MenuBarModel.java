package ZombieApocalypse.Model;

import ZombieApocalypse.Model.Items.Items;
import ZombieApocalypse.View.MenuBar.MenuBarAnimation;
import ZombieApocalypse.View.MenuBar.MenuBarView;

public class MenuBarModel {
    MenuBarView menuBarView;
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
        for(int i=0; i<2; i++){
        Game.getInstance().getPlayerCharacter().cure();}
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
        setLabelEmpty(b);   //Da implementare la logica
    }

    private void spell(boolean b) {
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


    public void setGrenadeEmpty() {
        if(getLabel1()== Items.ItemType.GRENADE)
            setLabelEmpty(true);
        else
            setLabelEmpty(false);
    }
}
