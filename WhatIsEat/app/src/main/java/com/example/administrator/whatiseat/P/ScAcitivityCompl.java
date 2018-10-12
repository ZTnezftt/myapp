package com.example.administrator.whatiseat.P;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.whatiseat.Activity.IScActivity;
import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Bean.Scitem;
import com.example.administrator.whatiseat.Component.DaggerSqlDBFactory;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.trello.rxlifecycle2.LifecycleTransformer;

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

public class ScAcitivityCompl implements IScAcitivityPresenter {

    @Inject @Named("Read") SQLiteDatabase read;
    private IScActivity iScActivity;
    public ScAcitivityCompl(IScActivity iScActivity){
        this.iScActivity=iScActivity;
        InitDagger();
    }

    @Override
    public void setAdapter(List<Scitem> list) {
        iScActivity.setAdapter(list);
    }

    @Override
    public void getDataForDB() {
        Observable.create(new ObservableOnSubscribe<List<Scitem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Scitem>> emitter) throws Exception {
                Cursor cursor=read.rawQuery("select * from zan where value=1",null);
                List<Scitem> list=new LinkedList<>();
                while(cursor.moveToNext()){//查询到
                    Log.i("sql","查询到数据");
                    Scitem scitem=new Scitem();
                    String id=cursor.getString(cursor.getColumnIndex("id"));
                    Log.i("sql","id"+id);
                    String title=cursor.getString(cursor.getColumnIndex("title"));
                    Log.i("sql","title"+title);
                    String urls=cursor.getString(cursor.getColumnIndex("urls"));
                    scitem.id=id;
                    scitem.title=title;
                    scitem.urls=urls;
                    list.add(scitem);
                }
                emitter.onNext(list);
                emitter.onComplete();
            }})
                .compose(rxunbind())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Scitem>>() {
                    @Override
                    public void accept(List<Scitem> scitems) throws Exception {
                        setAdapter(scitems);
                    }
                });
    }

    @Override
    public LifecycleTransformer rxunbind() {
        return iScActivity.rxlifecycle();
    }

    public void InitDagger(){
        DaggerSqlDBFactory.builder()
                .sqlDBModule(new SqlDBModule(MyApplication.getInstance()))
                .build().inject(this);
    }
}
