package pk.wei.com.newpractice.layout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import pk.wei.com.newpractice.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rememberMe;
    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_login);
//        setContentView(R.layout.activity_linear_login);
//        setContentView(R.layout.activity_relative_login);

        initView();
    }


    private void initView() {
        rememberMe = (RadioButton) findViewById(R.id.remember_me_btn);
        userName = (EditText) findViewById(R.id.user_name_ed);
        password = (EditText) findViewById(R.id.password_ed);
        Button button = (Button) findViewById(R.id.login_btn);
        button.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        userName.setText(preferences.getString("userName", ""));
        password.setText(preferences.getString("password", ""));
        if (userName.getText().length() != 0)
            rememberMe.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (rememberMe.isChecked()) {
                    rememberUserNameAndPassword();
                }
                break;
        }
    }

    void rememberUserNameAndPassword() {
        SharedPreferences.Editor editor = getSharedPreferences("loginInfo", MODE_PRIVATE).edit();
        String user = userName.getText().toString();
        String pass = password.getText().toString();
        editor.putString("userName", user);
        editor.putString("password", pass);
        editor.apply();
    }
}
