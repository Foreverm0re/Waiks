package pitsoker.waiks.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Pitsoker on 02/10/2015.
 */
public class GenderDAO extends DAOBase {
    public static final String TABLE_NAME = "Gender";
    public static final String ID = "id";
    public static final String SEX = "sex";

    public static final String GENDER_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SEX + " TEXT);";
    public static final String GENDER_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public GenderDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Gender g) {
        open();
        ContentValues value = new ContentValues();
        value.put(GenderDAO.SEX, g.getSex());
        mDb.insert(GenderDAO.TABLE_NAME, null, value);
        close();
    }

    public void supprimer(long id) {
        open();
        mDb.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});
        close();
    }

    public void modifier(Gender g) {
        open();
        ContentValues value = new ContentValues();
        value.put(SEX, g.getSex());
        mDb.update(TABLE_NAME, value, ID + " = ?", new String[]{String.valueOf(g.getId())});
        close();
    }

    public String selectSex(long id) {
        open();
        Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where " + ID + " = ?", new String[]{String.valueOf(id)});
        String sex = " ";
        Gender g = new Gender(id, sex);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            sex = c.getString(1);
        }
        c.close();
        close();
        return sex;
    }
}
