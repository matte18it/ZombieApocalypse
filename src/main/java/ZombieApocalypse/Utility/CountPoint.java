package ZombieApocalypse.Utility;

import ZombieApocalypse.Model.Enemy.Enemies;
import ZombieApocalypse.Model.Game;

public class CountPoint {
    //classe singleton per gestione dei punti
    public static CountPoint setPoint = null;
    private CountPoint(){}

    public static CountPoint getInstance(){
        if(setPoint == null)
            setPoint = new CountPoint();

        return setPoint;
    }

    public void setPoint(Enemies.EnemiesType b){
        //per l'assegnazione dei punti tengo conto di tre parametri: difficoltà di gioco, tipo di nemico ucciso e tipo di arma con cui quel nemico è stato ucciso.
        int base = 0;
        if(Settings.diff == Settings.Difficulty.EASY){
            //Aumento in base alla difficoltà
            base += 1;

            //Aumento in base al tipo di nemico
            if(b.equals(Enemies.EnemiesType.SKINNYZOMBIE)){
                base += 1;
            }
            else if(b.equals(Enemies.EnemiesType.KIDZOMBIE)){
                base += 5;
            }
            else if(b.equals(Enemies.EnemiesType.TURRETZOMBIE)){
                base += 3;
            }
            else if(b.equals(Enemies.EnemiesType.FATZOMBIE)){
                base += 6;
            }
            else if(b.equals(Enemies.EnemiesType.BANDIT)){
                base += 4;
            }
            else if(b.equals(Enemies.EnemiesType.BOMBBANDIT)){
                base += 2;
            }

            //Aumento in base alla vita
            if(Game.getInstance().hasShotgun){
                base += 1;
            }
            else if(Game.getInstance().hasPistol){
                base += 2;
            }
            else if(Game.getInstance().hasKnife || Game.getInstance().hasGrenade){
                base += 3;
            }

        }
        else if(Settings.diff == Settings.Difficulty.MEDIUM){
            //Aumento in base alla difficoltà
            base += 3;

            //Aumento in base al tipo di zombie ucciso
            if(b.equals(Enemies.EnemiesType.SKINNYZOMBIE))
                base += 6;
            else if(b.equals(Enemies.EnemiesType.KIDZOMBIE))
                base += 10;
            else if(b.equals(Enemies.EnemiesType.TURRETZOMBIE))
                base += 8;
            else if(b.equals(Enemies.EnemiesType.FATZOMBIE))
                base += 11;
            else if(b.equals(Enemies.EnemiesType.BANDIT))
                base += 9;
            else if(b.equals(Enemies.EnemiesType.BOMBBANDIT))
                base += 7;

            //Aumento in base alla vita
            if(Game.getInstance().hasShotgun){
                base += 4;
            }
            else if(Game.getInstance().hasPistol){
                base += 5;
            }
            else if(Game.getInstance().hasKnife || Game.getInstance().hasGrenade){
                base += 6;
            }

        }
        else if(Settings.diff == Settings.Difficulty.HARD){
            //Aumento in base alla difficoltà
            base += 5;

            //Aumento in base al tipo di zombie ucciso
            if(b.equals(Enemies.EnemiesType.SKINNYZOMBIE))
                base += 10;
            else if(b.equals(Enemies.EnemiesType.KIDZOMBIE))
                base += 14;
            else if(b.equals(Enemies.EnemiesType.TURRETZOMBIE))
                base += 12;
            else if(b.equals(Enemies.EnemiesType.FATZOMBIE))
                base += 15;
            else if(b.equals(Enemies.EnemiesType.BANDIT))
                base += 13;
            else if(b.equals(Enemies.EnemiesType.BOMBBANDIT))
                base += 11;

            //Aumento in base alla vita
            if(Game.getInstance().hasShotgun){
                base += 7;
            }
            else if(Game.getInstance().hasPistol){
                base += 8;
            }
            else if(Game.getInstance().hasKnife || Game.getInstance().hasGrenade){
                base += 10;
            }

        }
        GameData.punti += base;
        Game.getInstance().updateBarPoint(GameData.punti);
    }

    public void setPointBoss(){
        //assegno i punti per aver sconfitto il boss
        GameData.punti += 200;
        Game.getInstance().updateBarPoint(GameData.punti);
    }

    public void malusPoint(){
        //assegno un malus se l'utente perde o torna al menu senza aver completato la campagna
        if(GameData.punti >= 50)
            GameData.punti -= 50;
        else
            GameData.punti -= GameData.punti;
    }
}
