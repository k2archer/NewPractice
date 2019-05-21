package pk.wei.com.newpractice.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import pk.wei.com.newpractice.R;

public class BroadcastDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardcast_demo);
    }

    private void normalBroadcast() {
        Intent intent = new Intent();
        intent.setAction("CUSTOM_ACTION");
        sendBroadcast(intent);
    }

    private void ordereDBroadcast() {
        Intent intent = new Intent();
        intent.setAction("CUSTOM_ACTION");
        String receiverPermission = "";
        sendOrderedBroadcast(intent, "");
    }

    private LocalBroadcastManager localBroadcastManager;
    private CustomReceiver mCustomReceiver;

    private void lcoalBroadcast() {
        // 注册应用内广播接收器
        // 步骤1：实例化 BroadcastReceiver 子类 & IntentFilter mCustomReceiver
        mCustomReceiver = new CustomReceiver();
        IntentFilter intentFilter = new IntentFilter();

        // 步骤2：实例化 LocalBroadcastManager 的实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        // 步骤3：设置接收广播的类型
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        // 步骤4：调用 LocalBroadcastManager 单一实例的 registerReceiver() 方法进行动态注册
        localBroadcastManager.registerReceiver(mCustomReceiver, intentFilter);

        // 取消注册应用内广播接收器
        localBroadcastManager.unregisterReceiver(mCustomReceiver);

        // 发送应用内广播
        Intent intent = new Intent();
        intent.setAction("BROADCAST_ACTION");
        localBroadcastManager.sendBroadcast(intent);
    }

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onResume() {
        super.onResume();

        // 实例化接收
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();

        // 设置接收广播类型
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        // 注册接收器
        registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 解除已注册接收器
        unregisterReceiver(networkChangeReceiver);
    }
}
