package com.yto.zz.fristdemo.factory.simple2;

/**
 * Created by zz on 2018/6/12.
 */

public class MemoryFactory implements IOFactory{
    @Override
    public IOHandler createIOHandler() {
        return new MemoryIOHandler();
    }
}
