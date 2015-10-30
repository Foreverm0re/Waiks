package pitsoker.waiks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Pitsoker on 02/09/2015.
 */
public class WaiksCalendar extends AppCompatActivity implements View.OnClickListener {
    public int code;
    private static Context calContext;
    Button bouton;
    private Button currentMonth;
    private Button prevWeek;
    private Button nextWeek;
    private Button currentWeek;
    private ListView calendarView;
    private Calendrier adapter;
    public int week, month, year;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);


        Calendar cal = Calendar.getInstance();
        week = cal.get(Calendar.WEEK_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        // Bouton
        currentMonth = (Button) this.findViewById(R.id.MonthYear);
        currentMonth.setEnabled(false);
        currentWeek = (Button) findViewById(R.id.Week);
        currentWeek.setEnabled(false);

        //Button Prev and Next
        prevWeek = (Button) this.findViewById(R.id.previousWeek);
        prevWeek.setOnClickListener(this);
        nextWeek = (Button) this.findViewById(R.id.nextWeek);
        nextWeek.setOnClickListener(this);

        calendarView = (ListView) findViewById(R.id.calendar);

       setGridCellAdapterToDate(week, month, year);

    }


    @Override
    protected void onStart() {
        super.onStart();
        EvActivationDAO eaDao = new EvActivationDAO(getApplicationContext());
        setGridCellAdapterToDate(week, month, year);
    }

    private void setGridCellAdapterToDate(int week, int month, int year) {
        Calendar calg = new GregorianCalendar(year, month, 1);
        adapter = new Calendrier(getApplicationContext(), R.id.calendar_day_gridcell, week, month, year);
        calg.set(year, month, calg.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(calg.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE) + " " + calg.get(Calendar.YEAR));
        currentWeek.setText("Week " + week);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == code) {
        }


    }

    @Override
    public void onClick(View v) {
        if (v == prevWeek) {
            if (week <= 1) {
                week = 5;
                month--;
                if(month == 12){
                    year--;
                }
            } else {
                week--;
            }
            setGridCellAdapterToDate(week, month, year);
        }
        if (v == nextWeek) {
            if (week > 4) {
                week = 1;
                month++;
                if(month == 1){
                    year++;
                }
            } else {
                week++;
            }
            setGridCellAdapterToDate(week, month, year);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.waiks_menu_calendar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.googlesign:
                Intent i = new Intent(WaiksCalendar.this, GoogleSign.class);
                startActivity(i);
                return true;
            case R.id.option:
                Intent i2 = new Intent(WaiksCalendar.this, WaiksOptions.class);
                startActivity(i2);
                return true;
            case R.id.adress:
                Intent i3 = new Intent(WaiksCalendar.this, WaiksAdress.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    class Calendrier extends BaseAdapter implements View.OnClickListener {
        private Context context;


        private Event[] ev;
        private int week, month, year;
        private int daysInMonth;
        private int daysInWeek;
        private int currentDayOfMonth;
        private int currentWeekDay;
        Button gridcell;
        TextView num_events_per_day;
        Button activation;


        public Calendrier(Context context, int textViewRessourceId, int week, int month, int year) {
            super();
            this.context = context;
            this.week = week;
            this.month = month;
            this.year = year;

            Calendar calendar = new GregorianCalendar(year, month, 1);
            Calendar cal2 = Calendar.getInstance();
            currentDayOfMonth = cal2.get(Calendar.DAY_OF_MONTH);
            currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK);

            daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            daysInWeek = 7;

            ArrayList<Event> tabOfEvent = Utility.readCalendarEvent(context);
            this.ev = new Event[daysInMonth];

            HashMap<Date,Boolean> dayDone= new HashMap<Date,Boolean>();

            for (Event i : tabOfEvent) {
                calendar.setTime(i.getDateOfEvent());

                Date d=new Date(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                if (calendar.get(Calendar.MONTH) == this.month && calendar.get(Calendar.YEAR) == this.year && !dayDone.containsKey(d)) {
                        ev[calendar.get(Calendar.DAY_OF_MONTH) - 1] = i;
                        dayDone.put(d, true);
                }
            }
            for (int i = 0; i < daysInMonth; i++) {
                if (ev[i] == null) {
                    ev[i] = new Event("No event", new Date(year, month, i + 1));
                }
            }
        }


        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int waikerHour =0;
            int waikerMinute =0;

            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.calendar_day_row, parent, false);
            }

            activation = (Button) row.findViewById(R.id.calendar_event_activation);
            gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
            num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);

            Event event = ev[position];
            Calendar cal = new GregorianCalendar(year, month, position + 1);


            // Set the Day GridCell
            gridcell.setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.FRANCE) + " " + cal.get(Calendar.DAY_OF_MONTH));
            num_events_per_day.setText(event.getNameOfEvent());
            // gridcell.setTag(theday + "-" + (themonth) + "-" + theyear);

            EvActivationDAO eaDao = new EvActivationDAO(getApplicationContext());
            WaikerTimesDao wtDao = new WaikerTimesDao(getApplicationContext());

            Calendar calp = Calendar.getInstance();

            calp.set(Calendar.YEAR, year);
            calp.set(Calendar.MONTH, month);
            calp.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
            calp.set(Calendar.HOUR_OF_DAY, event.getDateOfEvent().getHours());
            calp.set(Calendar.MINUTE, event.getDateOfEvent().getMinutes());

            ((TestButton) activation).setDateCode(calp);

            if(event.getNameOfEvent().equals("No event") == true){
                activation.setText("N/A");
                activation.setEnabled(false);
            }
            else{
                num_events_per_day.setText(event.getNameOfEvent() + " - " + getHourTime(event.getDateOfEvent()));
                SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
                String currentDate = formatter.format(calp.getTime());
                String active = eaDao.selectActive(currentDate);
                activation.setText("Not set");
                activation.setEnabled(true);
                activation.setOnClickListener(this);
                if(Data.dt.containsKey(currentDate)) {
                    if (Data.dt.get(currentDate) || active.equals("yes")) {
                        activation.setText("Set !");
                    }
                }
                else if(active.equals("yes")){
                    activation.setText("Set !");
                    waikerHour = Integer.parseInt(wtDao.selectWaikerTimeHour(currentDate));
                    waikerMinute = Integer.parseInt(wtDao.selectWaikerTimeMinute(currentDate));
                    num_events_per_day.setText(event.getNameOfEvent() + " - " + getHourTime(event.getDateOfEvent()) + " - " + "set for " + waikerHour + "h" + waikerMinute);
                }
                //if(isPassed(d)){
                //    activation.setText("Passed");
                //    activation.setEnabled(false);
                //}

            }

            if (cal.get(Calendar.MONTH) == this.month && cal.get(Calendar.YEAR) == this.year && cal.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth) {
                gridcell.setTextColor(Color.BLUE);
            } else {
                gridcell.setTextColor(Color.WHITE);
            }
            return row;
        }

        @Override
        public int getCount() {
            return ev.length;
        }

        @Override
        public String getItem(int position) {
            return ev[position].toString();
        }

        @Override
        public void onClick(View view) {
            //VERS LA PAGE DE CREATION D EVENEMENT
            Data.actualCode = ((TestButton) view).getDateCode();
            Intent i = new Intent(WaiksCalendar.this, WaiksSetEvent.class);
            startActivityForResult(i, code);

        }

        private String getDateTime(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "MM/dd/yyyy", Locale.getDefault());
            return dateFormat.format(date);
        }

        private String getHourTime(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "HH:mm", Locale.getDefault());
            return dateFormat.format(date);
        }

        private boolean isPassed(Date d){
            Calendar cl = Calendar.getInstance();
            if(d.getMonth() < cl.get(Calendar.MONTH) || d.getDay() < cl.get(Calendar.DAY_OF_MONTH)){
                return true;
            }
            return false;
        }
    }
}