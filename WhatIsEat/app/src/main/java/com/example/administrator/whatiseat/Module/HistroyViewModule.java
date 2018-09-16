package com.example.administrator.whatiseat.Module;

import android.content.Context;

import com.example.administrator.whatiseat.Adapter.HistroyViewAdapter;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class HistroyViewModule {
    private Context context;
    public HistroyViewModule(Context context){
        this.context=context;
    }
    @Provides
    public HistroyViewAdapter provideHistroyViewAdapter(List<String> list){
        return new HistroyViewAdapter(context,list);
    }
    @Provides
    public List<String> provideList(){
        return new LinkedList<>();
    }
}
