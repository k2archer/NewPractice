package pk.wei.com.newpractice.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pk.wei.com.newpractice.R;

public class SharedPreferencesDemoActivity extends AppCompatActivity {

    private static final String KEY_NAME = "name";
    private static final String KEY_AGO = "ago";

    private SharedPreferences preferences ;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences_demo_demo);

        preferences = getPreferences(Activity.MODE_PRIVATE);
         editor = preferences.edit();
    }
}
