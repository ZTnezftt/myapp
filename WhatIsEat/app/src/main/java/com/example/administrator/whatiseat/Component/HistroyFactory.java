package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Fragment.HistoryFragment;
import com.example.administrator.whatiseat.Module.HistroyViewModule;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.TagModule;

import dagger.Component;

@Component(modules = {TagModule.class, HistroyViewModule.class, LayoutManagerModule.class})
public interface HistroyFactory {
    public void inject(HistoryFragment historyFragment);
}
