package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.Activity.SearchView;
import com.example.administrator.whatiseat.Module.HisttroyFragmentMoudle;
import com.example.administrator.whatiseat.Module.ListFragmentModule;
import dagger.Component;

@Component(modules = {HisttroyFragmentMoudle.class, ListFragmentModule.class})
public interface SearchViewFactory {
    public void inject(SearchView searchView);
}
