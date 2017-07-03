package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;

import com.fangmi.mylibrary.utils.WidgetUtil;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ProgressBgPainter2 implements Painter {

    private Paint paint;
    private RectF circle;
    private Context context;
    private int width;
    private int height;

    /**
     * 开始角
     */
    private float startAngle = 270;
    /**
     * 完成角
     */
    private float finishAngle = 180;
    /**
     * 加角度
     */
    private float plusAngle = 0;

    private float strokeWidth;
    private float blurMargin;
    private float lineWidth;
    private float lineSpace;
    private int color;
    private float max = 100;

    public ProgressBgPainter2(int color, int margin, Context context, float strokeWidth) {
        this.blurMargin = margin;
        this.context = context;
        this.color = color;
        this.strokeWidth = strokeWidth;
        initSize();
        initPainter();
    }

    private void initSize() {
        //宽度
        this.lineWidth = WidgetUtil.Dp2Px(context, 2);
        //间距
        this.lineSpace = WidgetUtil.Dp2Px(context, 6);
    }

    private void initPainter() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{lineWidth, lineSpace}, 2));
    }


    private void initCircle() {
        float padding = strokeWidth / 2 + blurMargin;
        circle = new RectF();
        circle.set(padding, padding, width - padding, height - padding);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawArc(circle, startAngle, finishAngle - plusAngle, false, paint);
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void onSizeChanged(int height, int width) {
        this.width = width;
        this.height = height;
        initCircle();
    }

    public void setValue(int value) {
        this.plusAngle = (int) ((360 * value) / max / 2);
    }
}