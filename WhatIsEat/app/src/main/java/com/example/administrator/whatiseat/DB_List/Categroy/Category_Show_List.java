package com.example.administrator.whatiseat.DB_List.Categroy;

import android.content.Context;
import android.util.Log;

import com.example.administrator.whatiseat.Bean.Category_Bean.CategoryJSON;
import com.example.administrator.whatiseat.Bean.Category_Bean.Result;
import com.example.administrator.whatiseat.Util.ApiUtil;
import com.example.administrator.whatiseat.Util.SpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Category_Show_List {
    private static Category_Show_List instance;
    private Boolean tag;
    public  Map<String,List<Items>> category;
    private Category_Show_List(){
        tag=false;
    }
    public static Category_Show_List getInstance(){
        if (instance==null){
            instance=new Category_Show_List();
        }
        return instance;
    }
    public void SetMap(Map<String,List<Items>> map){
        this.category=null;
        this.category=map;
    }
    public void Set(Category_Show_List category_show_list){
        instance=category_show_list;
    }

    public void getCategroy(Context context) throws Exception{
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();
        Request request = new Request.Builder()
                .url(ApiUtil.queryCategory()).build();
        Response response = null;
        response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            Log.i("okhttpcategroy", "success");
            Gson gson = new Gson();
            CategoryJSON categoryJSON = gson.fromJson(response.body().string(), CategoryJSON.class);
            List<Result> result = categoryJSON.getResult();
            for (Result r : result) {
                List<Items> iList = new LinkedList<>();
                for (com.example.administrator.whatiseat.Bean.Category_Bean.List list : r.list) {
                    Items items = new Items();
                    items.setId(list.getId());
                    items.setName(list.getName());
                    iList.add(items);
                }
                this.category.put(r.name, iList);
                SpUtil.putString(context, "Category", gson.toJson(this, Category_Show_List.class));
            }
            Log.i("get", this.category.size() + "");
        }else {
            throw new Exception();
        }
    }
}
