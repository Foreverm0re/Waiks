package pitsoker.waiks.DataBases;

/**
 * Created by Pitsoker on 28/09/2015.
 */
public class TimeOption {

    private long id;
    private String option;
    private int heure;
    private int minute;


    public TimeOption(long id, String option, int heure, int minute) {
        super();
        this.id = id;
        this.option = option;
        this.heure = heure;
        this.minute = minute;

    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getOption() {
        return option;
    }


    public void setOption(String option) {
        this.option = option;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }


    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
