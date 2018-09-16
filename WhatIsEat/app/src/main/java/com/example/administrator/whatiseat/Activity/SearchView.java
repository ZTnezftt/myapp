package com.example.administrator.whatiseat.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.whatiseat.Bean.Category_Bean.List;
import com.example.administrator.whatiseat.Component.DaggerSearchViewFactory;
import com.example.administrator.whatiseat.DB_List.DataBase;
import com.example.administrator.whatiseat.Fragment.HistoryFragment;
import com.example.administrator.whatiseat.Fragment.ListFragment;
import com.example.administrator.whatiseat.Module.HisttroyFragmentMoudle;
import com.example.administrator.whatiseat.Module.ListFragmentModule;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.example.administrator.whatiseat.P.SearchViewCompl;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.Util.Util;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchView extends BaseActivity implements View.OnKeyListener,ISearchView {
    @BindView(R.id.cancel)  TextView cancel;
    @BindView(R.id.Dir_Search) EditText search;
    @Inject ListFragment listFragment;
    @Inject HistoryFragment historyFragment;
    private FragmentManager fragmentManager;
    private SearchViewCompl searchViewCompl=new SearchViewCompl(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity);
        ButterKnife.bind(this);
        init();
    }
    /*
    初始化控件和页面
     */
    private void init(){
        DaggerSearchViewFactory.builder()
                .histtroyFragmentMoudle(new HisttroyFragmentMoudle())
                .listFragmentModule(new ListFragmentModule())
                .build().inject(this);
        int len= Util.dpToPx(MyApplication.getInstance(),20);
        Drawable drawable=getResources().getDrawable(R.drawable.searchicon);
        drawable.setBounds(0,0,len,len);
        search.setCompoundDrawables(drawable,null,null,null);
        drawable=null;
        fragmentManager=getSupportFragmentManager();
        search.setOnKeyListener(this);
        Intent intent=getIntent();
        /*
        判断从哪个页面跳转到serchview的
         */
        if (intent.getStringExtra("id")!=null){
            //能获取到id，说是从categroy跳转过来的，直接就加载listfragment，并将id给listfragment让他加载id的数据
            Bundle bundle=new Bundle();
            bundle.putString("id",intent.getStringExtra("id"));
            listFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.search_fragment,listFragment);
            fragmentTransaction.commit();
        }else {
            //否则就正常显示推荐和历史fragment
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.search_fragment, historyFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        /*
            收起软键盘
         */
        if(i == KeyEvent.KEYCODE_ENTER&&keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(SearchView.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            toDirShow();//执行搜索操作
            return false;
        }
        return false;
    }

    public void toDirShow(){
        FragmentTransaction fragmentTransaction;
        String str=search.getText().toString();
        if (str.equals("")){
            Util.Toast("没有内容呢",this);
        }else {
            Log.i("Onkey", "" + str);
            searchViewCompl.toDB(str);
            ListFragment listFragment2=new ListFragment();//因为要传入一个新的listfrag对象
            fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("str", str);
            listFragment2.setArguments(bundle);
            fragmentTransaction.replace(R.id.search_fragment, listFragment2);
            fragmentTransaction.commit();
        }
    }

    @Override
    public String getStr(String s) {
        return s;
    }

    @Override
    public LifecycleTransformer rxlifecycle() {
        LifecycleTransformer lifecycleTransformer = bindToLifecycle();
        return lifecycleTransformer;
    }

    @OnClick(R.id.cancel)
    public void Finish(){
        finishThis();
    }

    @Override
    protected void onDestroy() {
        listFragment=null;
        historyFragment=null;
        fragmentManager=null;
        searchViewCompl=null;
        super.onDestroy();
    }
}
