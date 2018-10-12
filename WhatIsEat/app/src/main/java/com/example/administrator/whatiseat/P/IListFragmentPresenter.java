package com.example.administrator.whatiseat.P;

public interface IListFragmentPresenter {
    public void getDataForId();//通过id查询数据
    public void upDataForId();//通过id更新数据
    public String getId();//获得id
    public String getIndex();//获得下标
    public void getDataForMenu();//通过输入获得数据
    public void upDataForMenu();//通过输入更新数据
    public String getMenu();//获得输入
}
