package com.example.a41908.weather_forecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a41908.weather_forecast.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.view.View;


public class Home extends AppCompatActivity {
    FrameLayout layout1;
    FrameLayout layout2;
    FrameLayout layout3;
    LinearLayout layout4;
    LinearLayout jmp_wear;
    LinearLayout jmp_feel;
    LinearLayout jmp_wash;
    LinearLayout jmp_sun;
    LinearLayout jmp_choice_city;
    LinearLayout jmp_daily_weather1;
    LinearLayout jmp_daily_weather2;
    LinearLayout jmp_daily_weather3;
    LinearLayout jmp_hourly_weather;
    ImageView icon_weather_big;
    //ImageView icon_weather_yesterday;
    ImageView icon_weather_today;
    ImageView icon_weather_tomorrow;
    ImageView icon_weather_aftertomorrow;
    ImageView icon_wind_up;
    //ImageView icon_wind_yesterday;
    ImageView icon_wind_today;
    ImageView icon_wind_tomorrow;
    ImageView icon_wind_aftertomorrow;
    TextView message_time;
    //TextView weather_yesterday;
    TextView weather_today;
    TextView weather_tomorrow;
    TextView weather_aftertomorrow;
    TextView temp_up;
    //TextView temp_yesterday;
    TextView temp_today;
    TextView temp_tomorrow;
    TextView temp_aftertomorrow;
    TextView wind_up;
    //TextView wind_yesterday;
    TextView wind_today;
    TextView wind_tomorrow;
    TextView wind_aftertomorrow;
    TextView air_quality_up;
    //TextView air_quality_yesterday;
    //TextView air_quality_today;
    //TextView air_quality_tomorrow;
    //TextView air_quality_aftertomorrow;
    TextView wear;
    TextView feel;
    TextView wash;
    TextView sun;
    TextView text_pos;
    TextView text_jmp_hour;
    Button button_feeltmp;
    Button button_realtmp;
    Button jmp_airquality;

