package com.example.administrator.whatiseat.Fragment;

import android.support.v4.app.Fragment;

import com.example.administrator.whatiseat.DB_List.Categroy.Items;

import java.util.List;

public interface ICategoryFragmentView {
    public void setTitile(List<String> list);//设置标题
    public void setFragmetList(List<List<Items>> list);//设置分类的内容
    public void setView();//设置tab标题
    public void setToast(String str);//加载toast
}
