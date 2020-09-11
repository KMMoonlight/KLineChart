package com.kmmoonlight.chartdemo.repo;


//Candle数据类
public interface ICandle {

    //获取开盘价
    float getOpenPrice();


    //获取最高价
    float getHighPrice();


    //获取最低价
    float getLowPrice();


    //获取收盘价
    float getClosePrice();



    //MA指标相关
    //MA5
    float getMA5Price();

    //MA10
    float getMA10Price();

    //MA20
    float getMA20Price();

    //MA30
    float getMA30Price();

    //MA60
    float getMA60Price();


    //BOLL指标相关
    //上轨线
    float getUp();

    //中轨线
    float getMb();

    //下轨线
    float getDn();

}
