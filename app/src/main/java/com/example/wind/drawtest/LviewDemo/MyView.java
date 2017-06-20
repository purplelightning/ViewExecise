package com.example.wind.drawtest.LviewDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by wind on 17-6-4.
 */

public class MyView extends View {

    public MyView(Context context){
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){

        Paint paint_blue=new Paint();       //1.蓝环
        paint_blue.setColor(Color.BLUE);
        //实心,空心
        paint_blue.setStyle(Paint.Style.STROKE);
        //线的宽度
        paint_blue.setStrokeWidth(10);
        canvas.drawCircle((float)110,150,60,paint_blue);

        Paint yellow=new Paint();
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);
        yellow.setStrokeWidth(10);
        canvas.drawCircle((float)240,150,60,yellow);

        Paint green=new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.STROKE);
        green.setStrokeWidth(10);
        //距离左边和上面的距离                //矩形
        canvas.drawRect((float)400,100,500,200,green);

        Paint pline=new Paint();            //直线
        pline.setColor(Color.RED);
        pline.setStyle(Paint.Style.STROKE);
        pline.setStrokeWidth(10);
        //4个数字分别是两个端点的横纵坐标
        canvas.drawLine(110,310,410,310,pline);

    }
}
