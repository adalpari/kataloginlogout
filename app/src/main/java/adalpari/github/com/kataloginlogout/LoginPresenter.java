package adalpari.github.com.kataloginlogout;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginPresenter {

    private final LoginHelper loginHelper;
    private final LoginView view;

    public LoginPresenter(LoginHelper loginHelper, LoginView view) {
        this.loginHelper = loginHelper;
        this.view = view;
    }

    public void onLogInClicked(String userName, String password) {
        LoginResult success = loginHelper.login(userName, password);

        if (success.isError()) {
            view.showError(success.getError());
        } else {
            view.showLogOutForm();
        }
    }

    public void onLogOutClicked() {
        boolean success = loginHelper.canLogout();

        if (success) {
            view.showLogInForm();
        } else {
            view.showError("You are trapped!");
        }
    }
}