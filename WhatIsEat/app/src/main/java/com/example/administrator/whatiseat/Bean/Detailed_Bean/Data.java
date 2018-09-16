package com.example.administrator.whatiseat.Bean.Detailed_Bean;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

//详细数据BEAN
public class Data {

    @Inject
    public Data(){

    }
    public String ShouCangText;
    public String id;
    public String title;
    public String tags;
    public String imtro;
    public String ingredients;
    public String burden;
    public String[] albums;
    public List<Step> steps=new LinkedList<>();
}
