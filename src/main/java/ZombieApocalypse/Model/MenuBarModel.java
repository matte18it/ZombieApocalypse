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
        if(b)
            setLabel1Empty();
        else
            setLabel2Empty();

    }

    public void useItem(boolean b) {
        Items.ItemType value;
        if(b)
            value=menuBarView.gunLable1Type();
        else
            value=menuBarView.gunLable2Type();
        switch (value){
            case MEDKIT -> medKit(b);
        }

    }

    public Items.ItemType getLabel1() {
        return menuBarView.gunLable1Type();
    }
    public Items.ItemType getLabel2() {
        return menuBarView.gunLable2Type();
    }

    public void setLabel1Empty() {
        menuBarView.setGunLable1(Items.ItemType.EMPTY);

    }

    public void setLabel2Empty() {
        menuBarView.setGunLable2(Items.ItemType.EMPTY);
    }
}
