package com.yto.zz.fristdemo.factory;

/**
 * Created by zz on 2018/6/11.
 */

public interface IOHandler {
    void save(String key,String value);
    void save(String key,int value);
    void save(String key,double value);
    void save(String key,boolean value);
    void save(String key,long value);
    void save(String key,Object value);

    String getString(String key,String value);
    int getInt(String key,int value);
    double getDouble(String key,double value);
    boolean getBoolean(String key,boolean value);
    long getLong(String key,long value);
    Object getObject(String key,Object value);
}
