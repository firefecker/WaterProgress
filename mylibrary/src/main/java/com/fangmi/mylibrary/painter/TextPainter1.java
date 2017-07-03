package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/1/13.
 */

public class TextPainter1 implements Painter {

    private String value;
    protected Paint mTextPaint;
    private Context context;
    private float textSize;
    private int color;
    private float centerX;
    private float centerY;


    public TextPainter1(int color, int textSize, Context context) {
        this.context = context;
        this.color = color;
        this.textSize = textSize;
        initPainter();
    }

    private void initPainter() {
        //字体
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(color);
        mTextPaint.setTextSize(textSize);
    }


    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(value, centerX, centerY, mTextPaint);
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
        this.centerY = 3 * height / 5;
    }
}

