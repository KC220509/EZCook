package com.example.ezcook.model;

public class h_category_regime_eat_model {
   private int pic;
    private String title;

    public h_category_regime_eat_model(int pic, String title) {
        this.pic = pic;
        this.title = title;
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
}
