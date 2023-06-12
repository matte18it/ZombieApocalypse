package ZombieApocalypse.Utility;

import ZombieApocalypse.Controller.PlayerController;
import ZombieApocalypse.View.CharacterAnimation;
import ZombieApocalypse.View.Gun.GunAnimation;
import ZombieApocalypse.View.Gun.GunAttackAnimation;
import ZombieApocalypse.View.Gun.ItemAnimation;

import java.util.Objects;
import java.util.concurrent.*;

public class ThreadPool {
        private static ExecutorService executor=Executors.newCachedThreadPool();;
    private static final ThreadPool instance = new ThreadPool();
    private ThreadPool() {}



    public static Future<CharacterAnimation> executeCharacterAnimation(CharacterAnimation task) {
        return  executor.submit(()->task);
    }
    public static Future<GunAnimation> executeGunAnimation(GunAnimation task) {
        return  executor.submit(()->task);
    }
    public static Future<ItemAnimation> executeItemAnimation(ItemAnimation task) {
        return  executor.submit(()->task);
    }
    public static Future<GunAttackAnimation> GunAttackAnimation(GunAttackAnimation task) {
        return  executor.submit(()->task);
    }
    public static ExecutorService getExecutor(){
        return executor;
    }



    public void stop(){
            executor.shutdown();
        }

}
