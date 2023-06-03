package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;
import ZombieApocalypse.View.SplashScreenView;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class SplashScreenModel {
    public static SplashScreenView view;
    public SplashScreenModel(SplashScreenView view) {
        //Creo la view
        this.view = view;
    }

    public static void checkFile() {
        File file = new File("player.txt");
        Scanner myReader = null;

        if(file.exists()){

            try { myReader = new Scanner(file); }
            catch (FileNotFoundException e) { throw new RuntimeException(e); }

            GameData.nick = myReader.nextLine();
            myReader.close();
            try { getData("https://progettouid.altervista.org/ZombieApocalypse/getData.php?nickname=" + GameData.nick); } catch (IOException e) { throw new RuntimeException(e); }
        }

    }

    private static void getData(String path) throws IOException {
        //chiamo script per fare get dei dati
        URL sript = new URL(path);
        URLConnection conn = sript.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;

        //Aggiorno i dati
        if ((inputLine = in.readLine()) != null)
            GameData.musicVolume = Integer.parseInt(inputLine);
        if ((inputLine = in.readLine()) != null)
            GameData.soundVolume = Integer.parseInt(inputLine);
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.music = false;
            else
                GameData.music = true;
        }
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.sound = false;
            else
                GameData.sound = true;
        }
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.mancino = false;
            else
                GameData.mancino = true;
        }
        if ((inputLine = in.readLine()) != null)
            GameData.skinAttiva = Integer.parseInt(inputLine);
        if ((inputLine = in.readLine()) != null){
            if(Integer.parseInt(inputLine) == 0)
                GameData.lang = GameData.Language.EN;
            else
                GameData.lang = GameData.Language.IT;
        }
        if ((inputLine = in.readLine()) != null)
            GameData.recordPunti = Integer.parseInt(inputLine);

        in.close();
    }

    public static void loadInterface() {
        if(GameData.nick != null){
            //Creo un menuView, parte interna della mia cornice
            view.bar.setIcon(ResourcesLoader.getInstance().getImageIcon("/SplashScreen/bar.png", 550, 7, false));
            GameFrame.menu = new MenuView();
            GameFrame.menuLaunch();
        }
        else{
            //Creo un loginView, parte interna della mia cornice
            view.bar.setIcon(ResourcesLoader.getInstance().getImageIcon("/SplashScreen/bar.png", 550, 7, false));
            GameFrame.panel = new LoginView();
            GameFrame.loginLaunch();
        }
    }
}
