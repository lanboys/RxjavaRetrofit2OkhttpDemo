package com.bing.lan.retrofit2.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author 蓝兵
 * @time 2017/1/24  22:44
 */
public interface ApiStores {

    String BASE_URL = "http://192.168.100.104:8080/GooglePlayServer/";

    // http://192.168.196.2:8080/GooglePlayServer/image?name=app/com.m520it.www/icon.jpg

    // @GET{}
    // @GET{}
    // @GET{}
    //      public static String getAppIconUrl(String url);

    @GET("home")
    Call<ResponseBody> getUrl(@Query("index") long index);
    //http://192.168.196.2:8080/GooglePlayServer/home?index=0

    @GET("home")
    Call<HomeInfoBean> getUrl1(@Query("index") long index);
    //http://192.168.196.2:8080/GooglePlayServer/home?index=0








    // /*获得最新新闻*/
    // @GET("4/news/latest")
    // Observable<StoriesLatestBean> getLatestNews();
    // //http://news-at.zhihu.com/api/4/news/latest

    // /*获得新闻详情*/
    // @GET("4/news/{id}")
    // Observable<NewsDetailBean> getNewsDetails(@Path("id") long id);
    // //http://news-at.zhihu.com/api/4/news/3892357
}
