package com.example.ezcook.model;

import java.util.List;

public class category_listdata_model {
    private int type;
    private String title_list;
    private List<category_suggest_model> category_suggest_models;
    private List<category_foodnew_model> category_foodnew_models;

    public category_listdata_model(int type, String title_list, List<category_suggest_model> category_suggest_models, List<category_foodnew_model> category_foodnew_models) {
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

    public List<category_suggest_model> getCategory_suggest_models() {
        return category_suggest_models;
    }

    public void setCategory_suggest_models(List<category_suggest_model> category_suggest_models) {
        this.category_suggest_models = category_suggest_models;
    }

    public List<category_foodnew_model> getCategory_foodnew_models() {
        return category_foodnew_models;
    }

    public void setCategory_foodnew_models(List<category_foodnew_model> category_foodnew_models) {
        this.category_foodnew_models = category_foodnew_models;
    }
}
