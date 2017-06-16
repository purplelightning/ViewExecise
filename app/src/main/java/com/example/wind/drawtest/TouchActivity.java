package com.example.wind.drawtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class TouchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_layout);

        Button begin_ani=(Button)findViewById(R.id.start_animation);
        img1=(ImageView)findViewById(R.id.img1);

        final Animation tran1= AnimationUtils.loadAnimation(TouchActivity.this,R.anim.translate2);
        img1.setOnClickListener(this);
        begin_ani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.startAnimation(tran1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img1:
                Animation tran1= AnimationUtils.loadAnimation(TouchActivity.this,R.anim.translate2);
                tran1.setDuration(2000);
                tran1.setFillAfter(false);
                tran1.setStartOffset(500);//动画执行等待时间
                v.startAnimation(tran1);
                break;

            default:
                break;
        }
    }
}
