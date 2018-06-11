package com.yto.zz.fristdemo.check;

import android.content.Context;
import android.os.Handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Created by Zc on 2018/4/10.
 */

public class HookUtil {
    private Context context;

    public HookUtil(Context context) {
        this.context = context;
    }

    public void hookAms(){
        //一路反射，直到拿到IActivityManager的对象
        try{
            Class ActivityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
            Field defaultFiled = ActivityManagerNativeClass.getDeclaredField("gDefault");//获取私有方法
            defaultFiled.setAccessible(true);//解除私有限定
            Object defaultValue = defaultFiled.get(null);
            //反射SingleTon
            Class SingletonClass = Class.forName("android.util.Singleton");
            Field mInstance = SingletonClass.getDeclaredField("mInstance");
            mInstance.setAccessible(true);
            //到这里已经拿到ActivityManager对象
            Object iActivityManagerObject = mInstance.get(defaultValue);

            //开始动态代理，用代理对象替换掉真实的ActivityManager，瞒天过海
            Class IActivityManagerIntercept = Class.forName("android.app.IActivityManager");

            AmsInvocationHandler handler = new AmsInvocationHandler(iActivityManagerObject,context);
            Object proxy = newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{IActivityManagerIntercept}, handler);
            //现在替换掉这个对象
            mInstance.set(defaultValue, proxy);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hookSystemHandler() {
        try {

            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            //获取主线程对象
            Object activityThread = currentActivityThreadMethod.invoke(null);

            Method getPackageManager = activityThread.getClass().getDeclaredMethod("getPackageManager");
            Object iPackageManager = getPackageManager.invoke(activityThread);
            PackageManagerHandler phandler = new PackageManagerHandler(iPackageManager,context);
            Class<?> iPackageManagerIntercept = Class.forName("android.content.pm.IPackageManager");
            Object proxy = newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{iPackageManagerIntercept}, phandler);
            // 获取 sPackageManager 属性
            Field iPackageManagerField = activityThread.getClass().getDeclaredField("sPackageManager");
            iPackageManagerField.setAccessible(true);
            iPackageManagerField.set(activityThread, proxy);


            //获取mH字段
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);
            //获取Handler
            Handler handler = (Handler) mH.get(activityThread);
            //获取原始的mCallBack字段
            Field mCallBack = Handler.class.getDeclaredField("mCallback");
            mCallBack.setAccessible(true);
            //这里设置了我们自己实现了接口的CallBack对象
            mCallBack.set(handler, new ActivityThreadHandlerCallback(handler)) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
