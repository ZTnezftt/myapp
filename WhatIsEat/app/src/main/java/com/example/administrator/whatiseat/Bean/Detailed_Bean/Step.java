package com.example.administrator.whatiseat.Bean.Detailed_Bean;

import javax.inject.Inject;

public class Step {
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Inject
    public Step(){

    }
    private String img;
    private String step;
}
