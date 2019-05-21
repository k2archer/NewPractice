package pk.wei.com.newpractice.fragment.dialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;

public class DialogFragmentDemoActivity extends AppCompatActivity {

    @BindView(R.id.fragment_dialog)
    Button dialogAlert;
    @BindView(R.id.normal_radio)
    AppCompatRadioButton rrNormal;
    @BindView(R.id.dialog_radio_group)
    RadioGroup dialogRadioGroup;

    public enum DialogStyle {NORMAL}

    private DialogStyle style = DialogStyle.NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment_demo);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        dialogRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.normal_radio:
                        style = DialogStyle.NORMAL;
                        break;
                }
            }
        });
    }

    @OnClick({R.id.fragment_dialog, R.id.large_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_dialog:
                showDemoFragmentDialog();
                break;
            case R.id.large_dialog:
//                showDialog();
                break;
        }
    }


    private void showDemoFragmentDialog() {
        DialogFragmentDemo demoDialog = new DialogFragmentDemo();
        demoDialog.show(getSupportFragmentManager(), "DemoTitle");
    }

    private void showInLayoutDialog() {
        DialogFragmentDemo dialog = new DialogFragmentDemo();
        boolean large = getResources().getBoolean(R.bool.large_layout);
        if (large) {
            dialog.show(getSupportFragmentManager(), "Title");
        } else {
            replaceFragment(dialog);
        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.dialog_fragment_place, fragment).commit();
    }
}
