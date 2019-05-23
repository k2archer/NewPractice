package pk.wei.com.newpractice.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

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

    public void stop () {
        runClicked = false;
    }

    private void startNotification() {
        Intent intent = new Intent(this, ServiceMangerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Title")
                .setContentText("text")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();
        /** 如果 service 被 destroy 会主取消 notification **/
        startForeground(notificationId, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
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
    }
}
