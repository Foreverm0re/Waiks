package pitsoker.waiks.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pitsoker.waiks.UtilityClasses.AlarmReceiver;
import pitsoker.waiks.UtilityClasses.MediaPlayerService;
import pitsoker.waiks.R;

/**
 * Created by Pitsoker on 21/10/2015.
 */
public class WaiksAlarm extends AppCompatActivity implements View.OnClickListener{
    Button stop;
    MediaPlayerService mps = new MediaPlayerService();
    AlarmReceiver alarm = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);

        stop = (Button) findViewById(R.id.button);
        stop.setOnClickListener(this);
    }

    //lauching alarm
    @Override
    protected void onStart() {
        super.onStart();
        mps.onStartCommand(this);
    }

    //stopping the alarm
    @Override
    public void onClick(View v) {
        alarm.cancelAlarm(this);
        mps.onStopCommand();
        finish();
    }
}
