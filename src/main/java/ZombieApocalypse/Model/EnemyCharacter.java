package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.Settings;

public class EnemyCharacter extends Character{
    EnemyCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        x=500;
        y=500;
        health=6;
        super.setHitbox();
    }



    public void hit(){
        super.hit();
        if(countHit==30 || countHit==0){

            health=health-Game.getInstance().getPistolModel().damage;
        }
    }}