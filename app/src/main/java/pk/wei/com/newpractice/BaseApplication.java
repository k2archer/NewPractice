package pk.wei.com.newpractice;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
