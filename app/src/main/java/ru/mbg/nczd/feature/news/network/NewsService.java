package ru.mbg.nczd.feature.news.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.mbg.nczd.network.RetrofitService;

/**
 * Created by Дмитрий on 30.01.2018.
 */

public interface NewsService {

    @GET("/")
    Call<ResponseBody> getConfiguration(@Query("request") String request);

    @GET("/")
    Call<ResponseBody> getNewsList(@Query("request") String request, @Query("page") int number);

}
