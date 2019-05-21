package pk.wei.com.newpractice.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pk.wei.com.appserice.AppServiceRemoteBinder;
import pk.wei.com.newpractice.R;

public class ServiceMangerActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private static final String TAG = "ServiceMangerActivity";
    private Button bindServiceBtn;
    private Button unbindServiceBtn;
    private EditText etInput;
    private AppServiceRemoteBinder binder = null;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servcie_manger);

        initView();

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("pk.wei.com.appserice", "pk.wei.com.appserice.AppService"));

    }

    void initView() {
        bindServiceBtn = (Button) findViewById(R.id.bind_service_btn);
        bindServiceBtn.setOnClickListener(this);
        unbindServiceBtn = (Button) findViewById(R.id.unbind_service_btn);
        unbindServiceBtn.setOnClickListener(this);
        etInput = (EditText) findViewById(R.id.input_ed);
        Button button = (Button) findViewById(R.id.sync_data_btn);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.stop_service_btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_service_btn:
                bindService(serviceIntent, this,  Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_btn:
                if (binder != null) {
                    unbindService(this);
                    binder = null;
                }
                break;
            case R.id.sync_data_btn:
                if (binder != null) {
                    try {
                        binder.setData(etInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.stop_service_btn:
                stopService(serviceIntent);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(TAG, "onServiceConnected: Service is binded.");
        binder = AppServiceRemoteBinder.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG, "onServiceDisconnected: service is unbinded.");
    }
}
