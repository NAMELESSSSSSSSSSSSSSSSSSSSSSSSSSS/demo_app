package com.example.a41908.weather_forecast.gson;

public class Daily_forecast {
    public String date;
    public Cond cond;
    public Tmp tmp;

    public class Cond{
        public String txt_d;
    }

    public class Tmp{
        public String max;
        public String min;
    }
}
