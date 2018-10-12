package com.example.administrator.whatiseat.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.whatiseat.Adapter.BurdenAdapter;
import com.example.administrator.whatiseat.Adapter.ScAdapter;
import com.example.administrator.whatiseat.Bean.Scitem;
import com.example.administrator.whatiseat.Component.DaggerScAcitivityFactory;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.ScAdapterMoudle;
import com.example.administrator.whatiseat.P.ScAcitivityCompl;
import com.example.administrator.whatiseat.R;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScActivity extends BaseActivity implements IScActivity{
    @BindView(R.id.de_Title) TextView title;
    @BindView(R.id.scrv) RecyclerView recyclerView;
    @Inject ScAdapter scAdapter;
    @Named("LinearLayoutManager") @Inject RecyclerView.LayoutManager layoutManager;
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
        DaggerScAcitivityFactory.builder()
                .layoutManagerModule(new LayoutManagerModule(this))
                .scAdapterMoudle(new ScAdapterMoudle(this))
                .build().inject(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(scAdapter);
        scAdapter.CallBack(new ScAdapter.OnClick() {
            @Override
            public void Click(String id) {
                Intent intent=new Intent();
                intent.putExtra("ID",Integer.valueOf(id));
                intent.setClass(ScActivity.this,DetailedActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setAdapter(List<Scitem> list) {
        scAdapter.replace(list);
    }

    @Override
    public LifecycleTransformer rxlifecycle() {
        LifecycleTransformer lifecycleTransformer = bindToLifecycle();
        return lifecycleTransformer;
    }
}
