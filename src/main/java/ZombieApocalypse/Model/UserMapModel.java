package ZombieApocalypse.Model;

import ZombieApocalypse.Utility.GameData;
import ZombieApocalypse.Utility.Settings;
import ZombieApocalypse.View.UserMapView;
import org.joda.time.DateTime;

import java.io.File;

public class UserMapModel {
    private UserMapView view;
    public UserMapModel(UserMapView view) {
        //Creo la view
        this.view = view;
    }

    public String stampData(File file) {
        String data = "";
        DateTime date = new DateTime(file.lastModified());
        data = date.getDayOfMonth() + "/" + date.getMonthOfYear() + "/" + date.getYear() + " " + date.getHourOfDay() + ":" + date.getMinuteOfHour() + ":" + date.getSecondOfMinute();
        return data;
    }

    public void changeDifficulty() {
        if(GameData.lang.equals(GameData.Language.EN)){
            if(Settings.diff == Settings.Difficulty.EASY)
                view.getLblDescrizione().setText("<html>- Right difficulty for those who are new to the game.<br>" +
                        "- Zombies: random number of zombies between 5 and 15.<br>" +
                        "- Medikit: heals 3 lives at a time.<br>" +
                        "- Grenade: double damage.<br>" +
                        "- Hits: double damage.</html>");
            else if(Settings.diff == Settings.Difficulty.MEDIUM)
                view.getLblDescrizione().setText("<html>- Right difficulty for those who want a more complex.<br>" +
                        "- Zombies: random number of zombies between 16 and 25.<br>" +
                        "- Medikit: heals 2 lives at a time.<br>" +
                        "- Grenade: normal damage.<br>" +
                        "- Hits: normal damage.</htmL>");
            else if(Settings.diff == Settings.Difficulty.HARD)
                view.getLblDescrizione().setText("<html>- Right difficulty for those who want a complex challenge.<br>" +
                        "- Zombies: random number of zombies between 26 and 35.<br>" +
                        "- Medikit: heals 1 life at a time.<br>" +
                        "- Grenade: damage halved.<br>" +
                        "- Hits: damage halved.</html>");
        }
        else{
            if(Settings.diff == Settings.Difficulty.EASY)
                view.getLblDescrizione().setText("<html>- Difficoltà giusta per chi è agli inizi col gioco.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 5 e 15.<br>" +
                        "- Medikit: cura 3 vite alla volta.<br>" +
                        "- Granata: danni raddoppiati.<br>" +
                        "- Colpi: danni raddoppiati.</html>");
            else if(Settings.diff == Settings.Difficulty.MEDIUM)
                view.getLblDescrizione().setText("<html>- Difficoltà giusta per chi vuole una sfida più complessa.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 16 e 25.<br>" +
                        "- Medikit: cura 2 vite alla volta.<br>" +
                        "- Granata: danni normali.<br>" +
                        "- Colpi: danni normali.</html>");
            else if(Settings.diff == Settings.Difficulty.HARD)
                view.getLblDescrizione().setText("<html>- Difficoltà giusta per chi vuole una sfida complessa.<br>" +
                        "- Zombie: numero di zombie casuale compreso tra 26 e 35.<br>" +
                        "- Medikit: cura 1 vita alla volta.<br>" +
                        "- Granata: danni dimezzati.<br>" +
                        "- Colpi: danni dimezzati.</html>");
        }
    }
}
