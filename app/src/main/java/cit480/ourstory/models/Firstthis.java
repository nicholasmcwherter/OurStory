package cit480.ourstory.models;


import io.realm.RealmObject;

public class Firstthis extends RealmObject {

    private int id;
    private String firstthisTemplateName;
    private String imagePath1;
    private String imageName1;
    private String imagePath2;
    private String imageName2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstthisTemplateName() {
        return firstthisTemplateName;
    }

    public void setFirstthisTemplateName(String firstthisTemplateName) {
        this.firstthisTemplateName = firstthisTemplateName;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public String getImageName1() {
        return imageName1;
    }

    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public String getImageName2() {
        return imageName2;
    }

    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }
}
