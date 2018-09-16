package com.example.administrator.whatiseat.M;

import android.util.Log;

import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.Component.DaggerRetrofitFactory;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Advise_DetailedModel {
    @Inject @Named("page") RetrofitRequest request;
    private Data_CallBack data_callBack;
    public Advise_DetailedModel(){
        Init();
    }
    public Advise_DetailedModel getData(List<String> ids){
        Log.i("listtoString",listToString(ids,';'));
        request.getids(listToString(ids,';'))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Advise_Title>>() {
                    @Override
                    public void accept(List<Advise_Title> advise_titles) throws Exception {
                        //数据已经成功拿到,回调
                        data_callBack.putData(advise_titles);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        data_callBack.OnError("网络异常");
                    }
                });
        return this;
    }
    private void Init(){
        DaggerRetrofitFactory.builder()
                .retrofitRequestModule(new RetrofitRequestModule())
                .build().inject(this);
    }

    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
    public void putdata(Data_CallBack data_callBack){
        this.data_callBack=data_callBack;
    }
    public interface Data_CallBack{
        public void putData(List<Advise_Title> advise_titles);
        public void OnError(String str);
    }
}
