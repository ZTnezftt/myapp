package com.example.demo.Bean;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Ids {
    public Ids(String str){
        String[] strings=str.split(";");
        ids=Arrays.asList(strings);
    }
    public List<String> ids=new LinkedList<>();
}
