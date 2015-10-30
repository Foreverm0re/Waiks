package pitsoker.waiks.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Pitsoker on 28/10/2015.
 */
public class FirstTimeIdentifierDao extends DAOBase {

    public FirstTimeIdentifierDao(Context pContext) {
        super(pContext);
    }
    public static final String TABLE_NAME = "FirstTime";
    public static final String ID = "id";
    public static final String KEY = "key";

    public static final String FTI_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY + " TEXT);";

    public static final String FTI_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public void ajouter(FirstTimeIdentifier fti) {
        open();
        ContentValues value = new ContentValues();
        value.put(KEY, fti.getKey());
        mDb.insert(TABLE_NAME, null, value);
        close();
    }

    public void supprimer(long id) {
        open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
        close();
    }

    public void modifier(FirstTimeIdentifier fti) {
        open();
        ContentValues value = new ContentValues();
        value.put(KEY, fti.getKey());
        mDb.update(TABLE_NAME, value, ID + " = ?", new String[]{String.valueOf(fti.getId())});
        close();
    }

    public String selectIdentifier(long id) {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + ID + " = ?", new String[]{String.valueOf(id)});
        String key = "";
        FirstTimeIdentifier fti = new FirstTimeIdentifier(id, key);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            key = c.getString(1);
        }
        c.close();
        close();
        return key;
    }

    public void selectTest() {
        open();
        Cursor c = mDb.rawQuery("select * FROM " + TABLE_NAME , null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                System.out.println("index: " + c.getString(0) + " key: " + c.getString(1));
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
                System.out.println("index: " + c.getString(0) + " key: " + c.getString(1));
                c.moveToNext();
            }
        }
        c.close();
        close();

    }

}
