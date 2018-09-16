package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Fragment.MainFragment;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.MainFragmentAdapterModule;

import dagger.Component;

@Component(modules = {LayoutManagerModule.class, MainFragmentAdapterModule.class})
public interface MainFragmentFactroy {
    public void inject(MainFragment mainFragment);
}
