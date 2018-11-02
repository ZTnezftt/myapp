package com.example.administrator.whatiseat.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.whatiseat.Activity.Main;
import com.example.administrator.whatiseat.Activity.MyApplication;
import com.example.administrator.whatiseat.Adapter.CategoryPagerAdapter;
import com.example.administrator.whatiseat.Component.DaggerCategoryFactory;
import com.example.administrator.whatiseat.DB_List.Categroy.Category_Show_List;
import com.example.administrator.whatiseat.DB_List.Categroy.Items;
import com.example.administrator.whatiseat.Module.CategoryPagerAdapterModule;
import com.example.administrator.whatiseat.P.CategoryFragmentCompl;
import com.example.administrator.whatiseat.R;
import com.example.administrator.whatiseat.Util.SpUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends BaseFragment implements ICategoryFragmentView{
    @BindView(R.id.vp) ViewPager viewPager;
    @BindView(R.id.stl) SlidingTabLayout slidingTabLayout;
    @Inject CategoryPagerAdapter categoryPagerAdapter;
    private List<String> title=new LinkedList<>();
    private List<Fragment> mFragments = new LinkedList<>();
    private FragmentManager fragmentManager;
    private CategoryFragmentCompl categoryFragmentCompl=new CategoryFragmentCompl(this);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.categoryfragment,container,false);
        ButterKnife.bind(this,view);
        Init_Dagger2();
        CardView Title=getActivity().findViewById(R.id.cardtitle);
        Title.setVisibility(View.VISIBLE);
        categoryFragmentCompl.getTitle(MyApplication.isFirst,SpUtil.getInt(getContext(),"category",0));
        //MyApplication.FirstOpen=false;

        return view;
    }
    public void Init_Dagger2(){
        fragmentManager=getChildFragmentManager();
        DaggerCategoryFactory.builder()
                .categoryPagerAdapterModule(new CategoryPagerAdapterModule(fragmentManager))
                .build().inject(this);
        viewPager.setAdapter(categoryPagerAdapter);
        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public void setTitile(List<String> list) {
        title=list;
    }
    @Override
    public void setFragmetList(List<List<Items>> list) {
        for (int i=0;i<list.size();i++){
            CategoryItemFragment categoryItemFragment=new CategoryItemFragment();
            categoryItemFragment.setList(list.get(i));
            mFragments.add(categoryItemFragment);
        }
    }

    @Override
    public void setView() {
        categoryPagerAdapter.addList(title,mFragments);
        slidingTabLayout.notifyDataSetChanged();
    }

    @Override
    public void setToast(String str) {
        Toast(str);
    }

    @Override
    public LifecycleTransformer bindto() {
        return bindToLifecycle();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (title!=null){
            title.clear();
            title=null;
        }
        if (mFragments!=null){
            mFragments.clear();
            mFragments=null;
        }
    }
}

