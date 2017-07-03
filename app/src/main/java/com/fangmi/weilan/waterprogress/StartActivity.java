package com.fangmi.weilan.waterprogress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


/**
 * Created by Administrator on 2017/7/3.
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton tvText1;
    private AppCompatButton tvText2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tvText1 = (AppCompatButton) findViewById(R.id.text1);
        tvText2 = (AppCompatButton) findViewById(R.id.text2);
        tvText1.setOnClickListener(this);
        tvText2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.text2:
                startActivity(new Intent(this,ThirdActivity.class));
                break;
        }
    }
}
