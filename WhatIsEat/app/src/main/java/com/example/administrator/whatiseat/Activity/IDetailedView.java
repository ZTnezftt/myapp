package com.example.administrator.whatiseat.Activity;

import com.example.administrator.whatiseat.Bean.Burden;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;

import java.util.List;

/*
定义了V有哪些功能和暴露出去的方法
 */
public interface IDetailedView {
    int getId();
    public void setdeimtro(String str);//设置图片
    public void setdename(String str);
    public void setdetitle(String str);//设置标题
    public void setburdenList(List<Burden> list);//设置用料
    public void setstepsList(List<Step> list);//设置步骤
    public void setdealbums(String url);//设置简介
    public void setShouCangText(String str);//设置收藏
    public void setToggle(Boolean b);//设置收藏按钮
    public void setValue(Boolean b);//设置收藏值
    public void setToast(String str);//设置toast
}
