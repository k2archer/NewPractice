package pk.wei.com.newpractice.part;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import pk.wei.com.newpractice.R;

public class PartDemoActivity extends AppCompatActivity {


    @BindView(R.id.s_spinner_dialog)
    AppCompatSpinner sSpinnerDialog;
    @BindView(R.id.bt_normal_dialog)
    Button btDialog;

    @BindView(R.id.bt_item_dialog)
    Button btItemDialog;

    @BindArray(R.array.languages)
    String[] languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_demo);
        ButterKnife.bind(this);

        Spinner spinner = (Spinner) findViewById(R.id.s_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                Toast.makeText(PartDemoActivity.this,
                        "你点击的是:" + languages[pos], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @OnItemSelected(R.id.s_spinner_dialog)
    public void onSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] languages = getResources().getStringArray(R.array.languages);
        Toast.makeText(PartDemoActivity.this,
                "你点击的是:" + languages[position], Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_normal_dialog)
    public void onDialogClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("标题")
                .setMessage("内容")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false);


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.bt_item_dialog)
    public void onItemDialog() {
        handleLanguageDialog().show();
    }

    private AlertDialog handleLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("编程语言")
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:// English
                                break;
                            case 1:// عربى
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