    Weather weather;//天气信息
    String weather_id = "CN101210107";
    //http://guolin.tech/api/weather?cityid=CN101210107&key=6d2c22ffc9ad4ee8a5197606f1257d91

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null){//去掉标题栏
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        String new_id;
        new_id = intent.getStringExtra("ID");
        if(new_id!=null)
            weather_id = new_id;

        update();//更新数据

        icon_weather_big = (ImageView) findViewById(R.id.icon_weather_big);//大图标icon_weather_yesterday = (ImageView) findViewById(R.id.icon_weather_1);

        icon_weather_tomorrow = (ImageView) findViewById(R.id.icon_weather_3);
        icon_weather_aftertomorrow = (ImageView) findViewById(R.id.icon_weather_4);
        icon_wind_up = (ImageView) findViewById(R.id.icon_wind_up);//上方的风力
        icon_wind_today = (ImageView) findViewById(R.id.icon_wind_2);
        icon_wind_tomorrow = (ImageView) findViewById(R.id.icon_wind_3);
        icon_wind_aftertomorrow = (ImageView) findViewById(R.id.icon_wind_4);
        weather_today = (TextView) findViewById(R.id.weather_2);
        weather_tomorrow = (TextView) findViewById(R.id.weather_3);
        weather_aftertomorrow = (TextView) findViewById(R.id.weather_4);
        temp_up = (TextView) findViewById(R.id.temp_big);
        temp_today = (TextView) findViewById(R.id.temp_2);
        temp_tomorrow = (TextView) findViewById(R.id.temp_3);
        temp_aftertomorrow = (TextView) findViewById(R.id.temp_4);
        wind_up = (TextView) findViewById(R.id.wind_up);
        wind_today = (TextView) findViewById(R.id.wind_2);
        wind_tomorrow = (TextView) findViewById(R.id.wind_3);
        wind_aftertomorrow = (TextView) findViewById(R.id.wind_4);
        air_quality_up = (TextView) findViewById(R.id.air_quality_up);

        wear = (TextView) findViewById(R.id.text_wear);//穿衣
        feel = (TextView) findViewById(R.id.text_feel);//舒适度
        wash = (TextView) findViewById(R.id.text_wash);//洗车
        sun = (TextView) findViewById(R.id.text_sun);//防晒
        layout1 = (FrameLayout)findViewById(R.id.layout1);
        layout2 = (FrameLayout)findViewById(R.id.layout2);
        layout3 = (FrameLayout)findViewById(R.id.layout3);
        layout4 = (LinearLayout)findViewById(R.id.button_weather_now);//跳转至当前天气页面
        button_feeltmp = (Button)findViewById(R.id.button_feel);//体感温度转换按钮
        button_realtmp = (Button)findViewById(R.id.button_real);//实测温度转换按钮
        jmp_feel = (LinearLayout)findViewById(R.id.jmp_feel);
        jmp_wear = (LinearLayout)findViewById(R.id.jmp_wear);
        jmp_wash = (LinearLayout)findViewById(R.id.jmp_wash);
        jmp_sun = (LinearLayout)findViewById(R.id.jmp_sun);
        jmp_airquality = (Button)findViewById(R.id.air_quality_up);
        jmp_choice_city = (LinearLayout)findViewById(R.id.jmp_choice_city);
        jmp_daily_weather1 = (LinearLayout)findViewById(R.id.jmp_daily_weather1);
        jmp_daily_weather2 = (LinearLayout)findViewById(R.id.jmp_daily_weather2);
        jmp_daily_weather3 = (LinearLayout)findViewById(R.id.jmp_daily_weather3);
        jmp_hourly_weather = (LinearLayout)findViewById(R.id.jmp_hourly_weather);
/*
        //跳转逐小时天气
        jmp_hourly_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Hourly_Weather.class);
                intent.putExtra("ID", weather_id);
                startActivity(intent);
            }
        });

        //跳转每日天气
        jmp_daily_weather1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = 1;
                Intent intent = new Intent(Home.this, Daily_Weather.class);
                intent.putExtra("ID",weather_id);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
        jmp_daily_weather2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = 2;
                Intent intent = new Intent(Home.this, Daily_Weather.class);
                intent.putExtra("ID",weather_id);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
        jmp_daily_weather3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = 3;
                Intent intent = new Intent(Home.this, Daily_Weather.class);
                intent.putExtra("ID",weather_id);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
*/
        //跳转城市选择
        jmp_choice_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ChooseCity.class);
                startActivity(intent);
            }
        });
