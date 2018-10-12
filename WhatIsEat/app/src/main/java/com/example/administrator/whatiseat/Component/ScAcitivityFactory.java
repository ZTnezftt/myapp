package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Activity.ScActivity;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.ScAdapterMoudle;

import dagger.Component;
import dagger.Module;

@Component(modules = {LayoutManagerModule.class, ScAdapterMoudle.class})
public interface ScAcitivityFactory {
    public void inject(ScActivity scActivity);
}
