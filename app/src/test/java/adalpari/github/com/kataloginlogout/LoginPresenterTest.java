package adalpari.github.com.kataloginlogout;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    LoginView loginView;

    private LoginHelperMock loginHelperMock;
    private LoginPresenter loginPresenter;

    @Before
    public void setUp() throws Exception {
        loginHelperMock = new LoginHelperMock();
        loginPresenter = new LoginPresenter(loginHelperMock, loginView);
    }

    @Test
    public void shouldShowLoginWhenLogout() {
        givenAllowLogout(true);

        loginPresenter.onLogOutClicked();

        verify(loginView, times(1)).showLogInForm();
    }

    @Test
    public void shouldShowErrorWhenLogoutFails() {
        givenAllowLogout(false);

        loginPresenter.onLogOutClicked();

        verify(loginView, times(1)).showError(LoginResult.CAN_NOT_LOG_OUT_MESSAGE);
    }

    @Test
    public void shouldShowErrorForBadUser() {
        givenLoginResult(new LoginResult(true, LoginResult.BAD_CREDENTIALS_MESSAGE));

        loginPresenter.onLogInClicked("", "");

        verify(loginView, times(1)).showError(LoginResult.BAD_CREDENTIALS_MESSAGE);
    }

    @Test
    public void shouldShowELogOutForm() {
        givenLoginResult(new LoginResult(false, null));

        loginPresenter.onLogInClicked("", "");

        verify(loginView, times(1)).showLogOutForm();
    }

    void givenAllowLogout(boolean allow) {
        loginHelperMock.setAllowLogout(allow);
    }

    void givenLoginResult(LoginResult loginResult) {
        loginHelperMock.setLoginResult(loginResult);
    }

    class LoginHelperMock extends LoginHelper {

        private boolean allowLogout;
        private LoginResult loginResult;

        public LoginHelperMock() {
            super(new Clock());
        }

        @Override
        public boolean canLogout() {
            return allowLogout;
        }

        public void setAllowLogout(boolean allowLogout) {
            this.allowLogout = allowLogout;
        }

        @Override
        public LoginResult login(String userName, String password) {
            return loginResult;
        }

        public void setLoginResult(LoginResult loginResult) {
            this.loginResult = loginResult;
        }
    }

}