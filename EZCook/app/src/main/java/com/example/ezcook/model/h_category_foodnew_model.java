package com.example.ezcook.model;

import java.io.Serializable;

public class h_category_foodnew_model implements Serializable {
    private String id;
    private String pic;
    private String title;
    private String time;
    private String kcal;
    private String pic_user;
    private String title_user;

    public h_category_foodnew_model(String id, String pic, String title, String time, String kcal, String pic_user, String title_user) {
        this.id = id;
        this.pic = pic;
        this.title = title;
        this.time = time;
        this.kcal = kcal;
        this.pic_user = pic_user;
        this.title_user = title_user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
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

    public String getPic_user() {
        return pic_user;
    }

    public void setPic_user(String pic_user) {
        this.pic_user = pic_user;
    }

    public String getTitle_user() {
        return title_user;
    }

    public void setTitle_user(String title_user) {
        this.title_user = title_user;
    }
}
