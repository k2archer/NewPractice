package pk.wei.com.newpractice.sharedpreferencesdemo;

import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import pk.wei.com.newpractice.R;

public class PreferenceActivityDemoActivity extends PreferenceActivity {

    PreferenceManager manager ;
    CheckBoxPreference checkBoxPreference;
    ListPreference listPreference;
    EditTextPreference editTextPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_preference_demo);

        addPreferencesFromResource(R.xml.mypreference);

        manager = getPreferenceManager();
        checkBoxPreference = (CheckBoxPreference) manager.findPreference("checkbox");
//        checkBoxPreference.setSummary("当前状态是 " + checkBoxPreference.isChecked());
        toastShow("当前状态是  "+checkBoxPreference.isChecked());

        Resources res = getResources();
        String[] entries = res.getStringArray(R.array.entries);
        String[] values = res.getStringArray(R.array.values);
        final Map<String, String> ens = new HashMap<>();
        for (int i=0; i < entries.length; i++) {
            ens.put(values[i], entries[i]);
        }

        listPreference = (ListPreference) manager.findPreference("list");
        listPreference.setSummary(listPreference.getEntry() + " " + listPreference.getValue());
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (preference == listPreference) {
                    String value = newValue.toString();
                    preference.setSummary(ens.get(value) + " " + value);
                }
                return true;
            }
        });
//        toastShow(listPreference.getEntry() + " " + listPreference.getValue());

        editTextPreference = (EditTextPreference) manager.findPreference("text");
        toastShow("Edit内容为 " + editTextPreference.getText());
    }

    void toastShow(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
