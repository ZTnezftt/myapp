package com.example.demo.Bean;

import java.util.LinkedList;
import java.util.List;

public class BannerJson {
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    int size;

    public Banner getBanner() {
        return banner;
    }

    Banner banner=new Banner();
    public class Banner{
        public List<String> getBanner() {
            return banner;
        }

        public List<String> getUrls() {
            return urls;
        }

        List<String> banner=new LinkedList<>();
        List<String> urls=new LinkedList<>();
    }
}