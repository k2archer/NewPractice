package pk.wei.com.newpractice.part.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import pk.wei.com.newpractice.R;


/**
 * 自定义底部弹出对话框
 * Created by zhaomac on 2017/9/8.
 */

public class BottomDialog extends Dialog {

    private boolean isCancelable;//控制点击dialog外部是否 dismiss
    private boolean isBackCanCelable;//控制返回键是否 dismiss
    private View view;
    private Context context;

    public BottomDialog(@NonNull Context context) {
        super(context);
    }

    // 这里的 view 其实可以替换直接传 layout 过来的 因为各种原因没传(lan)
    public BottomDialog(Context context, int layoutId, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.BottomDialog);

        View view = LayoutInflater.from(context).inflate(layoutId, null);

        this.context = context;
        this.view = view;
        this.isCancelable = isCancelable;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view); // 这行一定要写在前面
        setCancelable(isCancelable);// 点击外部不可 dismiss
        setCanceledOnTouchOutside(isBackCanCelable);

        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);


    }


}