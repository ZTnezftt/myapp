package com.example.administrator.whatiseat.Module;

import android.content.Context;

import com.example.administrator.whatiseat.Adapter.TagAdapter;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class TagModule {
    private Context context;
    public TagModule(Context context){
        this.context=context;
    }
    @Provides
    public TagAdapter provideTagAdapter(List list){
        return new TagAdapter(list,context);
    }
    @Provides
    public List provideList(){
        return new LinkedList<>();
    }
}
