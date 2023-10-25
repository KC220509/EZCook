package com.example.ezcook.model;

public class p_MonAn_model {
    private String tenmon;
    private String thoigian;

    private String kcal;
    private int hinh;

    public p_MonAn_model(String tenmon, String thoigian, String kcal, int hinh) {
        this.tenmon = tenmon;
        this.thoigian = thoigian;
        this.kcal = kcal;
        this.hinh = hinh;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
