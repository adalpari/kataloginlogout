package adalpari.github.com.kataloginlogout.ui.login.presenter;

import adalpari.github.com.kataloginlogout.domain.login.LoginHelper;
import adalpari.github.com.kataloginlogout.domain.login.LoginResult;
import adalpari.github.com.kataloginlogout.executor.BackgroundExecutor;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginPresenter {

    private final LoginHelper loginHelper;
    private final BackgroundExecutor backgroundExecutor;
    private final LoginView view;

    public LoginPresenter(LoginHelper loginHelper, BackgroundExecutor backgroundExecutor, LoginView view) {
        this.loginHelper = loginHelper;
        this.backgroundExecutor = backgroundExecutor;
        this.view = view;
    }

    public void onLogInClicked(String userName, String password) {
        backgroundExecutor.run(() -> {
            LoginResult success = loginHelper.login(userName, password);

            if (success.isError()) {
                view.showError(success.getError());
            } else {
                view.showLogOutForm();
            }
        });
    }

    public void onLogOutClicked() {
        backgroundExecutor.run(() -> {
            boolean success = loginHelper.canLogout();

            if (success) {
                view.showLogInForm();
            } else {
                view.showError(LoginResult.CAN_NOT_LOG_OUT_MESSAGE);
            }
        });
    }
}
