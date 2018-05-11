package com.yto.zz.fristdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.butterknife.ButterKnife;
import com.butterknife.Unbinder;
import com.butterknife.annotation.BindView;
import com.yto.zz.fristdemo.aop.NetCheck;
import com.yto.zz.fristdemo.eventbus.EventBus;
import com.yto.zz.fristdemo.eventbus.Subscribe;
import com.yto.zz.fristdemo.eventbus.ThreadMode;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @BindView(R.id.tv1)
    TextView textView;

    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        textView.setText("jiazaile");
    }




    @NetCheck
    public void click(View view){
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100,sticky = true)
    public void test1(String msg){
        Log.e(TAG,"msg1 = "+msg);
        textView.setText(msg);
    }

}
