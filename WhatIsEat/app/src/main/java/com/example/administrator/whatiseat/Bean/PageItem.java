package com.example.administrator.whatiseat.Bean;


import java.util.List;
/*
 Banner 部分
 */
public class PageItem {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getList() {
        return urls;
    }

    public void setList(List<String> list) {
        this.urls = list;
    }

    private int size;
    private List<String> urls;
}
