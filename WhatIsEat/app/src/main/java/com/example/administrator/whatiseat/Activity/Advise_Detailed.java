package com.example.administrator.whatiseat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.whatiseat.Adapter.Advise_Adapter;
import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Title;
import com.example.administrator.whatiseat.Component.DaggerAdvise_DetailedFactory;
import com.example.administrator.whatiseat.Fragment.MainFragment;
import com.example.administrator.whatiseat.Module.Advise_AdapterMoudle;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.P.Advise_DetailedCompl;
import com.example.administrator.whatiseat.R;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
        title栏的设置
        其他界面优化

 */
public class Advise_Detailed extends BaseActivity implements IAdvise_Detailed {
    @BindView(R.id.TitleBack) ImageView back;
    @BindView(R.id.de_Title) TextView title;
    @BindView(R.id.advise_list) RecyclerView recyclerView;
    @Inject @Named("LinearLayoutManager") RecyclerView.LayoutManager layoutManager;
    @Inject Advise_Adapter advise_adapter;
    Advise_DetailedCompl advise_detailedCompl=new Advise_DetailedCompl(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisedetailed);
        Init_Dagger();
        Bundle bundle=getIntent().getBundleExtra("ids");
        title.setText(bundle.getString("title"));
        advise_detailedCompl.getData((List<String>)bundle.getSerializable("ids"));
    }

    public void Init_Dagger(){
        ButterKnife.bind(this);
        DaggerAdvise_DetailedFactory.builder()
                .advise_AdapterMoudle(new Advise_AdapterMoudle(this))
                .layoutManagerModule(new LayoutManagerModule(this))
                .build().inject(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(advise_adapter);
        advise_adapter.layoutClick(new Advise_Adapter.Callback_Click() {
            @Override
            public void layoutClick(String id) {
                Intent intent=new Intent();
                intent.putExtra("ID",Integer.valueOf(id));
                intent.setClass(Advise_Detailed.this,DetailedActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setAdapter(List<Advise_Title> list) {
        advise_adapter.replace(list);
    }

    @Override
    public void setToast(String str) {
        Toast(str);
    }

    @OnClick(R.id.TitleBack)
    public void ClickBack(){
        this.finishThis();
    }
}
