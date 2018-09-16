package com.example.administrator.whatiseat.Bean;


import java.util.LinkedList;
import java.util.List;

public class TuiJianItem {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    private String text;
    private String urls;
    private List<String> ids=new LinkedList<>();
}
