package ZombieApocalypse.Model;

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
}
