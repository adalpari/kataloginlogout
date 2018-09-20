package adalpari.github.com.kataloginlogout;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginHelper {

    private final static String ADMIN = "admin";

    public static boolean login(String userName, String password) {
        return ADMIN.equals(userName) && ADMIN.equals(password);
    }
}
