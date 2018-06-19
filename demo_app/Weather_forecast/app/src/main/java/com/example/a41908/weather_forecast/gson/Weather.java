package com.example.a41908.weather_forecast.gson;

import java.util.List;

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    public List<Daily_forecast> daily_forecast;
    public List<Hourly_forecat> hourly;
    public Update update;
}
