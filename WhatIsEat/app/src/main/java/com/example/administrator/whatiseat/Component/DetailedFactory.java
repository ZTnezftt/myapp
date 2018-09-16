package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Activity.DetailedActivity;
import com.example.administrator.whatiseat.Fragment.MainFragment;
import com.example.administrator.whatiseat.Module.BurdenModule;
import com.example.administrator.whatiseat.Module.LayoutManagerModule;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.example.administrator.whatiseat.Module.StepsModule;
import com.example.administrator.whatiseat.P.DetailedPresenterCompl;

import dagger.Component;
@Component(modules = { BurdenModule.class, LayoutManagerModule.class,StepsModule.class, SqlDBModule.class, RetrofitRequestModule.class})
public interface DetailedFactory {
    public void inject(DetailedActivity detailedActivity);
    public void inject(DetailedPresenterCompl detailedPresenterCompl);
}
