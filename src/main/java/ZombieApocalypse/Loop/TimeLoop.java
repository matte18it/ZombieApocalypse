package ZombieApocalypse.Loop;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.Model.Game;
import ZombieApocalypse.Utility.ResultsPanel;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLoop {

    private  ScheduledExecutorService executor;
    public  long time=0;
    public  void start() {
        time=System.nanoTime();
        if(executor==null)
            executor = Executors.newSingleThreadScheduledExecutor();
        try{
            executor.scheduleAtFixedRate(() -> Game.getInstance().getMenuBar().updateTimeLable(time),
                    0, 1000, TimeUnit.MILLISECONDS);
        }catch (RejectedExecutionException | NullPointerException | IllegalArgumentException e){
            ResultsPanel.getInstance().showError("Errore nell'aggiornamento del Timer", 82, e);
        }
    }
    public  void stop(){
        executor.shutdown();
    }

}
