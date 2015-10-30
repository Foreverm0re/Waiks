package pitsoker.waiks.DataBases;

/**
 * Created by Pitsoker on 12/10/2015.
 */
public class ExtraOption {
    private int key;
    private String option;
    private int hour;
    private int minute;

    public ExtraOption(int key, String option,  int hour, int minute) {

        this.key = key;
        this.option = option;
        this.hour = hour;
        this.minute = minute;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getKey() {
        return key;
    }

    public String getOption() {
        return option;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }


}
