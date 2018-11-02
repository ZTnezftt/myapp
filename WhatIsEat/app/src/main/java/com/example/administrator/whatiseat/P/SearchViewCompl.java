package com.example.administrator.whatiseat.P;

import android.app.Application;
import android.content.Context;
import android.database.Observable;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.whatiseat.Activity.ISearchView;
import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Activity.SearchView;
import com.example.administrator.whatiseat.Component.DaggerSqlDBFactory;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchViewCompl implements ISearchViewPresenter {
    @Named("Write") @Inject SQLiteDatabase history;
    private ISearchView iSearchView;
    public SearchViewCompl(ISearchView iSearchView){
        this.iSearchView=iSearchView;
        DaggerSqlDBFactory.builder()
                .sqlDBModule(new SqlDBModule(MyApplication.getInstance()))
                .build().inject(this);
    }

    @Override
    public void toDB(final String s) {
        io.reactivex.Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                try {
                    Log.i("db","入库");
                    history.execSQL("insert into history values(?)",new String[]{s});
                    emitter.onNext(true);
                }catch (Exception e){
                    Log.e("dberror",e.getMessage());
                    emitter.onNext(false);
                }finally {
                    history.close();
                }
            }}) .compose(rxunbind())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            Log.i("db",true+"");
                        }else{
                            Log.i("db",false+"");
                        }
                        close();
                        iSearchView.toListView(s);
                    }
                });
    }
    public void init(){
        DaggerSqlDBFactory.builder()
                .sqlDBModule(new SqlDBModule(MyApplication.getInstance()))
                .build().inject(this);
    }
    @Override
    public LifecycleTransformer rxunbind() {
        return iSearchView.rxlifecycle();
    }
    public void close(){
        history.close();
    }

}
