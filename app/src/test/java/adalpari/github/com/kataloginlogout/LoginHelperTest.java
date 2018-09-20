package adalpari.github.com.kataloginlogout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginHelperTest {

    private static final String RIGHT_USER = "admin";
    private static final String RIGHT_PASSWORD = "admin";
    private static final String WRONG_USER = "admi";
    private static final String WRONG_PSSWORD = "admi";
    public static final String USER_NAME_NOT_ALLOWED_CHARACTER = "ADMIN;";
    private LoginHelper mLoginHelper;
    private MockClock mockClock;

    @Before
    public void setUp() throws Exception {
        mockClock = new MockClock();
        mLoginHelper = new LoginHelper(mockClock);
    }

    @Test
    public void shouldReturnTrueIfAdminAdmin() {
        LoginResult result = mLoginHelper.login(RIGHT_USER, RIGHT_PASSWORD);

        assertFalse(result.isError());
    }

    @Test
    public void shouldReturnFalseForBadUser() {
        LoginResult result = mLoginHelper.login(WRONG_USER, RIGHT_PASSWORD);

        assertEquals(LoginResult.BAD_CREDENTIALS_MESSAGE, result.getError());
    }

    @Test
    public void shouldReturnFalseForBadPassword() {
        LoginResult result = mLoginHelper.login(RIGHT_USER, WRONG_PSSWORD);

        assertEquals(LoginResult.BAD_CREDENTIALS_MESSAGE, result.getError());
    }

    @Test
    public void shouldReturnFalseForNullUser() {
        LoginResult result = mLoginHelper.login(null, RIGHT_PASSWORD);

        assertEquals(LoginResult.BAD_CREDENTIALS_MESSAGE, result.getError());
    }

    @Test
    public void shouldReturnFalseForNullPassword() {
        LoginResult result = mLoginHelper.login(RIGHT_USER, null);

        assertEquals(LoginResult.BAD_CREDENTIALS_MESSAGE, result.getError());
    }

    @Test
    public void shouldReturnFalseForBlankLogin() {
        LoginResult result = mLoginHelper.login("", "");

        assertEquals(LoginResult.BAD_CREDENTIALS_MESSAGE, result.getError());
    }

    @Test
    public void shouldReturnFalseForCharactersNotAllowed() {
        LoginResult result = mLoginHelper.login(USER_NAME_NOT_ALLOWED_CHARACTER, RIGHT_PASSWORD);

        assertEquals(LoginResult.NOT_ALLOWED_CHARACTER_MESSAGE, result.getError());
    }

    @Test
    public void testLogOutEven() {
        givenSecond(2);

        boolean result = mLoginHelper.canLogout();

        assertTrue(result);
    }

    @Test
    public void testLogOutUneven() {
        givenSecond(3);

        boolean result = mLoginHelper.canLogout();

        assertFalse(result);
    }

    private void givenSecond(long second) {
        mockClock.setSeconds(second);
    }


    class MockClock extends Clock {

        private long seconds;

        void setSeconds(long seconds) {
            this.seconds = seconds;
        }

        @Override
        public long getSecondsNow() {
            return seconds;
        }
    }
}