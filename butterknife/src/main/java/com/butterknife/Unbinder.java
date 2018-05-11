package com.butterknife;

import android.support.annotation.UiThread;

/**
 * Created by zz on 2018/5/9.
 */

public interface Unbinder {
    @UiThread
    void unbind();

    Unbinder EMPTY = new Unbinder() {
        @Override
        public void unbind() {
        }
    };
}
