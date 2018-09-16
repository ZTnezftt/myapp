package com.example.administrator.whatiseat.P;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface ISearchViewPresenter {
    public void toDB(String s);
    public LifecycleTransformer rxunbind();
}
