package com.example.a41908.weather_forecast;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

public class around extends AppCompatActivity {
    FrameLayout layout1;
    FrameLayout layout2;
    FrameLayout layout3;

    String weather_id;

    // 定位
    LocationClient _locationClient;
    TextView _tvPosition;
    // 地图显示
    MapView _mapView;
    // 控制
    BaiduMap _map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null){//去掉标题栏
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        weather_id = intent.getStringExtra("ID");

        // 定位客户端初始化
        _locationClient = new LocationClient(
                getApplicationContext());
        // 注册一个位置监听器
        _locationClient.registerLocationListener(
                new MyLocationListener());
        // 初始化sdk
        SDKInitializer.initialize( getApplicationContext() );
        setContentView(R.layout.activity_around);

        // 控件初始化
        _mapView = (MapView)findViewById(R.id.mapViewTest);
        _map = _mapView.getMap();// 得到地图数据

        // 移动位置
        LatLng ll = new LatLng( 30.257939, 119.727531 ); // 坐标
        MapStatusUpdate update =
                MapStatusUpdateFactory.newLatLng( ll );
        _map.animateMapStatus( update );// 刷新地图状态

        // 显示自身的定位情况
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude( 30.257939 );
        builder.longitude( 119.727531 );
        MyLocationData data = builder.build();
        _map.setMyLocationData( data );

        // 设为允许定位
        _map.setMyLocationEnabled( true );

        // 有一个权限不处理，则无法定位
        // 处理权限
        ArrayList<String> permissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(
                around.this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // 未授权: 精确定位
            permissionList.add(
                    Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(around.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // 未授权: 读取手机状态权限
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(around.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 未授权: 读取手机状态权限
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()) {
            // 有未授权权限
            String[] permissions = permissionList.toArray(
                    new String[permissionList.size()]
            );
            ActivityCompat.requestPermissions(
                    around.this, permissions, 1);
        } else {
            requestLocation();
        }

        layout1 = (FrameLayout)findViewById(R.id.layout1);
        layout2 = (FrameLayout)findViewById(R.id.layout2);
        layout3 = (FrameLayout)findViewById(R.id.layout3);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Home
                Intent intent = new Intent(around.this, Home.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Around
                Intent intent = new Intent(around.this, around.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Set
                Intent intent = new Intent(around.this, Set.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        _mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        _mapView.onPause();
    }


    // 启动定位客户端
    private void requestLocation(){
        // 启动定位客户端
        initLocation();
        _locationClient.start();
    }

    public class MyLocationListener implements BDLocationListener {
        // 接收到坐标
        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder builder = new StringBuilder();
            builder.append("纬度:")
                    .append( location.getLatitude() ).append("\n");
            builder.append("经度：")
                    .append( location.getLongitude() ).append( "\n" );
            builder.append( location.getProvince() ).append( "--" )
                    .append( location.getCity() ).append("\n");
            builder.append( "定位方式：" );
            if( location.getLocType() == BDLocation.TypeGpsLocation ){
                builder.append( "GPS定位" );
            }
            else if( location.getLocType() == BDLocation.TypeNetWorkLocation ){
                builder.append( "网络定位" );
            }
        }
    }

    private  void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan( 5000 ); // 5000毫秒扫描一次
        option.setIsNeedAddress( true ); // 需要地址
        _locationClient.setLocOption( option ); // 设置参数
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _locationClient.stop();
        _mapView.onDestroy();

        // 撤销允许定位
        _map.setMyLocationEnabled( false );
    }
}

