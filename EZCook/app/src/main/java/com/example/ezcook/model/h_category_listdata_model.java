package com.example.ezcook.model;

import java.util.List;

public class h_category_listdata_model {
    private int type;
    private String title_list;
    private List<h_category_suggest_model> category_suggest_models;
    private List<h_category_foodnew_model> category_foodnew_models;

    public h_category_listdata_model(int type, String title_list, List<h_category_suggest_model> category_suggest_models, List<h_category_foodnew_model> category_foodnew_models) {
        this.type = type;
        this.title_list = title_list;
        this.category_suggest_models = category_suggest_models;
        this.category_foodnew_models = category_foodnew_models;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle_list() {
        return title_list;
    }

    public void setTitle_list(String title_list) {
        this.title_list = title_list;
    }

    public List<h_category_suggest_model> getCategory_suggest_models() {
        return category_suggest_models;
    }

    public void setCategory_suggest_models(List<h_category_suggest_model> category_suggest_models) {
        this.category_suggest_models = category_suggest_models;
    }

    public List<h_category_foodnew_model> getCategory_foodnew_models() {
        return category_foodnew_models;
    }

    public void setCategory_foodnew_models(List<h_category_foodnew_model> category_foodnew_models) {
        this.category_foodnew_models = category_foodnew_models;
    }
}
