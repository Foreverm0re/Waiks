package pitsoker.waiks.UtilityClasses;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pitsoker on 08/10/2015.
 */
public class TestButton extends Button {

    private Calendar datecode;

    public TestButton(Context context) {
        super(context);
    }

    public TestButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public TestButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setDateCode(Calendar code) {
        this.datecode = code;
    }

    public Calendar getDateCode() {

        return datecode;
    }



}
