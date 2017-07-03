package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;

import com.fangmi.mylibrary.utils.WidgetUtil;


/**
 * Created by Administrator on 2016/9/10.
 */
public class ProgressBgPainter implements Painter {

    private Paint paint;
    private RectF circle;
    private Context context;
    private int width;
    private int height;

    /**
     * 开始角
     */
    private float startAngle = 0;
    /**
     * 完成角
     */
    private float finishAngle = 360;

    private int strokeWidth;
    private int blurMargin;
    private int lineWidth;
    private int lineSpace;
    private int color;

    public ProgressBgPainter(int color, int margin, Context context) {
        this.blurMargin = margin;
        this.context = context;
        this.color = color;
        initSize();
        initPainter();
    }

    private void initSize() {
        //宽度
        this.lineWidth = WidgetUtil.Dp2Px(context, 2);
        //间距
        this.lineSpace = WidgetUtil.Dp2Px(context, 6);
        //每一刻度的宽度
        this.strokeWidth = WidgetUtil.Dp2Px(context, 10);
    }

    private void initPainter() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{lineWidth, lineSpace}, 0));
    }


    private void initCircle() {
        int padding = strokeWidth / 2 + blurMargin;
        circle = new RectF();
        circle.set(padding, padding, width - padding, height - padding);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawArc(circle, startAngle, finishAngle, false, paint);
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

}
