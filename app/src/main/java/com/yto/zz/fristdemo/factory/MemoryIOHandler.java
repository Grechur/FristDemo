package com.yto.zz.fristdemo.factory;

import android.util.LruCache;

/**
 * Created by zz on 2018/6/11.
 */

public class MemoryIOHandler implements IOHandler{
    LruCache<String,Object> mCache = new LruCache<>(10*1024*1024);
    @Override
    public void save(String key, String value) {
        mCache.put(key,value);
    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, boolean value) {

    }

    @Override
    public void save(String key, long value) {

    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key, String value) {
        return (String) mCache.get(key);
    }

    @Override
    public int getInt(String key, int value) {
        return 0;
    }

    @Override
    public double getDouble(String key, double value) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean value) {
        return false;
    }

    @Override
    public long getLong(String key, long value) {
        return 0;
    }

    @Override
    public Object getObject(String key, Object value) {
        return null;
    }
}
