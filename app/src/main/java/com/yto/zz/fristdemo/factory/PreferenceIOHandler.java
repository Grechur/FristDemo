package com.yto.zz.fristdemo.factory;

import android.content.SharedPreferences;

/**
 * Created by zz on 2018/6/11.
 */

public class PreferenceIOHandler implements IOHandler{
    PreferenceUtil preferenceUtil = PreferenceUtil.getInstance();
    @Override
    public void save(String key, String value) {
        preferenceUtil.putString(key, value);
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
        return preferenceUtil.getString(key);
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
