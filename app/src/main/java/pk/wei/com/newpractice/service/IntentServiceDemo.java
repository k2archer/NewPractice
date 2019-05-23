package pk.wei.com.newpractice.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class IntentServiceDemo extends IntentService {

    public static final String TASK_NAME = "task";

    private static final String TAG = "IntentServiceDemo";

    // 必须有构造实现父类的构造,否则会报异常
//    public IntentServiceDemo(String name) {
//        super(name);
//    }

    public IntentServiceDemo() {
        super("");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        Log.d(TAG, "onStart: ");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    //  IntentService的核心方法,通过串行来处理任务
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: " + "工作线程是: " + Thread.currentThread().getName());
        String task = intent.getStringExtra(TASK_NAME);
        Log.d(TAG, "onHandleIntent: " + "任务是 : " + task);
        // todo ... something
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}