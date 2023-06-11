package ZombieApocalypse.Utility;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.View.CharacterAnimation;

import java.util.Objects;
import java.util.concurrent.*;

public class ThreadPool {
        private static ExecutorService executor=Executors.newCachedThreadPool();;
    private static final ThreadPool instance = new ThreadPool();
    private ThreadPool() {}



    public static Future<CharacterAnimation> executeCharacterAnimation(CharacterAnimation task) {
        return  executor.submit(()->task);
    }



    public void stop(){
            executor.shutdown();
        }

}
