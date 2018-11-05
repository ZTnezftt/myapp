package com.example.administrator.whatiseat.P;

import android.support.v4.app.Fragment;
import android.util.Log;
import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.DB_List.Categroy.Category_Show_List;
import com.example.administrator.whatiseat.DB_List.Categroy.Items;
import com.example.administrator.whatiseat.Fragment.ICategoryFragmentView;
import com.example.administrator.whatiseat.Util.SpUtil;
import com.google.gson.Gson;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CategoryFragmentCompl implements ICategoryPresenter {
    //Map<String,List<Items>>
    ///vp
    private List<Fragment> mFragments = new LinkedList<>();
    private ICategoryFragmentView iCategoryFragmentView;
    private Category_Show_List category_show_list;//单例M
    public CategoryFragmentCompl(ICategoryFragmentView iCategoryFragmentView){
        this.iCategoryFragmentView=iCategoryFragmentView;
        this.category_show_list=Category_Show_List.getInstance();
    }
    @Override
    public void getTitle(final Boolean isfirst,final int dbvalue) {
        final List<String> title = new LinkedList<>();
        final List<List<Items>> items = new LinkedList<>();

        Observable.create(new ObservableOnSubscribe<Map<String, List<Items>>>() {
            @Override
            public void subscribe(ObservableEmitter<Map<String, List<Items>>> emitter) throws Exception {
                Log.i("c","isfirst="+isfirst+"    dbvalue="+dbvalue);
                if (dbvalue==0) {
                    Log.i("category","network");
                    category_show_list.getCategroy(MyApplication.getInstance());
                }else{
                    Log.i("category","Sp");
                    Category_Show_List category= new Gson().fromJson(SpUtil.getString(MyApplication.getInstance(),"Category"),Category_Show_List.class);
                    category_show_list.SetMap(category.category);
                }
                emitter.onNext(category_show_list.category);
                emitter.onComplete();
                }})
                .compose(iCategoryFragmentView.bindto())
                .map(new Function<Map<String, List<Items>>, Boolean>() {
                    @Override
                    public Boolean apply(Map<String, List<Items>> stringListMap) throws Exception {
                        Iterator<Map.Entry<String, List<Items>>> it = stringListMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, List<Items>> entry = it.next();
                            title.add(entry.getKey());
                            items.add(entry.getValue());
                        }
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.i("c","2");
                        SpUtil.putInt(MyApplication.getInstance(),"category",1);
                        setTitle(title);
                        setFragments(items);
                        setAdapter();
                    }
                }, new io.reactivex.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        iCategoryFragmentView.setToast("初始化资源失败，请联网后再试");
                    }
                });
    }
    @Override
    public void setTitle(List<String> list) {
        iCategoryFragmentView.setTitile(list);
    }

    @Override
    public void setFragments(List<List<Items>> lists) {
        iCategoryFragmentView.setFragmetList(lists);
    }

    @Override
    public void setAdapter(){
        iCategoryFragmentView.setView();
    }

}
