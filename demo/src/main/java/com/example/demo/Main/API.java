package com.example.demo.Main;

public class API {
    public static String getQueryIdToDataBase(String id,String key){
         String url="http://apis.juhe.cn/cook/queryid?id="+id+"&dtype=&key="+key;
         return url;
    }
}
