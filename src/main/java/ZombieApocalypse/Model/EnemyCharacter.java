package ZombieApocalypse.Model;

import ZombieApocalypse.Settings;

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



}
