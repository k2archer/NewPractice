package pk.wei.com.newpractice.testdemo;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "CustomImageView";
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: execute");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
        }

        return super.onTouchEvent(event);
    }
}
