package com.example.administrator.whatiseat.P;

import android.os.Bundle;

import com.example.administrator.whatiseat.Fragment.IMainFragmentView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface IMainFragmentPresenter<T> {
    void SetAdapter();
    //public List<T> getList();
    public void setBundle();
    public void getList();
    public LifecycleTransformer rxunbind();
}
