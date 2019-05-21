package pk.wei.com.newpractice.part.recyclerlistdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pk.wei.com.newpractice.R;


class ListAdapter extends RecyclerView.Adapter {

    class ViewHolder extends RecyclerView.ViewHolder {

        private View root;
        private TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            titleView = (TextView) root.findViewById(R.id.list_view_demo_item_name);
        }

        public View getRoot() {
            return root;
        }

        public TextView getTitleView() {
            return titleView;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_demo_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.getTitleView().setText("Item " + datas[position].getName());
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    private ItemData[] datas = new ItemData[]{
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
            new ItemData("hi hello"),new ItemData("hi hello"),new ItemData("hi hello"),
    };
}
