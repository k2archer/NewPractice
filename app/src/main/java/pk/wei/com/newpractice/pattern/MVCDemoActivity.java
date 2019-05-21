package pk.wei.com.newpractice.pattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;

public class MVCDemoActivity extends AppCompatActivity implements BaseListener {

    BaseModel model;
    @BindView(R.id.tv_hi_text)
    TextView tvHiText;
    @BindView(R.id.bt_hi)
    Button btHi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvcdemo);
        ButterKnife.bind(this);

        model = new BaseModelImpl();
    }

    @OnClick({R.id.tv_hi_text, R.id.bt_hi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_hi:
                model.getMessageWord(this);
                break;
            case R.id.tv_hi_text:
                break;
        }
    }

    @Override
    public void onSuccess(Object object) {
        tvHiText.setText((String)object);
    }

    @Override
    public void onError() {

    }

}
