package pk.wei.com.newpractice.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;

public class ServiceMangerActivity extends AppCompatActivity {

    private static final String TAG = "ServiceMangerActivity";

    @BindView(R.id.start_service_btn)
    Button startServiceBtn;
    @BindView(R.id.bind_service_btn)
    Button bindServiceBtn;
    @BindView(R.id.unbind_service_btn)
    Button unbindServiceBtn;
    @BindView(R.id.input_et)
    EditText inputEt;
    @BindView(R.id.sync_get_data_btn)
    Button syncDataBtn;
    @BindView(R.id.stop_service_btn)
    Button stopServiceBtn;
    @BindView(R.id.tv_click_count)
    TextView tvClickCount;
    @BindView(R.id.sync_set_data_btn)
    Button syncSetDataBtn;
    @BindView(R.id.start_click_btn)
    Button startClickBtn;
    @BindView(R.id.stop_click_btn)
    Button stopClickBtn;
    @BindView(R.id.start_intent_service_btn)
    Button startIntentServiceBtn;

    private Intent serviceIntent;
    private boolean mIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servcie_manger);
        ButterKnife.bind(this);

        serviceIntent = new Intent(this, TestService.class);

    }

    @OnClick(R.id.start_service_btn)
    public void onStartServiceClicked(View view) {
        Log.d(TAG, "onStartServiceClicked: ");
        startService(serviceIntent);
    }

    @OnClick(R.id.stop_service_btn)
    public void onStopServiceClicked(View view) {
        Log.d(TAG, "onStopServiceClicked: ");
        // 不会马上停止 service, 需要等全部 binder 断开
        stopService(serviceIntent);
    }

    @OnClick(R.id.bind_service_btn)
    public void onBindServiceClicked(View view) {
        Log.d(TAG, "onBindServiceClicked: ");
        mIsBound  = bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @OnClick(R.id.unbind_service_btn)
    public void onUnbindServiceClicked(View view) {
        Log.d(TAG, "onUnbindServiceClicked: ");
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
    }


    @OnClick({R.id.start_click_btn, R.id.stop_click_btn})
    public void onViewClicked(View view) {
        if (binderDemo == null) return;
        switch (view.getId()) {
            case R.id.start_click_btn:
                binderDemo.getService().startClick();
                break;
            case R.id.stop_click_btn:
                binderDemo.getService().stop();
                break;
        }
    }

    @OnClick({R.id.sync_get_data_btn, R.id.sync_set_data_btn})
    public void onSyncGetDataBtnClicked(View v) {
        if (binderDemo != null) {
            switch (v.getId()) {
                case R.id.sync_get_data_btn:
                    inputEt.setText(binderDemo.getData());
                    break;
                case R.id.sync_set_data_btn:
                    binderDemo.setDate(inputEt.getText().toString());
                    inputEt.setText("");
                    break;
            }

        }
    }


    private TestService.BinderDemo binderDemo;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: Service is binded. 已绑定");
            binderDemo = (TestService.BinderDemo) service;
            binderDemo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(int click) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvClickCount.setText(String.valueOf(click));
                        }
                    });
                }
            });
        }

        /** 只会在 Service 丢失时才会调用 **/
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: service is unbinded. 断开绑定");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null && mIsBound) {
            unbindService(serviceConnection);
            mIsBound = false;
        }
    }

    @OnClick(R.id.start_intent_service_btn)
    public void onStartIntentServiceClicked() {
        Intent intent = new Intent(this, IntentServiceDemo.class);
        intent.putExtra(IntentServiceDemo.TASK_NAME,"do something");
        startService(intent);
    }
}
