package com.yto.zz.fristdemo.check;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;


import com.yto.zz.fristdemo.ProxyActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Zc on 2018/4/10.
 */

public class AmsInvocationHandler implements InvocationHandler {
    public static final String TAG = "AmsInvocationHandler";
    private Object iActivityManagerObject;
    private Context context;
    private Class<?> proxyActivity;

    public AmsInvocationHandler(Object iActivityManagerObject, Context context) {
        this.iActivityManagerObject = iActivityManagerObject;
        this.context = context;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e(TAG,method.getName());
        if("startActivity".contains(method.getName())){
            proxyActivity = ProxyActivity.class;
        }
        if("startActivity".contains(method.getName())||"startService".equals(method.getName())){
//            Log.e("HookUtil","Activity已经开始启动");
//            Log.e("HookUtil","小弟到此一游！！！");
            //换掉
            Intent intent = null;
            int index = 0;
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Intent) {
                    //说明找到了startActivity的Intent参数
                    intent = (Intent) args[i];
                    //这个意图是不能被启动的，因为Acitivity没有在清单文件中注册
                    index = i;
                }
            }

            //伪造一个代理的Intent，代理Intent启动的是proxyActivity
            Intent proxyIntent = new Intent();
            ComponentName componentName = new ComponentName(context, proxyActivity);
            proxyIntent.setComponent(componentName);
            proxyIntent.putExtra("oldIntent", intent);
            args[index] = proxyIntent;
        }
//        if ("stopService".equals(method.getName())) {
//            Intent raw = null;
//            int index = 0;
//            for (int i = 0; i < args.length; i++) {
//                Object arg = args[i];
//                if (arg instanceof Intent) {
//                    //说明找到了startActivity的Intent参数
//                    raw = (Intent) args[i];
//                    //这个意图是不能被启动的，因为Acitivity没有在清单文件中注册
//                    index = i;
//                }
//            }
//            if (!TextUtils.equals(DemoApplication.AppContext.getPackageName(), raw.getComponent().getPackageName())) {
//                // 插件的intent才做hook
//                Log.v(TAG, "hook method stopService success");
//                return ServiceManager.getInstance().stopService(raw);
//            }
//        }
        return method.invoke(iActivityManagerObject, args);
    }
}
