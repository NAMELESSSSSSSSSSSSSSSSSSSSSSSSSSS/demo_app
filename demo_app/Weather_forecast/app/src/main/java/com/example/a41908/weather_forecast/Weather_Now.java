package com.example.a41908.weather_forecast;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a41908.weather_forecast.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weather_Now extends AppCompatActivity {
    ImageButton button_return;
    TextView text_feeltmp;
    TextView text_realtmp;
    TextView text_feeltmp2;
    TextView text_realtmp2;
    TextView time1;
    TextView time2;
    TextView time3;
    TextView time4;
    TextView time5;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    TextView tmp1;
    TextView tmp2;
    TextView tmp3;
    TextView tmp4;
    TextView tmp5;
    TextView text_weather;
    TextView wind_speed1;
    TextView wind_speed2;

    Weather weather;
    String weather_id = "CN101210107";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather__now);

        if (getSupportActionBar() != null){//去掉标题栏
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        weather_id = intent.getStringExtra("ID");
        //Toast.makeText(Weather_Now.this, weather_id, Toast.LENGTH_LONG).show();

        update();//更新数据

        button_return = (ImageButton)findViewById(R.id.button_return);
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weather_Now.this, Home.class);
                intent.putExtra("ID", weather_id);
                startActivity(intent);
            }
        });
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
    private  void showResponse(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //温度部分
                tmp1 = (TextView)findViewById(R.id.tmp1);
                tmp2 = (TextView)findViewById(R.id.tmp2);
                tmp3 = (TextView)findViewById(R.id.tmp3);
                tmp4 = (TextView)findViewById(R.id.tmp4);
                tmp5 = (TextView)findViewById(R.id.tmp5);
                text_feeltmp = (TextView)findViewById(R.id.text_feeltmp);
                text_feeltmp2 = (TextView)findViewById(R.id.text_feeltmp2);
                text_realtmp = (TextView)findViewById(R.id.text_realtmp);
                text_realtmp2 = (TextView)findViewById(R.id.text_realtmp2);

                tmp1.setText(weather.hourly.get(0).tmp);
                tmp2.setText(weather.hourly.get(1).tmp);
                tmp3.setText(weather.hourly.get(2).tmp);
                tmp4.setText(weather.hourly.get(3).tmp);
                tmp5.setText(weather.hourly.get(4).tmp);
                text_realtmp.setText(weather.now.tmp);
                text_realtmp2.setText(weather.now.tmp);
                text_feeltmp.setText(weather.now.fl);
                text_feeltmp2.setText(weather.now.fl);

                //时间部分
                time1 = (TextView)findViewById(R.id.time1);
                time2 = (TextView)findViewById(R.id.time2);
                time3 = (TextView)findViewById(R.id.time3);
                time4 = (TextView)findViewById(R.id.time4);
                time5 = (TextView)findViewById(R.id.time5);

                String text_time1,text_time2, text_time3, text_time4, text_time5;
                text_time1 = weather.hourly.get(0).time;
                text_time2 = weather.hourly.get(1).time;
                text_time3 = weather.hourly.get(2).time;
                text_time4 = weather.hourly.get(3).time;
                text_time5 = weather.hourly.get(4).time;
                time1.setText(text_time1.substring(text_time1.length()-5));
                time2.setText(text_time2.substring(text_time2.length()-5));
                time3.setText(text_time3.substring(text_time3.length()-5));
                time4.setText(text_time4.substring(text_time4.length()-5));
                time5.setText(text_time5.substring(text_time5.length()-5));

                //图片部分(天气)
                image1 = (ImageView)findViewById(R.id.image1);
                image2 = (ImageView)findViewById(R.id.image2);
                image3 = (ImageView)findViewById(R.id.image3);
                image4 = (ImageView)findViewById(R.id.image4);
                image5 = (ImageView)findViewById(R.id.image5);
                image6 = (ImageView)findViewById(R.id.image6);
                int code_1, code_2, code_3, code_4, code_5;
                code_1 = Get_Weather_Icon(weather.hourly.get(0).cond_code);
                code_2 = Get_Weather_Icon(weather.hourly.get(1).cond_code);
                code_3 = Get_Weather_Icon(weather.hourly.get(2).cond_code);
                code_4 = Get_Weather_Icon(weather.hourly.get(3).cond_code);
                code_5 = Get_Weather_Icon(weather.hourly.get(4).cond_code);
                image1.setImageResource(code_1);
                image6.setImageResource(code_1);
                image2.setImageResource(code_2);
                image3.setImageResource(code_3);
                image4.setImageResource(code_4);
                image5.setImageResource(code_5);

                //当前天气
                text_weather = (TextView)findViewById(R.id.text_weather);
                text_weather.setText(weather.daily_forecast.get(0).cond.txt_d);

                //风速
                wind_speed1 = (TextView)findViewById(R.id.wind_sped);
                wind_speed2 = (TextView)findViewById(R.id.wind_sped2);
                wind_speed1.setText(weather.now.wind_spd);
                wind_speed2.setText(weather.now.wind_spd);
            }
        });
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
