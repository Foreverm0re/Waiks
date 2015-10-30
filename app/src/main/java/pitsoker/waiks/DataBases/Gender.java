package pitsoker.waiks.DataBases;

/**
 * Created by Pitsoker on 02/10/2015.
 */
public class Gender {

    private long id;
    private String sex;

    public Gender (long id, String sex){
        super();
        this.id = id;
        this.sex = sex;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {

        return sex;
    }

    public long getId() {
        return id;
    }
}
