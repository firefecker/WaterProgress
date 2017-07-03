package com.fangmi.mylibrary.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;

import com.fangmi.mylibrary.utils.WidgetUtil;
/**
 * Created by Administrator on 2016/10/19.
 */
public class WaterWavePainter2 implements Painter {

    private Paint mPaintWater;
    private Context context;
    private int color;
    private int value;
    private int width;
    private int height;
    /**
     * 产生波浪效果的因子
     */
    private long mWaveFactor = 0L;
    /**
     * 正在执行波浪动画
     */
    private boolean isWaving = false;
    /**
     * 振幅
     */
    private float mAmplitude = 50; // 20F
    /**
     * 波浪的速度
     */
    private float mWaveSpeed = 0.040F; // 0.020F
    /**
     * 水的透明度
     */
    private int mWaterAlpha = 255; // 255
    // 进度 //浪峰个数
    float crestCount = 2f;

    private float spacing;

    public WaterWavePainter2(int color, Context context, float spacing) {
        this.color = color;
        this.context = context;
        this.spacing = spacing;
        init();
    }

    private void init() {
        mPaintWater = new Paint();
        mPaintWater.setStrokeWidth(1.0F);
        mPaintWater.setColor(color);
        mPaintWater.setAlpha(mWaterAlpha);
    }


    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void onSizeChanged(int height, int width) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas) {
        // 水与边框的距离
        float waterPadding = spacing + WidgetUtil.Dp2Px(context, 10);
        // 水最高处
        int waterHeightCount = (int) (height - waterPadding * 2);

        // 重新生成波浪的形状
        mWaveFactor++;
        if (mWaveFactor >= Integer.MAX_VALUE) {
            mWaveFactor = 0L;
        }

        // 计算出水的高度
        float waterHeight = waterHeightCount * (1 - (value * 1f) / 100) + waterPadding;
        int staticHeight = (int) (waterHeight + mAmplitude);
        Path mPath = new Path();
        mPath.reset();

        mPath.addCircle(width / 2, width / 2, waterHeightCount / 2,
                Path.Direction.CCW);

        // canvas添加限制,让接下来的绘制都在园内
        canvas.clipPath(mPath, Region.Op.REPLACE);

        // 绘制静止的水
        canvas.drawRect(waterPadding, staticHeight, waterHeightCount
                + waterPadding, waterHeightCount + waterPadding, mPaintWater);

        // 待绘制的波浪线的x坐标
        int xToBeDrawed = (int) waterPadding;
        int waveHeight = (int) (waterHeight - mAmplitude
                * Math.sin(Math.PI
                * ((xToBeDrawed + (mWaveFactor * width)
                * mWaveSpeed)) / width));
        // 波浪线新的高度
        int newWaveHeight = waveHeight;
        while (true) {
            if (xToBeDrawed >= waterHeightCount + waterPadding) {
                break;
            }
            // 根据当前x值计算波浪线新的高度
            newWaveHeight = (int) (waterHeight - mAmplitude
                    * Math.sin(Math.PI
                    * (crestCount * (xToBeDrawed + (mWaveFactor * waterHeightCount)
                    * mWaveSpeed)) / waterHeightCount));

            // 先画出梯形的顶边
            canvas.drawLine(xToBeDrawed, waveHeight, xToBeDrawed + 1,
                    newWaveHeight, mPaintWater);

            // 画出动态变化的柱子部分
            canvas.drawLine(xToBeDrawed, newWaveHeight, xToBeDrawed + 1,
                    staticHeight, mPaintWater);


            xToBeDrawed++;
            waveHeight = newWaveHeight;
        }
    }
}