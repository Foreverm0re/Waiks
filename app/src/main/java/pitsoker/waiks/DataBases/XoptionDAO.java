package pitsoker.waiks.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import pitsoker.waiks.DataBases.DAOBase;
import pitsoker.waiks.DataBases.ExtraOption;

/**
 * Created by Pitsoker on 12/10/2015.
 */
public class XoptionDAO extends DAOBase {
    public static final String TABLE_NAME = "Xoption";
    public static final String ID = "id";
    public static final String KEY = "key";
    public static final String OPTION = "option";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String XOPTION_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY + " INTEGER, " +
            OPTION + " TEXT, " +
            HOUR + " INTEGER, " +
            MINUTE + " INTEGER);";
    public static final String XOPTION_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public XoptionDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(ExtraOption eo) {
        open();
        ContentValues value = new ContentValues();
        value.put(KEY, eo.getKey());
        value.put(OPTION, eo.getOption());
        value.put(HOUR, eo.getHour());
        value.put(MINUTE, eo.getMinute());
        mDb.insert(TABLE_NAME, null, value);
        close();
    }

    public void supprimer(int key) {
        open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(key)});
        close();
    }

    public void modifier(ExtraOption eo) {
        open();
        ContentValues value = new ContentValues();
        value.put(HOUR, eo.getHour());
        value.put(MINUTE, eo.getMinute());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(eo.getKey())});
        close();
    }

    public String selectXOptionName(int key) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + KEY + " = ? ", new String[] {String.valueOf(key)});
        String name = "";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                 name = c.getString(2);
                 c.moveToNext();
            }
        }
        c.close();
        close();
        return name;
    }

    public String selectXOptionHour(int key) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + KEY + " = ? ", new String[] {String.valueOf(key)});
        String hour = "";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                hour = c.getString(3);
                c.moveToNext();
            }
        }
        c.close();
        close();
        return hour;
    }

    public String selectXOptionMinute(int key) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + KEY + " = ? ", new String[] {String.valueOf(key)});
        String minute = "";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                minute = c.getString(4);
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

    public String selectNumberOfXoption() {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " ORDER BY " + KEY + " DESC LIMIT 1 ", null);
        String key = "0";

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                key = c.getString(1);
                c.moveToNext();
            }
        }
        c.close();
        close();
        return key;

    }

}
