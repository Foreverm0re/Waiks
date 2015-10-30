package pitsoker.waiks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import pitsoker.waiks.DataBases.Data;
import pitsoker.waiks.DataBases.DatabaseHandler;
import pitsoker.waiks.DataBases.FirstTimeIdentifierDao;
import pitsoker.waiks.DataBases.Gender;
import pitsoker.waiks.DataBases.GenderDAO;
import pitsoker.waiks.DataBases.TimeOption;
import pitsoker.waiks.DataBases.TimeOptionDAO;
import pitsoker.waiks.DataBases.XoptionDAO;
import pitsoker.waiks.R;


/**
 * Classe gerant l ecran des options
 */
public class WaiksOptions extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener{
    TextView breakfast;
    TextView shower;
    TextView hairdressing;
    TextView makeUp;
    TextView shampoo;
    TextView depilation;
    TextView suit;
    TextView hairdressing2;
    TextView shaving;
    TextView suit2;
    Button male;
    Button female;
    TextView gender;
    public final static int code0 = 1;
    public final static int code1 = 2;
    public final static int code2 = 3;
    public final static int code3 = 4;
    public final static int code4 = 5;
    public final static int code5 = 6;
    public final static int code6 = 7;
    public final static int code7 = 8;
    public final static int code8 = 9;
    public final static int code9 = 10;
    public final static int code10 = 11;
    public String gend;
    TableLayout her;
    TableLayout him;
    TableLayout gene;
    RelativeLayout gen;
    public int offset = 0;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.addTab(ab.newTab().setText("Back").setTabListener(this));
        ab.addTab(ab.newTab().setText("Add an option").setTabListener(this));

        GenderDAO daoG = new GenderDAO(getApplicationContext());
        TimeOptionDAO dao = new TimeOptionDAO(getApplicationContext());
        gend = daoG.selectSex(1);

        gene = (TableLayout) findViewById(R.id.genLayout);
        her = (TableLayout) findViewById(R.id.herLayout);
        him = (TableLayout) findViewById(R.id.himLayout);
        gen = (RelativeLayout) findViewById(R.id.genderLayout);

        male = (Button) findViewById(R.id.male);
        male.setOnClickListener(this);
        female = (Button) findViewById(R.id.female);
        female.setOnClickListener(this);
        gender = (TextView) findViewById(R.id.gender);

        breakfast = (TextView) findViewById(R.id.breakfast);
        breakfast.setOnClickListener(this);
        shower = (TextView) findViewById(R.id.shower);
        shower.setOnClickListener(this);

        hairdressing = (TextView) findViewById(R.id.hairdressing);
        hairdressing.setOnClickListener(this);
        makeUp = (TextView) findViewById(R.id.Makeup);
        makeUp.setOnClickListener(this);
        shampoo = (TextView) findViewById(R.id.Shampoo);
        shampoo.setOnClickListener(this);
        depilation = (TextView) findViewById(R.id.depilation);
        depilation.setOnClickListener(this);
        suit = (TextView) findViewById(R.id.suit);
        suit.setOnClickListener(this);

        hairdressing2 = (TextView) findViewById(R.id.hairdressing2);
        hairdressing2.setOnClickListener(this);
        shaving = (TextView) findViewById(R.id.Shaving);
        shaving.setOnClickListener(this);
        suit2 = (TextView) findViewById(R.id.suit2);
        suit2.setOnClickListener(this);


        // running only the first time the application is launched
        FirstTimeIdentifierDao ftiDao = new FirstTimeIdentifierDao(this);
        String identifier = ftiDao.selectIdentifier(1);
        if (identifier.equals("first")) {
            gen.setVisibility(View.VISIBLE);
            gene.setVisibility(View.GONE);
            him.setVisibility(View.GONE);
            her.setVisibility(View.GONE);

            Gender defaultGender = new Gender(1, "default");
            daoG.ajouter(defaultGender);

            TimeOption to = new TimeOption(code0, "breakfast", 0, 20);
            TimeOption to1 = new TimeOption(code1, "shower", 0, 20);
            TimeOption to2 = new TimeOption(code2, "hairdressing", 0, 20);
            TimeOption to3 = new TimeOption(code3, "makeup", 0, 20);
            TimeOption to4 = new TimeOption(code4, "shampoo", 0, 20);
            TimeOption to5 = new TimeOption(code5, "depilation", 0, 20);
            TimeOption to6 = new TimeOption(code6, "suit", 0, 20);
            TimeOption to7 = new TimeOption(code7, "hairdressing", 0, 20);
            TimeOption to8 = new TimeOption(code8, "shaving", 0, 20);
            TimeOption to9 = new TimeOption(code9, "suit", 0, 20);
            dao.ajouter(to);
            dao.ajouter(to1);
            dao.ajouter(to2);
            dao.ajouter(to3);
            dao.ajouter(to4);
            dao.ajouter(to5);
            dao.ajouter(to6);
            dao.ajouter(to7);
            dao.ajouter(to8);
            dao.ajouter(to9);
            //END
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseHandler dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);
        TimeOptionDAO dao = new TimeOptionDAO(getApplicationContext());
        GenderDAO daoG = new GenderDAO(getApplicationContext());
        gend = daoG.selectSex(1);

