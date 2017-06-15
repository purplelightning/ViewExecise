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
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener
{
    private WuziqiPanel wuziqiPanel;
    private Button btn3,btn4,btn5,btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1=(Button)findViewById(R.id.paint);
        Button btn2=(Button)findViewById(R.id.wuziqi);
        btn3=(Button)findViewById(R.id.trail1);
        btn4=(Button)findViewById(R.id.trail2);
        btn5=(Button)findViewById(R.id.trail3);
        btn6=(Button)findViewById(R.id.trail4);

        //点击监听
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        //触摸监听
        btn1.setOnTouchListener(this);
        btn2.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Animation animation1= AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_to_big);
                v.startAnimation(animation1);
                break;
            case MotionEvent.ACTION_UP:
                Animation animation2=AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_to_small);
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
        switch (v.getId()){
            case R.id.paint:
                Intent intent=new Intent(MainActivity.this,MyViewAcitivity.class);
                startActivity(intent);
                break;
            case R.id.wuziqi:
                Intent intent2=new Intent(MainActivity.this,WuziqiActivity.class);
                startActivity(intent2);
                break;

            case R.id.trail1:
                Animation animation1= AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_to_big1);
                v.startAnimation(animation1);
                break;

            case R.id.trail2:
                Animation animation3=AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_btn);
                v.startAnimation(animation3);
                break;

            case R.id.trail3:
                Animation animation4=AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha_btn);
                v.startAnimation(animation4);
                break;

            case R.id.trail4:
                Animation animation5=AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate_btn);
                v.startAnimation(animation5);
                break;
            default:
                break;
        }
    }


}
