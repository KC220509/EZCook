package com.example.ezcook.model;

public class f_Save_model {
    private int pic;
    private String title;
    private String time;
    private String kcal;

    public f_Save_model(int pic, String title, String time, String kcal) {
        this.pic = pic;
        this.title = title;
        this.time = time;
        this.kcal = kcal;
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
}
