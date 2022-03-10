package pk.wei.com.newpractice.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pk.wei.com.newpractice.R;
import pk.wei.com.newpractice.part.dialog.BottomDialog;
import pk.wei.com.newpractice.part.dialog.BottomDialog1;

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

    @OnClick({R.id.fragment_dialog, R.id.large_dialog, R.id.anim_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_dialog:
                showDemoFragmentDialog();
                break;
            case R.id.large_dialog:
//                showDialog();
//                showCustomDialog();
                showBottomDialog();
//                showBottomDialog1();
                break;
            case R.id.anim_dialog:
                showBottomDialog1();
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

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        dialog.setTitle("Dialog");
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);

        // 直接设置大小
        lp.x = 10;
        lp.y = 10;
        lp.width = 200;
        lp.height = 200;
        lp.alpha = 0.6f;
        window.setAttributes(lp);

//        // 设置屏幕百分比
//        WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
//        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
//        window.setAttributes(params);

        dialog.show();
    }

    private void showBottomDialog() {
        BottomDialog dialog = new BottomDialog(this, R.layout.activity_dialog,
                true, true);
        dialog.show();
    }

    private void showBottomDialog1() {

        BottomDialog1.Builder builder = new BottomDialog1.Builder(this);
        builder.setContentView(R.layout.activity_dialog);
        builder.setHasAnimation(true);
        BottomDialog1 dialog = builder.create();
        dialog.show();
    }
}
