package adalpari.github.com.kataloginlogout.ui.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adalpari.github.com.kataloginlogout.domain.login.Clock;
import adalpari.github.com.kataloginlogout.domain.login.LoginHelper;
import adalpari.github.com.kataloginlogout.executor.BackgroundExecutor;
import adalpari.github.com.kataloginlogout.ui.login.presenter.LoginPresenter;
import adalpari.github.com.kataloginlogout.ui.login.presenter.LoginView;
import adalpari.github.com.kataloginlogout.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login)
    Button loginButton;

    @BindView(R.id.logout)
    Button logoutButton;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        injectDependencies();
    }

    private void injectDependencies() {
        final LoginHelper loginHelper = new LoginHelper(new Clock());
        final BackgroundExecutor backgroundExecutor = new BackgroundExecutor();
        loginPresenter = new LoginPresenter(loginHelper, backgroundExecutor, this);
    }

    @OnClick(R.id.login)
    public void onLoginClicked() {
        loginPresenter.onLogInClicked(name.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.logout)
    public void onLogoutClicked() {
        loginPresenter.onLogOutClicked();
    }

    @Override
    public void showError(String error) {
        runOnUiThread(() -> {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showLogInForm() {
        runOnUiThread(() -> {
            name.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void showLogOutForm() {
        runOnUiThread(() -> {
            name.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
        });
    }
}
