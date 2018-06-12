package com.yto.zz.fristdemo.factory.simple2;

/**
 * Created by zz on 2018/6/11.
 */

public class PreferenceIOHandler implements IOHandler {
    PreferenceUtil preferenceUtil = PreferenceUtil.getInstance();
    @Override
    public void save(String key, String value) {
        preferenceUtil.putString(key, value);
    }

    @Override
    public void save(String key, int value) {
        preferenceUtil.putInt(key, value);
    }

    @Override
    public void save(String key, float value) {
        preferenceUtil.putFloat(key, value);
    }

    @Override
    public void save(String key, boolean value) {
        preferenceUtil.putBool(key, value);
    }

    @Override
    public void save(String key, long value) {
        preferenceUtil.putLong(key, value);
    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key, String value) {
        return preferenceUtil.getString(key);
    }

    @Override
    public int getInt(String key, int value) {
        return preferenceUtil.getInt(key,value);
    }

    @Override
    public float getFloat(String key, float value) {
        return preferenceUtil.getFloat(key, value);
    }

    @Override
    public boolean getBoolean(String key, boolean value) {
        return preferenceUtil.getBool(key, value);
    }

    @Override
    public long getLong(String key, long value) {
        return preferenceUtil.getLong(key,value);
    }

    @Override
    public Object getObject(String key, Object value) {
        return null;
    }
}
