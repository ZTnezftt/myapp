package com.example.administrator.whatiseat.Fragment;

import android.os.Bundle;

import com.example.administrator.whatiseat.Bean.Burden;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public interface IMainFragmentView<T> {
    public void Click_Titile();
    public void SetAdapter(List<T> list);
    public void setToast(String str);
    public LifecycleTransformer rxlifecycle();
}
