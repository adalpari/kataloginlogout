package adalpari.github.com.kataloginlogout.executor;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class BackgroundExecutor {

    public void run(Runnable runnable) {
        // This is a simple thread creator to simulate background working to practise with tests
        new Thread(runnable);
    }
}
