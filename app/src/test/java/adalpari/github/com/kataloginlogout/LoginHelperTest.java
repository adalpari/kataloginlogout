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
    public void shouldReturnFalseForBadUser() {
        boolean result = LoginHelper.login("admi", "admin");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBadPassword() {
        boolean result = LoginHelper.login("admin", "admi");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullUser() {
        boolean result = LoginHelper.login(null, "admin");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullPassword() {
        boolean result = LoginHelper.login("admin", null);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBlankLogin() {
        boolean result = LoginHelper.login("", "");

        assertFalse(result);
    }
}