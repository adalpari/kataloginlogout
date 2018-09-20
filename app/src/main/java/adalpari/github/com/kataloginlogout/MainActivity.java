package adalpari.github.com.kataloginlogout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    EditText nameTv;

    @BindView(R.id.password)
    EditText passwordTv;

    @BindView(R.id.login)
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        boolean success = LoginHelper.login(nameTv.getText().toString(), passwordTv.getText().toString());

        if (success) {
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG). show();
        } else {
            Toast.makeText(this, "FAIL", Toast.LENGTH_LONG). show();
        }
    }
}
