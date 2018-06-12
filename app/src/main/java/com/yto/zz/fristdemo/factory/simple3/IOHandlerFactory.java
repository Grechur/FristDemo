package com.yto.zz.fristdemo.factory.simple3;


/**
 * Created by zz on 2018/6/11.
 */

public class IOHandlerFactory {
    private static volatile IOHandlerFactory instance;
    private IOHandler mMemoryIOHandler;
    private IOHandler mPreferencesIOHandler;
    private IOHandler mDiskIOHandler;

    private IOHandlerFactory(){

    }

    public static IOHandlerFactory getInstance(){
        if(instance == null){
            synchronized (IOHandlerFactory.class){
                if(instance == null){
                    instance = new IOHandlerFactory();
                }
            }
        }
        return instance;
    }

    private IOHandler createIOHandler(Class<? extends IOHandler> ioHandler){
        try {
            return ioHandler.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new PreferenceIOHandler();
    }

    public IOHandler getDefaultIOHandler(){
        return getPreferenceIOHandler();
    }

    public IOHandler getPreferenceIOHandler(){
        if(mPreferencesIOHandler == null){
            mPreferencesIOHandler = createIOHandler(PreferenceIOHandler.class);
        }
        return mPreferencesIOHandler;
    }

    public IOHandler getMemoryIOHandler(){
        if(mMemoryIOHandler == null){
            mMemoryIOHandler = createIOHandler(MemoryIOHandler.class);
        }
        return mMemoryIOHandler;
    }

    public IOHandler getDiskIOHandler(){
        if(mDiskIOHandler == null){
            mDiskIOHandler = createIOHandler(DiskIOHandler.class);
        }
        return mDiskIOHandler;
    }
}
