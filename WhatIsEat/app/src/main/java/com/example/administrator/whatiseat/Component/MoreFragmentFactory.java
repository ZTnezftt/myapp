package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Fragment.MoreFragment;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.MoreAdapterModule;

import dagger.Component;
import dagger.Module;

@Component(modules = {LayoutManagerModule.class, MoreAdapterModule.class})
public interface MoreFragmentFactory {
    public void inject(MoreFragment moreFragment);
}
