package com.example.administrator.whatiseat.M;

import android.util.Log;

import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Data_DIRJSON;
import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Title;
import com.example.administrator.whatiseat.Bean.Dir_Show_List;
import com.example.administrator.whatiseat.Component.DaggerRetrofitFactory;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListFragmentModel {

    @Inject @Named("de") RetrofitRequest retrofitRequest;
    private PutData putData;
    private int sum=0;
    public ListFragmentModel(){
        DaggerRetrofitFactory.builder()
                .retrofitRequestModule(new RetrofitRequestModule())
                .build().inject(this);
    }

    public void getDataForId(String id,String index){
        Log.i("model","get id");
        retrofitRequest.getListForId(id,index)
                .map(new Function<Data_DIRJSON, List<Dir_Show_List>>() {
                    @Override
                    public List<Dir_Show_List> apply(Data_DIRJSON data_dirjson) throws Exception {
                        List<Dir_Show_List> lists=new LinkedList<>();
                        List<Title> data=data_dirjson.result.data;
                        sum=data.size();
                        if (sum==0){
                            return lists;
                        }
                        for (int i=0;i<sum;i++){
                            Dir_Show_List dir_show_list=new Dir_Show_List();
                            dir_show_list.setId(data.get(i).id);
                            dir_show_list.setTitle(data.get(i).title);
                            dir_show_list.setIngredients(data.get(i).ingredients);
                            dir_show_list.setImtro("          "+data.get(i).imtro);
                            dir_show_list.setAlbums(data.get(i).albums[0]);
                            dir_show_list.setValue(1);
                            lists.add(dir_show_list);
                            if (i==5){
                                Dir_Show_List advertise=new Dir_Show_List();
                                advertise.setValue(0);
                                lists.add(advertise);
                            }
                        }
                        return lists;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Dir_Show_List>>() {
                    @Override
                    public void accept(List<Dir_Show_List> lists) throws Exception {
                        putData.putData(lists,sum);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("error","网络异常listfragmentforcid");
                        putData.OnError("网络异常");
                    }
                });
    }
    public void getDataForMenu(String menu,String index){
        Log.i("model","get menu");
        retrofitRequest.getListForMenu(menu,index)
                .map(new Function<Data_DIRJSON, List<Dir_Show_List>>() {
                    @Override
                    public List<Dir_Show_List> apply(Data_DIRJSON data_dirjson) throws Exception {
                        List<Dir_Show_List> lists=new LinkedList<>();
                        List<Title> data=data_dirjson.result.data;
                        sum=data.size();
                        if (sum==0){
                            return lists;
                        }
                        for (int i=0;i<sum;i++){
                            Dir_Show_List dir_show_list=new Dir_Show_List();
                            dir_show_list.setId(data.get(i).id);
                            dir_show_list.setTitle(data.get(i).title);
                            dir_show_list.setIngredients(data.get(i).ingredients);
                            dir_show_list.setImtro("          "+data.get(i).imtro);
                            dir_show_list.setAlbums(data.get(i).albums[0]);
                            dir_show_list.setValue(1);
                            lists.add(dir_show_list);
                            if (i==5){
                                Dir_Show_List advertise=new Dir_Show_List();
                                advertise.setValue(0);
                                lists.add(advertise);
                            }
                        }
                        return lists;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Dir_Show_List>>() {
                    @Override
                    public void accept(List<Dir_Show_List> lists) throws Exception {
                        putData.putData(lists,sum);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("error","网络异常listfragmentformenu");
                        putData.OnError("网络异常");
                    }
                });
    }
    public void put(PutData putData){
        this.putData=putData;
    }
    public interface PutData{
        public void putData(List<Dir_Show_List> lists,int size);
        public void OnError(String str);
    }
}
