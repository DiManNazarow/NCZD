package ru.mbg.nczd.network;

import retrofit2.Retrofit;

/**
 * Created by Дмитрий on 30.01.2018.
 */

public class RetrofitProvider {

    private static RetrofitProvider sInstance;

    public static final String BASE_URL = "http://dev4.moonsitecrew.ru";

    private Retrofit mRetrofit;

    private RetrofitProvider() {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
    }

    public static RetrofitProvider instance(){
        if (sInstance == null){
            sInstance = new RetrofitProvider();
        }
        return sInstance;
    }

    public <S> S getService(Class<S> type){
        return mRetrofit.create(type);
    }

}
