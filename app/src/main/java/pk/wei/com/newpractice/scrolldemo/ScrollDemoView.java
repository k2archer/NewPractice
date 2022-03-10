package pk.wei.com.newpractice.scrolldemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;


public class ScrollDemoView extends View {


    private int lastX;
    private int lastY;
    private String TAG = "ScrollDemoView";

    public ScrollDemoView(Context context) {
        super(context);
    }

    public ScrollDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public ScrollDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        Log.e(TAG, "onTouchEvent: x: " + x + " y:" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.i(TAG, "onTouchEvent: ACTION_DONW");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent: last X:" + lastX + " Y:" + lastY);
                int offsetX = x - lastX;
                int offsetY = y - lastY;
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);

//                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                setTranslationX(getTranslationX() + offsetX);
                setTranslationY(getTranslationY() + offsetY);
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);


                lastX = x;
                lastY = y;

                break;

        }

        return true;
    }


    private Scroller mScroller;

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        // 1000 秒内滑向 destX
        mScroller.startScroll(scrollX, 0, delta, 0, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
//            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过不断的重绘不断的调用 computeScroll 方法
            postInvalidate();
        }
    }

}
