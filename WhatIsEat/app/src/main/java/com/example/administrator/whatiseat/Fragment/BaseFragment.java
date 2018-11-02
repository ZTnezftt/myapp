package com.example.administrator.whatiseat.Fragment;

import android.content.Context;
import com.example.administrator.whatiseat.Util.Util;
import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseFragment extends RxFragment {
    protected void Toast(String str){
        Context context=getContext();
        Util.Toast(str,context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
