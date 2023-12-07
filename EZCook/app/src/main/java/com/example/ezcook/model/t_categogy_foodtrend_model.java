package com.example.ezcook.model;
public class t_categogy_foodtrend_model {
    private int pic_food;
    private String title_food;
    private String time_food;
    private String kcal_food;
    private int pic_level;

    public t_categogy_foodtrend_model(int pic_food, String title_food, String time_food, String kcal_food, int pic_level) {
        this.pic_food = pic_food;
        this.title_food = title_food;
        this.time_food = time_food;
        this.kcal_food = kcal_food;
        this.pic_level = pic_level;
    }

    public int getPic_food() {
        return pic_food;
    }

    public void setPic_food(int pic_food) {
        this.pic_food = pic_food;
    }

    public String getTitle_food() {
        return title_food;
    }

    public void setTitle_food(String title_food) {
        this.title_food = title_food;
    }

    public String getTime_food() {
        return time_food;
    }

    public void setTime_food(String time_food) {
        this.time_food = time_food;
    }

    public String getKcal_food() {
        return kcal_food;
    }

    public void setKcal_food(String kcal_food) {
        this.kcal_food = kcal_food;
    }

    public int getPic_level() {
        return pic_level;
    }

    public void setPic_level(int pic_level) {
        this.pic_level = pic_level;
    }
}