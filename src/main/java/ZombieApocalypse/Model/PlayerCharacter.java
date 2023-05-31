package ZombieApocalypse.Model;


import ZombieApocalypse.Utility.Settings;


public class PlayerCharacter extends Character {
    //Gestisce il player e i suoi movimenti

    private boolean sound=false;
     final World world=new World();
     public World getWorld(){
         return world;
     }


    Audio audio = new Audio();

    // Metodo usato per musica in loop, riceve come argomento index da array Audio e lo passa a SetFile
    public void playMusic(int i) {

        audio.setFile(i);
        audio.play();
        audio.loop();
    }
    public void stopMusic() {
        audio.stop();
    }
    // Metodo usato per i sound effects
    public void playSE(int i) {
        audio.setFile(i);
        audio.play();
    }

    PlayerCharacter(){
        wight=Settings.CELL_SIZEX;
        height=Settings.CELL_SIZEY;
        x=50;
        y=50;
        health=6;
        super.setHitbox();
    }


    public void hit() {
        super.hit();
        if(countHit==30 || countHit==0){
            health--;
            Game.getInstance().getMenuBar().removeHeart();
        }
    }

    public void cure(){
        health++;
        Game.getInstance().getMenuBar().addHeart();
    }


    public void move() {

        if(dir==movementDirection.RIGHT && (world.isGround0(getX()+Settings.CELL_SIZEX, getY())) && !world.isEnemy(getX()+20, getY()))
            x += (10*speed);
        else if(dir==movementDirection.LEFT && (world.isGround0(getX()-Settings.CELL_SIZEX, getY())) && !world.isEnemy(getX()-20, getY()))
            x -= (10*speed);
        else if(dir==movementDirection.UP && (world.isGround0(getX(), getY()-Settings.CELL_SIZEY)) && !world.isEnemy(getX(), getY()-20) )
            y -= (10*speed);
        else if(dir==movementDirection.DOWN && (world.isGround0(getX(), getY()+Settings.CELL_SIZEY))&& !world.isEnemy(getX(), getY()+20))
            y += (10*speed);
        else
            movement=false;
        if(sound){
            playSE(0);
            sound=false;}
        else
            sound=true;
        hitBox.x=x;
        hitBox.y=y;



    }


}

