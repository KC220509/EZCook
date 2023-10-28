package com.example.ezcook.model;

public class f_Step_detail {
    private String step;
    private String make;

    public f_Step_detail(String step, String make) {
        this.step = step;
        this.make = make;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
