package com.yto.zz.fristdemo.testdagger.module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yto.zz.fristdemo.R;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class HttpActivity extends AppCompatActivity {
    @Inject
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        DaggerHttpActivityComponent.create().inject(this);
        Toast.makeText(this," OkHttpClient"+okHttpClient.hashCode(),Toast.LENGTH_SHORT).show();
    }
}
