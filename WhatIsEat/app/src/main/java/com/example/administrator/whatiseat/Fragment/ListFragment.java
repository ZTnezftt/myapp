package com.example.administrator.whatiseat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.whatiseat.Activity.DetailedActivity;
import com.example.administrator.whatiseat.Adapter.Dir_Show_Adapter;
import com.example.administrator.whatiseat.Bean.Dir_Show_List;
import com.example.administrator.whatiseat.Component.DaggerListFragmentFactory;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.ListFragmentAdapterMoudle;
import com.example.administrator.whatiseat.P.ListFragmentCompl;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.Util.Util;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
    1.设置刷新间隔
    2.如果当前id or menu 的数量刚好是10的倍数（或者其他原因），那可能导致已经没有数据确依然请求了服务器，从而发生Rxjava没有获取到数据抛出异常
      io.reactivex.exceptions.OnErrorNotImplementedException: Attempt to read from field 'java.util.List com.example
 */
public class ListFragment extends BaseFragment implements IListFragmentView{
    private int INDEX=0;//从第几个开始获取
    private String Type="";//id模式或者Menu模式
    private String MENU="";//通过搜索栏
    private String ID="";//种类id
    private boolean datanum=true;//是否还有数据
    private Boolean scrollvalue=true;//是否允许刷新
    private ListFragmentCompl listFragmentCompl=new ListFragmentCompl(this);
    @Inject Dir_Show_Adapter dir_show_adapter;
    @Inject @Named("StaggeredGridLayoutManager") RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.Dir_Show) RecyclerView recyclerView;
    /*
            1.判断是否有传递过来的种类id，有的话查询种类列表，否则查询关键字列表
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dir_data,container,false);
        ButterKnife.bind(this,view);
        Init_Dagger();
        if (!ID.equals("")){
            Type="ID";
            listFragmentCompl.getDataForId();
        }else if (!MENU.equals("")){
            Type="MENU";
            listFragmentCompl.getDataForMenu();
        }else{
            Util.Toast("发生出乎意料的错误",getActivity());
        }
        return view;
    }

    @Override
    public String putId() {
        return this.ID;
    }

    @Override
    public String putMenu() {
        return this.MENU;
    }

    @Override
    public String putIndex() {
        return String.valueOf(INDEX);
    }

    @Override
    public void addList(List<Dir_Show_List> lists) {
        dir_show_adapter.replace(lists);
    }

    @Override
    public void setIndex(int i) {
        this.INDEX+=i;
    }

    @Override
    public void updataList(List<Dir_Show_List> lists) {
        dir_show_adapter.addList(lists);
    }

    @Override
    public void setDataNum(Boolean b) {
        Log.i("type",b+"");
        setToast("已经到底了~，看看其他的吧");
        this.datanum=b;
    }

    @Override
    public Boolean putDataNum() {
        return this.datanum;
    }

    @Override
    public void setToast(String str) {
        Log.i("error",str);
        Toast(str);
    }

    /*
        初始化数据及回调
     */

    public void Init_Dagger(){
        DaggerListFragmentFactory.builder()
                .layoutManagerModule(new LayoutManagerModule(getActivity(),2))
                .listFragmentAdapterMoudle(new ListFragmentAdapterMoudle(getActivity()))
                .build().inject(this);
        Bundle bundle=getArguments();
        this.MENU=bundle.getString("str","");
        this.ID=bundle.getString("id","");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dir_show_adapter);

        dir_show_adapter.CallBack(new Dir_Show_Adapter.GetId() {
            @Override
            public void getId(int id) {
                Intent intent=new Intent(getActivity(),DetailedActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RCVOnScrollListenner());
    }

    //Recyclerview的OnScrollListenner抽象类的实现
    public class RCVOnScrollListenner extends RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            if (recyclerView.canScrollVertically(1) == false && scrollvalue) {//&&datanum
                scrollvalue=false;
                if (Type.equals("MENU")) {
                    listFragmentCompl.upDataForMenu();
                }else{
                    listFragmentCompl.upDataForId();
                }
                Observable observable=Observable.create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                        Thread.sleep(1500);
                        emitter.onNext(true);
                        emitter.onComplete();
                    }
                });
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) throws Exception {
                                if ((boolean)o==true){
                                    scrollvalue=true;
                                }
                            }
                        });
                /*
                    考虑设置最低加载间隔
                 */
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
