package adalpari.github.com.kataloginlogout.executor;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class BackgroundExecutor {

    // This is a simple thread creator to simulate background working to practise with tests
    public void run(Runnable runnable) {
        new Thread(runnable);
    }
}
