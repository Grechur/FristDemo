package com.yto.zz.fristdemo;

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zz on 2018/5/29.
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class TestFragment extends DialogFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pop_search,container, false);
        return view;
    }
}
