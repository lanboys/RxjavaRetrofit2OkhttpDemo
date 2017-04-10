package com.bing.lan.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.HOURS)
                .build();
    }

    public void request2(View view) {

        //创建okHttpClient对象
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://www.baidu.com/")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            //子线程
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.d("onResponse(): " + response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                log.e("onFailure(): " + e);
            }
        });
    }

    public void request1(View view) {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.HOURS).build();
    }

    public void copyWithBufferedStream(File src, File dest) {
        BufferedInputStream bufferedInput = null;
        BufferedOutputStream bufferedOutput = null;
        try {

            FileInputStream fileInputStream = new FileInputStream(src);

            bufferedInput = new BufferedInputStream(new FileInputStream(src));
            bufferedOutput = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[1024];
            int copySize;
            bufferedInput.read();

            fileInputStream.read();
            fileInputStream.read(buffer);

            while ((copySize = bufferedInput.read(buffer)) > 0) {
                bufferedOutput.write(buffer, 0, copySize);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedInput.close();
                bufferedOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

