package com.example.administrator.whatiseat.Module;

import com.example.administrator.whatiseat.Fragment.ListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ListFragmentModule {
    @Provides
    public ListFragment provideListFragment(){
        return new ListFragment();
    }
}
