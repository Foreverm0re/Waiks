package pitsoker.waiks.UtilityClasses;

import android.content.Context;
import android.media.MediaPlayer;

import pitsoker.waiks.R;

/**
 * class managing the sound played for the alarm
 */
public class MediaPlayerService {
    public static MediaPlayer mMediaPlayer = null;


    public void onStartCommand(Context context) {
        mMediaPlayer = MediaPlayer.create(context, R.raw.masterex);
        mMediaPlayer.start();
    }


    public void onStopCommand() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}

