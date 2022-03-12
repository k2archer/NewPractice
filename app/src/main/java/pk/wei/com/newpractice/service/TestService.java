package pk.wei.com.newpractice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import pk.wei.com.newpractice.R;

public class TestService extends Service {

    private static final String TAG = "TestService";
    private static final int notificationId = 1001;

    private String data = "";
    private BinderDemo binderDemo = new BinderDemo();

    public class BinderDemo extends Binder {
        public String getData() {
            return data;
        }

        public void setDate(String d) {
            data = d;
        }

        public void setOnClickListener(OnClickListener listener) {
            clickListener = listener;
        }

        public TestService getService() {
            return TestService.this;
        }

    }

    public TestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        startNotification();
    }

    private int click = 0;
    private boolean runClicked = false;
    private OnClickListener clickListener;

    public void startClick() {
        if (runClicked || clickListener == null) return;
        runClicked = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (runClicked) {
                    clickListener.onClick(click++); // 在非 UI 线程回调
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop() {
        runClicked = false;
    }

    // 通知保持 Service 前台，保活 Service
    private void startNotification() {
        String CHANNEL_ONE_ID = "CHANNEL_ONE_ID";
        String CHANNEL_ONE_NAME = "CHANNEL_ONE_ID";
        NotificationChannel notificationChannel;
        // 进行8.0的判断
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(notificationChannel);
            }
        }

        Intent intent = new Intent(this, ServiceMangerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ONE_ID).setChannelId(CHANNEL_ONE_ID);
        } else {
            builder = new Notification.Builder(this);
        }

        builder.setTicker("Nature")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("这是一个TestService")
                .setContentIntent(pendingIntent)
                .setContentText("这是一个测试内容");

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

        /* Service 所在进程被 kill ,Android 仍会交 Service 设置 started 状态，
         * 不保存传入的 Intent 对象，Android 尝试重新创建 Service , 并执行
         *  onStartCommand()
         */
        // return START_STICKY;

        /* 和 START_STICKY 类似，但保留传入的 Intent 对象，重新创建 Service,
         * 并执行 onStartCommand() 传入保留的 Intent 对象。
         */
        // return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
//         TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return binderDemo;
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        stop(); // 需要停止 service 内部线程

        /* todo 可在此发广播重启 Service ，保活 Service **/
    }
}
