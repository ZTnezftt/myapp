package com.example.demo.Bean;

import java.util.LinkedList;
import java.util.List;

public class AdviseJson {
    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size;
    List<String> ids=new LinkedList<>();

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    List<String> text=new LinkedList<>();
    List<String> urls=new LinkedList<>();
}
