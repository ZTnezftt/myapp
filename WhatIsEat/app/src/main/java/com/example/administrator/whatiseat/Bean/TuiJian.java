package com.example.administrator.whatiseat.Bean;


import java.util.LinkedList;
import java.util.List;
/*
    推荐部分的数据
    测试版
    List<T> ids 包含了推荐内容的id
    Urls 标题图片
    text 标题
 */
public class TuiJian {
    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    int size;
    List<String> ids=new LinkedList<>();
    List<String> urls=new LinkedList<>();
    List<String> text=new LinkedList<>();
}
