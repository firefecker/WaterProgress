package com.fangmi.weilan.waterprogress;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fangmi.mylibrary.widget.AlternateProgress;

public class MainActivity extends AppCompatActivity{

    private AlternateProgress mWaterWaveProgress;

    private int count = 0;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                count+=10;
                init();
                sendEmptyMessageDelayed(1,5000);
            }
        }
    };

    private void init() {
        mWaterWaveProgress.setTextValue(count+"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWaterWaveProgress = (AlternateProgress) findViewById(R.id.mWaterWaveProgress);
        polling();
        handler.sendEmptyMessageDelayed(1,5000);
    }

    private void polling() {
        mWaterWaveProgress.animateWave();
        mWaterWaveProgress.setContent("汽车已充电量(度)");
    }

}
