package adalpari.github.com.kataloginlogout;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginHelperTest {

    public static final String RIGHT_USER = "admin";
    public static final String RIGHT_PASSWORD = "admin";
    public static final String WRONG_USER = "admi";
    public static final String WRONG_PSSWORD = "admi";
    private LoginHelper loginHelper;
    private MockClock mockClock;

    @Before
    public void setUp() throws Exception {
        mockClock = new MockClock();
        loginHelper = new LoginHelper(mockClock);
    }

    @Test
    public void shouldReturnTrueIfAdminAdmin() {
        boolean result = loginHelper.login(RIGHT_USER, RIGHT_PASSWORD);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseForBadUser() {
        boolean result = loginHelper.login(WRONG_USER, RIGHT_PASSWORD);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForBadPassword() {
        boolean result = loginHelper.login(RIGHT_USER, WRONG_PSSWORD);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullUser() {
        boolean result = loginHelper.login(null, RIGHT_PASSWORD);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseForNullPassword() {
        boolean result = loginHelper.login(RIGHT_USER, null);

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
        mockClock.setSeconds(2);
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