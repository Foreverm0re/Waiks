package pitsoker.waiks.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


/**
 * Created by Pitsoker on 28/09/2015.
 */
public class TimeOptionDAO extends DAOBase {

    public static final String TABLE_NAME = "Time";
    public static final String ID = "id";
    public static final String OPTION = "option";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String TIME_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OPTION + " TEXT, " +
            HOUR + " INTEGER, " +
            MINUTE + " INTEGER);";
    public static final String TIME_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public TimeOptionDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(TimeOption t) {
        open();
        ContentValues value = new ContentValues();
        value.put(TimeOptionDAO.OPTION, t.getOption());
        value.put(TimeOptionDAO.HOUR, t.getHeure());
        value.put(TimeOptionDAO.MINUTE, t.getMinute());
        mDb.insert(TimeOptionDAO.TABLE_NAME, null, value);
        close();
    }

    public void supprimer(long id) {
        open();
        mDb.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});
        close();
    }

    public void modifier(TimeOption t) {
        open();
        ContentValues value = new ContentValues();
        value.put(HOUR, t.getHeure());
        value.put(MINUTE, t.getMinute());
        mDb.update(TABLE_NAME, value, ID + " = ?", new String[]{String.valueOf(t.getId())});
        close();
    }


    public String selectHour(long id) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + ID + " = ?", new String[]{String.valueOf(id)});
        String heure = " ";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                heure = c.getString(2);
                c.moveToNext();
            }
        }
        c.close();
        close();
        return heure;
    }


    public String selectMinute(long id) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + ID + " = ?", new String[]{String.valueOf(id)});
        String minute = " ";

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
                System.out.println("index: " + c.getString(0) + " nom: " + c.getString(1) + " heure: " + c.getString(2) + " minute: " +c.getString(3));
                c.moveToNext();
            }
        }
        c.close();
        close();

    }

}
