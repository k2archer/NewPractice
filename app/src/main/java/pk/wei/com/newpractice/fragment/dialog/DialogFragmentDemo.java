package pk.wei.com.newpractice.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import pk.wei.com.newpractice.R;

/**
 * 简单定义 Dialog
 * 适用于创建替代传统的 Dialog 对话框的场景，UI 简单，功能单一
 *  参考链接 https://likfe.com/2016/10/27/dialog-fragment/
 */

public class DialogFragmentDemo extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d("TAG process", "onCreateDialog: ");

//        Dialog dialog =  createByDialog(null);
        Dialog dialog =  createByView(null);
        return dialog;
    }

    /**
     * 普通 Dialog, 支持自定义风格
     */
    private Dialog createByDialog(Integer style) {
        AlertDialog.Builder builder = null;
        if (style == null) {
            builder = new AlertDialog.Builder(getActivity());
        } else {
            builder = new AlertDialog.Builder(getActivity(), R.style.dialog_style_demo);
        }

        builder.setTitle("标题")
                .setMessage("内容")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .setCancelable(false);

        return builder.create();
    }

    /**
     * 自定义 dialog 布局、风格
     */
    private Dialog createByView(Integer style) {
        AlertDialog.Builder builder = null;
        if (style == null) {
            builder = new AlertDialog.Builder(getActivity());
        } else {
            builder = new AlertDialog.Builder(getActivity(), R.style.dialog_style_demo);
        }

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_demo, null);

        builder.setView(view);
        initView(view);
        Dialog dialog =  builder.create();

//        Dialog dialog = new Dialog(getActivity(), R.style.dialog_style_demo);

        // 关闭标题栏，setContentView() 之前调用
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        // todo something ...
        // 设置宽度为屏宽、位置靠近屏幕底部
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
//        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);


        return dialog;
    }

    private void initView(View view) {
        // Do Someting,eg: TextView tv = view.findViewById(R.id.tv);
    }

}
