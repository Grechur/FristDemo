package com.yto.zz.fristdemo.factory.simple1;

/**
 * Created by zz on 2018/6/11.
 */

public class IOHandlerFactory {
    public enum IOType{
        PREFERENCE,MEMORY,DISK
    }
    public static IOHandler createIOHandler(IOType ioType){
        switch (ioType){
            case PREFERENCE:
                return new PreferenceIOHandler();
            case MEMORY:
                return new MemoryIOHandler();
            case DISK:
                return new DiskIOHandler();
        }
        return null;
    }
}
