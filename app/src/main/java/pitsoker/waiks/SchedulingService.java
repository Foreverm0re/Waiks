package pitsoker.waiks;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Pitsoker on 21/10/2015.
 */
public class SchedulingService extends IntentService{
    public SchedulingService() {
        super("SchedulingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Intent dialogIntent = new Intent(getBaseContext(), WaiksAlarm.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(dialogIntent);

        AlarmReceiver.completeWakefulIntent(intent);
    }
}
