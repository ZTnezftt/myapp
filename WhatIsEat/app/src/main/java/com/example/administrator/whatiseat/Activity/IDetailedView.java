package com.example.administrator.whatiseat.Activity;

import com.example.administrator.whatiseat.Bean.Burden;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;

import java.util.List;

/*
定义了V有哪些功能和暴露出去的方法
 */
public interface IDetailedView {
    int getId();
    public void setdeimtro(String str);
    public void setdename(String str);
    public void setdetitle(String str);
    public void setburdenList(List<Burden> list);
    public void setstepsList(List<Step> list);
    public void setdealbums(String url);
    public void setShouCangText(String str);
    public void setToggle(Boolean b);
    public void setValue(Boolean b);
    public void setToast(String str);
}
