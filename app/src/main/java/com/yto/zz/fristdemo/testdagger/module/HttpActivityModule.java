package com.yto.zz.fristdemo.testdagger.module;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by zz on 2018/6/1.
 */

@Module
public class HttpActivityModule {
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }
}
