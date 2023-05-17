package ZombieApocalypse.Model;

public class Game {
    //Gestisce gli aspetti del gioco

    private final PlayerCharacter character = new PlayerCharacter();
    private static final Game instance = new Game();
    private Game() {
    }
    public static Game getInstance() {
        return instance;
    }

    public void startMovementRight() {
        character.startMovementRight();
    }
    public void startMovementUp() {
        character.startMovementUp();
    }
    public void startMovementDown() {
        character.startMovementDown();
    }
    public void startMovementLeft() {
        character.startMovementLeft();
    }

    public void stopMovement() {
        character.stopMovement();
    }

 //Da fare nel Game Loop
    public void update() {
        if(character.isMoving())
            character.move();}



    public PlayerCharacter getPlayerCharacter() {
        return character;}
    }
