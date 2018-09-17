package com.example.administrator.whatiseat.Activity;

import com.example.administrator.whatiseat.Bean.Scitem;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface IScActivity {
    public void setAdapter(List<Scitem> list);
    public LifecycleTransformer rxlifecycle();
}
