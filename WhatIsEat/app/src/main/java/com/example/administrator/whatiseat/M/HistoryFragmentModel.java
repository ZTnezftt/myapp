package com.example.administrator.whatiseat.M;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Adapter.HistroyViewAdapter;
import com.example.administrator.whatiseat.Component.DaggerSqlDBFactory;
import com.example.administrator.whatiseat.Module.SqlDBModule;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HistoryFragmentModel {
    private String[] Tags={"干锅花菜","红烧肉","麻婆豆腐","虾","糖醋排骨","紫菜包饭","鸡翅","小炒肉","鸭血粉丝煲","肉沫茄子","黄瓜",
                                    "番茄炒蛋","青椒"};
    @Named("Read") @Inject SQLiteDatabase rhistory;
    @Named("Write") @Inject SQLiteDatabase whistory;
    private Tag_CallBack tag_callBack;
    private HistroyView_CallBack histroyView_callBack;
    public HistoryFragmentModel() {
        DaggerSqlDBFactory.builder().sqlDBModule(new SqlDBModule(MyApplication.getInstance()))
                .build().inject(this);
    }
    public HistoryFragmentModel getTagData(){
        final List<String> list=new LinkedList<>();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                for (String str : Tags) {
                    list.add(str);
                }
                emitter.onNext(list);
                emitter.onComplete();
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> list) throws Exception {
                        tag_callBack.TagOnSuccess(list);
                    }
                });
        return this;
    }
    public HistoryFragmentModel getHistroyViewData(){
        final List<String> list=new LinkedList<>();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                Cursor cursor=rhistory.rawQuery("select * from history",null);
                while(cursor.moveToNext()){
                    list.add(cursor.getString(cursor.getColumnIndex("name")));
                    Log.i("db",cursor.getString(cursor.getColumnIndex("name")));
                }
                emitter.onNext(list);
                emitter.onComplete();
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> list) throws Exception {
                        Log.i("size",list.size()+"");
                        histroyView_callBack.HistroyViewOnSuccess(list);
                    }
                });
        return this;
    }
    public void clear(){
        whistory.execSQL("delete from history");
    }
    public void putTagsData(Tag_CallBack Tag_callBack){
        this.tag_callBack=Tag_callBack;
    }

    public void putHistroyViewData(HistroyView_CallBack histroyView_callBack){
        this.histroyView_callBack=histroyView_callBack;
    }

    public interface Tag_CallBack {
        public void TagOnSuccess(List<String> list);
    }
    public interface HistroyView_CallBack{
        public void HistroyViewOnSuccess(List<String> list);
    }
    public void close(){
        whistory.close();
        rhistory.close();
    }
}

