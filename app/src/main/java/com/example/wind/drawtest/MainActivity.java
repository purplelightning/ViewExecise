package com.example.wind.drawtest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private WuziqiPanel wuziqiPanel;
    private Button btn3, btn4, btn5, btn6, btn_all;
    private ImageView huan;
    private Animation animation_big;
    private Animation animation_rot;
    private Animation animation_alp;
    private Animation animation_tran;
    private Animation ani_java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btna1 = (Button) findViewById(R.id.paint);
        Button btna2 = (Button) findViewById(R.id.wuziqi);
        Button btna3 = (Button) findViewById(R.id.ges);
        btn3 = (Button) findViewById(R.id.trail1);
        btn4 = (Button) findViewById(R.id.trail2);
        btn5 = (Button) findViewById(R.id.trail3);
        btn6 = (Button) findViewById(R.id.trail4);
        btn_all = (Button) findViewById(R.id.all_start);

        huan = (ImageView) findViewById(R.id.huanci);

        //点击监听
        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        huan.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        //触摸监听
        btna1.setOnTouchListener(this);
        btna2.setOnTouchListener(this);
        btna3.setOnTouchListener(this);

        initAnimation();
    }

    private void initAnimation() {

        animation_big = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_to_big1);
        animation_rot = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_btn);
        animation_alp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_btn);
        animation_tran = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_btn);
        //只用java代码设置动画
        // 创建一个ScaleAnimation对象（以某个点为中心缩放）
        ani_java = new ScaleAnimation(1, 0.1f, 1, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ani_java.setStartOffset(500);// 设置动画执行之前等待的时间（单位：毫秒）
        ani_java.setDuration(2000);// 设置动画执行的时间（单位：毫秒）
        ani_java.setFillAfter(true);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_to_big);
                v.startAnimation(animation1);
                break;
            case MotionEvent.ACTION_UP:
                Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_to_small);
                v.startAnimation(animation2);
                break;
            case MotionEvent.ACTION_MOVE:
                v.clearAnimation();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paint:
                Intent intent = new Intent(MainActivity.this, MyViewAcitivity.class);
                startActivity(intent);
                break;
            case R.id.wuziqi:
                Intent intent2 = new Intent(MainActivity.this, WuziqiActivity.class);
                startActivity(intent2);
                break;

            case R.id.ges:
                Intent intent3 = new Intent(MainActivity.this, TouchActivity.class);
                startActivity(intent3);
                break;

            case R.id.trail1:
                v.startAnimation(animation_big);
                break;
            case R.id.trail2:
                v.startAnimation(animation_rot);
                break;
            case R.id.trail3:
                v.startAnimation(animation_alp);
                break;
            case R.id.trail4:
                v.startAnimation(animation_tran);
                break;
            case R.id.huanci:
                v.startAnimation(ani_java);
                break;
            case R.id.all_start:
                btn3.startAnimation(animation_big);
                btn4.startAnimation(animation_rot);
                btn5.startAnimation(animation_alp);
                btn6.startAnimation(animation_tran);
                huan.startAnimation(ani_java);
                break;

            default:
                break;
        }
    }

}
