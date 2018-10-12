package com.example.administrator.whatiseat.Fragment;

import android.os.Bundle;

import com.example.administrator.whatiseat.Bean.Burden;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface IMainFragmentView<T> {
    public void SetAdapter(List<T> list);//给fragment设置数据
    public void setToast(String str);//设置toast
    public LifecycleTransformer rxlifecycle();
}
