package pitsoker.waiks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pitsoker.waiks.R;

/**
 * Created by Pitsoker on 21/09/2015.
 */
public class WaiksAdress extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener{
    Button voiture;
    Button metro;
    Button pied;
    Button velo;
    TextView transpSelected;
    TextView adresse;
    EditText saisie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiksadress_layout);
        voiture = (Button)findViewById(R.id.voiture);
        metro = (Button)findViewById(R.id.metro);
        pied = (Button)findViewById(R.id.pied);
        velo = (Button)findViewById(R.id.velo);
        adresse = (TextView)findViewById(R.id.adresse);

        saisie = (EditText)findViewById(R.id.saisie);
        saisie.addTextChangedListener(ttW);

        transpSelected = (TextView) findViewById(R.id.transp_selected);

        voiture.setOnClickListener(this);
        metro.setOnClickListener(this);
        pied.setOnClickListener(this);
        velo.setOnClickListener(this);

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.addTab(ab.newTab().setText("Back").setTabListener(this));
        ab.addTab(ab.newTab().setText("Next").setTabListener(this));
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        Intent activiteSuivante = new Intent(WaiksAdress.this, WaiksOptions.class);
        int id = tab.getPosition();
        switch(id){
            case 1:
                startActivity(activiteSuivante);
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.waiks_menu_adress, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.googlesign:
                Intent i = new Intent(WaiksAdress.this, GoogleSign.class);
                startActivity(i);
                return true;
            case R.id.calendar:
                Intent i2 = new Intent(WaiksAdress.this, WaiksCalendar.class);
                startActivity(i2);
                return true;
            case R.id.option:
                Intent i3 = new Intent(WaiksAdress.this, WaiksOptions.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Intent activiteSuivante = new Intent(WaiksAdress.this, GoogleSign.class);
        int id = tab.getPosition();
        switch(id){
            case 0:
                startActivity(activiteSuivante);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v == voiture){
            transpSelected.setText("by car");
        }
        if(v == metro){
            transpSelected.setText("by underground");
        }
        if(v == pied){
            transpSelected.setText("by foot");
        }
        if(v == velo){
            transpSelected.setText("by bicycle");
        }
    }

    TextWatcher ttW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
