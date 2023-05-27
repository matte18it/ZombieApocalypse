package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.SplashScreenModel;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.SplashScreenView;

public class SplashScreenController {
    private final SplashScreenModel login;
    private final SplashScreenView view;

    public SplashScreenController(SplashScreenModel login, SplashScreenView view){
        //Creo il model e la view
        this.login = login;
        this.view = view;
    }

    public void addListener() {
    }
}
