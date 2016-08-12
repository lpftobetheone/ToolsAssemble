package tools.lpf.com.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tools.lpf.com.login.bean.User;
import tools.lpf.com.login.presenter.UserLoginPresenter;
import tools.lpf.com.login.view.IUserLoginView;

public class LoginActivity extends AppCompatActivity implements IUserLoginView {


    @InjectView(R.id.id_email)
    AutoCompleteTextView idEmail;
    @InjectView(R.id.id_password)
    EditText idPassword;
    @InjectView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @InjectView(R.id.login_progress)
    ProgressBar loginProgress;

    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        initViews();
    }

    private void initViews() {

        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });
    }

    @Override
    public String getUserName() {
        return idEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return idPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        idEmail.setText("");
    }

    @Override
    public void clearPassword() {
        idPassword.setText("");
    }

    @Override
    public void showLoading() {
        loginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loginProgress.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
    }
}
