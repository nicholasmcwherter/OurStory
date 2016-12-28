package cit480.ourstory.models;


import io.realm.RealmObject;

public class PECS extends RealmObject {

    private int id;
    private String pecsImage;
    private String pecsImagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPecsImage() {
        return pecsImage;
    }

    public void setPecsImage(String pecsImage) {
        this.pecsImage = pecsImage;
    }

    public String getPecsImagePath() {
        return pecsImagePath;
    }

    public void setPecsImagePath(String pecsImagePath) {
        this.pecsImagePath = pecsImagePath;
    }


}
