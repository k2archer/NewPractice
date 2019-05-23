package pk.wei.com.newpractice.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import pk.wei.com.appserice.AppServiceRemoteBinder;
import pk.wei.com.newpractice.R;

public class RemoteServiceDemoActivity extends AppCompatActivity {

    private static final String TAG = "RemoteServiceDemo";

    private AppServiceRemoteBinder binder = null;
    private Intent remoteServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service_demo);

        remoteServiceIntent = new Intent();
//        remoteServiceIntent.setComponent(new ComponentName("pk.wei.com.xxx", "pk.wei.com.xxx.AppService"));
    }

    private ServiceConnection remoteServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: remote Service is binded.");
            binder = AppServiceRemoteBinder.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: remote service is unbinded.");
        }
    };
}
