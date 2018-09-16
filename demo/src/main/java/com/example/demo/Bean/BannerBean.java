package com.example.demo.Bean;

public class BannerBean {
    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURLS() {
        return URLS;
    }

    public void setURLS(String URLS) {
        this.URLS = URLS;
    }

    String ImageURL;
    String Name;
    String URLS;
}