        updateHud(gend);
        update(dao);

        //Creating custom(user made) options
        XoptionDAO XoDAO = new XoptionDAO(getApplicationContext());
        Data.numberOfXoption = Integer.parseInt(XoDAO.selectNumberOfXoption());
        if(Data.numberOfXoption != 0) {
            for (int i = 1; i < Data.numberOfXoption + 1; i++) {
                TextView Xoption = new TextView(this);
                Xoption.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                Xoption.setText(XoDAO.selectXOptionName(i) + "  (" + XoDAO.selectXOptionHour(i) + "h " + XoDAO.selectXOptionMinute(i) + "min)");
                gene.addView(Xoption);
            }
        }
    }


    @Override
    public void onClick(View v) {
        GenderDAO daoG = new GenderDAO(getApplicationContext());

        if (v == breakfast){
            Data.actuel = Data.BREAKFAST;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code0);
        }
        if (v == shower){
            Data.actuel = Data.SHOWER;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code1);
        }
        if (v == hairdressing){
            Data.actuel = Data.HAIRDRESSING;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code2);
        }
        if (v == makeUp){
            Data.actuel = Data.MAKEUP;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code3);
        }
        if (v == shampoo){
            Data.actuel = Data.SHAMPOO;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code4);
        }
        if (v == depilation){
            Data.actuel = Data.DEPILATION;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code5);
        }
        if (v == suit){
            Data.actuel = Data.SUIT;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code6);
        }
        if (v == hairdressing2){
            Data.actuel = Data.HAIRDRESSING2;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code7);
        }
        if (v == shaving){
            Data.actuel = Data.SHAVING;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code8);
        }
        if (v == suit2){
            Data.actuel = Data.SUIT2;
            Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksSetOption.class);
            startActivityForResult(activiteSuivante, code9);
        }

        if(v == female) {
            Gender femaleG = new Gender(1, "female");
            daoG.modifier(femaleG);
            gend = daoG.selectSex(1);
            updateHud(gend);
        }

        if(v == male) {
            Gender maleG = new Gender(1, "male");
            daoG.modifier(maleG);
            gend = daoG.selectSex(1);
            updateHud(gend);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TimeOptionDAO dao = new TimeOptionDAO(getApplicationContext());
        XoptionDAO xodao = new XoptionDAO(getApplicationContext());

        System.out.println("heure :" + Data.heure[Data.actuel]);
        switch (requestCode) {
            case code0:
                TimeOption to = new TimeOption(code0, "breakfast", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], breakfast, "breakfast");
                break;
            case code1:
                TimeOption to1 = new TimeOption(code1, "shower", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to1);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], shower, "shower");
                break;
            case code2:
                TimeOption to2 = new TimeOption(code2, "hairdressing", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to2);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], hairdressing, "hairdressing");
                break;
            case code3:
                TimeOption to3 = new TimeOption(code3, "makeup", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to3);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], makeUp, "make up");
                break;
            case code4:
                TimeOption to4 = new TimeOption(code4, "shampoo", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to4);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], shampoo, "shampoo");
                break;
            case code5:
                TimeOption to5 = new TimeOption(code5, "depilation", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to5);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], depilation, "depilation");
                break;
            case code6:
                TimeOption to6 = new TimeOption(code6, "suit", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to6);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], suit, "suit");
                break;
            case code7:
                TimeOption to7 = new TimeOption(code7, "hairdressing", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to7);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], hairdressing2, "hairdressing");
                break;
            case code8:
                TimeOption to8 = new TimeOption(code8, "shaving", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to8);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], shaving, "shaving");
                break;
            case code9:
                TimeOption to9 = new TimeOption(code9, "suit", Data.heure[Data.actuel], Data.minute[Data.actuel]);
                dao.modifier(to9);
                changeView(Data.heure[Data.actuel], Data.minute[Data.actuel], suit2, "suit");
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void changeView(int h, int min, TextView v, String viewString){
            if(h == 0){
                v.setText(viewString +"  ("+ min + "min)");
            }
            else{
                v.setText(viewString + "  (" + h + "h " + min + "min)");
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.waiks_menu_option, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.googlesign:
                Intent i = new Intent(WaiksOptions.this, GoogleSign.class);
                startActivity(i);
                return true;
            case R.id.calendar:
                Intent i2 = new Intent(WaiksOptions.this, WaiksCalendar.class);
                startActivity(i2);
                return true;
            case R.id.adress:
                Intent i3 = new Intent(WaiksOptions.this, WaiksAdress.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // This is called when a tab is selected.
        Intent activiteSuivante = new Intent(WaiksOptions.this, WaiksAddOption.class);
        int id = tab.getPosition();
        switch(id){
            case 1:
                startActivity(activiteSuivante);
                break;
        }
    }

    // Implemented from ActionBar.TabListener
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // This is called when a previously selected tab is unselected.
    }

    // Implemented from ActionBar.TabListener
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // This is called when a previously selected tab is selected again.
        int id = tab.getPosition();
        switch(id){
            case 0:
                finish();
                break;
        }
    }

    //method updating the options timing
    public void update(TimeOptionDAO dao){

        Data.heure[Data.BREAKFAST] = Integer.parseInt(dao.selectHour(code0));
        Data.minute[Data.BREAKFAST] = Integer.parseInt(dao.selectMinute(code0));
        Data.heure[Data.SHOWER] = Integer.parseInt(dao.selectHour(code1));
        Data.minute[Data.SHOWER] = Integer.parseInt(dao.selectMinute(code1));
        Data.heure[Data.HAIRDRESSING] = Integer.parseInt(dao.selectHour(code2));
        Data.minute[Data.HAIRDRESSING] = Integer.parseInt(dao.selectMinute(code2));
        Data.heure[Data.SHAMPOO] = Integer.parseInt(dao.selectHour(code3));
        Data.minute[Data.SHAMPOO] = Integer.parseInt(dao.selectMinute(code3));
        Data.heure[Data.MAKEUP] = Integer.parseInt(dao.selectHour(code4));
        Data.minute[Data.MAKEUP] = Integer.parseInt(dao.selectMinute(code4));
        Data.heure[Data.DEPILATION] = Integer.parseInt(dao.selectHour(code5));
        Data.minute[Data.DEPILATION] = Integer.parseInt(dao.selectMinute(code5));
        Data.heure[Data.SUIT] = Integer.parseInt(dao.selectHour(code6));
        Data.minute[Data.SUIT] = Integer.parseInt(dao.selectMinute(code6));
        Data.heure[Data.HAIRDRESSING2] = Integer.parseInt(dao.selectHour(code7));
        Data.minute[Data.HAIRDRESSING2] = Integer.parseInt(dao.selectMinute(code7));
        Data.heure[Data.SHAVING] = Integer.parseInt(dao.selectHour(code8));
        Data.minute[Data.SHAVING] = Integer.parseInt(dao.selectMinute(code8));
        Data.heure[Data.SUIT2] = Integer.parseInt(dao.selectHour(code9));
        Data.minute[Data.SUIT2] = Integer.parseInt(dao.selectMinute(code9));

        changeView(Data.heure[Data.BREAKFAST], Data.minute[Data.BREAKFAST], breakfast, "breakfast");
        changeView(Data.heure[Data.SHOWER], Data.minute[Data.SHOWER], shower, "shower");
        changeView(Data.heure[Data.HAIRDRESSING], Data.minute[Data.HAIRDRESSING], hairdressing, "hairdressing");
        changeView(Data.heure[Data.MAKEUP], Data.minute[Data.MAKEUP], makeUp, "make up");
        changeView(Data.heure[Data.SHAMPOO], Data.minute[Data.SHAMPOO], shampoo, "shampoo");
        changeView(Data.heure[Data.DEPILATION], Data.minute[Data.DEPILATION], depilation, "depilation");
        changeView(Data.heure[Data.SUIT], Data.minute[Data.SUIT], suit, "suit");
        changeView(Data.heure[Data.HAIRDRESSING2], Data.minute[Data.HAIRDRESSING2], hairdressing2, "hairdressing");
        changeView(Data.heure[Data.SHAVING], Data.minute[Data.SHAVING], shaving, "shaving");
        changeView(Data.heure[Data.SUIT2], Data.minute[Data.SUIT2], suit2, "suit");
    }

    //updating the hud according to user gender
    public void updateHud(String gend){

        if(gend.equals("female")) {
            her.setVisibility(View.VISIBLE);
            gene.setVisibility(View.VISIBLE);
            him.setVisibility(View.GONE);
            gen.setVisibility(View.GONE);
        }

        if(gend.equals("male")) {
            him.setVisibility(View.VISIBLE);
            gene.setVisibility(View.VISIBLE);
            her.setVisibility(View.GONE);
            gen.setVisibility(View.GONE);
        }


    }

}