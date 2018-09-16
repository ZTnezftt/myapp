package com.example.administrator.whatiseat.Module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.whatiseat.DB_List.DataBase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SqlDBModule {
    private Context context;
    public SqlDBModule(Context context){
        this.context=context;
    }

    @Named("Write")
    @Provides
    public SQLiteDatabase pvsqlw(DataBase dataBase){
        return dataBase.getWritableDatabase();
    }

    @Named("Read")
    @Provides
    public SQLiteDatabase pvsqlr(DataBase dataBase){
        return dataBase.getReadableDatabase();
    }

    @Provides
    public DataBase pvhistroy(){
        return new DataBase(context, "DataBase", null, 1);
    }
}
