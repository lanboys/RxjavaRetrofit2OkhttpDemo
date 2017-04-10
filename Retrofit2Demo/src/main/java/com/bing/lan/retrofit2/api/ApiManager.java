package com.bing.lan.retrofit2.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 蓝兵
 * @time 2017/1/24  22:50
 */
public class ApiManager {

    private static volatile ApiManager instance  ;
    private final Retrofit mRetrofit;

    private ApiManager() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiStores.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create( ))
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }

    public static ApiStores getApiService() {
        return getInstance().mRetrofit.create(ApiStores.class);
    }

    public static ApiManager getInstance() {

        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }
}
