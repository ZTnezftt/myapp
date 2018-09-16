package com.example.administrator.whatiseat.Module;

import android.content.Context;

import com.example.administrator.whatiseat.Adapter.MoreItemAdapter;
import com.example.administrator.whatiseat.Bean.MoreItem;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.POST;

@Module
public class MoreAdapterModule {
    private Context context;
    public MoreAdapterModule(Context context){
        this.context=context;
    }
    @Provides
    public MoreItemAdapter pvmia(List<MoreItem> list){
        return  new MoreItemAdapter(context,list);
    }
    @Provides
    public List<MoreItem> pvl(){
        return new LinkedList<>();
    }
}
