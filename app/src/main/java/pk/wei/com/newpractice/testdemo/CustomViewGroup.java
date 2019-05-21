package pk.wei.com.newpractice.testdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {

    private static final String TAG = "CustomViewGroup";

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = measureHandler(widthMeasureSpec);
        int measureHeight = measureHandler(heightMeasureSpec);
//         // 计算自定义的 CustomViewGroup 中所有子控件的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为 ViewGroup 中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();

            if ( childWidth > measureWidth)
                measureWidth = childWidth;

            measureHeight += childView.getMeasuredHeight();

        }
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//         // 设置自定义的控件 CustomViewGroup 的大小
        super.setMeasuredDimension(measureWidth, measureHeight);
    }

    private int measureHandler(int measureSpec) {
        final int DefaultSize = 100;
        int size = DefaultSize;
        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                size = specSize;
                break;
            case MeasureSpec.AT_MOST:
                size = Math.min(size, specSize);
                break;
            case MeasureSpec.UNSPECIFIED:
//                size =  DefaultSize;
                break;
        }

        return size;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 记录总高度
        int mTotalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            // 获取在onMeasure中计算的视图尺寸
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();

            childView.layout(l, mTotalHeight, measuredWidth, mTotalHeight
                    + measureHeight);

            mTotalHeight += measureHeight;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: execute");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: execute");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent: ACTION_DONW");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: ACTION_UP");
                break;
        }

        Log.i(TAG, "onTouchEvent: execute");
        return super.onTouchEvent(event);
    }
}
