package com.example.administrator.whatiseat.Module;

import android.content.Context;
import android.util.Log;

import com.example.administrator.whatiseat.Adapter.BurdenAdapter;
import com.example.administrator.whatiseat.Bean.Burden;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class BurdenModule {
    private Context context;
    public BurdenModule(Context context){
        this.context=context;
    }
    @Provides
    public BurdenAdapter provideBurden(List<Burden> list,Context context){
        Log.i("module","new BurdenAdapter");
        return new BurdenAdapter(context,list);
    }
    @Provides
    public List<Burden> provideList(){
        return new LinkedList<>();
    }
    @Provides
    public Context provideContext(){
        return this.context;
    }
}
