package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Fragment.CategoryFragment;
import com.example.administrator.whatiseat.Module.CategoryPagerAdapterModule;

import dagger.Component;

@Component(modules = {CategoryPagerAdapterModule.class})
public interface CategoryFactory {
    void inject(CategoryFragment categoryFragment);
}
