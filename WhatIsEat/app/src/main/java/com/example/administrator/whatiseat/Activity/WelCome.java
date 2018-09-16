package com.example.administrator.whatiseat.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.whatiseat.Bean.Category_Bean.CategoryJSON;
import com.example.administrator.whatiseat.Bean.Category_Bean.Result;
import com.example.administrator.whatiseat.DB_List.Categroy.Category_Show_List;
import com.example.administrator.whatiseat.DB_List.Categroy.Items;
import com.example.administrator.whatiseat.DB_List.DataBase;
import com.example.administrator.whatiseat.M.ListFragmentModel;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.Util.ApiUtil;
import com.example.administrator.whatiseat.Util.SpUtil;
import com.example.administrator.whatiseat.Util.Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WelCome extends BaseActivity {
    private final Context context=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                if (SpUtil.getBoolean(getContext(), "FirstOpen", true)) {
                    Log.i("Welcome", "首次启动");
                    DataBase DataBase = new DataBase(context, "DataBase", null, 1);
                    DataBase.close();
                    SpUtil.putInt(WelCome.this,"category",0);
                    Thread.sleep(2000);
                    emitter.onNext(true);
                }else{
                    MyApplication.isFirst=false;
                    Thread.sleep(2000);
                    emitter.onNext(false);
                }
                emitter.onComplete();
            }})
                .compose(this.<Boolean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            SpUtil.putBoolean(getContext(), "FirstOpen", false);
                        }
                        Intent intent = new Intent(WelCome.this, Main.class);
                        startActivity(intent);
                    }
                });
    }
    @Override
    protected void onStop() {
        this.finish();
        super.onStop();
    }
}
