package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.fangmi.mylibrary.utils.WidgetUtil;

/**
 * Created by Administrator on 2016/10/20.
 */
public class Ring4Painter implements Painter {

    private Paint mRingPainter;
    private Paint mRingPainter2;
    private Context context;
    private int color = Color.parseColor("#B3BFDD");
    private float width;
    private float height;
    private float margin;

    private float mutix = 5.0f / 32.0f;
    private float mutix1 = 20.0f / 32.0f;
    private float subadd;
    private float subadd1;


    public Ring4Painter(float margin, Context context) {
        this.context = context;
        this.margin = margin;
        subadd = margin * mutix;
        subadd1 = margin * mutix1;
        initPainter();
    }

    private void initPainter() {
        //字体
        mRingPainter = new Paint();
        mRingPainter.setAntiAlias(true);
        mRingPainter.setAntiAlias(true);
        // 描边
        mRingPainter.setStyle(Paint.Style.STROKE);
        mRingPainter.setColor(color);
        mRingPainter.setStrokeWidth(WidgetUtil.Dp2Px(context, 6));

        //字体
        mRingPainter2 = new Paint();
        mRingPainter2.setAntiAlias(true);
        mRingPainter2.setAntiAlias(true);
        // 描边
        mRingPainter2.setStyle(Paint.Style.STROKE);
        mRingPainter2.setColor(color);
        mRingPainter2.setStrokeWidth(WidgetUtil.Dp2Px(context, 2));

    }

    @Override
    public void draw(Canvas canvas) {
        RectF oval = new RectF();
        oval.left = (margin * 2);                              //左边
        oval.top = (margin * 2);                                   //上边
        oval.right = (width - margin * 2);                           //右边
        oval.bottom = (height - margin * 2);            //RectF对象

        RectF oval2 = new RectF();                     //RectF对象
        oval2.left = margin;                              //左边
        oval2.top = margin;                                   //上边
        oval2.right = (width - margin);                             //右边
        oval2.bottom = (height - margin);

        //绘制圆弧

        canvas.drawArc(oval, 252, 36, false, mRingPainter);//上边圆弧

        canvas.drawArc(oval, 72, 36, false, mRingPainter);//下边圆弧

        canvas.drawArc(oval2, -45, 90, false, mRingPainter2);//右边圆弧

        canvas.drawArc(oval2, 135, 90, false, mRingPainter2);//左边圆弧

        //绘制小直线


        canvas.drawLine(margin * 3 + subadd,margin * 3 + subadd,margin * 3 + subadd1,margin * 3 + subadd1,mRingPainter2);   //左上角的小直线（起点坐标(x1,y1)，终点坐标(x2,y2)，画笔）

        canvas.drawLine(width - (margin * 3 + subadd),height - (margin * 3 + subadd),
                width - (margin * 3 + subadd1),height - (margin * 3 + subadd1),mRingPainter2);//右下角的小直线
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void onSizeChanged(int height, int width) {
        this.width = width;
        this.height = height;
    }
}