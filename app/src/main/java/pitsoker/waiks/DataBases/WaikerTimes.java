package pitsoker.waiks.DataBases;

/**
 * Created by Pitsoker on 23/10/2015.
 */
public class WaikerTimes {
    private String key;
    private int hour;
    private int minute;

    public WaikerTimes(String key, int hour, int minute) {

        this.key = key;
        this.minute = minute;
        this.hour = hour;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getKey() {

        return key;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

}
