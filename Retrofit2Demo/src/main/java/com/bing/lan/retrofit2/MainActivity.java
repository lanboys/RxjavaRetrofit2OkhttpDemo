package com.bing.lan.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bing.lan.myapplication.R;
import com.bing.lan.retrofit2.api.ApiManager;
import com.bing.lan.retrofit2.api.ApiStores;
import com.bing.lan.retrofit2.api.HomeInfoBean;
import com.bing.lan.utils.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    private ApiStores mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPic1(View view) {
        mApiService = ApiManager.getApiService();

        final Call<ResponseBody> call = mApiService.getUrl(0);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> bodyResponse) {
                try {
                    ResponseBody body = bodyResponse.body();
                    log.d("onResponse(): " + Thread.currentThread().getName());
                    String s = null;
                    s = body.string();
                    log.d("requestPic1(): 异步请求" + s);
                } catch (IOException e) {
                    log.e("onResponse:  ", e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void requestPic2(View view) {
        mApiService = ApiManager.getApiService();

        final Call<HomeInfoBean> call = mApiService.getUrl1(0);
        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //
        //         try {
        //             Response<HomeInfoBean> beanResponse = call.execute();
        //             HomeInfoBean homeInfoBean = beanResponse.body();
        //
        //             log.d("requestPic2(): " + homeInfoBean.toString());
        //         } catch (IOException e) {
        //             log.e("requestPic:  ", e);
        //         }
        //     }
        // }).start();

        call.enqueue(new Callback<HomeInfoBean>() {
            @Override
            public void onResponse(Call<HomeInfoBean> call, Response<HomeInfoBean> response) {
                HomeInfoBean homeInfoBean = response.body();
                log.d("onResponse(): " + homeInfoBean.toString());
            }

            @Override
            public void onFailure(Call<HomeInfoBean> call, Throwable t) {
                log.e("onFailure():  ", t);
            }
        });
    }

    public void requestPic3(View view) {
        mApiService = ApiManager.getApiService();
    }
}
