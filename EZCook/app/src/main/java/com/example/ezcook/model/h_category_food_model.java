package com.example.ezcook.model;

import java.io.Serializable;

public class h_category_food_model implements Serializable {
    private int pic_star;
    private String text_evaluate;
    private int pic_food;
    private String title_food;
    private String time_food;
    private String kcal_food;
    private int pic_level;

    public h_category_food_model(int pic_star, String text_evaluate, int pic_food, String title_food, String time_food, String kcal_food, int pic_level) {
        this.pic_star = pic_star;
        this.text_evaluate = text_evaluate;
        this.pic_food = pic_food;
        this.title_food = title_food;
        this.time_food = time_food;
        this.kcal_food = kcal_food;
        this.pic_level = pic_level;
    }

    public int getPic_star() {
        return pic_star;
    }

    public void setPic_star(int pic_star) {
        this.pic_star = pic_star;
    }

    public String getText_evaluate() {
        return text_evaluate;
    }

    public void setText_evaluate(String text_evaluate) {
        this.text_evaluate = text_evaluate;
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
