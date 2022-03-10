package pk.wei.com.newpractice.part.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import pk.wei.com.newpractice.R;

public class BottomDialog1 {

    public Dialog dialog;
    private View contentView;

    public static class Builder {
        private LinearLayout bottomLayout; //  根布局
        private View contentView;             // 内容布局
        private android.app.Dialog dialog;     // dialog对象
        private boolean hasAnimation = false;  // 是否开启位移动画的标志位
        private Context context;    // activity或fragment的上下文对象
        private int layoutId;       // 内容布局文件id

        public Builder(Context context) {
            this.context = context;
            // 从指定的布局文件中初始化根布局（不限于 LinearLayout）
            bottomLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.activity_dialog, null);
            bottomLayout = new LinearLayout(context);
        }

        public Builder setContentView(int layoutId) {
            this.layoutId = layoutId;
            // 设置内容布局，使其绑定到父布局 bottomLayout，使其宽高属性生效
            this.contentView = LayoutInflater.from(context).inflate(this.layoutId, bottomLayout);
            return Builder.this;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            // 这个方法的弊端是 contentView 根布局宽高属性没有生效
            this.bottomLayout.addView(contentView);
            return Builder.this;
        }

        public Builder setHasAnimation(boolean hasAnimation) {
            this.hasAnimation = hasAnimation;
            return Builder.this;
        }

        public BottomDialog1 create() {

            BottomDialog1 bottomDialog = new BottomDialog1();  // 初始化 bottomDialog对象
            dialog = new Dialog(context, R.style.BottomDialog);  // 初始化 dialog 对象
            contentView.measure(0, 0);  // 测量 contentView
            bottomLayout.measure(0, 0); //  测量 bottomLayout
            // 为 Dialog 添加 View
            dialog.setContentView(bottomLayout);

            Window dialogWindow = dialog.getWindow();  // 从dialog对象中获取window对象
            // 设置 Gravity,使 contentView 的初始位置在 Window 最底部，
            // 如果不设置则默认在屏幕的中央，就达不到在屏幕底部显示的效果了
//            dialogWindow.setGravity(Gravity.BOTTOM);

            if (hasAnimation)
                dialogWindow.setWindowAnimations(R.style.dialog_animStyle);

            /*
             * 设置 Window 参数
             */
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = (int) context.getResources().getDisplayMetrics().widthPixels;
            lp.height = bottomLayout.getMeasuredHeight();
            Log.i("BottomDialog", "width = " + lp.width);
            Log.i("BottomDialog", "height = " + lp.height);
            lp.alpha = 9f; // 透明度
            dialogWindow.setAttributes(lp);

            bottomDialog.dialog = this.dialog;
            bottomDialog.contentView = this.contentView;

            return bottomDialog;
        }
    }

    public void show() {
        // 显示 dialog
        dialog.show();
    }
}
