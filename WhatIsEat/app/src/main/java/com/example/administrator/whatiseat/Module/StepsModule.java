package com.example.administrator.whatiseat.Module;

import android.content.Context;

import com.example.administrator.whatiseat.Adapter.StepAdapter;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.Step;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StepsModule {
    private Context context;
    public StepsModule(Context context){
        this.context=context;
    }
    @Provides
    public StepAdapter provideAdapter(List<Step> list){
        return new StepAdapter(context,list);
    }
    @Provides
    public List<Step> provideList(){
        return new LinkedList<>();
    }
}
