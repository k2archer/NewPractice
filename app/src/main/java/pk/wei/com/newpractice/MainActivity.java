package pk.wei.com.newpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import pk.wei.com.newpractice.animator.AnimatorDemoActivity;
import pk.wei.com.newpractice.camera.CameraDemoActivity;
import pk.wei.com.newpractice.component.LifeCycleActivity;
import pk.wei.com.newpractice.customViewDemo.CustomViewDemoActivity;
import pk.wei.com.newpractice.fragment.dialog.DialogFragmentDemoActivity;
import pk.wei.com.newpractice.fragment.fragmentdemo.FragmentDemoActivity;
import pk.wei.com.newpractice.layout.ConstraintActivity;
import pk.wei.com.newpractice.part.PartDemoActivity;
import pk.wei.com.newpractice.part.listviewdemo.ListViewDemoActivity;
import pk.wei.com.newpractice.part.recyclerlistdemo.RecyclerDemoActivity;
import pk.wei.com.newpractice.part.viewpagerdemo.ViewPagerDemoActivity;
import pk.wei.com.newpractice.pattern.mvc.MVCDemoActivity;
import pk.wei.com.newpractice.pattern.mvp.view.MVPDemoActivity;
import pk.wei.com.newpractice.scrolldemo.ScrollClashDemoActivity;
import pk.wei.com.newpractice.service.ServiceMangerActivity;
import pk.wei.com.newpractice.sharedpreferencesdemo.PreferenceActivityDemoActivity;
import pk.wei.com.newpractice.sharedpreferencesdemo.SharedPreferencesDemoActivity;
import pk.wei.com.newpractice.thirdParty.RxJava.RxJavaDemoActivity;
import pk.wei.com.newpractice.thirdParty.glide.GlideDemoActivity;
import pk.wei.com.newpractice.thirdParty.retrofit.RetrofitDemoActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activiy_menu_main);

        ActionBar mActionbar = getSupportActionBar();
        if (mActionbar == null) {
            return;
        }

        mActionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionbar.setDisplayShowCustomEnabled(true);
        mActionbar.setCustomView(R.layout.action_bar_top_base);
        TextView tvTitle = (TextView) mActionbar.getCustomView().findViewById(
                R.id.tv_title);
        mActionbar.getCustomView().findViewById(R.id.iv_back)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

//        // 显示返回箭头
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        //
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        // 设置并显示 Logo
//        getSupportActionBar().setLogo(R.drawable.common_google_signin_btn_icon_dark);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

        initView();
    }

    Map<Integer, Class<?>> buttons = new HashMap<Integer, Class<?>>();

    private void initView() {
        // 注意这个 vGroup 并不是 activity.xml 中定义的根布局， mRootView 才是。
        ViewGroup vGroup = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        ViewGroup mRootView = (ViewGroup)vGroup.getChildAt(0);
        traversalView(mRootView); // 遍历 View , Button 设置不可用


        buttons.put(R.id.mvc, MVCDemoActivity.class);
        buttons.put(R.id.mvp, MVPDemoActivity.class);
        buttons.put(R.id.bt_activity, LifeCycleActivity.class);
        buttons.put(R.id.bt_service, ServiceMangerActivity.class);
        buttons.put(R.id.constraint_btn, ConstraintActivity.class);
        buttons.put(R.id.scroll_clash, ScrollClashDemoActivity.class);
        buttons.put(R.id.list_view_btn, ListViewDemoActivity.class);
        buttons.put(R.id.animator_btn, AnimatorDemoActivity.class);
        buttons.put(R.id.recycler_view_btn, RecyclerDemoActivity.class);
        buttons.put(R.id.view_pager_btn, ViewPagerDemoActivity.class);
        buttons.put(R.id.shared_preference_btn, SharedPreferencesDemoActivity.class);
        buttons.put(R.id.preference_activity_btn, PreferenceActivityDemoActivity.class);
        buttons.put(R.id.fragment_btn, FragmentDemoActivity.class);
        buttons.put(R.id.DialogFragment, DialogFragmentDemoActivity.class);
        buttons.put(R.id.action_bar_btn, ActionBarDemoActivity.class);
        buttons.put(R.id.custom_view, CustomViewDemoActivity.class);

        buttons.put(R.id.glide_demo, GlideDemoActivity.class);
        buttons.put(R.id.rxjava_demo, RxJavaDemoActivity.class);
        buttons.put(R.id.retrofit_demo, RetrofitDemoActivity.class);

        buttons.put(R.id.btn_test, CameraDemoActivity.class);

        buttons.put(R.id.bt_part_demo, PartDemoActivity.class);



        for (Map.Entry<Integer, Class<?>> entry : buttons.entrySet()) {
            findViewById(entry.getKey()).setOnClickListener(this);
            findViewById(entry.getKey()).setEnabled(true);  // 添加监听器后，启用 Button
        }
    }

    // rootView 传入第一步中获取到的 mRootView
    private void traversalView(ViewGroup rootView) {
        for(int i = 0; i<rootView.getChildCount(); i++)
        {
            View childVg = rootView.getChildAt(i);
            if(childVg instanceof ViewGroup)
                traversalView((ViewGroup) childVg);
            else if(childVg instanceof Button) {
                childVg.setEnabled(false);
            }
        }
    }

    @Override
    public void onClick(View v) {

        Class<?> cl = buttons.get(v.getId());
        if (cl != null) {
            startActivity(new Intent(MainActivity.this, cl));
            return;
        }

//        switch(v.getId()) {
//            case R.id.btn_start:
//                break;
//            default:
//
//        }
    }

}
