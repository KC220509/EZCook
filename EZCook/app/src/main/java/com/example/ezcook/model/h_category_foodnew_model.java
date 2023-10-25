package com.example.ezcook.model;

public class h_category_foodnew_model {
    private int pic;
    private String title;
    private String time;
    private String kcal;
    private int pic_user;
    private String title_user;

    public h_category_foodnew_model(int pic, String title, String time, String kcal, int pic_user, String title_user) {
        this.pic = pic;
        this.title = title;
        this.time = time;
        this.kcal = kcal;
        this.pic_user = pic_user;
        this.title_user = title_user;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public int getPic_user() {
        return pic_user;
    }

    public void setPic_user(int pic_user) {
        this.pic_user = pic_user;
    }

    public String getTitle_user() {
        return title_user;
    }

    public void setTitle_user(String title_user) {
        this.title_user = title_user;
    }
}
