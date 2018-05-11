package com.butterknife;

import android.app.Activity;

import java.lang.reflect.Constructor;

/**
 * Created by zz on 2018/5/9.
 */

public class ButterKnife {
    public static Unbinder bind(Activity activity){

        try {
            Class<? extends Unbinder> activityClassName = (Class<? extends Unbinder>) Class.forName(activity.getClass().getName()+"_ViewBinding");
            Constructor<? extends Unbinder> bindConstructor = activityClassName.getDeclaredConstructor(activity.getClass());
            Unbinder unbinder = bindConstructor.newInstance(activity);
            return unbinder;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Unbinder.EMPTY;

    }
}
