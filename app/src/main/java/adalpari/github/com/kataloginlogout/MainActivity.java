package adalpari.github.com.kataloginlogout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login)
    Button loginButton;

    @BindView(R.id.logout)
    Button logoutButton;

    private LoginHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginHelper = new LoginHelper(new Clock());
    }

    @OnClick(R.id.login)
    public void onLoginClicked() {
        boolean success = loginHelper.login(name.getText().toString(), password.getText().toString());

        if (success) {
            showLogOut();
        } else {
            Toast.makeText(this, "Meeeh", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.logout)
    public void onLogoutClicked() {
        boolean success = loginHelper.canLogout();

        if (success) {
            showLogIn();
        } else {
            Toast.makeText(this, "You are trapped!", Toast.LENGTH_LONG).show();
        }
    }

    private void showLogOut() {
        name.setVisibility(View.INVISIBLE);
        password.setVisibility(View.INVISIBLE);
        loginButton.setVisibility(View.INVISIBLE);
        logoutButton.setVisibility(View.VISIBLE);
    }

    private void showLogIn() {
        name.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        logoutButton.setVisibility(View.INVISIBLE);
    }
}
