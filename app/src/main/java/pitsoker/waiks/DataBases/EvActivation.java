package pitsoker.waiks.DataBases;

/**
 * Created by Pitsoker on 06/10/2015.
 */
public class EvActivation {

    private String active;
    private String key;


    public EvActivation (String active, String Key){
        super();

        this.active = active;
        this.key = Key;
    }


    public void setActive(String name) {
        this.active = name;
    }

    public String getActive() {

        return active;
    }


    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {

        return key;
    }
}