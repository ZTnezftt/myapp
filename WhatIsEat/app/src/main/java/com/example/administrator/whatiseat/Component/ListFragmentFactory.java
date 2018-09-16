package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Fragment.ListFragment;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.ListFragmentAdapterMoudle;

import dagger.Component;

@Component(modules = {LayoutManagerModule.class, ListFragmentAdapterMoudle.class})
public interface ListFragmentFactory {
    public void inject(ListFragment listFragment);
}
