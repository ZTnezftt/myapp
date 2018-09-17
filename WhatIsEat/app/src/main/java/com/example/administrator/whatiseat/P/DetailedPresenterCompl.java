package com.example.administrator.whatiseat.P;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.whatiseat.Activity.IDetailedView;
import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Bean.Burden;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Data;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.DetailedJSON;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;
import com.example.administrator.whatiseat.Component.DaggerDetailedFactory;
import com.example.administrator.whatiseat.Component.DaggerRetrofitFactory;
import com.example.administrator.whatiseat.Component.DaggerSqlDBFactory;
import com.example.administrator.whatiseat.Module.BurdenModule;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.example.administrator.whatiseat.Module.StepsModule;
import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
    客户端是否已经点赞：
        通过本地数据库判断id的值是否为1,则说明已经点过赞了，否则id不存在获value=0说明没有点赞
        id不存在则创建这个id的字段默认为0

        通过点击赞的按钮，提交网络数据库，点赞次数+1，
        本地数据库更新value为 1，标记为已经点赞

        通过取消点赞的按钮，提交网络数据库，点赞次数-1
        本地数据库更新value为0 ，标记为未点赞

        注意更新Textview的值，已经根据value的值设置toggle的checked

 */
public class DetailedPresenterCompl implements IDetailedPresenter {
    IDetailedView iDetailedView;
    private int websize;
    @Inject @Named("Read") SQLiteDatabase readzan;
    @Inject @Named("Write") SQLiteDatabase writezan;
    @Named("de") @Inject RetrofitRequest derequest;
    @Named("page") @Inject RetrofitRequest pagerequest;
    int id;
    public DetailedPresenterCompl(IDetailedView iDetailedView){
        this.iDetailedView=iDetailedView;
        init_Dagger2();
    }
    @Override
    public void getId() {
        this.id=iDetailedView.getId();
    }

    @Override
    public void getDetailed() {
        Log.i("de","获得详情");
        getId();
        Observable<DetailedJSON> o1=derequest.getDe(String.valueOf(id));//请求菜谱
        Observable<Integer> o2=pagerequest.getZan(String.valueOf(id));//请求赞
        Observable.zip(o1, o2, new BiFunction<DetailedJSON, Integer, Data>() {
            @Override
            public Data apply(DetailedJSON detailedJSON, Integer integer) throws Exception {
                Data data=new Data();
                data=detailedJSON.result.data.get(0);
                websize=integer;
                data.ShouCangText=String.valueOf(integer);
                return data;
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data data) throws Exception {
                        setdb(data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iDetailedView.setToast("网络异常");
                    }
                });
    }

    @Override
    public void setdb(final Data data){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Cursor cursor=readzan.rawQuery("select * from zan where id=?",new String[]{String.valueOf(id)});
                while(cursor.moveToNext()){//查询到
                    Log.i("sql","查询到数据");
                    int value=cursor.getInt(cursor.getColumnIndex("value"));
                    emitter.onNext(value);
                    emitter.onComplete();
                }
                //未查询到
                Log.i("de","未查询到数据,发送0");
                writezan.beginTransaction();
                try {
                    writezan.execSQL("insert into zan values(?,0,?,?)",new String[]{String.valueOf(id),data.title,data.albums[0]});
                    writezan.setTransactionSuccessful();
                }catch (Exception e) {

                }finally{
                    writezan.endTransaction();
                }
                emitter.onNext(0);
                emitter.onComplete();
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (integer == 1) {
                            Log.i("de", "结果为1，按钮设置true");
                            iDetailedView.setToggle(true);
                        } else {
                            Log.i("de", "结果为0，按钮设置false");
                            iDetailedView.setToggle(false);
                        }
                        setdetitle(data.title);
                        setdealbums(data.albums[0]);
                        setdeimtro("     "+data.imtro);
                        setdename(data.title);
                        setburdenList(getBurden(data.ingredients,data.burden));
                        setstepsList(data.steps);
                        setShouCangText(data.ShouCangText);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    @Override
    public void putZan(final String id, final Boolean type) {
        //异步操作数据库
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (type){
                   writezan.execSQL("update zan set value=1 where id=?",new String[]{String.valueOf(id)});
                   emitter.onNext(1);
                }else{
                    writezan.execSQL("update zan set value=0 where id=?",new String[]{String.valueOf(id)});
                    emitter.onNext(0);
                }
                emitter.onComplete();
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (integer==1){
                            Log.i("sql","设置为1");
                        }else{
                            Log.i("sql","设置为0");
                        }
                    }
                });
        ////请求api从而完成点赞
        pagerequest.putZan(id,type).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (type){
                    setShouCangText(String.valueOf(websize+1));
                    websize+=1;
                }else{
                    setShouCangText(String.valueOf(websize-1));
                    websize-=1;
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    @Override
    public void setdeimtro(String str) {
        iDetailedView.setdeimtro(str);
    }

    @Override
    public void setdename(String str) {
        iDetailedView.setdename(str);
    }

    @Override
    public void setdetitle(String str) {
        iDetailedView.setdetitle(str);
    }

    @Override
    public void setburdenList(List<Burden> list) {
        iDetailedView.setburdenList(list);
    }

    @Override
    public void setstepsList(List<Step> list) {
        iDetailedView.setstepsList(list);
    }

    @Override
    public void setdealbums(String url) {
        iDetailedView.setdealbums(url);
    }

    @Override
    public void setShouCangText(String str) {
        iDetailedView.setShouCangText(str);
    }


    void init_Dagger2(){
        DaggerDetailedFactory.builder()
                .burdenModule(new BurdenModule(MyApplication.getInstance()))
                .layoutManagerModule(new LayoutManagerModule(MyApplication.getInstance()))
                .stepsModule(new StepsModule(MyApplication.getInstance()))
                .retrofitRequestModule(new RetrofitRequestModule())
                .sqlDBModule(new SqlDBModule(MyApplication.getInstance()))
                .build().inject(this);
    }

    public List<Burden> getBurden(String in,String bu){
        List<Burden> list=new LinkedList<>();
        String strs[]=(in+";"+bu).split(";");
        for (String item:strs){
            String items[]=item.split(",");
            Burden burden=new Burden();
            burden.setT1(items[0]);
            burden.setT2(items[1]);
            list.add(burden);
        }
        return list;
    }
}
