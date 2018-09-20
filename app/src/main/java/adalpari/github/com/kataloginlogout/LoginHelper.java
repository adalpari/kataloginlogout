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

    public LoginResult login(String userName, String password) {
        if (userName != null && userName.contains(";")) {
            return new LoginResult(true, LoginResult.NOT_ALLOWED_CHARACTER_MESSAGE);
        }

        if (!ADMIN.equals(userName) || !ADMIN.equals(password)) {
            return new LoginResult(true, LoginResult.BAD_CREDENTIALS_MESSAGE);
        }

        return new LoginResult(false, null);
    }

    public boolean canLogout() {
        return clock.getSecondsNow() % 2 == 0;
    }
}
