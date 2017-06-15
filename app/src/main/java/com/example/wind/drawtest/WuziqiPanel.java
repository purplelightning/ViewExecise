package com.example.wind.drawtest;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.wind.drawtest.Checkwin.*;

import static com.example.wind.drawtest.Checkwin.checkFiveInLine;

/**
 * Created by wind on 17-6-4.
 */

public class WuziqiPanel extends View {

    private int mPanelWidth;//棋盘宽度
    private float mLineHeight;//行高
    private int MAX_LINE = 10;//最大行数

    private Paint mPaint = new Paint();

    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;

    private float ratioPieceOfLineHeight = 3 * 1.0f / 4;

    //白棋先手,或当前轮到白棋
    private boolean mIsWhite = true;
    //存储棋子坐标
    private ArrayList<Point> mWhiteArray = new ArrayList<>();
    private ArrayList<Point> mBlackArray = new ArrayList<>();

    private boolean mIsGameOver;
    private boolean mIsWhiteWinner;

    public WuziqiPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        //半透明棋盘
//        setBackgroundColor(0x440000ff);
        init();
    }

    //初始化画笔,棋子
    private void init()
    {
        //黑色画笔
        mPaint.setColor(0xffff0000);
        //抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(1);
        //棋子初始化
        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);//可以在xml设置,这样就能让用户动态定制
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);
    }

    /*
        测量,决定棋盘大小
    */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);
        //防止嵌套在ScrollView中高度为wrapcontent时,高度不确定
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        setMeasuredDimension(width, width);
    }

    /*
        与尺寸相关
        大小改变时回调,初始化成员变量*/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;

        /*棋子大小跟随行高大小动态变化
        int转换后面是式子要加括号*/
        int pieceWidth = (int) (mLineHeight * ratioPieceOfLineHeight);
        //参数：原本加载的图，目标宽度,目标高度，filter
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);

    }

    /*
        绘制
    复写View类的onDraw()函数,在onDraw()函数中使用Paint和Canvas对象绘制我们需要的图形
    */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制棋盘
        drawBoard(canvas);
        //绘制棋子
        drawPieces(canvas);

        checkGameOver();//游戏结束不允许落子,落子在OnTouchEvent里判断

    }

    private void checkGameOver()
    {
        boolean whiteWin=checkFiveInLine(mWhiteArray);
        boolean blackWin=checkFiveInLine(mBlackArray);

        if(whiteWin||blackWin)
        {
            mIsGameOver=true;
            mIsWhiteWinner=whiteWin;

            String text=mIsWhiteWinner?"白棋胜利":"黑棋胜利";

            Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
        }
    }



    //绘制棋盘
    private void drawBoard(Canvas canvas) {
        int w = mPanelWidth;
        float lineHeight = mLineHeight;

        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) lineHeight / 2;
            int endX = (int) (w - lineHeight / 2);
            int startY = startX;
            int endY = endX;
            //每一行的x,y坐标
            int y = (int) ((0.5 + i) * lineHeight);
            int x = (int) ((0.5 + i) * lineHeight);

            canvas.drawLine(startX, y, endX, y, mPaint);    //横线
            canvas.drawLine(x, startY, x, endY, mPaint);    //纵线
        }
    }

    //绘制棋子
    private void drawPieces(Canvas canvas)
    {    //为了考虑效率，只调用一次mWhiteArray.size()
        for(int i=0,n=mWhiteArray.size();i<n;i++)
        {
            Point whitePoint=mWhiteArray.get(i);
            //参数：棋子，横坐标，纵坐标，null
            canvas.drawBitmap(mWhitePiece,
                    (whitePoint.x+(1-ratioPieceOfLineHeight)/2)*mLineHeight,
                    (whitePoint.y+(1-ratioPieceOfLineHeight)/2)*mLineHeight, null);
        }
        for (int i = 0, n = mBlackArray.size(); i < n; i++) {
            Point blackPoint = mBlackArray.get(i);
            canvas.drawBitmap(mBlackPiece,
                    (blackPoint.x + (1 - ratioPieceOfLineHeight) / 2) * mLineHeight,
                    (blackPoint.y + (1 - ratioPieceOfLineHeight) / 2) * mLineHeight, null);
        }
    }

    /*
        棋子图片,大小准备好之后,要与用户交互才能下下去.用OnTouch方法
    */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(mIsGameOver)return false;

        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP)
        {
            //从屏幕获取棋子坐标
            int x = (int) event.getX();
            int y = (int) event.getY();
            //每次点击的位置不一样，不能作为存储位置
            //Point p=new Point(x,y);useless!
//            Point p = new Point(x, y);
            Point p = getValidPoint(x, y);

            if(mWhiteArray.contains(p)||mBlackArray.contains(p))//不能下在同一位置
            {
                return false;
            }

            if (mIsWhite) {
                mWhiteArray.add(p);
            } else {
                mBlackArray.add(p);
            }
            /*invalidate()方法 ：
            说明：请求重绘View树，即draw()过程，假如视图发生大小没有变化就不会调用layout()过程，并且只绘制那些“需要重绘的”
            视图，即谁(View的话，只绘制该View ；ViewGroup，则绘制整个ViewGroup)请求invalidate()方法，就绘制该视图。
            */
            invalidate();
            //下完一步后要改变先后手
            mIsWhite = !mIsWhite;
            //接受父View的Touch事件
            return true;
        }

        return true;
    }

    private Point getValidPoint(int x, int y)
    {
        return new Point((int)(x/mLineHeight),(int) (y/mLineHeight));
    }

    private static final String INSTANCE="instance";
    private static final String INSTANCE_GAME_OVER="instance_game_over";
    private static final String INSTANCE_WHITE_ARRAY="instance_white_array";
    private static final String INSTANCE_BLACK_ARRAY="instance_black_array";

    //保存实例，在旋转或者暂停时
    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle=new Bundle();
        bundle.putParcelable(INSTANCE,super.onSaveInstanceState());
        bundle.putBoolean(INSTANCE_GAME_OVER,mIsGameOver);
        bundle.putParcelableArrayList(INSTANCE_WHITE_ARRAY,mWhiteArray);
        bundle.putParcelableArrayList(INSTANCE_BLACK_ARRAY,mBlackArray);

        return bundle;
    }

    //恢复实例
    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if(state instanceof Bundle)
        {
            Bundle bundle=(Bundle) state;
            mIsGameOver=bundle.getBoolean(INSTANCE_GAME_OVER);
            mWhiteArray=bundle.getParcelableArrayList(INSTANCE_WHITE_ARRAY);
            mBlackArray=bundle.getParcelableArrayList(INSTANCE_BLACK_ARRAY);

            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE));

            return ;
        }
        super.onRestoreInstanceState(state);
    }

    //再来一局
    public void restart()
    {
        mWhiteArray.clear();
        mBlackArray.clear();
        mIsGameOver=false;
        mIsWhiteWinner=false;
        //重绘
        invalidate();
    }
}










