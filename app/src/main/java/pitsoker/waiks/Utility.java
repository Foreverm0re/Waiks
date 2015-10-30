package pitsoker.waiks;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pitsoker on 15/09/2015.
 */
public class Utility {


        public static ArrayList<Event> readCalendarEvent(Context context) {
            ArrayList<Event> allEvent = new ArrayList<Event>();

            Cursor cursor = context.getContentResolver()
                    .query(
                            Uri.parse("content://com.android.calendar/events"),
                            new String[] { "calendar_id", "title", "description",
                                    "dtstart", "dtend", "eventLocation" }, null,
                            null, null);
            cursor.moveToFirst();
            // fetching calendars name
            String CNames[] = new String[cursor.getCount()];

            // fetching calendars id

            for (int i = 0; i < CNames.length; i++) {
                if(cursor.getString(1).contains("Birthday") == false){
                    allEvent.add(new Event(cursor.getString(1), new Date(Long.parseLong(cursor.getString(3)))));
                }
                //CNames[i] = cursor.getString(1);
                cursor.moveToNext();

            }
            cursor.close();
            return allEvent;

        }
        public void afficheAll(ArrayList<Event> event, ListView v){

        }

        public static String getDate(long milliSeconds) {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd/MM/yyyy/hh/mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return formatter.format(calendar.getTime());
        }
}
