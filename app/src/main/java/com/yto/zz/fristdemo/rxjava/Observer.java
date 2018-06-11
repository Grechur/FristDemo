package com.yto.zz.fristdemo.rxjava;

import android.support.annotation.NonNull;

/**
 * Created by zz on 2018/5/15.
 */

public interface Observer<T> {
    void onSubscribe();

    void onNext(@NonNull T t);

    void onError(@NonNull Throwable t);

    void onComplete();
}
