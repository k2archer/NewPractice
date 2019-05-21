package pk.wei.com.newpractice.customViewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import pk.wei.com.newpractice.R;

public class CircleView extends View {

    float mRadius = 10;
    int mColor = Color.BLACK;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public CircleView(Context context) {
        super(context);

        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mRadius = array.getDimension(R.styleable.CircleView_radius, -1);
        mColor = array.getColor(R.styleable.CircleView_fill_color, Color.BLACK);
        array.recycle();

        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mRadius = array.getDimension(R.styleable.CircleView_radius, -1);
        mColor = array.getColor(R.styleable.CircleView_fill_color, Color.BLACK);
        array.recycle();

        init();
    }

    private void init() {
        paint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /** 处理 padding 填充计算 **/
        final int paddingTop = getPaddingTop();
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        /** getWidth() | getHeight() 返回的结果包括 padding **/
        int x = (getWidth() - paddingLeft - paddingRight) / 2 + paddingLeft;
        int y = (getHeight() - paddingTop - paddingBottom) / 2 + paddingTop;
        float radius = Math.min(getWidth() - paddingLeft - paddingRight, getHeight() - paddingTop - paddingBottom) / 2;
        radius = mRadius != -1 ? Math.min(radius, mRadius) : radius;
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /** 设置 wrap_content 默认尺寸 **/
        int defaultWidth = mRadius != -1 ? (int)mRadius * 2 : 1;
        int defaultHeight = defaultWidth;
        /** 解析 measureSpc **/
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        /** 处理 MeasureSpc.AT_MOST 适应 wrap_content **/
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, defaultHeight);
        }
    }
}
