package com.example.demo.Main;

import com.example.demo.Bean.IdBean;
import com.example.demo.DAO.GetIdsDAO;
import com.example.demo.DAO.InsertDataDAO;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Future;

@Component
public class getIdDe {

    @Autowired
    InsertDataDAO insertDataDAO;

    @Autowired
    GetIdsDAO getIdsDAO;

    @Async
    public void getid(String id, String appkey){
        try {
            if (getIdsDAO.getids(id)!=null){
                System.out.println("id"+id+": 存在");
                return;
            }else{
                System.out.println("id"+id+": 不存在");
            }
        }catch (Exception e){
            System.out.println("未知错误");
        }
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(API.getQueryIdToDataBase(id,appkey)).build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                Gson gson=new Gson();
                IdBean idBean=gson.fromJson(response.body().string(), IdBean.class);
                insertDataDAO.insertDataToids(idBean.result.data.get(0).id,idBean.result.data.get(0).title,idBean.result.data.get(0).imtro,idBean.result.data.get(0).albums[0]);
                System.out.println(id+"join");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("未知错误");
    }
}
