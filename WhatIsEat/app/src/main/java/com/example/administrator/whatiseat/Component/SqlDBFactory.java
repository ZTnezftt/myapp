package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.M.HistoryFragmentModel;
import com.example.administrator.whatiseat.Module.SqlDBModule;
import com.example.administrator.whatiseat.P.DetailedPresenterCompl;
import com.example.administrator.whatiseat.P.SearchViewCompl;

import dagger.Component;

@Component(modules = {SqlDBModule.class})
public interface SqlDBFactory {
    public void inject(SearchViewCompl searchViewCompl);
    public void inject(HistoryFragmentModel historyFragmentModel);
}
