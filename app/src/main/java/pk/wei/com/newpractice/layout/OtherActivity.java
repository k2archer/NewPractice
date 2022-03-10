package pk.wei.com.newpractice.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pk.wei.com.newpractice.R;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

//        ((ViewStub) findViewById(R.id.stub_ok)).setVisibility(View.VISIBLE);
//
//        ((ViewStub) findViewById(R.id.stub_no)).setVisibility(View.VISIBLE);
    }
}
