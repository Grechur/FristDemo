package com.yto.zz.fristdemo.testdagger.module;

import dagger.Component;

/**
 * Created by zz on 2018/6/1.
 */
@Component(modules = HttpActivityModule.class)
public interface HttpActivityComponent {
    void inject(HttpActivity httpActivity);
}
