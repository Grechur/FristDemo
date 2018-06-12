package com.yto.zz.fristdemo.factory.simple1;

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
        mCache.put(key,value);
    }

    @Override
    public void save(String key, float value) {
        mCache.put(key,value);
    }

    @Override
    public void save(String key, boolean value) {
        mCache.put(key,value);
    }

    @Override
    public void save(String key, long value) {
        mCache.put(key,value);
    }

    @Override
    public void save(String key, Object value) {
        mCache.put(key,value);
    }

    @Override
    public String getString(String key, String value) {
        return (String) mCache.get(key);
    }

    @Override
    public int getInt(String key, int value) {
        return (int) mCache.get(key);
    }

    @Override
    public float getFloat(String key, float value) {
        return (float) mCache.get(key);
    }

    @Override
    public boolean getBoolean(String key, boolean value) {
        return (boolean) mCache.get(key);
    }

    @Override
    public long getLong(String key, long value) {
        return (long) mCache.get(key);
    }

    @Override
    public Object getObject(String key, Object value) {
        return mCache.get(key);
    }


}
