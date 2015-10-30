package pitsoker.waiks.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pitsoker on 28/09/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //OPTION TIME DATABASE
    public static final String TIME_ID = "id";
    public static final String TIME_OPTION = "option";
    public static final String TIME_HOUR = "hour";
    public static final String TIME_MINUTE = "minute";

    public static final String TIME_TABLE_NAME = "Time";
    public static final String TIME_TABLE_CREATE =
            "CREATE TABLE " + TIME_TABLE_NAME + " (" +
                    TIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TIME_OPTION + " TEXT, " +
                    TIME_HOUR + " INTEGER, " +
                    TIME_MINUTE + " INTEGER);";

    public static final String TIME_TABLE_DROP = "DROP TABLE IF EXISTS " + TIME_TABLE_NAME + ";";

    //GENDER (mini) DATABASE
    public static final String GENDER_ID = "id";
    public static final String GENDER_SEX = "sex";

    public static final String GENDER_TABLE_NAME = "Gender";
    public static final String GENDER_TABLE_CREATE =
            "CREATE TABLE " + GENDER_TABLE_NAME + " (" +
                    GENDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GENDER_SEX + " TEXT);";

    public static final String GENDER_TABLE_DROP = "DROP TABLE IF EXISTS " + GENDER_TABLE_NAME + ";";

    // EVENT ACTIVATION DATABASE

    public static final String EVENT_ID = "id";
    public static final String EVENT_ACTIVE = "active";
    public static final String EVENT_KEY = "key";

    public static final String EVENT_TABLE_NAME = "Event";
    public static final String EVENT_TABLE_CREATE =
            "CREATE TABLE " + EVENT_TABLE_NAME + " (" +
                    EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EVENT_ACTIVE + " TEXT, " +
                    EVENT_KEY + " TEXT);";

    public static final String EVENT_TABLE_DROP = "DROP TABLE IF EXISTS " + EVENT_TABLE_NAME + ";";

    // EXTRA OPTION DATABASE

    public static final String XOPTION_ID = "id";
    public static final String XOPTION_KEY = "key";
    public static final String XOPTION_OPTION = "option";
    public static final String XOPTION_HOUR = "hour";
    public static final String XOPTION_MINUTE = "minute";

    public static final String XOPTION_TABLE_NAME = "Xoption";
    public static final String XOPTION_TABLE_CREATE =
            "CREATE TABLE " + XOPTION_TABLE_NAME + " (" +
                    XOPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    XOPTION_KEY + " INTEGER, " +
                    XOPTION_OPTION + " TEXT, " +
                    XOPTION_HOUR + " INTEGER, " +
                    XOPTION_MINUTE + " INTEGER);";

    public static final String XOPTION_TABLE_DROP = "DROP TABLE IF EXISTS " + XOPTION_TABLE_NAME + ";";


    // WAIKER TIME DATABASE

    public static final String WT_ID = "id";
    public static final String WT_KEY = "key";
    public static final String WT_HOUR = "hour";
    public static final String WT_MINUTE = "minute";

    public static final String WT_TABLE_NAME = "WaikerTimes";
    public static final String WT_TABLE_CREATE =
            "CREATE TABLE " + WT_TABLE_NAME + " (" +
                    WT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WT_KEY + " TEXT, " +
                    WT_HOUR + " INTEGER, " +
                    WT_MINUTE + " INTEGER);";

    public static final String WT_TABLE_DROP = "DROP TABLE IF EXISTS " + WT_TABLE_NAME + ";";

    // FIRST TIME LAUCH IDENTIFIER DATABASE

    public static final String FTI_ID = "id";
    public static final String FTI_KEY = "key";

    public static final String FTI_TABLE_NAME = "FirstTime";
    public static final String FTI_TABLE_CREATE =
            "CREATE TABLE " + FTI_TABLE_NAME + " (" +
                    FTI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FTI_KEY + " TEXT);";

    public static final String FTI_TABLE_DROP = "DROP TABLE IF EXISTS " + FTI_TABLE_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TIME_TABLE_CREATE);
        db.execSQL(GENDER_TABLE_CREATE);
        db.execSQL(EVENT_TABLE_CREATE);
        db.execSQL(XOPTION_TABLE_CREATE);
        db.execSQL(WT_TABLE_CREATE);
        db.execSQL(FTI_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TIME_TABLE_DROP);
        db.execSQL(GENDER_TABLE_DROP);
        db.execSQL(EVENT_TABLE_DROP);
        db.execSQL(XOPTION_TABLE_DROP);
        db.execSQL(WT_TABLE_DROP);
        db.execSQL(FTI_TABLE_DROP);
        onCreate(db);
    }
}
