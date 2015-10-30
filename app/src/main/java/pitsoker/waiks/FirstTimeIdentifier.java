package pitsoker.waiks;

/**
 * Created by Pitsoker on 28/10/2015.
 */
public class FirstTimeIdentifier {

    private String key;
    private long id;

    public FirstTimeIdentifier(long id, String key) {
        this.id = id;
        this.key = key;
    }

    public long getId() {

        return id;
    }

    public String getKey() {

        return key;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setKey(String key) {

        this.key = key;
    }
}
