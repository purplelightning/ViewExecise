package com.example.wind.drawtest.LviewDemo;

import android.app.Activity;
import android.os.Bundle;

public class MyViewAcitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.myview_layout);
        //代码中设置画图
        setContentView(new MyView(this));

    }
}
