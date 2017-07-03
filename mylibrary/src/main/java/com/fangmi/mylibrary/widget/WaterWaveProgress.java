package com.fangmi.mylibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.fangmi.mylibrary.painter.ProgressBgPainter;
import com.fangmi.mylibrary.painter.ProgressBgPainter1;
import com.fangmi.mylibrary.painter.ProgressBgPainter2;
import com.fangmi.mylibrary.painter.ProgressPainter1;
import com.fangmi.mylibrary.painter.ProgressPainter2;
import com.fangmi.mylibrary.painter.Ring4Painter;
import com.fangmi.mylibrary.painter.RingPainter;
import com.fangmi.mylibrary.painter.TextPainter;
import com.fangmi.mylibrary.painter.TextPainter1;
import com.fangmi.mylibrary.painter.WaterWavePainter;
import com.fangmi.mylibrary.painter.WaterWavePainter2;
import com.fangmi.mylibrary.utils.WidgetUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/9/10.
 */
public class WaterWaveProgress extends View {

    /**
     * 水的画笔
     */
    private WaterWavePainter waterWavePainter;
    private WaterWavePainter2 waterWavePainter2;
    /**
     * 进度百分比的画笔
     */
    private TextPainter textPainter;

    /**
     * 设置标题的画笔
     */
    private TextPainter1 textPainter1;
    /**
     * 进度条背景画笔
     */
    private ProgressBgPainter1 progressBgPainter1;
    private ProgressBgPainter2 progressBgPainter2;
    /**
     * 进度条画笔
     */
    private ProgressPainter1 progressPainter1;
    private ProgressPainter2 progressPainter2;
    /**
     * 刻度圆环
     */
    private RingPainter ringPainter;
    private Ring4Painter ring4Painter;

    int mMaxProgress = 100;

    // 画布中心点
    private Point mCenterPoint;


    private MyHandler mHandler = null;

    /**
     * 进度条背景色 #A7B5DB
     */
    private int progressBgColor = Color.parseColor("#A7B5DB");
    /**
     * 进度条颜色
     */
    private int progressColor = Color.parseColor("#F7EF3A");
    /**
     * 字体颜色
     */
    private int numberColor = Color.WHITE;

    /**
     * 水的背景色
     */
    private int waterWaveBgColor = Color.parseColor("#425481");
    /**
     * 水的颜色
     */
    private int waterWaveColor = Color.parseColor("#5083D4");
    private int waterWaveColor2 = Color.parseColor("#5364BA");
    /**
     * 刻度宽度
     */
    private float scaleWidth;
    private int mValue;

    private class MyHandler extends Handler {
        private WeakReference<WaterWaveProgress> mWeakRef = null;

        private int refreshPeriod = 100;

        public MyHandler(WaterWaveProgress host) {
            mWeakRef = new WeakReference<>(host);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (mWeakRef.get() != null) {
                        mWeakRef.get().invalidate();
                        sendEmptyMessageDelayed(0, refreshPeriod);
                    }
                    break;
            }
        }
    }

    public WaterWaveProgress(Context paramContext) {
        super(paramContext);
    }

    public WaterWaveProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaterWaveProgress(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        mCenterPoint = new Point();

        int margin1 = WidgetUtil.Dp2Px(getContext(), 16);
        int margin2 = margin1 * 3;

        scaleWidth = WidgetUtil.Dp2Px(getContext(), 10);

        // 如果手机版本在4.0以上,则开启硬件加速
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } else {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        waterWavePainter = new WaterWavePainter(waterWaveColor, getContext(), margin2 + scaleWidth);
        waterWavePainter2 = new WaterWavePainter2(waterWaveColor2, getContext(), margin2 + scaleWidth);
        /**
         * 进度条背景
         */
        progressBgPainter1 = new ProgressBgPainter1(progressBgColor
                , margin2, getContext(), scaleWidth);
        progressBgPainter2 = new ProgressBgPainter2(progressBgColor
                , margin2, getContext(), scaleWidth);

        progressPainter1 = new ProgressPainter1(progressColor,
                mMaxProgress, margin2, getContext(), scaleWidth);
        progressPainter2 = new ProgressPainter2(progressColor,
                mMaxProgress, margin2, getContext(), scaleWidth);

        ringPainter = new RingPainter(progressBgColor, margin2 + scaleWidth, getContext());

        ring4Painter = new Ring4Painter(margin1, getContext());

        textPainter = new TextPainter(numberColor, 90, getContext());

        textPainter1 = new TextPainter1(numberColor,40,getContext());
        mHandler = new MyHandler(this);

    }

    public void animateWave() {
        mHandler.sendEmptyMessage(0);
    }


    @SuppressLint({"DrawAllocation", "NewApi"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取整个View（容器）的宽、高
        int width = getWidth();
        int height = getHeight();
        width = height = (width < height) ? width : height;
        mCenterPoint.x = width / 2;
        mCenterPoint.y = height / 2;

        //使用isInEditMode解决可视化编辑器无法识别自定义控件的问题
        if (isInEditMode()) {
            Paint mPaintWater = new Paint();
            mPaintWater.setStrokeWidth(1.0F);
            mPaintWater.setColor(waterWaveBgColor);
            mPaintWater.setAlpha(255);
            canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x
                    , mPaintWater);
            return;
        }
        /**
         * 刻度圆环
         */
        ringPainter.draw(canvas);
        ring4Painter.draw(canvas);
        /**
         * 进度条背景
         */
        progressBgPainter1.draw(canvas);
        progressBgPainter2.draw(canvas);
        /**
         * 进度
         */
        progressPainter1.draw(canvas);
        progressPainter2.draw(canvas);
        /**
         * 波浪
         */
        waterWavePainter.draw(canvas);
        waterWavePainter2.draw(canvas);
        /**
         * 进度文字
         */
        textPainter.draw(canvas);

        /**
         * 内容文字
         */
        textPainter1.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /**
         * 进度条背景
         */
        progressBgPainter1.onSizeChanged(h, w);
        progressBgPainter2.onSizeChanged(h, w);
        /**
         * 进度条
         */
        progressPainter1.onSizeChanged(h, w);
        progressPainter2.onSizeChanged(h, w);

        waterWavePainter.onSizeChanged(h, w);
        waterWavePainter2.onSizeChanged(h, w);

        ringPainter.onSizeChanged(h, w);
        ring4Painter.onSizeChanged(h, w);

        textPainter.onSizeChanged(h, w);

        textPainter1.onSizeChanged(h, w);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = widthMeasureSpec;
        int height = heightMeasureSpec;
        width = height = (width < height) ? width : height;
        setMeasuredDimension(width, height);
    }


    public void setValue(int value) {
        value = value > 100 ? 100 : value < 0 ? 0 : value;
        mValue = value;
        textPainter.setValue(mValue);

        waterWavePainter.setValue(mValue);
        waterWavePainter2.setValue(mValue);

        progressPainter1.setValue(mValue);
        progressPainter2.setValue(mValue);
        /**
         *进度条背景
         */
        progressBgPainter1.setValue(mValue);
        progressBgPainter2.setValue(mValue);
    }

    public void setContent(String content) {
        textPainter1.setValue(content);
    }
}