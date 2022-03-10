package pk.wei.com.newpractice.testdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class CustomView extends View {

    private static final String TAG = "CustomView";

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        int widthSize = measureHandler(widthMeasureSpec);
//        int heightSize = measureHandler(heightMeasureSpec);
//
//        Log.d(TAG, "onMeasure: " + widthSize + " " + heightSize);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        super.setMeasuredDimension(widthSize, heightSize);
    }

    private int measureHandler(int measureSpec) {
        final int DefaultSize = 100;
        int size = DefaultSize;
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                size =  specSize;
                break;
            case MeasureSpec.AT_MOST:
                size =  Math.min(size, specSize);
                break;
            case MeasureSpec.UNSPECIFIED:
//                size =  DefaultSize;
                break;
        }

        return size;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: execute");
        return super.dispatchTouchEvent(event);
    }

    int lastX, lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                Log.i(TAG, "onTouchEvent: ACTION_DONW");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: last X:" + lastX + " Y:" + lastY);
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY,
                        getRight() + offsetX, getBottom() + offsetY);

                lastX = rawX;
                lastY = rawY;
                Log.i(TAG, "onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: ACTION_UP");
                break;
        }

        Log.i(TAG, "onTouchEvent: execute");
        Log.i(TAG, "onTouchEvent: return true");
        return true;
//        return super.onTouchEvent(event);
    }
}
