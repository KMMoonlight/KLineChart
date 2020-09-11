package com.kmmoonlight.chartdemo.repo;

public interface IVolume {

    //成交量相关数据
    //开盘价
    float getOpenPrice();


    //收盘价
    float getClosePrice();


    //成交量
    float getVolume();


    //Vol MA5
    float getMA5Volume();


    //Vol MA10
    float getMA10Volume();

}
