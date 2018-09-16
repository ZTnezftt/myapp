package com.example.administrator.whatiseat.Bean;

import java.util.LinkedList;
import java.util.List;

public class Banner_in {
    public List<String> getUrls() {
        return urls;
    }

    public void setUrl(List<String> urls) {
        this.urls = urls;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    List<String> urls=new LinkedList<>();
    int size;

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Banner getBanner() {
        return banner;
    }

    Banner banner=new Banner();

    public class Banner{
        public List<String> getBanner() {
            return banner;
        }

        public void setBanner(List<String> banner) {
            this.banner = banner;
        }

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        List<String> banner=new LinkedList<>();
        List<String> urls=new LinkedList<>();
    }
}
