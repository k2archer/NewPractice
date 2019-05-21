package pk.wei.com.newpractice.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import pk.wei.com.newpractice.R;

/**
 * 适用于建复杂内容弹窗或全屏展示效果的场景，UI 复杂，功能复杂，一般有网络请求等异步操作
 * 参考链接 https://likfe.com/2016/10/27/dialog-fragment/
 */
public class CustomDialog extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("TAG process", "onCreate: ");
        super.onCreate(savedInstanceState);
        /**
         * 设置主题需要在 onCreate() 方法中调用 setStyle() 方法
         * setStyle() 的第一个参数有四个可选值：
         * STYLE_NORMAL|STYLE_NO_TITLE|STYLE_NO_FRAME|STYLE_NO_INPUT
         * 其中 STYLE_NO_TITLE 和 STYLE_NO_FRAME 可以关闭标题栏
         * 每一个参数的详细用途可以直接看 Android 源码的说明
         */
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_style_demo);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("TAG process", "onCreateView: ");

        // 关闭标题栏，setContentView() 之前调用
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题柆
        View view = inflater.inflate(R.layout.fragment_dialog_demo, container, false);
        // todo something ...
        // 设置宽度为屏宽、靠近屏幕底部。
        final Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("TAG process", "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
//        setCancelable(false);
//        Window window = getDialog().getWindow();
//        window.setGravity();
//        window.setWindowAnimations(); // 设置出入动画
//        window.setBackgroundDrawable();
//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public interface Listener {
        void callBacker(Object object);
    }


}
