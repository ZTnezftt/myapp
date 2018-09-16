package com.example.administrator.whatiseat.Module;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LayoutManagerModule {

    private Context context;
    private int RowNum=0;
    public LayoutManagerModule(Context context){
        this.context=context;
    }

    public LayoutManagerModule(Context context, int RowNum){
        this.context=context;
        this.RowNum=RowNum;
    }

    @Named("LinearLayoutManager")
    @Provides
    public RecyclerView.LayoutManager provideLLM(){
        return new LinearLayoutManager(context);
    }

    @Named("GridLayoutManager")
    @Provides
    public RecyclerView.LayoutManager provideGLM(){
        if (RowNum<=0){
            Log.e("LayoutManagerError","return LinearLayoutManager");
            return new LinearLayoutManager(context);
        }
        return new GridLayoutManager(context,RowNum);
    }

    @Named("StaggeredGridLayoutManager")
    @Provides
    public RecyclerView.LayoutManager provideSGLM(){
        Log.i("layout","start");
        if (RowNum<=0){
            Log.e("LayoutManagerError","return LinearLayoutManager");
            return new LinearLayoutManager(context);
        }
        return new StaggeredGridLayoutManager(RowNum, StaggeredGridLayoutManager.VERTICAL);
    }

}
