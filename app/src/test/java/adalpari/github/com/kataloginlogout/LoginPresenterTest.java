package adalpari.github.com.kataloginlogout;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import adalpari.github.com.kataloginlogout.domain.login.Clock;
import adalpari.github.com.kataloginlogout.domain.login.LoginHelper;
import adalpari.github.com.kataloginlogout.domain.login.LoginResult;
import adalpari.github.com.kataloginlogout.executor.BackgroundExecutor;
import adalpari.github.com.kataloginlogout.ui.login.presenter.LoginPresenter;
import adalpari.github.com.kataloginlogout.ui.login.presenter.LoginView;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    private static final String BLANK = "";

    @Mock
    LoginView loginView;

    private LoginHelperMock loginHelperMock;
    private LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        final BackgroundExecutorMock backgroundExecutorMock = new BackgroundExecutorMock();
        loginHelperMock = new LoginHelperMock();
        loginPresenter = new LoginPresenter(loginHelperMock, backgroundExecutorMock, loginView);
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

        loginPresenter.onLogInClicked(BLANK, BLANK);

        verify(loginView, times(1)).showError(LoginResult.BAD_CREDENTIALS_MESSAGE);
    }

    @Test
    public void shouldShowELogOutForm() {
        givenLoginResult(new LoginResult(false, null));

        loginPresenter.onLogInClicked(BLANK, BLANK);

        verify(loginView, times(1)).showLogOutForm();
    }

    private void givenAllowLogout(boolean allow) {
        loginHelperMock.setAllowLogout(allow);
    }

    private void givenLoginResult(LoginResult loginResult) {
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

        void setAllowLogout(boolean allowLogout) {
            this.allowLogout = allowLogout;
        }

        @Override
        public LoginResult login(String userName, String password) {
            return loginResult;
        }

        void setLoginResult(LoginResult loginResult) {
            this.loginResult = loginResult;
        }
    }

    class BackgroundExecutorMock extends BackgroundExecutor {
        @Override
        public void run(Runnable runnable) {
            runnable.run();
        }
    }

}