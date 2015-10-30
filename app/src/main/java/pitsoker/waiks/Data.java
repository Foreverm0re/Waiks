package pitsoker.waiks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Pitsoker on 15/09/2015.
 */
public class Data {

    public final static int BREAKFAST =0, SHOWER = 1, HAIRDRESSING = 2, MAKEUP = 3, SHAMPOO = 4, DEPILATION = 5, SUIT = 6, HAIRDRESSING2 = 7, SHAVING = 8, SUIT2 = 9;
    public final static int size = 10;
    public final static int size2 = 8;
    public static int numberOfXoption = 0;

    public static HashMap<Calendar, Boolean> dt = new HashMap<>();
    public static Calendar actualCode = Calendar.getInstance();
    public static int[] key = new int[size2];
    public static int[] heure = new int[size];
    public static int[] minute = new int[size];
    public static int actuel = 0;


}
