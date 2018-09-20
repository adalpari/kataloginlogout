package adalpari.github.com.kataloginlogout.domain.login;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class Clock {

    public long getSecondsNow() {
        return System.currentTimeMillis() / 1000;
    }
}
