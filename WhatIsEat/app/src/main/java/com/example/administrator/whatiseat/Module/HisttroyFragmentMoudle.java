package com.example.administrator.whatiseat.Module;

import com.example.administrator.whatiseat.Fragment.HistoryFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class HisttroyFragmentMoudle {
    @Provides
    public HistoryFragment pvhf(){
        return new HistoryFragment();
    }
}
