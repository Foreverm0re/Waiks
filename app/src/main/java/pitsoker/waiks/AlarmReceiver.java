package pitsoker.waiks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Pitsoker on 07/10/2015.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver{
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private int hour;
    private int minute;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, SchedulingService.class);
        startWakefulService(context, service);
    }

    public void setAlarm(Context context, Calendar gregCal) {

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Set the alarm's trigger time to parameters;
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
        String currentDate = formatter.format(gregCal.getTime());
        System.out.println(currentDate);

        calendar.set(Calendar.YEAR, gregCal.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, gregCal.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, gregCal.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, gregCal.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, gregCal.get(Calendar.MINUTE));

        String currentDate2 = formatter.format(calendar.getTime());
        System.out.println(currentDate2);
        // Set the alarm to fire at approximately the date entered, according to the device's
        // clock.
        alarmMgr.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), alarmIntent);

    }

    public void cancelAlarm(Context context) {
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }

    }
}
