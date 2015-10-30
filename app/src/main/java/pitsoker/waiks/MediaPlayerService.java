package pitsoker.waiks;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Pitsoker on 21/10/2015.
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

