package com.example.ezcook.model;

import java.io.Serializable;

public class h_category_suggest_model implements Serializable {
    private int pic;
    private String title;
    private String time;
    private String kcal;
    private int pic_level;
    private boolean isAddToSave;

    public h_category_suggest_model(int pic, String title, String time, String kcal, int pic_level) {
        this.pic = pic;
        this.title = title;
        this.time = time;
        this.kcal = kcal;
        this.pic_level = pic_level;
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

    public int getPic_level() {
        return pic_level;
    }

    public void setPic_level(int pic_level) {
        this.pic_level = pic_level;
    }

    public boolean isAddToSave() {
        return isAddToSave;
    }

    public void setAddToSave(boolean addToSave) {
        isAddToSave = addToSave;
    }
}
