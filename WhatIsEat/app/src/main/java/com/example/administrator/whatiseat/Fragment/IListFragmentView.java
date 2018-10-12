package com.example.administrator.whatiseat.Fragment;

import com.example.administrator.whatiseat.Bean.Dir_Show_List;

import java.util.List;

public interface IListFragmentView {
    public String putId();//提供id
    public String putMenu();//提供搜索信息
    public String putIndex();//提供当前数据下标
    public void addList(List<Dir_Show_List> lists);//添加数据到listview中
    public void setIndex(int i);//设置数据下标
    public void updataList(List<Dir_Show_List> lists);//更新listview数据
    public void setDataNum(Boolean b);//设置数据是否允许被刷新
    public Boolean putDataNum();//提供数据刷新属性
    public void setToast(String str);//设置toast
}
