package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/5/4.
 */

public class TextPainter2 implements Painter {

    private String value = "0.0";
    protected Paint mTextPaint;
    private Context context;
    private float textSize;
    private int color;
    private float centerX;
    private float centerY;


    public TextPainter2(int color, int textSize, Context context) {
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


    public void setValue(int value) {
        this.value = value+"";
    }

    public void setValue(float value) {
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(Double.parseDouble(String.valueOf(value)));
        if (".00".equals(format)) {
            format = "0.00";
        }
        this.value = format;
    }

    public void setValue(String value) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(Double.parseDouble(value));
        if (".00".equals(format)) {
            format = "0.00";
        }
        this.value = format;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(value+"", centerX, centerY, mTextPaint);
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
