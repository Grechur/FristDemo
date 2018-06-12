package com.yto.zz.fristdemo.factory.simple3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by zz on 2018/6/12.
 */

public class DiskUtil {
    private DiskLruCache mDiskLruCache;
    private static DiskUtil instance;
    private DiskUtil(){

    }
    public static DiskUtil getInstance(){
        if(instance == null){
            synchronized (DiskUtil.class){
                if(instance == null){
                    instance = new DiskUtil();
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        File diskFile = getDiskCacheDir(context,"memory");
        long size = 50*1024*1024;
        try {
            mDiskLruCache = DiskLruCache.open(diskFile,getAppVersion(context),100,size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
    public int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void put(String key,Object value){
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            // 创建一个新的输出流 ，创建DiskLruCache时设置一个节点只有一个数据，所以这里的index直接设为0即可
            OutputStream outputStream = editor.newOutputStream(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key){
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                InputStream in = snapshot.getInputStream(0);
                byte[] bytes = new byte[0];
                bytes = new byte[in.available()];
                in.read(bytes);
                return new String(bytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
