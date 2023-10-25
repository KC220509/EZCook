package com.example.ezcook.model;

public class f_Collection_model {

    private int img_mode_collection;
    private String name_model_collection;

   private String privacy_model_collection;

    public f_Collection_model(int img_mode_collection, String name_model_collection, String privacy_model_collection) {
        this.img_mode_collection = img_mode_collection;
        this.name_model_collection = name_model_collection;
        this.privacy_model_collection = privacy_model_collection;
    }

    public int getImg_mode_collection() {
        return img_mode_collection;
    }

    public void setImg_mode_collection(int img_mode_collection) {
        this.img_mode_collection = img_mode_collection;
    }

    public String getName_model_collection() {
        return name_model_collection;
    }

    public void setName_model_collection(String name_model_collection) {
        this.name_model_collection = name_model_collection;
    }

    public String getPrivacy_model_collection() {
        return privacy_model_collection;
    }

    public void setPrivacy_model_collection(String privacy_model_collection) {
        this.privacy_model_collection = privacy_model_collection;
    }
}
