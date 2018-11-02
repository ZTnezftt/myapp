package com.example.administrator.whatiseat.M;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.administrator.whatiseat.Bean.Banner_in;
import com.example.administrator.whatiseat.Bean.TuiJian;
import com.example.administrator.whatiseat.Bean.TuiJianItem;
import com.example.administrator.whatiseat.Component.DaggerRetrofitFactory;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.P.IMainFragmentPresenter;
import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainFragmentModel{
    @Inject @Named("page") RetrofitRequest BannerRequest,AdviseRequest,AdviseLRRequest;
    private CallBack callBack;
    private IMainFragmentPresenter iMainFragmentPresenter;
    public MainFragmentModel(IMainFragmentPresenter iMainFragmentPresenter){
        DaggerRetrofitFactory.builder()
                .retrofitRequestModule(new RetrofitRequestModule())
                .build().inject(this);
        this.iMainFragmentPresenter=iMainFragmentPresenter;
    }

    private List mainList=new LinkedList<>();
    public void getData(){
        final List<List<String>> lists=new LinkedList<>();
        BannerRequest.getMainFragmentList()
                .compose(iMainFragmentPresenter.rxunbind())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Banner_in>() {
                    @Override
                    public void accept(Banner_in banner_in) throws Exception {
                        banner_in.getBanner().getBanner().add(0,banner_in.getBanner().getBanner().get(banner_in.getSize()-1));
                        banner_in.getBanner().getBanner().add(banner_in.getBanner().getBanner().get(1));
                        mainList.add(banner_in);
                        Log.i("list","banner load");
                        callBack.OnSuccess(mainList);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Banner_in, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Banner_in banner_in) throws Exception {
                        Log.i("list","l1 ...load");
                        return AdviseLRRequest.getLR("l");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("list","l1 "+s);
                        List<String> list1=new LinkedList<>();
                        list1.add(s);
                        lists.add(list1);
                        Log.i("list","l1 load");
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return AdviseLRRequest.getLR("r");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        List<String> list2=new LinkedList<>();
                        list2.add(s);
                        lists.add(list2);
                        mainList.add(lists);
                        Log.i("list","l2 load");
                        callBack.OnSuccess(mainList);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<TuiJian>>() {
                    @Override
                    public ObservableSource<TuiJian> apply(String str) throws Exception {
                        return AdviseRequest.getAdvise();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<TuiJian>() {
                    @Override
                    public void accept(TuiJian tuiJian) throws Exception {
                        for (int i = 0; i< tuiJian.getSize(); i++){
                            Log.i("tuijianitem", tuiJian.getSize()+"");
                            TuiJianItem tuiJianItem=new TuiJianItem();
                            tuiJianItem.getIds().add(tuiJian.getIds().get(i));
                            tuiJianItem.setText(tuiJian.getText().get(i));
                            tuiJianItem.setUrls(tuiJian.getUrls().get(i));
                            mainList.add(tuiJianItem);
                        }
                    }
                })
                .subscribe(new Consumer<TuiJian>() {
                    @Override
                    public void accept(TuiJian tuiJian) throws Exception {
                        callBack.OnSuccess(mainList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.OnError("网络异常");
                    }
                });
    }


    /*
        对外接口,暴露数据
     */
    public void putData(CallBack callBack) {
        this.callBack=callBack;
    }
    public interface CallBack{
        public void OnSuccess(List list);
        public void OnError(String str);
    }
}


