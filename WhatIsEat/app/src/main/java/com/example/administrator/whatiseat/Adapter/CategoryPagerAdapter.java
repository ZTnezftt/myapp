package com.example.administrator.whatiseat.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private List<String> Titlelist;
    private List<Fragment> Fragmentlist;
    public CategoryPagerAdapter(FragmentManager fm,List<String> Titlelist,List<Fragment> Fragmentlist) {
        super(fm);
        this.Titlelist=Titlelist;
        this.Fragmentlist=Fragmentlist;
    }
    @Override
    public Fragment getItem(int i) {
        return Fragmentlist.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Titlelist.get(position);
    }

    @Override
    public int getCount() {
        return Fragmentlist.size();
    }

    public void addList(List<String> list,List<Fragment> list2) {
        this.Titlelist.addAll(list);
        this.Fragmentlist.addAll(list2);
        this.notifyDataSetChanged();
    }

    public void addItem(Object o) {

    }

    public void replace(List list) {

    }
}
