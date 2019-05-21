package pk.wei.com.newpractice.part.listviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pk.wei.com.newpractice.R;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int mResourceId;
    private LayoutInflater mInflater;

    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        mResourceId = resource;
        mInflater = LayoutInflater.from(getContext());
    }

    class ViewHolder {
        public TextView textView;
    }

    @Override
    public void add(@Nullable Fruit object) {
        super.add(object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(mResourceId, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.list_view_demo_item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Fruit fruit = getItem(position);
        holder.textView.setText(fruit.getName());

        return convertView;
    }
}
