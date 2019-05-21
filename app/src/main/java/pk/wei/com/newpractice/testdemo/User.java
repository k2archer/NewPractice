package pk.wei.com.newpractice.testdemo;

import android.util.Log;

import java.io.Serializable;


public class User implements Serializable {
    private static final String TAG = "User";

    private int id;
    private String name;
    private boolean male;

    public User(int id, String name, boolean male) {
        this.id = id;
        this.name = name;
        this.male = male;
    }

    public void show() {
        Log.i(TAG, "show: id " + id + " name " + name);
    }


}
