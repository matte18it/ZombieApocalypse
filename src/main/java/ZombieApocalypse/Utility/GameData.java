package ZombieApocalypse.Utility;

public class GameData {
    public enum Language{EN, IT};   //enumeratore per la lingua
    public static String nick;  //Variabile che contiene il nickname del player
    public static String pass;  //Variabile che contiene la password del player
    public static String version = "V1.0.0";    //Variabile per rappresentare la versione
    public static boolean music = true;     //Variabile per la musica muta
    public static boolean sound = true;     //variabile per i suoni muti
    public static int punti = 0;            //variabile per i punti
    public static int recordPunti = 0;      //Record punti del player
    public static int setBg;    //Variabile che mi serve per settare lo sfondo della schermata di login e menu
    public static int musicVolume = 0;    //variabile per volume musica
    public static int soundVolume = 0;    //variabile per volume suoni
    public static int skinAttiva = 1;       //variabile per gestione skin player
    public static Language lang = Language.EN;  //variabile per la lingua
    public static boolean mancino = false;      //variabile per mancino

}
