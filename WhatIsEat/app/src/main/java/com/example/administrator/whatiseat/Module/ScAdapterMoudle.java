package com.example.administrator.whatiseat.Module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.whatiseat.Adapter.ScAdapter;
import com.example.administrator.whatiseat.Bean.MoreItem;
import com.example.administrator.whatiseat.Bean.Scitem;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ScAdapterMoudle {
    private Context context;
    public ScAdapterMoudle(Context context){
        this.context=context;
    }

    @Provides
    public ScAdapter prvdAdapter(List<Scitem> list){
        return new ScAdapter(context,list);
    }

    @Provides
    public List<Scitem> prvdList(){
        return new LinkedList<>();
    }
}
