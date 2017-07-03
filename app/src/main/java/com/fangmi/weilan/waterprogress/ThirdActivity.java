package com.fangmi.weilan.waterprogress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fangmi.mylibrary.widget.WaterWaveProgress;


/**
 * Created by Administrator on 2017/7/3.
 */
public class ThirdActivity extends AppCompatActivity{

    private WaterWaveProgress mWaterWaveProgress;

    private int count = 0;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                count+=5;
                if (count > 100 || count < 0) {
                    count = 0;
                }
                init();
                sendEmptyMessageDelayed(1,5000);
            }
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mWaterWaveProgress = (WaterWaveProgress) findViewById(R.id.mWaterWaveProgress);
        polling();
        handler.sendEmptyMessageDelayed(1,5000);
    }

    private void polling() {
        mWaterWaveProgress.animateWave();
        mWaterWaveProgress.setContent("当前汽车总电量");
    }

    private void init() {
        mWaterWaveProgress.setValue(count);
    }
}
