package pk.wei.com.newpractice.part.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import pk.wei.com.newpractice.R;

public class ListViewDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private FruitListView mListView;

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear",
            "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear",
            "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        initListView();
        initView();
    }

    private void layoutAnim(int flags) {
    }
    public class ListViewTouchListener implements View.OnTouchListener {
        private float mFirstY, mCurrentY;
        private float mTouchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        private boolean mShow = true;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirstY > mTouchSlop) {
                        if (mShow) {
                            // hide
                            mShow = !mShow;
                        }
                    } else if (mFirstY - mCurrentY > mTouchSlop) {
                        if (mShow) {
                            // show
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:  break;
            }
            return false;
        }
    }

    private void initListView() {
        List<Fruit> fruitDatas = new LinkedList<>();
        fruitDatas.add(new Fruit("Apple"));
        for (String it : data) {
            fruitDatas.add(new Fruit(it));
        }

        ListView view = (ListView) findViewById(R.id.list_view_demo_list);
        mListView = new FruitListView(this, view, R.layout.list_view_demo_item, fruitDatas);
        mListView.setOnTouchListener(new ListViewTouchListener());



    }

    private void initView() {
        findViewById(R.id.list_view_demo_add_item_btn).setOnClickListener(this);
        findViewById(R.id.list_view_demo_delete_item_btn).setOnClickListener(this);
        findViewById(R.id.list_view_demo_scroll_item_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_view_demo_add_item_btn:
                mListView.add(new Fruit("Add item"));
                break;
            case R.id.list_view_demo_delete_item_btn:
                mListView.remove(1);
                break;
            case R.id.list_view_demo_scroll_item_btn:
                mListView.smoothToPosition(16);
                break;
        }
    }

}
