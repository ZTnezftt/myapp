package com.example.administrator.whatiseat.Module;

import android.content.Context;

import com.example.administrator.whatiseat.Adapter.Dir_Show_Adapter;
import com.example.administrator.whatiseat.Bean.Dir_Show_List;

import java.util.LinkedList;

import dagger.Module;
import dagger.Provides;

@Module
public class ListFragmentAdapterMoudle {
    private Context context;
    public ListFragmentAdapterMoudle(Context context){
        this.context=context;
    }
    @Provides
    public Dir_Show_Adapter pvd_s_a(){
        return new Dir_Show_Adapter(context,new LinkedList<Dir_Show_List>());
    }
}
