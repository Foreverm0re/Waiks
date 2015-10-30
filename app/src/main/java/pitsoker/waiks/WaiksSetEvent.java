package pitsoker.waiks;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Pitsoker on 22/09/2015.
 */
public class WaiksSetEvent extends AppCompatActivity implements ActionBar.TabListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    RelativeLayout him;
    RelativeLayout her;
    LinearLayout gene;
    LinearLayout Xop;
    LinearLayout activateEv;
    LinearLayout deactivateEv;


    String gender;
    Button activate;
    Button deactivate;
    CheckBox breakfast;
    CheckBox shower;

    CheckBox hairdressing;
    CheckBox shampoo;
    CheckBox depilation;
    CheckBox suit;

    CheckBox hairdressing2;
    CheckBox shaving;
    CheckBox suit2;

    //CheckBox Xoption;

    TextView time;
    TextView countTime;
    int totHour = 0;
    int totMin = 0;
    int i = 0;
    int j = 0;

    ArrayList<CheckBox> Xoption;

    Calendar dateOfEvent = Calendar.getInstance();
    AlarmReceiver alarm = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setevent_layout);

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.addTab(ab.newTab().setText("Back").setTabListener(this));

        activateEv = (LinearLayout) findViewById(R.id.activateEv);
        gene = (LinearLayout) findViewById(R.id.gene);
        Xop = (LinearLayout) findViewById(R.id.Xop);

        time = (TextView) findViewById(R.id.time);
        countTime = (TextView) findViewById(R.id.totaltime);

        breakfast = (CheckBox) findViewById(R.id.breakfast);
        breakfast.setOnCheckedChangeListener(this);
        shower = (CheckBox) findViewById(R.id.shower);
        shower.setOnCheckedChangeListener(this);

        her = (RelativeLayout) findViewById(R.id.her);

        hairdressing = (CheckBox) findViewById(R.id.hairdressing);
        hairdressing.setOnCheckedChangeListener(this);
        shampoo = (CheckBox) findViewById(R.id.shampoo);
        shampoo.setOnCheckedChangeListener(this);
        depilation = (CheckBox) findViewById(R.id.depilation);
        depilation.setOnCheckedChangeListener(this);
        suit = (CheckBox) findViewById(R.id.suit);
        suit.setOnCheckedChangeListener(this);

        him = (RelativeLayout) findViewById(R.id.him);

        hairdressing2 = (CheckBox) findViewById(R.id.hairdressing2);
        hairdressing2.setOnCheckedChangeListener(this);
        shaving = (CheckBox) findViewById(R.id.shaving);
        shaving.setOnCheckedChangeListener(this);
        suit2 = (CheckBox) findViewById(R.id.suit2);
        suit2.setOnCheckedChangeListener(this);

        activate = (Button) findViewById(R.id.activate);
        activate.setOnClickListener(this);

        deactivateEv = (LinearLayout) findViewById(R.id.deactivateEv);

        deactivate = (Button) findViewById(R.id.deactivate);
        deactivate.setOnClickListener(this);

        him.setVisibility(View.GONE);
        her.setVisibility(View.GONE);

        XoptionDAO xoDAO = new XoptionDAO(getApplicationContext());
        Data.numberOfXoption = Integer.parseInt(xoDAO.selectNumberOfXoption());
        i = Data.numberOfXoption;

        Xoption = new ArrayList<CheckBox>();

        if (i != 0) {
            for (i = 1; i < Data.numberOfXoption + 1; i++) {
                Xoption.add(new CheckBox(this));
                int last = Xoption.size() - 1;
                //Data.key[i] = i;
                Xoption.get(last).setLayoutParams(new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                Xoption.get(last).setText(xoDAO.selectXOptionName(i));
                Xoption.get(last).setTag(i);
                Xop.addView(Xoption.get(last));
            }
        }

        for(j = 0; j < Xoption.size(); j++) {
            Xoption.get(j).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    XoptionDAO xoDAO = new XoptionDAO(getApplicationContext());
                    int actualH = 0;
                    int actualM = 0;
                    actualH = Integer.parseInt(xoDAO.selectXOptionHour((Integer) buttonView.getTag()));
                    actualM = Integer.parseInt(xoDAO.selectXOptionMinute((Integer) buttonView.getTag()));
                    if (buttonView.isChecked()){
                        addTime(actualH, actualM);
                        updateText(totHour, totMin, countTime);
                    }else{
                        removeTime(actualH, actualM);
                        updateText(totHour, totMin, countTime);
                    }
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        GenderDAO daoG = new GenderDAO(getApplicationContext());
        EvActivationDAO eaDAO = new EvActivationDAO(getApplicationContext());

        SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
        String currentDate = formatter.format(Data.actualCode.getTime());
        String active = eaDAO.selectActive(currentDate);
        gender = daoG.selectSex(1);

        if(active.equals("yes")){
            deactivateEv.setVisibility(View.VISIBLE);
            activateEv.setVisibility(View.GONE);
        }

        else {

            deactivateEv.setVisibility(View.GONE);
            activateEv.setVisibility(View.VISIBLE);
            if (gender.equals("female")) {
                her.setVisibility(View.VISIBLE);
                him.setVisibility(View.GONE);

            }

            if (gender.equals("male")) {
                him.setVisibility(View.VISIBLE);
                her.setVisibility(View.GONE);

            }
        }
    }

    public void addTime(int heure, int minute){
        totHour = totHour + heure;
        totMin = totMin + minute;
        int rest = 0;
        if(totMin >= 60){
            rest = totMin - 60;
            totHour++;
            totMin = rest;
        }
    }

    public void removeTime(int heure, int minute){
        totHour = totHour - heure;
        int rest = 0;
        if (totMin < minute){
            totHour--;
            rest = minute - totMin;
            totMin = 60 - rest;
        }
        else{
            totMin = totMin - minute;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        Intent activiteSuivante = new Intent(WaiksSetEvent.this, WaiksCalendar.class);
        int id = tab.getPosition();
        switch(id){
            case 0:
                startActivity(activiteSuivante);
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        DatabaseHandler dbh = new DatabaseHandler(getApplicationContext(), null, null, 1);
        TimeOptionDAO dao = new TimeOptionDAO(getApplicationContext());
        int actualH = 0;
        int actualM = 0;
        int id = buttonView.getId();
        switch(id){
            case R.id.breakfast:
                actualH = Integer.parseInt(dao.selectHour(1));
                actualM = Integer.parseInt(dao.selectMinute(1));
                if(breakfast.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                        removeTime(actualH, actualM);
                        updateText(totHour, totMin, countTime);
                    }
                break;

            case R.id.shower:
                actualH = Integer.parseInt(dao.selectHour(2));
                actualM = Integer.parseInt(dao.selectMinute(2));
                if(shower.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;


            case R.id.hairdressing:
                actualH = Integer.parseInt(dao.selectHour(3));
                actualM = Integer.parseInt(dao.selectMinute(3));
                if(hairdressing.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;

            case R.id.shampoo:
                actualH = Integer.parseInt(dao.selectHour(4));
                actualM = Integer.parseInt(dao.selectMinute(4));
                if(shampoo.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;

            case R.id.depilation:
                actualH = Integer.parseInt(dao.selectHour(5));
                actualM = Integer.parseInt(dao.selectMinute(5));
                if(depilation.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;

            case R.id.suit:
                actualH = Integer.parseInt(dao.selectHour(6));
                actualM = Integer.parseInt(dao.selectMinute(6));
                if(suit.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;

            case R.id.hairdressing2:
                actualH = Integer.parseInt(dao.selectHour(7));
                actualM = Integer.parseInt(dao.selectMinute(7));
                if(hairdressing2.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;

            case R.id.shaving:
                actualH = Integer.parseInt(dao.selectHour(8));
                actualM = Integer.parseInt(dao.selectMinute(8));
                if(shaving.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;

            case R.id.suit2:
                actualH = Integer.parseInt(dao.selectHour(9));
                actualM = Integer.parseInt(dao.selectMinute(9));
                if(suit2.isChecked()) {
                    addTime(actualH, actualM);
                    updateText(totHour,totMin, countTime);
                }
                else{
                    removeTime(actualH, actualM);
                    updateText(totHour, totMin, countTime);
                }
                break;
        }

    }

    public void updateText(int h, int m, TextView v){
        v.setText(String.valueOf(h) + "h " + String.valueOf(m) + "min");
    }

    private String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM/dd/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }

    @Override
    public void onClick(View v) {
        EvActivationDAO eaDao = new EvActivationDAO(getApplicationContext());
        WaikerTimesDao wtDao = new WaikerTimesDao(this);
        if(v == activate){
            Data.dt.put(Data.actualCode, true);
            SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
            String currentDate = formatter.format(Data.actualCode.getTime());
            EvActivation ea = new EvActivation("yes", currentDate);
            eaDao.ajouter(ea);
            dateOfEvent.set(Data.actualCode.get(Calendar.YEAR), Data.actualCode.get(Calendar.MONTH), Data.actualCode.get(Calendar.DAY_OF_MONTH), Data.actualCode.get(Calendar.HOUR) - totHour, Data.actualCode.get(Calendar.MINUTE) - totMin);
            alarm.setAlarm(getApplicationContext(), dateOfEvent);
            WaikerTimes wt = new WaikerTimes(currentDate, dateOfEvent.get(Calendar.HOUR), dateOfEvent.get(Calendar.MINUTE));
            wtDao.ajouter(wt);
            Toast.makeText(this, "alarm set for " + dateOfEvent.get(Calendar.HOUR) + "h " + dateOfEvent.get(Calendar.MINUTE) + "min", Toast.LENGTH_LONG).show();
            finish();
        }
        if(v == deactivate){
            SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
            String currentDate = formatter.format(Data.actualCode.getTime());
            eaDao.supprimer(currentDate);
            Data.dt.remove(Data.actualCode);
            alarm.cancelAlarm(getApplicationContext());
            finish();
        }
    }
}
