package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.fangmi.mylibrary.utils.WidgetUtil;


/**
 * Created by Administrator on 2016/10/19.
 */
public class RingPainter implements Painter {

    protected Paint mRingPainter;
    private Context context;
    private int color;
    private float centerX;
    private float centerY;
    private float margin;

    public RingPainter(int color, float margin, Context context) {
        this.context = context;
        this.color = color;
        this.margin = margin;
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
        mRingPainter.setStrokeWidth(WidgetUtil.Dp2Px(context, 1));     //为了实验效果明显，特地设置描边宽度非常大

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, centerX - margin, mRingPainter);
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
        this.centerX = width / 2;
        this.centerY = height / 2;
    }
}