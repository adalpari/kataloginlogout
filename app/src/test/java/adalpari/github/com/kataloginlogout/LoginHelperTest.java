package adalpari.github.com.kataloginlogout;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginHelperTest {

    @Test
    public void shouldReturnTrueIfAdminAdmin() {
        boolean result = LoginHelper.login("admin", "admin");

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseForNullLogin() {
        boolean result = LoginHelper.login(null, null);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBlankLogin() {
        boolean result = LoginHelper.login("", "");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBadLogin() {
        boolean result = LoginHelper.login("admi", "admi");

        assertFalse(result);
    }
}