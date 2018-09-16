package com.example.administrator.whatiseat.Bean.Category_Bean;

import java.util.LinkedList;
import java.util.List;
public class Result {
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ///obj
    public String parentId;
    public String name;

    public List<com.example.administrator.whatiseat.Bean.Category_Bean.List> getList() {
        return list;
    }

    ///array
    public java.util.List<com.example.administrator.whatiseat.Bean.Category_Bean.List> list=new LinkedList<>();

}
