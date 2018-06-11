package com.yto.zz.fristdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.zz.fristdemo.testdagger.DaggerFactoryActivityComponent;
import com.yto.zz.fristdemo.testdagger.Factory;
import com.yto.zz.fristdemo.testdagger.Producet;

import javax.inject.Inject;

public class FactoryActivity extends AppCompatActivity {
    @Inject
    Producet producet;
    @Inject
    Factory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        DaggerFactoryActivityComponent.create().inject(this);
        Toast.makeText(this,producet.hashCode()+" factory"+factory+" factory.producet"+factory.producet,Toast.LENGTH_SHORT).show();
        TextView textView = findViewById(R.id.text);
        textView.setText(producet.hashCode()+" factory"+factory.hashCode()+" factory.producet"+factory.producet.hashCode());
    }
}
