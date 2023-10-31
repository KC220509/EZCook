package com.example.ezcook.model;

public class f_Like_model {

    private int img_mode_like;
    private String name_model_like;
    private String time_model_like;
    private String kcal_model_like;
//    private String favorite_model_like;
//    private String cmt_model_like;
//    private String note_model_like;


    public f_Like_model(int img_mode_like, String name_model_like, String time_model_like, String kcal_model_like) {
        this.img_mode_like = img_mode_like;
        this.name_model_like = name_model_like;
        this.time_model_like = time_model_like;
        this.kcal_model_like = kcal_model_like;
    }

    public int getImg_mode_like() {
        return img_mode_like;
    }

    public void setImg_mode_like(int img_mode_like) {
        this.img_mode_like = img_mode_like;
    }

    public String getName_model_like() {
        return name_model_like;
    }

    public void setName_model_like(String name_model_like) {
        this.name_model_like = name_model_like;
    }

    public String getTime_model_like() {
        return time_model_like;
    }

    public void setTime_model_like(String time_model_like) {
        this.time_model_like = time_model_like;
    }

    public String getKcal_model_like() {
        return kcal_model_like;
    }

    public void setKcal_model_like(String kcal_model_like) {
        this.kcal_model_like = kcal_model_like;
    }
}
