package com.example.administrator.whatiseat.Fragment;

import android.support.v4.app.Fragment;

import com.example.administrator.whatiseat.DB_List.Categroy.Items;

import java.util.List;

public interface ICategoryFragmentView {
    public void setTitile(List<String> list);
    public void setFragmetList(List<List<Items>> list);
    public void setView();
    public void setToast(String str);
}
