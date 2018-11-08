package com.example.administrator.whatiseat.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.administrator.whatiseat.Adapter.BurdenAdapter;
import com.example.administrator.whatiseat.Adapter.StepAdapter;
import com.example.administrator.whatiseat.Bean.Burden;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;
import com.example.administrator.whatiseat.Component.DaggerDetailedFactory;
import com.example.administrator.whatiseat.Module.BurdenModule;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.example.administrator.whatiseat.Module.StepsModule;
import com.example.administrator.whatiseat.P.DetailedPresenterCompl;
import com.example.administrator.whatiseat.R;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
  rxjava retrofit butterknife dagger2 的简单应用
 */
public class DetailedActivity extends BaseActivity implements IDetailedView {
    @BindView(R.id.shoucangtext) TextView deShouCangText;
    @BindView(R.id.ctb) CollapsingToolbarLayout collapsingToolbarLayout;
    //@BindView(R.id.TitleBack) ImageButton TitleBack;
    //@BindView(R.id.de_Title) TextView deTitle;
    @BindView(R.id.detoolbar) Toolbar toolbar;
    @BindView(R.id.de_albums) ImageView deAlbums;
    //@BindView(R.id.de_name) TextView deName;
    @BindView(R.id.shoucang) ToggleButton deshoucang;
    @BindView(R.id.imtro) TextView deimtro;
    @BindView(R.id.de_burden) RecyclerView deburden;
    @BindView(R.id.de_steps) RecyclerView desteps;
    @Inject BurdenAdapter burdenAdapter;
    @Inject StepAdapter stepAdapter;
    @Named("LinearLayoutManager") @Inject RecyclerView.LayoutManager layoutManager;
    @Named("LinearLayoutManager") @Inject RecyclerView.LayoutManager layoutManager2;
    private int id;
    private Boolean value=true;
    private DetailedPresenterCompl detailedPresenterCompl;//持有p
    /*
      1.得到传来的id
      2.根据id查询api  并返回json类  需 okhttp gson DetailedJson类
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailedactivity);
        postponeEnterTransition();
        ButterKnife.bind(this);
        init_Dagger2();
        this.id=getIntent().getIntExtra("ID",0);
        detailedPresenterCompl=new DetailedPresenterCompl(this);/*持有P的对象*/
        detailedPresenterCompl.getDetailed();
    }


    /*
       初始化控件
     */
    public void init_Dagger2(){
        DaggerDetailedFactory.builder()
                .sqlDBModule(new SqlDBModule(this))
                .retrofitRequestModule(new RetrofitRequestModule())
                .stepsModule(new StepsModule(this))
                .layoutManagerModule(new LayoutManagerModule(this))
                .burdenModule(new BurdenModule(this))
                .build().inject(this);
        //toolbar.setSubtitleTextAppearance(this,R.style.Toolbar_TitleText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deburden.setLayoutManager(layoutManager);
        deburden.setAdapter(burdenAdapter);
        desteps.setLayoutManager(layoutManager2);
        desteps.setAdapter(stepAdapter);

        deshoucang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (deshoucang.isPressed()) {
                    Log.i("deshoucang","点击触发");
                    detailedPresenterCompl.putZan(String.valueOf(id), deshoucang.isChecked());
                }else {
                    Log.i("deshoucang","设置触发");
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishThis();
            }
        });
    }
    /*
     MVP-V的方法(通过这些方法和P交互更新控件数据)
     */
    @Override
    public int getId() {
        Log.i("V",""+id);
        return this.id;
    }
    @Override
    public void setdeimtro(String str) {
        deimtro.setText(str);
    }

    @Override
    public void setdename(String str) {
        //deName.setText(str);
    }

    @Override
    public void setdetitle(String str) {
        Log.i("title",str);
        collapsingToolbarLayout.setTitle(str);
        //getSupportActionBar().setTitle(str);
        //deTitle.setText(str);
        //toolbar.setTitle(str);
    }

    @Override
    public void setburdenList(List<Burden> list) {
        burdenAdapter.addList(list);
    }

    @Override
    public void setstepsList(List<Step> list) {
        stepAdapter.addList(list);
    }

    @Override
    public void setdealbums(String url) {
        supportStartPostponedEnterTransition();
        Glide.with(this)
                .load(url)
                .into(deAlbums);
    }

    @Override
    public void setShouCangText(String str) {
        deShouCangText.setText("该菜品收获了 "+str+" 个赞~~~~");
    }


    @Override
    public void setToggle(Boolean b) {
        deshoucang.setChecked(b);
    }

    @Override
    public void setValue(Boolean b) {
        this.value=b;
    }

    @Override
    public void setToast(String str) {
        Toast(str);
    }

    /*@OnClick(R.id.TitleBack)
    public void back(){
        finishThis();
    }*/
}
/*
 RxJava RxAndrodi 依赖版本************************一定******************************要一致rxjava2 retrofit2
 Retrofit retrofit.create创建request
 */