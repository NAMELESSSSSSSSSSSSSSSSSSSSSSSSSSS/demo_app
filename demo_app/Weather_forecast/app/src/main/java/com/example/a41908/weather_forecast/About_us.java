package com.example.a41908.weather_forecast;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class About_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        if (getSupportActionBar() != null){//去掉标题栏
            getSupportActionBar().hide();
        }
    }
}

