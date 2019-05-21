package pk.wei.com.newpractice.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

public class TestService extends Service {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
//                case MSG:
                default:
                    super.handleMessage(msg);
            }
        }
    };
    private final Messenger mMessenger = new Messenger(mHandler);

    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//         TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return mMessenger.getBinder();
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }
}
