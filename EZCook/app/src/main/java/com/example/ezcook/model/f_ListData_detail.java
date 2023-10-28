package com.example.ezcook.model;

import java.util.List;

public class f_ListData_detail {
    private int type;
    private String title_content;
    private List<f_ingredient_detail> listIngredient;
    private List<f_Step_detail> listStep;

    public f_ListData_detail(int type, String title_content, List<f_ingredient_detail> listIngredient, List<f_Step_detail> listStep) {
        this.type = type;
        this.title_content = title_content;
        this.listIngredient = listIngredient;
        this.listStep = listStep;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle_content() {
        return title_content;
    }

    public void setTitle_content(String title_content) {
        this.title_content = title_content;
    }

    public List<f_ingredient_detail> getListIngredient() {
        return listIngredient;
    }

    public void setListIngredient(List<f_ingredient_detail> listIngredient) {
        this.listIngredient = listIngredient;
    }

    public List<f_Step_detail> getListStep() {
        return listStep;
    }

    public void setListStep(List<f_Step_detail> listStep) {
        this.listStep = listStep;
    }
}
