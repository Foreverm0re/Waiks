package pitsoker.waiks.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Pitsoker on 23/10/2015.
 */
public class WaikerTimesDao extends DAOBase {

    public static final String TABLE_NAME = "WaikerTimes";
    public static final String ID = "id";
    public static final String KEY = "key";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String WT_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY + " TEXT, " +
            HOUR + " INTEGER, " +
            MINUTE + " INTEGER);";
    public static final String WT_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public WaikerTimesDao(Context pContext) {
        super(pContext);
    }

    public void ajouter(WaikerTimes wt) {
        open();
        ContentValues value = new ContentValues();
        value.put(KEY, wt.getKey());
        value.put(HOUR, wt.getHour());
        value.put(MINUTE, wt.getMinute());
        mDb.insert(TABLE_NAME, null, value);
        close();
    }

    public void supprimer(String key) {
        open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{key});
        close();
    }

    public void modifier(WaikerTimes wt) {
        open();
        ContentValues value = new ContentValues();
        value.put(HOUR, wt.getHour());
        value.put(MINUTE, wt.getMinute());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{wt.getKey()});
        close();
    }


    public String selectWaikerTimeHour(String key) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + KEY + " = ? ", new String[] {key});
        String hour = "";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                hour = c.getString(2);
                c.moveToNext();
            }
        }
        c.close();
        close();
        return hour;
    }

    public String selectWaikerTimeMinute(String key) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + KEY + " = ? ", new String[] {key});
        String minute = "";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                minute = c.getString(3);
                c.moveToNext();
            }
        }
        c.close();
        close();
        return minute;
    }

    public void selectTest() {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME , null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                System.out.println("key: " + c.getString(1) + " nom: " + c.getString(4) + " heure: " + c.getString(3) + " minute: " +c.getString(2));
                c.moveToNext();
            }
        }
        c.close();
        close();

    }

}

