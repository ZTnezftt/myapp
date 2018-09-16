package com.example.administrator.whatiseat.Component;

import com.example.administrator.whatiseat.M.Advise_DetailedModel;
import com.example.administrator.whatiseat.M.ListFragmentModel;
import com.example.administrator.whatiseat.M.MainFragmentModel;
import com.example.administrator.whatiseat.Module.RetrofitRequestModule;
import com.example.administrator.whatiseat.P.DetailedPresenterCompl;
import com.example.administrator.whatiseat.P.MainFragmentCompl;
import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;
import dagger.Component;

@Component(modules = {RetrofitRequestModule.class})
public interface RetrofitFactory {
    //void inject(DetailedPresenterCompl detailedPresenterCompl);
    void inject(MainFragmentCompl mainFragmentCompl);
    void inject(MainFragmentModel mainFragmentModel);
    void inject(ListFragmentModel listFragmentModel);
    void inject(Advise_DetailedModel advise_detailedModel);
}
