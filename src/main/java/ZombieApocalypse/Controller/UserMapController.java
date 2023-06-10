package ZombieApocalypse.Controller;

import ZombieApocalypse.Model.LoginModel;
import ZombieApocalypse.Model.UserMapModel;
import ZombieApocalypse.View.LoginView;
import ZombieApocalypse.View.UserMapView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMapController {
    private UserMapModel model;
    private UserMapView view;

    public UserMapController(UserMapModel model, UserMapView view){
        //Creo il model e la view
        this.model = model;
        this.view = view;
    }

    public void addListener(){
        view.getBtnGiu().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(UserMapView.indice < UserMapView.maxIndice && UserMapView.maxIndice > 19){
                    UserMapView.indice ++;
                    view.initFile();
                }
            }
        });
        view.getBtnSu().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(UserMapView.indice > 0 && UserMapView.maxIndice > 19){
                    UserMapView.indice --;
                    view.initFile();
                }
            }
        });
        view.getNameMap().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(view.getNameMap().getText().length() > 11)
                    e.consume();
            }
        });
    }
}