/*
        //跳转到空气质量
        jmp_airquality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, AirQuality.class);
                intent.putExtra("PM25", weather.aqi.city.pm25);
                intent.putExtra("PM10", weather.aqi.city.pm10);
                intent.putExtra("SO2", weather.aqi.city.so2);
                intent.putExtra("NO2", weather.aqi.city.no2);
                intent.putExtra("CO", weather.aqi.city.co);
                intent.putExtra("O3", weather.aqi.city.o3);
                intent.putExtra("aqi", weather.aqi.city.aqi);
                intent.putExtra("ID", weather_id);
                startActivity(intent);
            }
        });

        //跳转建议区
        jmp_feel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Suggestion_feel.class);
                intent.putExtra("txt", weather.suggestion.comf.txt);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        jmp_wear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Suggestion_wear.class);
                intent.putExtra("txt", weather.suggestion.drsg.txt);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        jmp_wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Suggestion_wash.class);
                intent.putExtra("txt", weather.suggestion.cw.txt);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        jmp_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Suggestion_sun.class);
                intent.putExtra("txt", weather.suggestion.uv.txt);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });

        //转换体感温度
        button_feeltmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp_up.setText(weather.now.fl);
            }
        });
        //转换实测温度
        button_realtmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp_up.setText(weather.now.tmp);
            }
        });

        //跳转当前天气
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Weather_Now.class);
                intent.putExtra("ID", weather_id);
                //Toast.makeText(Home.this, weather_id, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        //底部
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Home
                Intent intent = new Intent(Home.this, Home.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Around
                Intent intent = new Intent(Home.this, around.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Set
                Intent intent = new Intent(Home.this, Set.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });*/
    }

    //更新数据
    void update(){
        sendRequestWhithOKHttp();
        //Toast.makeText(Home.this,"^ ^",Toast.LENGTH_SHORT).show();
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

    //更改控件
    private void showResponse(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在此进行UI操作

                //时间
                String time;
                message_time = (TextView)findViewById(R.id.message_time);
                time = weather.basic.update.loc;
                message_time.setText("中央气象台\n"+time.substring(time.length()-5)+"发布");
                text_jmp_hour = (TextView)findViewById(R.id.text_jmp_hour);
                text_jmp_hour.setText(time.substring(time.length()-5));

                //天气部分
                weather_today = (TextView) findViewById(R.id.weather_2);
                weather_tomorrow = (TextView) findViewById(R.id.weather_3);
                weather_aftertomorrow = (TextView) findViewById(R.id.weather_4);
                int code_today, code_tomorrow, code_aftertomorrow;
                code_today = weather.now.cond_code;
                code_tomorrow = weather.now.cond_code;
                code_aftertomorrow = weather.now.cond_code;
                icon_weather_today = (ImageView) findViewById(R.id.icon_weather_2);
                icon_weather_tomorrow = (ImageView) findViewById(R.id.icon_weather_3);
                icon_weather_aftertomorrow = (ImageView) findViewById(R.id.icon_weather_4);
                icon_weather_big = (ImageView) findViewById(R.id.icon_weather_big);
                icon_weather_big.setImageResource(Get_Weather_Icon_Big(code_today));
                icon_weather_today.setImageResource(Get_Weather_Icon(code_today));
                icon_weather_tomorrow.setImageResource(Get_Weather_Icon(code_tomorrow));
                icon_weather_aftertomorrow.setImageResource(Get_Weather_Icon(code_aftertomorrow));
                weather_today.setText(weather.daily_forecast.get(0).cond.txt_d);
                weather_tomorrow.setText(weather.daily_forecast.get(1).cond.txt_d);
                weather_aftertomorrow.setText(weather.daily_forecast.get(2).cond.txt_d);

                //温度部分
                temp_up = (TextView) findViewById(R.id.temp_big);
                temp_today = (TextView) findViewById(R.id.temp_2);
                temp_tomorrow = (TextView) findViewById(R.id.temp_3);
                temp_aftertomorrow = (TextView) findViewById(R.id.temp_4);

                temp_up.setText(weather.now.tmp);
                temp_today.setText(weather.daily_forecast.get(0).tmp.max+"/"+weather.daily_forecast.get(0).tmp.min+"°C");
                temp_tomorrow.setText(weather.daily_forecast.get(1).tmp.max+"/"+weather.daily_forecast.get(1).tmp.min+"°C");
                temp_aftertomorrow.setText(weather.daily_forecast.get(2).tmp.max+"/"+weather.daily_forecast.get(2).tmp.min+"°C");

                //风部分
                wind_up = (TextView) findViewById(R.id.wind_up);
                wind_today = (TextView) findViewById(R.id.wind_2);
                wind_tomorrow = (TextView) findViewById(R.id.wind_3);
                wind_aftertomorrow = (TextView) findViewById(R.id.wind_4);
                icon_wind_up = (ImageView) findViewById(R.id.icon_wind_up);
                icon_wind_today = (ImageView) findViewById(R.id.icon_wind_2);
                icon_wind_tomorrow = (ImageView) findViewById(R.id.icon_wind_3);
                icon_wind_aftertomorrow = (ImageView) findViewById(R.id.icon_wind_4);

                int code_wind_today,code_wind_tomorrow, code_wind_aftertomorrow;
                //风图标
                code_wind_today = Get_Wind_Icon(weather.now.wind_dir);
                code_wind_tomorrow = Get_Wind_Icon(weather.now.wind_dir);
                code_wind_aftertomorrow = Get_Wind_Icon(weather.now.wind_dir);
                icon_wind_up.setImageResource(code_wind_today);
                icon_wind_today.setImageResource(code_wind_today);
                icon_wind_tomorrow.setImageResource(code_wind_tomorrow);
                icon_wind_aftertomorrow.setImageResource(code_wind_aftertomorrow);
                //风信息
                wind_up.setText(weather.now.wind_sc);
                wind_today.setText(weather.now.wind_sc);
                wind_tomorrow.setText(weather.now.wind_sc);
                wind_aftertomorrow.setText(weather.now.wind_sc);

                //空气质量部分
                int aqi;
                aqi = weather.aqi.city.aqi;
                air_quality_up = (TextView) findViewById(R.id.air_quality_up);
                air_quality_up.setText(Judge_Aqi(aqi)+" "+aqi);

                //指数
                wear = (TextView) findViewById(R.id.text_wear);//穿衣
                feel = (TextView) findViewById(R.id.text_feel);//舒适度
                wash = (TextView) findViewById(R.id.text_wash);//洗车
                sun = (TextView) findViewById(R.id.text_sun);//防晒

                feel.setText(weather.suggestion.comf.brf);
                wash.setText(weather.suggestion.cw.brf);
                wear.setText("长袖、衬衫、单裤");
                sun.setText("SPF8-12之间的防晒霜");
                //wear.setText(weather.suggestion.drsg.brf);
                //sun.setText(weather.suggestion.uv.brf);

                //位置
                text_pos = (TextView)findViewById(R.id.text_pos);
                text_pos.setText(weather.basic.location);
            }
        });
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

    //获取风向图标
    private int Get_Wind_Icon(String dir){
        if(dir.equals("西风"))
            return getResourceByReflect("wind_west");
        if(dir.equals("北风"))
            return getResourceByReflect("wind_north");
        if(dir.equals("东风"))
            return getResourceByReflect("wind_east");
        if(dir.equals("南风"))
            return getResourceByReflect("wind_south");
        if(dir.equals("西北风"))
            return getResourceByReflect("wind_northwest");
        if(dir.equals("西南风"))
            return getResourceByReflect("wind_southwest");
        if(dir.equals("东北风"))
            return getResourceByReflect("wind_northeast");
        if(dir.equals("东南风"))
            return getResourceByReflect("wind_southeast");
        return getResourceByReflect("wind_unknown");
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

    //获取天气大图标
    private int Get_Weather_Icon_Big(int code){
        String id = "";
        switch (code){
            case 100:
                id = "ac_big_white_" + "1";
                break;
            case 101:
                id = "ac_big_white_" + "4";
                break;
            case 102:
                id = "ac_big_white_" + "2";
                break;
            case 103:
                id = "ac_big_white_" + "3";
                break;
            case 104:
                id = "ac_big_white_" + "7";
                break;
            case 300:case 301:case 302:case 303:case 304:
                id = "ac_big_white_" + "13";
                break;
            case 305:case 309:
                id = "ac_big_white_" + "18";
                break;
            case 306:
                id = "ac_big_white_" + "12";
                break;
            case 307:case 308:case 310:case 311:case 312:case 313:
                id = "ac_sbig_white_" + "25";
                break;
            case 400:
                id = "ac_big_white_" + "19";
                break;
            case 401:
                id = "ac_big_white_" + "22";
                break;
            case 402:case 403:case 404:case 405:case 406:case 407:
                id = "ac_big_white_" + "25";
                break;
            default:
                id = "ac_big_white_1";
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
