package com.example.administrator.whatiseat.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MainPagerAdapter extends PagerAdapter {
    List<View> list;
    int item;
    public MainPagerAdapter(List<View> list){
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (list.get(position)!=null) {
            container.addView(list.get(position));
            this.item = position;
            return list.get(position);
        }
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));
    }
    public int getthis(){
        return item;
    }
}
