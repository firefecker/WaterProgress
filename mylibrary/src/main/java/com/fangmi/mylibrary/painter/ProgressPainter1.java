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
public class ProgressPainter1 implements Painter {
    private RectF circle;
    protected Paint paint;
    private int color;
    /**
     * 开始角
     */
    private int startAngle = 90;
    private int width;
    private int height;
    /**
     * 加角度
     */
    private float plusAngle = 0;
    private float max;
    private float strokeWidth;
    private float blurMargin;
    private float lineWidth;
    private float lineSpace;
    private Context context;

    public ProgressPainter1(int color, float max, int margin, Context context, float strokeWidth) {
        this.color = color;
        this.max = max;
        this.blurMargin = margin;
        this.context = context;
        this.strokeWidth = strokeWidth;
        initSize();
        init();
    }

    private void initSize() {
        this.lineWidth = WidgetUtil.Dp2Px(context, 3);
        this.lineSpace = WidgetUtil.Dp2Px(context, 6);
    }

    private void init() {
        initPainter();
    }

    private void initPainter() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{lineWidth, lineSpace}, 2));
    }

    private void initExternalCircle() {
        float padding = strokeWidth / 2 + blurMargin;
        circle = new RectF();
        circle.set(padding, padding, width - padding, height - padding);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawArc(circle, startAngle, plusAngle, false, paint);
    }

    @Override
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void onSizeChanged(int height, int width) {
        this.width = width;
        this.height = height;
        initExternalCircle();
    }

    public void setValue(int value) {
        this.plusAngle = (int) ((360 * value) / max / 2);
    }
}
