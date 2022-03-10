package pk.wei.com.newpractice.scrolldemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;


public class ScrollDemoActivity extends AppCompatActivity {

    private static final String TAG = "ScrollDemoActivity";
    @BindView(R.id.btn_scroll)
    Button btnScroll;

    private ScrollDemoView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_demo2);
        ButterKnife.bind(this);


        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.START , ConstraintLayout.LayoutParams.TOP);
        params.width = 100;
        params.height = 100;

        mView = new ScrollDemoView(this, null);
        mView.setBackgroundColor(Color.GREEN);
        addContentView(mView, params);


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @OnClick(R.id.btn_scroll)
    public void onViewClicked() {
        Log.e(TAG, "onViewClicked: ");

        mView.scrollTo(100, 100);

//        mView.smoothScrollTo(100, 100);

//        ObjectAnimator.ofFloat(mView, "translationX", 0, 100).setDuration(100).start();
    }

    private void showViewDetail(View v) {
        Log.d(TAG, "showViewDetail: ");
    }
}
