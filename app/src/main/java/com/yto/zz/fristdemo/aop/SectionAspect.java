package com.yto.zz.fristdemo.aop;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by zz on 2018/5/9.
 * 处理标记
 */

@Aspect
public class SectionAspect {
    private static final String TAG = "SectionAspect";

    /**
     * 找到处理的切点 * *(..)处理所有方法
     */
    @Pointcut("execution(com.yto.zz.fristdemo.aop.NetCheck * *(..))")
    public void checkNetBehavior(){

    }

    @Around("checkNetBehavior")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.e(TAG,"checkNet");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        NetCheck netCheck = methodSignature.getMethod().getAnnotation(NetCheck.class);
        if(netCheck!=null){
            Object object = joinPoint.getThis();//有可能是view/activity/fragment
            Context context = getContext(object);
            if(context!=null){
                if(!isNetworkAvailable(context)){
                    Toast.makeText(context,"请检查您的网络",Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }

        return joinPoint.proceed();
    }

    private Context getContext(Object obj){
        if(obj instanceof Activity)
        {
            return (Activity)obj;
        }else if(obj instanceof Fragment){
            Fragment fragment = (Fragment) obj;
            return fragment.getActivity();
        }else if(obj instanceof View){
            View view = (View) obj;
            return view.getContext();
        }
        return null;
    }

    /**
     * @方法说明:网络是否可用
     * @方法名称:isNetworkAvailable
     * @param context
     * @return
     * @返回值:boolean
     */
    private boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager mgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
