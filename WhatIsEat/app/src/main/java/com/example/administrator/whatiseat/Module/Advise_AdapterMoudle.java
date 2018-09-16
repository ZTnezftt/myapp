package com.example.administrator.whatiseat.Module;

import android.content.Context;

import com.example.administrator.whatiseat.Adapter.Advise_Adapter;
import com.example.administrator.whatiseat.Bean.Advise_Title;

import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class Advise_AdapterMoudle {
    private Context context;
    public Advise_AdapterMoudle(Context context){
        this.context=context;
    }

    @Provides
    public Advise_Adapter pvaa(List<Advise_Title> list){
        return new Advise_Adapter(list,context);
    }

    @Provides
    public List<Advise_Title> pvl(){
        return new LinkedList<>();
    }
}
