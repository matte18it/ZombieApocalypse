package ZombieApocalypse.Model;

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
            GameFrame.menu = new MenuView();
            view.bar.setValue(100);
            GameFrame.menuLaunch();
        }
        else{
            //Creo un loginView, parte interna della mia cornice
            GameFrame.panel = new LoginView();
            view.bar.setValue(100);
            GameFrame.loginLaunch();
        }
    }
}
