package pk.wei.com.newpractice.part.recyclerlistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


public class RecyclerDemoActivity extends AppCompatActivity {

    private RecyclerView recyclerVew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycler_demo);

        recyclerVew = new RecyclerView(this);
        setContentView(recyclerVew);
//        recyclerVew.setLayoutManager(new LinearLayoutManager(this));
//        recyclerVew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        recyclerVew.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerVew.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recyclerVew.setAdapter(new ListAdapter());
    }

}
