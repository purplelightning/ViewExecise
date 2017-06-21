package com.example.wind.drawtest.HorizontalScroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.wind.drawtest.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.color.darker_gray;


/**
 * Created by wind on 17-6-21.
 */

public class HorizontalScrollSelectedView extends View {

    private Context context;
    //数据源
    private List<String > strings=new ArrayList<String>();

    private int seeSize=5;//可见个数

    private int anInt;//每个字母所占大小
    private TextPaint textPaint;
    private boolean firstVisible=true;
    private int width;//控件宽度
    private int height;//控件高度
    private Paint selectedPaint;
    private int n;
    private float downX;
    private float offset;

    private float selectedTextSize;
    private int selectedColor;
    private float textSize;
    private int textColor;

    private Rect rect=new Rect();

    private int textWidth=0;
    private int textHeight=0;
    private int centerTextHeight=0;

    public HorizontalScrollSelectedView(Context context){
        this(context,null);
    }

    public HorizontalScrollSelectedView(Context context,AttributeSet attrs){
        this(context,attrs,0);
    }

    public HorizontalScrollSelectedView(Context context, AttributeSet attrs,int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.context=context;
        setWillNotDraw(false);//使可以走到onDraw方法
        setClickable(true);//使可点击
        initAttrs(attrs);//初始化属性
        initPaint();//初始化画笔
    }

    //初始化属性
    private void initAttrs(AttributeSet attrs) {
        TypedArray tta=context.obtainStyledAttributes(attrs,
                R.styleable.HorScrollSelectedView);

        seeSize=tta.getInteger(R.styleable.HorScrollSelectedView_nums_in_horizon,5);
        //两种字体颜色和字体大小
        selectedColor=tta.getColor(R.styleable.HorScrollSelectedView_selected_text_color,
                context.getResources().getColor(R.color.black));
        selectedTextSize=tta.getFloat(R.styleable.HorScrollSelectedView_selected_text_size,50);

        textSize=tta.getFloat(R.styleable.HorScrollSelectedView_normal_text_size,40);
        textColor=tta.getColor(R.styleable.HorScrollSelectedView_normal_text_color,
                context.getResources().getColor(darker_gray));

    }

    public void initPaint(){
        textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

        selectedPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setTextSize(selectedTextSize);
        selectedPaint.setColor(selectedColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //第一次绘制的时候得到控件 宽高；
        if(firstVisible){
            width=getWidth();
            height=getHeight();
            anInt=width/seeSize;
            firstVisible=false;
        }
        //防止越界
        if(n>=0&&n<=strings.size()-1){

            String s=strings.get(n);//得到被选中的文字
            /**
             * 得到被选中文字 绘制时所需要的宽高
             */
            selectedPaint.getTextBounds(s,0,s.length(),rect);
            //从矩形区域中读出文本内容的宽高
            int centerTextWidth=rect.width();
            centerTextHeight=rect.height();
            //绘制被选中文字，注意点是y坐标
            canvas.drawText(strings.get(n),getWidth()/2-centerTextWidth/2+offset,
                    getHeight()/2+centerTextHeight/2,selectedPaint);

            //绘制没有被选中的文字
            for(int i=0;i<strings.size();i++){
//这里主要是因为strings数据源的文字长度(1,101,1000,10000)不一样，为了让被选中两边文字
// 距离中心宽度一样，我们取得左右两个文字长度的平均值
                if(n>0&&n<strings.size()-1){
                    //获得rect,并得到宽度
                    textPaint.getTextBounds(strings.get(n-1),0,strings.get(n-1).length(),rect);
                    int width1=rect.width();
                    textPaint.getTextBounds(strings.get(n+1),0,strings.get(n+1).length(),rect);
                    int width2=rect.width();
                    textWidth=(width1+width2)/2;
                }
                //得到高，高度是一样的，所以无所谓
                if(i==0){
                    textPaint.getTextBounds(strings.get(0),0,strings.get(0).length(),rect);
                    textHeight=rect.height();
                }
                //绘制没有被选中的文字
                if(i!=n){
                    canvas.drawText(strings.get(i),(i-n)*anInt+getWidth()/2-textWidth/2+offset,
                            getHeight()/2+textHeight/2,textPaint);
                }
            }

        }
    }
    /*
        触摸监听
    */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("action","onTouchEvent: "+event.getAction());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=event.getX();//获得点下去的x坐标
                break;
            //移动时的判断
            case MotionEvent.ACTION_MOVE:
                float scrollX=event.getX();

                if (n != 0 && n != strings.size() - 1)
                    offset = scrollX - downX;//滑动时的偏移量，用于计算每个是数据源文字的坐标值
                else {
                    offset = (float) ((scrollX - downX) / 1.5);//当滑到两端的时候添加一点阻力
                }
                //向右滑动,当滑动距离大于每个单元的长度时，则改变被选中的文字。
                if (scrollX>downX){
                    if(scrollX-downX>=anInt){
                        offset=0;
                        n=n-1;
                        downX=scrollX;
                    }
                }else{
                    //向左滑动，当滑动距离大于每个单元的长度时，则改变被选中的文字。
                    if(downX-scrollX>=anInt){
                        offset=0;
                        n=n+1;
                        downX=scrollX;
                    }
                }
                //通知重绘
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                //抬起手指时，偏移量归零，相当于回弹。
                offset=0;
                invalidate();
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
    //对外提供的方法
    /**
     * 改变中间可见文字的数目
     */

    public void setSeeSize(int seeSizes){
        if(seeSize>0){
            seeSize=seeSizes;
            invalidate();
        }
    }

    /**
     * 向左移动一个单元
     */
    public void setAnLeftOffset() {
        if (n < strings.size() - 1) {
            n = n + 1;
            invalidate();
        }

    }

    /**
     * 向右移动一个单元
     */
    public void setAnRightOffset() {
        if (n > 0) {
            n = n - 1;
            invalidate();
        }
    }

    /**
     * 设置个数据源
     */
    public void setData(List<String> strings){
        this.strings=strings;
        n=strings.size()/2;
        invalidate();
    }

    /**
     * 获得被选中的文本
     *
     */
    public String getSelectedString()
    {
        if(strings.size()!=0){
            return strings.get(n);
        }
        return null;
    }

}


















