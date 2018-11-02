package com.example.administrator.whatiseat.P;

import android.util.Log;

import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Component.DaggerRetrofitFactory;
import com.example.administrator.whatiseat.Fragment.IMainFragmentView;
import com.example.administrator.whatiseat.M.MainFragmentModel;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;
import com.example.administrator.whatiseat.Util.Util;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


public class MainFragmentCompl implements IMainFragmentPresenter {//p
    private static List mainList=new LinkedList<>();
    private IMainFragmentView iMainFragmentView;
    private MainFragmentModel mainFragmentModel;
    public MainFragmentCompl(IMainFragmentView iMainFragmentView){
        this.iMainFragmentView=iMainFragmentView;//持有v
        this.mainFragmentModel =new MainFragmentModel(this);//持有m
    }

    @Override
    public void SetAdapter() {
        if (mainList!=null) {
            iMainFragmentView.SetAdapter(mainList);
        }
    }

    @Override
    public void getList() {
        mainList.clear();
        mainFragmentModel.getData();
        mainFragmentModel.putData(new MainFragmentModel.CallBack() {
            @Override
            public void OnSuccess(List list) {
                mainList=list;
                SetAdapter();
            }
            @Override
            public void OnError(String str) {
                Log.i("error",str);
                iMainFragmentView.setToast(str);
            }
        });
    }

    @Override
    public LifecycleTransformer rxunbind() {
        return iMainFragmentView.rxlifecycle();
    }

    @Override
    public void setBundle() {

    }

    public void Init(){
        DaggerRetrofitFactory.builder()
                .retrofitRequestModule(new RetrofitRequestModule())
                .build().inject(this);
    }
}
