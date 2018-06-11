package com.yto.zz.fristdemo.rxjava;

/**
 * Created by zz on 2018/5/15.
 */

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
