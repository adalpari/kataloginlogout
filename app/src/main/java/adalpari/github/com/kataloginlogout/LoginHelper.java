package adalpari.github.com.kataloginlogout;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginHelper {

    private final static String ADMIN = "admin";

    private final Clock clock;

    public LoginHelper(Clock clock) {
        this.clock = clock;
    }

    public boolean login(String userName, String password) {
        return ADMIN.equals(userName) && ADMIN.equals(password);
    }

    public boolean canLogout() {
        return clock.getSecondsNow() % 2 == 0;
    }
}
