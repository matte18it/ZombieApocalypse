package ZombieApocalypse.Utility;

import java.awt.*;

import java.util.concurrent.*;

public class ThreadPool {
    //Classe utilizzata per caricare immagini per le View
    Future<Image> future;
    private static final ExecutorService executor=Executors.newCachedThreadPool();
    public ThreadPool(Image task) {
        future=executor.submit(()->task);
    }
    public Image get(){
        try{
                return future.get(); //mi assicuro che il risultato sia disponibile
        }catch (CancellationException |ExecutionException | InterruptedException e){
            ResultsPanel.getInstance().showError("Cancellazione, Interruzione o Errore nel caricamento di una risorsa tramite ThreadPool", 79, e);
        }
        return null;
    }
    public static void stop(){
            executor.shutdown();
        }
}
