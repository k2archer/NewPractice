package pk.wei.com.newpractice.animator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pk.wei.com.newpractice.R;
public class AnimatorDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_demo);

        findViewById(R.id.animator_demo_hide_btn).setOnClickListener(this);
        findViewById(R.id.animator_demo_show_btn).setOnClickListener(this);
    }

    private void setTestPosition() {
        View view = findViewById(R.id.animator_demo_hide_btn);
        view.setY(-view.getHeight());
//        view.setVisibility(View.GONE);
//        view.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animator_demo_hide_btn:
                View view = findViewById(R.id.animator_demo_hide_btn);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,
                        "translationY", view.getTranslationY(), -view.getHeight());
                objectAnimator.setDuration(1000);
                objectAnimator.start();

//                动画集合将前面两个动画加到一起，with同时播放
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.play(anim).with(objectAnimator);
//                animatorSet.play(objectAnimator);
//                animatorSet.setStartDelay(1000l);
//                animatorSet.setDuration(3000l);
//                animatorSet.start();
;
                break;

            case R.id.animator_demo_show_btn:
                 view = findViewById(R.id.animator_demo_hide_btn);
                objectAnimator = ObjectAnimator.ofFloat(view,
                        "translationY", view.getTranslationY(), 0);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
;
                break;
        }
    }
}
