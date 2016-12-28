package cit480.ourstory.models;


import io.realm.RealmObject;

public class ThreeChoices extends RealmObject {

    private int id;
    private String threeChoicesTemplateName;
    private String imagePath1;
    private String imageName1;
    private String imagePath2;
    private String imageName2;
    private String imagePath3;
    private String imageName3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThreeChoicesTemplateName() {
        return threeChoicesTemplateName;
    }

    public void setThreeChoicesTemplateName(String threeChoicesTemplateName) {
        this.threeChoicesTemplateName = threeChoicesTemplateName;
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

    public String getImagePath3() {
        return imagePath3;
    }

    public void setImagePath3(String imagePath3) {
        this.imagePath3 = imagePath3;
    }

    public String getImageName3() {
        return imageName3;
    }

    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }
}