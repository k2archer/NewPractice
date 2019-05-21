package pk.wei.com.newpractice.part.listviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pk.wei.com.newpractice.R;


public class FruitListView extends ListView {
    private ListView mListView;
    private FruitAdapter mAdapter;
    private List<Fruit> mData;
    private LayoutInflater mInflater;
    private int mResourceId;


    public FruitListView(@NonNull Context context, @NonNull ListView listVew, int itemResourceId,@NonNull List<Fruit> data) {
        super(context);
        mData = data;
        mAdapter = new FruitAdapter(context, itemResourceId, mData);

        mListView = (ListView) listVew;
        mListView.setAdapter(mAdapter);
//        mListView.setDivider(null);  // 设置分隔线为透明
//        mListView.setDividerHeight(2); // 设置分隔线厚度

    }

    public void add(Fruit object) {
        if (mAdapter != null)
            mAdapter.add(object);
    }

    public void remove(Fruit object) {
        if (mAdapter != null)
            mAdapter.remove(object);
    }

    public void remove(int position) {
//        if (mData != null && mAdapter != null)
        {
            mData.remove(position);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void clear() {
//        if (mAdapter != null)
            mAdapter.clear();
    }

    private void updateListItem(int position, Fruit Data){
        int visiblePosition = mListView.getFirstVisiblePosition();
        View v = mListView.getChildAt(position - visiblePosition);
        TextView tv = (TextView) v.findViewById(R.id.list_view_demo_item_name);
        tv.setText(Data.getName());
    }


    public void smoothToPosition(int position) {
        int distance = mListView.getSelectedItemPosition();
        int duration = 1000;
        mListView.smoothScrollBy(distance, duration);
        mListView.smoothScrollByOffset(1);
        mListView.smoothScrollToPosition(position);
    }
}
