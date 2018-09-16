package com.example.administrator.whatiseat.Module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.administrator.whatiseat.Adapter.CategoryPagerAdapter;
import com.example.administrator.whatiseat.Fragment.CategoryFragment;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryPagerAdapterModule {
    private FragmentManager fragmentManager;
    public CategoryPagerAdapterModule(FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
    }
    @Provides
    public CategoryPagerAdapter pvcpa(){
        return new CategoryPagerAdapter(fragmentManager,new LinkedList<String>(),new LinkedList<Fragment>());
    }
}
