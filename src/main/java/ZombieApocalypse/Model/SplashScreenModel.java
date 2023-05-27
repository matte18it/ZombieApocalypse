package ZombieApocalypse.Model;

import ZombieApocalypse.ResourcesLoader;
import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.View.GameFrame;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.MenuView;
import ZombieApocalypse.View.SplashScreenView;

import java.io.File;
import java.io.FileNotFoundException;
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
        }

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
