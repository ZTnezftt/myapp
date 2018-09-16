package com.example.demo.Bean;

import java.util.LinkedList;
import java.util.List;

public class IdBean {

    public Result result=new Result();
    public class Result{
        public List<Item> data=new LinkedList<>();
    }
    public class Item{
        public String id;
        public String title;
        public String imtro;
        public String[] albums;
    }
}
