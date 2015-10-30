package pitsoker.waiks.UtilityClasses;

import android.app.IntentService;
import android.content.Intent;

import pitsoker.waiks.Activities.WaiksAlarm;

/**
 * Alarm Service
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
