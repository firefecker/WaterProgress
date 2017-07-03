package com.fangmi.mylibrary.painter;

import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/9/10.
 */
public interface Painter {
    void draw(Canvas canvas);

    void setColor(int color);

    int getColor();

    void onSizeChanged(int height, int width);
}
