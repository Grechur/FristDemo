package com.yto.zz.fristdemo.testdagger;

import javax.inject.Inject;

/**
 * Created by zz on 2018/6/1.
 */

public class Factory {
    public Producet producet;
    @Inject
    public Factory(Producet producet){
        this.producet=producet;
    }
}
