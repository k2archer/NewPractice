package pk.wei.com.newpractice.pattern.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pk.wei.com.newpractice.R;
import pk.wei.com.newpractice.pattern.mvp.contract.LoginContract;
import pk.wei.com.newpractice.pattern.mvp.presenter.LoginPresenter;

public class MVPDemoActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.login_account)
    EditText loginAccount;
    @BindView(R.id.login_password)
    EditText loginPassword;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
    }

    public void btLogin(View view) {
        loginPresenter.login();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Login succeed.", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public String getName() {
        return loginAccount.getText().toString();
    }

    @Override
    public String getPassWord() {
        return loginPassword.getText().toString();
    }
}
