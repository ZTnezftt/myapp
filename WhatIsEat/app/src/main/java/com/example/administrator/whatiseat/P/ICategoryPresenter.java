package com.example.administrator.whatiseat.P;

import com.example.administrator.whatiseat.DB_List.Categroy.Items;

import java.util.List;

public interface ICategoryPresenter {
    public void getTitle(Boolean isfirst,int dbvalue);
    public void setTitle(List<String> list);
    public void setFragments(List<List<Items>> lists);
    public void setAdapter();
}
