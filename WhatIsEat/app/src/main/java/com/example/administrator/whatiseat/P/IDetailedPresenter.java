package com.example.administrator.whatiseat.P;

import com.example.administrator.whatiseat.Bean.Burden;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Data;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;

import java.util.List;
/*
定义了P能做哪些事情
 */
public interface IDetailedPresenter {
    public void getId();
    public void getDetailed();
    public void setdeimtro(String str);
    public void setdename(String str);
    public void setdetitle(String str);
    public void setburdenList(List<Burden> list);
    public void setstepsList(List<Step> list);
    public void setdealbums(String url);
    public void setShouCangText(String str);
    public void putZan(String id,Boolean type);
    public void setdb(Data data);
}
