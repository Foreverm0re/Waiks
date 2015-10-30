package pitsoker.waiks.DataBases;

import java.util.Date;

/**
 * Created by Pitsoker on 18/09/2015.
 */
public class Event {
    private String nameOfEvent;
    private Date dateOfEvent;

    public Event(String nameOfEvent, Date dateOfEvent){
        this.nameOfEvent = nameOfEvent;
        this.dateOfEvent = dateOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public String getNameOfEvent() {

        return nameOfEvent;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }
}
