package com.example.a41908.weather_forecast.gson;

public class AQI {
    public AQICity city;

    public class AQICity{
        public int aqi;
        public double pm25;
        public String qlty;
    }

    public AQICity getCity() {
        return city;
    }

    public void setCity(AQICity city) {
        this.city = city;
    }
}