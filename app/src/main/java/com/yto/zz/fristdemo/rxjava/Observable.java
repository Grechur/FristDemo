package com.yto.zz.fristdemo.rxjava;

/**
 * Created by zz on 2018/5/15.
 */

public abstract class Observable<T> implements ObservableSource<T>{

    public static <T> ObservableSource<T> just(T item){

        return onAccembly(new ObservableJust<>(item));
    }

    private static <T> ObservableSource<T> onAccembly(ObservableJust<T> observableJust) {
        return observableJust;
    }
}
