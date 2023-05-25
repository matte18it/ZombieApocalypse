package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;
import ZombieApocalypse.View.MenuBarView;

import java.awt.*;

public class EnemyCharacter extends Character{
    EnemyCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        x=500;
        y=500;
        health=6;
        hitBox= new Rectangle(x, y, Settings.CELL_SIZEX, Settings.CELL_SIZEY);
    }
    boolean hitted=false;


    public void stopHit(){
        hitted=false;
    }
    public boolean isHitted(){
        return hitted;
    }
    public int countHit;
    public void hit(){
        if(countHit==30 || countHit==0){
            countHit=0;
            hitted=true;
            health--;
        }
}}
