package pitsoker.waiks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Pitsoker on 06/10/2015.
 */
public class EvActivationDAO extends DAOBase {
    public static final String TABLE_NAME = "Event";
    public static final String ID = "id";
    public static final String ACTIVE = "active";
    public static final String KEY = "key";

    public static final String EVENT_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ACTIVE + " TEXT, " +
            KEY + " TEXT);";

    public static final String EVENT_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public EvActivationDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(EvActivation ea) {
        open();
        ContentValues value = new ContentValues();
        value.put(ACTIVE, ea.getActive());
        value.put(KEY, ea.getKey());
        mDb.insert(TABLE_NAME, null, value);
        close();
    }

    public void supprimer(String key) {
        open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{key});
        close();
    }

    public void modifier(EvActivation ea) {
        open();
        ContentValues value = new ContentValues();
        value.put(ACTIVE, ea.getActive());
        value.put(KEY, ea.getKey());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(ea.getKey())});
        close();
    }

    public String selectActive(String key) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{key});
        String active = "";
        EvActivation ea = new EvActivation (active, key);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            active = c.getString(1);
        }
        c.close();
        close();
        return active;
    }

    public void selectTest() {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME , null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                System.out.println("index: " + c.getString(0) + " activation: " + c.getString(1) + " key: " + c.getString(2));
                c.moveToNext();
            }
        }
        c.close();
        close();

    }
    public void selectLast() {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC LIMIT 1 ", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                System.out.println("index: " + c.getString(0) + " activation: " + c.getString(1) + " key: " + c.getString(2));
                c.moveToNext();
            }
        }
        c.close();
        close();

    }

}


