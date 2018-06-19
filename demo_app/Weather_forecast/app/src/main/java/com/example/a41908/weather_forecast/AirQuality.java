package com.example.a41908.weather_forecast;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class AirQuality extends Activity {

    ImageButton back1;

    int PM25=72;
    int NO2=38;
    int SO2=20;
    int O3=75;
    int CO=1;
    int PM10=128;
    int aqi;

    String weather_id = "CN101210107";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality);

        Intent intent = getIntent();
        String new_id;
        new_id = intent.getStringExtra("ID");
        if(new_id!=null)
            weather_id = new_id;

        PM25 = intent.getIntExtra("PM25", 72);
        PM10 = intent.getIntExtra("PM10", 128);
        SO2 = intent.getIntExtra("SO2", 20);
        NO2 = intent.getIntExtra("NO2", 38);
        CO = (int)intent.getDoubleExtra("CO", 1.0);
        O3 = intent.getIntExtra("O3", 75);
        aqi = intent.getIntExtra("aqi", 0);

        TextView text_aqi = (TextView)findViewById(R.id.text_aqi);
        text_aqi.setText(aqi+"");
        TextView text_judge = (TextView)findViewById(R.id.text_judge);
        text_judge.setText(Judge_Aqi(aqi));

        ImageView imageView = (ImageView) findViewById(R.id.aqi_backgroung);
        imageView.setImageResource(Choice_background(Judge_Aqi(aqi)));

        initPolygon();

        back1 = (ImageButton)findViewById(R.id.button_back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AirQuality.this, Home.class);
                intent.putExtra("ID", weather_id);
                startActivity(intent);
            }
        });
    }

    //绘图
    private void initPolygon() {
        Polygon poly = (Polygon) findViewById(R.id.poly);
        poly.setRealData(new int[]{PM25/35, NO2/20, SO2/10, O3/18, CO, PM10/40});
    }

    //换背景
    private int Choice_background(String aqi){
        if(aqi.equals("优"))
            return getResourceByReflect("aqi_quality_level0");
        if(aqi.equals("良"))
            return getResourceByReflect("aqi_quality_level1");
        if(aqi.equals("轻度污染"))
            return getResourceByReflect("aqi_quality_level2");
        if(aqi.equals("中度污染"))
            return getResourceByReflect("aqi_quality_level3");
        if(aqi.equals("重度污染"))
            return getResourceByReflect("aqi_quality_level4");
        if(aqi.equals("严重污染"))
            return getResourceByReflect("aqi_quality_level5");
        return getResourceByReflect("aqi_quality_level6");
    }

    //判断空气质量等级
    private String Judge_Aqi(int aqi){
        if(aqi<=50)
            return "优";
        if(aqi<=100)
            return "良";
        if(aqi<=150)
            return "轻度污染";
        if(aqi<=200)
            return "中度污染";
        if(aqi<=300)
            return "重度污染";
        return "严重污染";
    }

    //根据图片名获取图片ID
    public int getResourceByReflect(String imageName){
        Class drawable  =  R.mipmap.class;
        Field field = null;
        int r_id = 0;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r_id;
    }
}
