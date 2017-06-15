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

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener
{
    private WuziqiPanel wuziqiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1=(Button)findViewById(R.id.paint);
        Button btn2=(Button)findViewById(R.id.wuziqi);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

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
                v.clearAnimation();
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

            default:
                break;
        }
    }


}
