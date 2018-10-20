package com.example.administrator.whatiseat.Activity;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface ISearchView {
    public void toDirShow();//跳转list页面
    public String getStr(String s);//为p层提供传递string数据的接口
    public LifecycleTransformer rxlifecycle();
    public void toListView(String str);
}
