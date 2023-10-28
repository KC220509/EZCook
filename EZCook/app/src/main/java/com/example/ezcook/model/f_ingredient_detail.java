package com.example.ezcook.model;

public class f_ingredient_detail {
    private int img_ingredient;
    private String name_ingredient;
    private String mass_ingredient;

    public f_ingredient_detail(int img_ingredient, String name_ingredient, String mass_ingredient) {
        this.img_ingredient = img_ingredient;
        this.name_ingredient = name_ingredient;
        this.mass_ingredient = mass_ingredient;
    }

    public int getImg_ingredient() {
        return img_ingredient;
    }

    public void setImg_ingredient(int img_ingredient) {
        this.img_ingredient = img_ingredient;
    }

    public String getName_ingredient() {
        return name_ingredient;
    }

    public void setName_ingredient(String name_ingredient) {
        this.name_ingredient = name_ingredient;
    }

    public String getMass_ingredient() {
        return mass_ingredient;
    }

    public void setMass_ingredient(String mass_ingredient) {
        this.mass_ingredient = mass_ingredient;
    }
}
