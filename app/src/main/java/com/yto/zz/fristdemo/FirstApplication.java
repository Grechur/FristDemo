package com.yto.zz.fristdemo;

import android.app.Application;

import com.yto.zz.fristdemo.check.HookUtil;

/**
 * Created by zz on 2018/5/15.
 */

public class FirstApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        HookUtil hookUtil = new HookUtil(this);
        hookUtil.hookSystemHandler();
        hookUtil.hookAms();
    }
}
