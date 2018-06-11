package com.yto.zz.fristdemo.check;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;


import com.yto.zz.fristdemo.ProxyActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Zc on 2018/4/24.
 */

public class PackageManagerHandler  implements InvocationHandler {
    public static final String TAG = "PackageManagerHandler";
    private Object iPackageManager;
    private Context mContext;
    private Class<?> proxyActivity;
    public PackageManagerHandler(Object iPackageManager, Context context){
        this.iPackageManager = iPackageManager;
        this.mContext = context;
        proxyActivity = ProxyActivity.class;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e(TAG,"进入拦截package:"+method.getName());
        if("getActivityInfo".equals(method.getName())){
            ComponentName proComponentName = new ComponentName(mContext, proxyActivity);
            Log.e("ComponentName:",proComponentName.getClassName());
            for (int i = 0; i < args.length; i++) {
                Log.e("args:",args[i].toString());
                if(args[i] instanceof ComponentName){
                    args[i] = proComponentName;
                }

            }

        }
        //android.software.picture_in_picture
        for (Object arg : args) {
            if(arg!=null){
                Log.e("args:",arg.toString());
            }

        }

        return method.invoke(iPackageManager,args);
    }
}
