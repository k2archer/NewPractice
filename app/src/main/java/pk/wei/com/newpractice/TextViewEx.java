package pk.wei.com.newpractice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

class TextViewEx extends android.support.v7.widget.AppCompatTextView {

    public TextViewEx(Context context) {
        super(context);
    }

    public TextViewEx(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onFilterTouchEventForSecurity(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onFilterTouchEventForSecurity(event);
    }

    int lastX, lastY;
    GestureDetector gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
        private String TAG = "GestureDetector";

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e(TAG, "onFling: " );
            return false;
        }
    });


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
