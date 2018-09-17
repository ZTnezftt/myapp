package com.example.administrator.whatiseat.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.whatiseat.Bean.Scitem;
import com.example.administrator.whatiseat.P.ScAcitivityCompl;
import com.example.administrator.whatiseat.R;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScActivity extends BaseActivity implements IScActivity{
    @BindView(R.id.de_Title) TextView title;
    private ScAcitivityCompl scAcitivityCompl=new ScAcitivityCompl(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sclayout);
        ButterKnife.bind(this);
        title.setText("我的收藏");
        initDagger2();
        scAcitivityCompl.getDataForDB();
    }

    public void initDagger2(){

    }

    @Override
    public void setAdapter(List<Scitem> list) {

    }

    @Override
    public LifecycleTransformer rxlifecycle() {
        LifecycleTransformer lifecycleTransformer = bindToLifecycle();
        return lifecycleTransformer;
    }
}
