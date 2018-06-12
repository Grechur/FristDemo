package com.yto.zz.fristdemo.factory.simple1;

/**
 * Created by zz on 2018/6/12.
 */

public class DiskIOHandler implements IOHandler{
    DiskUtil mDiskUtil = DiskUtil.getInstance();
    @Override
    public void save(String key, String value) {
        mDiskUtil.put(key,value);
    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, float value) {

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
        return mDiskUtil.getValue(key);
    }

    @Override
    public int getInt(String key, int value) {
        return 0;
    }

    @Override
    public float getFloat(String key, float value) {
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
