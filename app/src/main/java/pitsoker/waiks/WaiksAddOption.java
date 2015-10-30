package pitsoker.waiks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Pitsoker on 25/09/2015.
 */
public class WaiksAddOption extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener {
    Button create;
    EditText name;
    EditText heure;
    EditText minute;
    int key = 1;
    String n;
    String h;
    String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addoption_layout);

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.addTab(ab.newTab().setText("Back").setTabListener(this));



        create = (Button) findViewById(R.id.create);
        name = (EditText) findViewById(R.id.name_entry);
        heure = (EditText) findViewById(R.id.hour_entry);
        minute = (EditText) findViewById(R.id.minute_entry);
        name.addTextChangedListener(ttW);
        heure.addTextChangedListener(ttW);
        minute.addTextChangedListener(ttW);

        create.setOnClickListener(this);

    }

    TextWatcher ttW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            n = name.getText().toString();
            h = heure.getText().toString();
            m = minute.getText().toString();

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // This is called when a previously selected tab is selected again.
        finish();
    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(n)) {
            name.setError("please entry a name");
        }
        if (TextUtils.isEmpty(h) || !isParsable(h)) {
            heure.setError("please entry hour timing");
        }
        if (TextUtils.isEmpty(m) || !isParsable(m)) {
            minute.setError("please entry minute timing");
        }
        else if(!TextUtils.isEmpty(n) && !TextUtils.isEmpty(h) && isParsable(h) && !TextUtils.isEmpty(m) && isParsable(m)){
            XoptionDAO XOdao = new XoptionDAO(getApplicationContext());
            Data.numberOfXoption++;
            ExtraOption eo = new ExtraOption(Data.numberOfXoption, n, Integer.parseInt(h), Integer.parseInt(m));
            XOdao.ajouter(eo);
            finish();
        }
    }

    public static boolean isParsable(String input){
            boolean parsable = true;
            try{
                Integer.parseInt(input);
            }catch(NumberFormatException e){
                parsable = false;
            }
            return parsable;
    }
}

