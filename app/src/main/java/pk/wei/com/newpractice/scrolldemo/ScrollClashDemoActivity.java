package pk.wei.com.newpractice.scrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pk.wei.com.newpractice.R;

public class ScrollClashDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_demo);

        HorizontalScrollEx horizontalScrollEx = (HorizontalScrollEx) findViewById(R.id.scroll_layout);

        ArrayList<String> data = new ArrayList<>();
        data.add("January");
        data.add("February");
        data.add("Match");
        data.add("April");
        data.add("May");
        data.add("May");
        data.add("July");
        data.add("August");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        ListView listView1 = (ListView) findViewById(R.id.list1);
        listView1.setAdapter(adapter);

        ArrayList<String> data2 = new ArrayList<>();
        data2.add("1");
        data2.add("2");
        data2.add("3");
        data2.add("4");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data2);
        ListView listView2 = (ListView) findViewById(R.id.list2);
        listView2.setAdapter(adapter2);

        ArrayList<String> data3 = new ArrayList<>();
        data3.add("One");
        data3.add("Two");
        data3.add("Three");
        data3.add("Four");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data3);
        ListView listView3 = (ListView) findViewById(R.id.list3);
        listView3.setAdapter(adapter3);
    }
}
