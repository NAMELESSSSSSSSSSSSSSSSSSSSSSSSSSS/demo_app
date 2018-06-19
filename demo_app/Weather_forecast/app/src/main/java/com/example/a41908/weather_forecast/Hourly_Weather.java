package com.example.a41908.weather_forecast;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.a41908.weather_forecast.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Hourly_Weather extends AppCompatActivity {


    String weather_id = "CN101210107";
    Weather weather;

    private List<weatherperhour> perhourList = new ArrayList<>();
    private IndexHorizontalScrollView indexHorizontalScrollView;
    private LinearLayout test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly__weather);

        Intent intent = getIntent();
        weather_id = intent.getStringExtra("ID");

        update();//更新数据

        //initprehour();
        //Toast.makeText(Hourly_Weather.this, perhourList.size()+"", Toast.LENGTH_LONG).show();

//        initViews();
//
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_verticalview_perhour);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        Adapter_ver_perhour_weather adapter = new Adapter_ver_perhour_weather(perhourList);
//        recyclerView.setAdapter(adapter);
    }


    public void initprehour() {

        for(int i=0; i<8; i++){
            weatherperhour temp_hour = new weatherperhour("1月2日","00：00",R.mipmap.ac_small_gray_15,
                    "良","37°C","暴雨","飓风");
            //Toast.makeText(Hourly_Weather.this, weather.basic.cityName, Toast.LENGTH_LONG).show();
            String time = weather.hourly.get(i).time;
            temp_hour.setPerhpurday(time.substring(5,10));
            temp_hour.setPerhourtime(time.substring(time.length()-5));
            temp_hour.setPerhourairquality(Judge_Aqi(weather.aqi.city.aqi));
            temp_hour.setPerhourtemperature(weather.hourly.get(i).tmp);
            temp_hour.setPerhourdesc_weather(weather.now.cond_txt);
            temp_hour.setWindpower(weather.hourly.get(i).wind_sc);
            int code = weather.hourly.get(i).cond_code;
            temp_hour.setPerhourpic_weather(Get_Weather_Icon(code));

            perhourList.add(temp_hour);
        }
        //Toast.makeText(Hourly_Weather.this, perhourList.size()+"", Toast.LENGTH_LONG).show();
        initViews();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_verticalview_perhour);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Adapter_ver_perhour_weather adapter = new Adapter_ver_perhour_weather(perhourList);
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {

        Today24HourView today24HourView = new Today24HourView(this);

        indexHorizontalScrollView = (IndexHorizontalScrollView)findViewById(R.id.indexHorizontalScrollView);

        indexHorizontalScrollView.addView(today24HourView);
    }

    //更新数据
    void update(){
        sendRequestWhithOKHttp();
    }

    private void sendRequestWhithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://guolin.tech/api/weather?cityid="+weather_id+"&key=6d2c22ffc9ad4ee8a5197606f1257d91")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    weather = parseJSONWithGSON(responseData);
                    showResponse();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(Hourly_Weather.this, "hehe", Toast.LENGTH_LONG).show();
                //Toast.makeText(Hourly_Weather.this, weather.basic.cityName, Toast.LENGTH_LONG).show();
                initprehour();
            }
        });
    }

    private static Weather parseJSONWithGSON(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

    //获取天气小图标
    private int Get_Weather_Icon(int code){
        String id = "";
        switch (code){
            case 100:
                id = "ac_small_white_" + "1";
                break;
            case 101:
                id = "ac_small_white_" + "4";
                break;
            case 102:
                id = "ac_small_white_" + "2";
                break;
            case 103:
                id = "ac_small_white_" + "3";
                break;
            case 104:
                id = "ac_small_white_" + "7";
                break;
            case 300:case 301:case 302:case 303:case 304:
                id = "ac_small_white_" + "13";
                break;
            case 305:case 309:
                id = "ac_small_white_" + "18";
                break;
            case 306:
                id = "ac_small_white_" + "12";
                break;
            case 307:case 308:case 310:case 311:case 312:case 313:
                id = "ac_small_white_" + "25";
                break;
            case 400:
                id = "ac_small_white_" + "19";
                break;
            case 401:
                id = "ac_small_white_" + "22";
                break;
            case 402:case 403:case 404:case 405:case 406:case 407:
                id = "ac_small_white_" + "25";
                break;
            default:
                id = "ac_small_white_1";
                break;
        }
        return getResourceByReflect(id);
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

