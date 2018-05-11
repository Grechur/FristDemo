package com.butterknife;

import android.app.Activity;
import android.view.View;

/**
 * Created by zz on 2018/5/9.
 */

public class Utils {

    public static <T extends View> T findViewById(Activity activity, int viewId){
        return (T)activity.findViewById(viewId);
    }

}
