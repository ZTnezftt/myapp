package com.example.administrator.whatiseat.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.whatiseat.Util.Util;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class BaseActivity extends RxFragmentActivity {
    public void finishThis(){
        this.finish();
    }
    public Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void Toast(String str){
        Util.Toast(str,this);
    }
}
