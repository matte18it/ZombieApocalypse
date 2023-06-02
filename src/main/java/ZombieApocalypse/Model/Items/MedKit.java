package ZombieApocalypse.Model.Items;

import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.Settings;

public class MedKit extends Item{


    public MedKit(int x, int y, Items.ItemType e){
        super(x,y, 30,e);
        taken=true;
    }
    public boolean update() {
        if(hitBox.intersects(Game.getInstance().getPlayerCharacter().hitBox) && taken && Game.getInstance().getMenuBar().collect()){
            getView().setTaken(true);
            taken=false;
            Game.getInstance().getMenuBar().addItem(type);
        }
        return taken;



    }
}
