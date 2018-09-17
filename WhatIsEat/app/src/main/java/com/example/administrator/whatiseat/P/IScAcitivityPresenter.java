package com.example.administrator.whatiseat.P;

import com.example.administrator.whatiseat.Bean.Scitem;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface IScAcitivityPresenter {
    public void  setAdapter(List<Scitem> list);
    public void getDataForDB();
    public LifecycleTransformer rxunbind();
}
