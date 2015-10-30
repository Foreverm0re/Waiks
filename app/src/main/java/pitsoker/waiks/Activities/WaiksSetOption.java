package pitsoker.waiks.Activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;



import java.util.Calendar;

import pitsoker.waiks.DataBases.Data;
import pitsoker.waiks.R;


/**
 * Created by Pitsoker on 08/09/2015.
 */
public class WaiksSetOption extends AppCompatActivity implements ActionBar.TabListener, View.OnClickListener {
    Button timing;
    Button repetition;
    TextView setrep;
    TextView settim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_option);
        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.addTab(ab.newTab().setText("Back").setTabListener(this));

        timing = (Button) findViewById(R.id.timing);
        timing.setOnClickListener(this);
        repetition = (Button) findViewById(R.id.repetition);
        repetition.setOnClickListener(this);

        setrep = (TextView) findViewById(R.id.setrep);
        settim = (TextView) findViewById(R.id.settim);
    }

    @Override
    public void onClick(View v) {
        if(v == timing){
        CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(this, timeSetListener, Calendar.getInstance().get(Calendar.HOUR),
                CustomTimePickerDialog.getRoundedMinute(Calendar.getInstance().get(Calendar.MINUTE) + CustomTimePickerDialog.TIME_PICKER_INTERVAL), true);
        timePickerDialog.setTitle("Set hours and minutes");
        timePickerDialog.show();
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // This is called when a tab is selected.

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
        Intent activiteSuivante = new Intent(WaiksSetOption.this, WaiksOptions.class);
        int id = tab.getPosition();
        switch (id) {
            case 0:
                startActivity(activiteSuivante);
                break;
        }
    }


    public static class CustomTimePickerDialog extends TimePickerDialog {

        public static final int TIME_PICKER_INTERVAL = 15;
        private boolean mIgnoreEvent = false;

        public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
            super(context, callBack, hourOfDay, minute, is24HourView);
        }

        @Override
        public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
            super.onTimeChanged(timePicker, hourOfDay, minute);
            if (!mIgnoreEvent) {
                minute = getRoundedMinute(minute);
                mIgnoreEvent = true;
                timePicker.setCurrentMinute(minute);
                mIgnoreEvent = false;
            }
        }

        public static int getRoundedMinute(int minute) {
            if (minute % TIME_PICKER_INTERVAL != 0) {
                int minuteFloor = minute - (minute % TIME_PICKER_INTERVAL);
                minute = minuteFloor + (minute == minuteFloor + 1 ? TIME_PICKER_INTERVAL : 0);
                if (minute == 60) minute = 0;
            }

            return minute;
        }
    }

    private CustomTimePickerDialog.OnTimeSetListener timeSetListener = new CustomTimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //*
            Data.heure[Data.actuel] = hourOfDay;
            Data.minute[Data.actuel] = minute;
            finish();
            //*/
        }
    };

}


