package pk.wei.com.newpractice.part.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pk.wei.com.newpractice.R;

public class ViewPagerDemoActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerAdapter vpAdapter;
    private List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);

        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mViews = new ArrayList<>();

        mViews.add(inflater.inflate(R.layout.viewpager_one, null));
        mViews.add(inflater.inflate(R.layout.viewpager_two, null));
        mViews.add(inflater.inflate(R.layout.viewpager_three, null));

        vpAdapter = new ViewPagerAdapter(this, mViews);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_demo);
        mViewPager.setAdapter(vpAdapter);

    }
}
