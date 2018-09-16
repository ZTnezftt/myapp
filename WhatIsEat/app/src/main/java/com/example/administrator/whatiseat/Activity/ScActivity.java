package com.example.administrator.whatiseat.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.whatiseat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScActivity extends BaseActivity {
    @BindView(R.id.de_Title) TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclayout);
        ButterKnife.bind(this);
        title.setText("我的收藏");
    }
    public void initDagger2(){

    }
}
