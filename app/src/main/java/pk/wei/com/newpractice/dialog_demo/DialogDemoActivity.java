package pk.wei.com.newpractice.dialog_demo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pk.wei.com.newpractice.R;

public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        Button button = findViewById(R.id.btn_dd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(DialogDemoActivity.this);
                b.setTitle("OK");
                AlertDialog d = b.create();
                d.getWindow().setDimAmount(0f);
                d.getWindow().setWindowAnimations(R.style.dialog_animStyle);

//                AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
//                alphaAnim.setDuration(3000);
//                alphaAnim.setFillBefore(true);
//                alphaAnim.setInterpolator(new LinearInterpolator());

                d.show();
            }
        });

        ;

    }

}
