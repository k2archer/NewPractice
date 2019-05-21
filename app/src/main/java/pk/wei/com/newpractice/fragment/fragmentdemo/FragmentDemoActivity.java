package pk.wei.com.newpractice.fragment.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import pk.wei.com.newpractice.R;

public class FragmentDemoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "FragmentDemoActivity";
    private final static int fragmentLayoutID = R.id.fragmentdemo_fragment_layout;
    private FragmentViewOne fragmentView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        initView();
    }

    void initView() {
        findViewById(R.id.btn_fragmentdemo_view1).setOnClickListener(this);
        findViewById(R.id.btn_fragmentdemo_view2).setOnClickListener(this);
        findViewById(R.id.btn_fragmentdemo_send).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragmentdemo_view1:
                fragmentView = new FragmentViewOne();
                replaceFragment(fragmentView);
                break;
            case R.id.btn_fragmentdemo_view2:
                replaceFragment(new FragmentViewTwo());
                break;
            case R.id.btn_fragmentdemo_send:
                fragmentView.getData(new FragmentViewOne.CallBack() {
                    @Override
                    public void getResult(String result) {              /*打印信息*/
//                        Toast.makeText(MainActivity.this, "-->>" + result, 1).show();
                        Log.d(TAG, "getResult: " + "-->>" + result);
                    }
                });
                break;
            default: break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(fragmentLayoutID, fragment).commit();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.fragmentdemo_fragment_layout, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }
}
