package com.yto.zz.fristdemo.rxjava;

/**
 * Created by zz on 2018/5/15.
 */

public class ObservableJust<T> extends Observable<T>{
    private T item;

    public ObservableJust(T item){
        this.item = item;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        observer.onSubscribe();

        try {
            observer.onNext(item);

            observer.onComplete();
        }catch (Exception e){
            observer.onError(e);
        }

    }
}
