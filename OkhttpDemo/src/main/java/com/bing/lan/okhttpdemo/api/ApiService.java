package com.bing.lan.okhttpdemo.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {

    String BASE_URL = "http://mobile.ximalaya.com/";

    /*访问全路径API并返回原生数据*/
    @GET
    Observable<ResponseBody> getRaw(@Url String url);

    @GET
    Call<ResponseBody> getRawUrl(@Url String url);

    // @GET
    // Call<String> getRawUrl1(@Url String url);

    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> map
    );


    //
    // @GET("banner")
    // Observable<BannerResultBean> loadBanner(@Query("adKind") int adKind);
    //
    // @GET("seckill")
    // Observable<SecKillResultBean> loadSecKill();
    //
    // @GET("getYourFav")
    // Observable<GetYourLikeResultBean> loadGetYourLike();
    //
    // @GET("category")
    // Observable<CategoryResultBean> loadCategory();
    //
    // @GET("category")
    // Observable<SubCategoryResultBean> loadCategory(@Query("parentId") int parentId);
    //
    // @GET("brand")
    // Observable<BrandResultBean> loadBrand(@Query("categoryId") int categoryId);

    // @POST("searchProduct")
    // Observable<ProductListResultBean> loadProductList(@QueryMap HashMap<String, String> params);    // @FormUrlEncoded
    //
    // @FormUrlEncoded
    // @POST("searchProduct")
    // Observable<ProductListResultBean> loadProductList(@FieldMap HashMap<String, String> params);
    //
    // @GET("productInfo")
    // Observable<ProductInfoResultBean> loadProductInfo(@Query("id") long id);
    // //http://mall.520it.com/productInfo?id=1
    //
    // @FormUrlEncoded
    // @POST("commentDetail")
    // Observable<CommentResultBean> loadProductCommentInfo(@FieldMap HashMap<String, String> params);

    // @GET("home")
    // Call<ResponseBody> getUrl(@Query("index") long index);
    // //http://192.168.196.2:8080/GooglePlayServer/home?index=0
    //
    // @GET("home")
    // Call<HomeInfoBean> getUrl1(@Query("index") long index);
    //
    // @GET("{home}")
    // Observable<HomeInfoBean> getHomeInfo(@Path("home") String home, @Query("index") int index);
    // //http://192.168.196.2:8080/GooglePlayServer/home?index=0

    // /*获得指定id的长评论*/
    // @GET("4/story/{id}/long-comments/{userId}")
    // Observable<CommentListBean> getLongCommentMore(@Path("id") long id, @Path("userId") long userId);
    // //http://news-at.zhihu.com/api/4/story/4232852/long-comments

    // /*获得指定栏目之前的新闻*/
    // @GET("4/section/{id}/before/{timestamp}")
    // Observable<StoriesBeforeBean> getSectionNewsBefore(@Path("id") long id, @Path("timestamp") long date);
    // //http://news-at.zhihu.com/api/4/section/#{section id}/before/#{timestamp}
}
