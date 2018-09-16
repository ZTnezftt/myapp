package com.example.administrator.whatiseat.Module;

import com.example.administrator.whatiseat.Retrofit.RetrofitRequest;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitRequestModule {


    @Named("de")
    @Provides
    public RetrofitRequest provideRequestde(@Named("rde") Retrofit retrofit){
        return retrofit.create(RetrofitRequest.class);
    }

    @Named("page")
    @Provides
    public RetrofitRequest provideRequestPage(@Named("rpage") Retrofit retrofit){
        return retrofit.create(RetrofitRequest.class);
    }

    @Named("rde")
    @Provides
    public Retrofit provideRetrofitde(){
        return new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/cook/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Named("rpage")
    @Provides
    public Retrofit provideRetrofitpage(){
        return new Retrofit.Builder()
                .baseUrl("http://134.175.17.50:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
