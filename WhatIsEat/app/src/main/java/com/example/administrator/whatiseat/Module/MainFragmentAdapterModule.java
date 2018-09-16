package com.example.administrator.whatiseat.Module;

import android.content.Context;
import android.telecom.Call;

import com.example.administrator.whatiseat.Adapter.TuiJianListAdapter;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentAdapterModule {
    private Context context;
    public MainFragmentAdapterModule(Context context){
        this.context=context;
    }

    @Provides
    public TuiJianListAdapter provideTuiJian(){
        return new TuiJianListAdapter(context,new LinkedList());
    }
}
