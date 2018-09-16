package com.example.administrator.whatiseat.Retrofit;

import com.example.administrator.whatiseat.Bean.Advise_Title;
import com.example.administrator.whatiseat.Bean.Banner_in;
import com.example.administrator.whatiseat.Bean.Data_DIR_Bean.Data_DIRJSON;
import com.example.administrator.whatiseat.Bean.Detailed_Bean.DetailedJSON;
import com.example.administrator.whatiseat.Bean.Dir_Show_List;
import com.example.administrator.whatiseat.Bean.TuiJian;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitRequest {
    public String key="4655e53039c9358c89fb0069e5829a3c";

    @GET("queryid?key="+key)
    Observable<DetailedJSON> getDe(@Query("id")String id);//获得详情

    @GET("GetBanner")
    Observable<Banner_in> getMainFragmentList();//获得banner

    @GET("GetAdvise")
    Observable<TuiJian> getAdvise();//获得推荐

    @GET("index?key="+key+"&format=1&rn=10")
    Observable<Data_DIRJSON> getListForId(@Query("cid") String id, @Query("pn") String index);//列表forid

    @GET("query.php?key="+key+"&rn=10")
    Observable<Data_DIRJSON> getListForMenu(@Query("menu")String str,@Query("pn") String index);//for menu

    @GET("GetZan")
    Observable<Integer> getZan(@Query("id") String id);//获得赞

    @GET("PutZan")
    Call<Void> putZan(@Query("id")String id , @Query("type")Boolean type);//提交赞

    @GET("getIds")
    Observable<List<Advise_Title>> getids(@Query("str") String str);//获得ids

    @GET("getLR")
    Observable<String> getLR(@Query("type") String type);//获得LR的advise ids

}
