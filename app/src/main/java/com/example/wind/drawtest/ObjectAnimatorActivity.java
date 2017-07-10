package com.example.wind.drawtest;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView vip,fire;
    private Button btn;
    private Animation tran1;

    ObjectAnimator animator0;
    ObjectAnimator animator1;
    ObjectAnimator animator2;
    ObjectAnimator animator22;
    ObjectAnimator animator23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_layout);

        btn = (Button) findViewById(R.id.start_animation);
        vip = (ImageView) findViewById(R.id.vip);
        fire = (ImageView) findViewById(R.id.fire);

        tran1 = AnimationUtils.loadAnimation(ObjectAnimatorActivity.this, R.anim.translate3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator0 = ObjectAnimator.ofFloat(fire,"alpha",0,1).setDuration(2500);
                animator1 = ObjectAnimator.ofFloat(fire, "rotation", 0, 270, 0).setDuration(2000);
                animator2 = ObjectAnimator.ofFloat(fire, "translationY", 0, 400).setDuration(1000);
                animator22 = ObjectAnimator.ofFloat(fire, "translationX", 0, -300).setDuration(1000);
                animator23 = ObjectAnimator.ofFloat(fire,"alpha",1,0).setDuration(500);
                animator1.setStartDelay(1000);
                animator2.setStartDelay(2000);
                animator22.setStartDelay(2000);
                animator23.setStartDelay(3000);

//                animator23.cancel();


//                animator0.start();
                Animation animation=AnimationUtils.loadAnimation(ObjectAnimatorActivity.this,R.anim.alpha2);
                fire.startAnimation(animation);
                animator1.start();
                animator2.start();
                animator22.start();
//                animator23.start();
                vip.startAnimation(tran1);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(vip, "scaleX", 1, 2, 2).setDuration(2000);
                ObjectAnimator animator4 = ObjectAnimator.ofFloat(vip, "scaleY", 1, 2, 2).setDuration(2000);
                animator3.start();
                animator4.start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vip:
                //使用属性动画实现
                ObjectAnimator.ofFloat(v,"translationY",0,-300,300,0).setDuration(2000).start();
                break;

            default:
                break;
        }
    }
}
