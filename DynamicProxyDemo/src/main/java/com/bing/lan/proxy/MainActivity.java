package com.bing.lan.proxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void newProxy(View view) {
        DynamicProxyDemo01 dynamicProxyDemo01 = new DynamicProxyDemo01();
        dynamicProxyDemo01.test2(Subject.class);

    }



}
