package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Activity.Advise_Detailed;
import com.example.administrator.whatiseat.Module.Advise_AdapterMoudle;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;

import dagger.Component;

@Component(modules = {Advise_AdapterMoudle.class, LayoutManagerModule.class})
public interface Advise_DetailedFactory {
    public void inject(Advise_Detailed advise_detailed);
}
