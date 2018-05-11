package com.yto.zz.fristdemo.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zz on 2018/5/11.
 */

class AsyncPoster implements Runnable {

    Subscription mSubscription;
    Object mEvent;

    final static ExecutorService executorService = Executors.newCachedThreadPool();

    AsyncPoster(Subscription subscription, Object event) {
        this.mEvent = event;
        this.mSubscription = subscription;
    }

    public static void enqueue(Subscription subscription, Object event) {
        AsyncPoster asyncPoster = new AsyncPoster(subscription,event);
        executorService.execute(asyncPoster);
    }

    @Override
    public void run() {
        try {
            mSubscription.subscriberMethod.method.invoke(mSubscription.subscriber,mEvent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
