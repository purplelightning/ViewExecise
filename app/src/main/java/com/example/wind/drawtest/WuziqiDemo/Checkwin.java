package com.example.wind.drawtest.WuziqiDemo;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 17-6-5.
 */

public class Checkwin {

    private static int MAX_COUNT_IN_LINE=5;

    public static boolean checkFiveInLine(List<Point> points)
    {
        for(Point p:points)
        {
            int x=p.x;
            int y=p.y;

            boolean win=checkHorizontal(x,y,points);
            if(win) return true;

            win=checkVertical(x,y,points);
            if(win) return true;

            win=checkLeftDiagonal(x,y,points);
            if(win) return true;

            win=checkRightDiagonal(x,y,points);
            if(win) return true;
        }
        return false;
    }

    /*应该把这些检测代码抽取到一个工具类中，这样主代码就少了
    判断x,y位置的棋子是否横向有相邻的五个一致*/
    private static boolean checkHorizontal(int x, int y, List<Point> points)
    {
        int count=1;
        //判断左面是否有5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x-i,y)))
            {
                count++;
            }else {
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }
        //判断右面是否连成5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x+i,y))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }

        return false;
    }

    /*
    判断x,y位置的棋子是否纵向有相邻的五个一致*/
    private static boolean checkVertical(int x, int y, List<Point> points)
    {
        int count=1;
        //判断上面是否有5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x,y-i))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }
        //判断右面是否连成5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x,y+i))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }

        return false;
    }

    /*
    判断x,y位置的棋子是否左斜有相邻的五个一致*/
    private static boolean checkLeftDiagonal(int x, int y, List<Point> points)
    {
        int count=1;
        //判断左下是否有5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x-i,y+i))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }
        //判断右上是否连成5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x+i,y-i))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }

        return false;
    }

    /*
    判断x,y位置的棋子是否右斜有相邻的五个一致*/
    private static boolean checkRightDiagonal(int x, int y, List<Point> points)
    {
        int count=1;
        //判断左上是否有5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x-i,y-i))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }
        //判断右下是否连成5个
        for(int i=1;i<MAX_COUNT_IN_LINE;i++)
        {
            if(points.contains(new Point(x+i,y+i))){
                count++;
            }else{
                break;
            }
            if(count == MAX_COUNT_IN_LINE) return true;
        }

        return false;
    }
}



















