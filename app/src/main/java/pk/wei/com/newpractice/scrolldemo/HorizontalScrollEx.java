package pk.wei.com.newpractice.scrolldemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;


public class HorizontalScrollEx extends LinearLayout {

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    private int mLastX = 0;
    private int mLastY = 0;

    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;


    public HorizontalScrollEx(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HorizontalScrollEx(Context context) {
        super(context);
        init();
    }

    public void init() {
        mScroller = new Scroller(getContext());
        // 初始化速度追踪
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildrenSize = childCount;

        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth,
                        childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    // 滑动冲突处理
    /** 采用外部拦截重写 onInterceptTouchEvent() **/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                if (!mScroller.isFinished()) {
                    // 当滑动中强制取消滑动动画
                    mScroller.abortAnimation();
                    // 返回 true , 拦截 ACTION_DOWN 事件, 交 onTouchEvent() 处理
                    //
                    intercepted = true;
                }
                Log.i(this.getClass().getName(), "onInterceptTouchEvent: ACTION_DOWN " + intercepted);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    // 返回 true , 拦截 ACTION_DOWN 事件, 交 onTouchEvent() 处理
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                Log.i(this.getClass().getName(),
                        "onInterceptTouchEvent: ACTION_MOVE " + intercepted + " x: " + x + " y:" + y);
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.i(this.getClass().getName(), "onInterceptTouchEvent: ACTION_UP");

                intercepted = false;
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event); // 添加当前的追踪速度
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(this.getClass().getName(), "onTouchEvent: ACTION_DOWN");
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                // 不断的 ACTION_MOVE 事件，不断的滑动(移动) View
                scrollBy(-deltaX, 0);
                Log.i(this.getClass().getName(), "onTouchEvent: ACTION_MOVE x:" + x + " y:" + y);
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.i(this.getClass().getName(), "onTouchEvent: ACTION_UP scrollX " + getScrollX());

                int scrollX = getScrollX();
//                int scrollToChildIndex = scrollX / mChildWidth;
                // 计算 1000ms 时间段内手指滑过的像素数
                mVelocityTracker.computeCurrentVelocity(1000);
                // 获得手指滑动的水平速度
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                // 平滑滑动 View
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                this.performClick();
                break;
            }
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx, int dy) {
        Log.i(this.getClass().getName(), "smoothScrollBy: ScrollX " + getScrollX() + " Y: 0");
        // 利用 Scroller 的延时滑动 View
        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        Log.i(this.getClass().getName(), "computeScroll: x "
                + mScroller.getCurrX() + " y:" + mScroller.getCurrY());
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        final int childCount = getChildCount();
        // 判断子 View 数量
        if (childCount == 0) {
            // 没有子 View
            // layout_width 和 layout_height 都设置 0
            setMeasuredDimension(0, 0);
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            // 当  layout_height、layout_width 都等于 wrap_content
            // 则设置 layout_width 为第一个子 View 的宽乘子 View 的数量
            // 则设置 layout_height 为第一个子 View 的高
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            // 当  layout_height 等于 wrap_content
            // 则设置 layout_height 为第一个子 View 的高
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, measuredHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            // 当 layout_width 等于 wrap_content
            // 则设置 layout_width 为第一个子 View 的宽乘子 View 的数量
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}



