package com.yto.zz.fristdemo.testdagger;

import com.yto.zz.fristdemo.FactoryActivity;

import dagger.Component;

/**
 * Created by zz on 2018/6/1.
 */
@Component
public interface FactoryActivityComponent {
    void inject(FactoryActivity factoryActivity);
}
