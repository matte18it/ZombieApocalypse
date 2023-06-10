package ZombieApocalypse.Model;

import ZombieApocalypse.View.Editor.EditorView;
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
}
