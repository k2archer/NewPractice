package pk.wei.com.newpractice.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;

public class LifeCycleActivity extends AppCompatActivity {

    private final String TAG = "LifeCycleActivity";
    @BindView(R.id.bt_normal_dialog)
    Button btDialog;
    @BindView(R.id.bt_activity_dialog)
    Button btActivityDialog;

    /**
     * 表示  Activity 正在被创建，常用来做初始化
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    /**
     * 表示 Activity 正在重新启动
     * 当前 Activity 从不可见重新变为可见时，OnRestart就会被调用
     */
    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @OnClick({R.id.bt_normal_dialog, R.id.bt_activity_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_normal_dialog:
                android.support.v7.app.AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                dialog = builder.setTitle("Dialog").create();
                dialog.show();
                break;
            case R.id.bt_activity_dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
        }
    }
}
