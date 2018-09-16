package com.example.administrator.whatiseat.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.whatiseat.Fragment.CategoryFragment;
import com.example.administrator.whatiseat.Fragment.MainFragment;
import com.example.administrator.whatiseat.Fragment.MoreFragment;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.Util.Util;

public class Main extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioGroup radioGroup;
    private RadioButton shouye,fenlei,more;
    private CardView cardTitle;
    Drawable drawable1,drawable2,drawable3,shouyeactive,fenleiactive,moreactive;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        initTab();
        fragmentManager=getSupportFragmentManager();
        cardTitle=(CardView)findViewById(R.id.cardtitle);
        cardTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_Titile();
            }
        });
        radioGroup=(RadioGroup)findViewById(R.id.maintab);
        shouye=(RadioButton)findViewById(R.id.shouye);
        fenlei=(RadioButton)findViewById(R.id.fenlei);
        more=(RadioButton)findViewById(R.id.more);
        shouye.setCompoundDrawables(null,drawable1,null,null);
        fenlei.setCompoundDrawables(null,drawable2,null,null);
        more.setCompoundDrawables(null,drawable3,null,null);
        radioGroup.setOnCheckedChangeListener(this);
        shouye.setChecked(true);
    }
    public void initTab(){
        int rb;
        rb= Util.dpToPx(this,26);
        drawable1=getResources().getDrawable(R.drawable.sy1);
        drawable1.setBounds(0,0,rb,rb);
        drawable2=getResources().getDrawable(R.drawable.fl1);
        drawable2.setBounds(0,0,rb,rb);
        drawable3=getResources().getDrawable(R.drawable.gd1);
        drawable3.setBounds(0,0,rb,rb);
        shouyeactive=getResources().getDrawable(R.drawable.sy2);
        shouyeactive.setBounds(0,0,rb,rb);
        fenleiactive=getResources().getDrawable(R.drawable.fl2);
        fenleiactive.setBounds(0,0,rb,rb);
        moreactive=getResources().getDrawable(R.drawable.gd2);
        moreactive.setBounds(0,0,rb,rb);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.shouye:
                Log.i("main","选择了首页");
                shouye.setCompoundDrawables(null,shouyeactive,null,null);
                fenlei.setCompoundDrawables(null,drawable2,null,null);
                more.setCompoundDrawables(null,drawable3,null,null);
                fragmentTransaction=fragmentManager.beginTransaction();
                MainFragment mainFragment=new MainFragment();
                fragmentTransaction.replace(R.id.mainfragment,mainFragment);
                fragmentTransaction.commit();
                break;
            case R.id.fenlei:
                Log.i("main","选择了分类");
                shouye.setCompoundDrawables(null,drawable1,null,null);
                more.setCompoundDrawables(null,drawable3,null,null);
                fenlei.setCompoundDrawables(null,fenleiactive,null,null);
                fragmentTransaction=fragmentManager.beginTransaction();
                CategoryFragment categoryFragment=new CategoryFragment();
                fragmentTransaction.replace(R.id.mainfragment,categoryFragment);
                fragmentTransaction.commit();
                break;
            case R.id.more:
                Log.i("main","选择了更多");
                shouye.setCompoundDrawables(null,drawable1,null,null);
                fenlei.setCompoundDrawables(null,drawable2,null,null);
                more.setCompoundDrawables(null,moreactive,null,null);
                fragmentTransaction=fragmentManager.beginTransaction();
                MoreFragment moreFragment=new MoreFragment();
                fragmentTransaction.replace(R.id.mainfragment,moreFragment);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    public void Click_Titile() {
        Intent intent = new Intent(this, SearchView.class);
        startActivity(intent);
    }
}
