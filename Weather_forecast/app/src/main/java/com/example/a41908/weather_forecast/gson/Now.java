package com.example.a41908.weather_forecast.gson;

public class Now {
    public Cond cond;
    public String cloud;
    public int cond_code;
    public String cond_txt;
    public String fl;
    public String hum;
    public String pcpn;
    public String pres;
    public String tmp;
    public String vis;
    public String wind_deg;
    public String wind_dir;
    public String wind_sc;
    public String wind_spd;

    public class Cond{
        public String code;
        public String txt;
    }
}
