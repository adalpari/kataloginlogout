package adalpari.github.com.kataloginlogout;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginHelperTest {

    private LoginHelper loginHelper;
    private MockClock mockClock;

    @Before
    public void setUp() throws Exception {
        mockClock = new MockClock();
        loginHelper = new LoginHelper(mockClock);
    }

    @Test
    public void shouldReturnTrueIfAdminAdmin() {
        boolean result = loginHelper.login("admin", "admin");

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseForBadUser() {
        boolean result = loginHelper.login("admi", "admin");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBadPassword() {
        boolean result = loginHelper.login("admin", "admi");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullUser() {
        boolean result = loginHelper.login(null, "admin");

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullPassword() {
        boolean result = loginHelper.login("admin", null);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBlankLogin() {
        boolean result = loginHelper.login("", "");

        assertFalse(result);
    }


    @Test
    public void testLogOutEven() {
        mockClock.setSeconds(2);
        boolean result = loginHelper.canLogout();

        assertTrue(result);
    }

    @Test
    public void testLogOutUneven() {
        mockClock.setSeconds(3);
        boolean result = loginHelper.canLogout();

        assertFalse(result);
    }


    class MockClock extends Clock {

        private long seconds;

        protected void setSeconds(long seconds) {
            this.seconds = seconds;
        }

        @Override
        public long getSecondsNow() {
            return seconds;
        }
    }
}