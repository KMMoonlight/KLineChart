package com.kmmoonlight.chartdemo.base;

import android.database.DataSetObserver;

public interface IAdapter {
    //数据适配器
    //获取数据条数
    int getCount();

    //获取对应位置的数据
    Object getItem(int position);

    //获取对应位置的日期
    String getDate(int position);

    //注册一个数据观察者
    void registerDataSetObserver(DataSetObserver observer);

    //移出一个数据观察者
    void removeDataSetObserver(DataSetObserver observer);

    //当数据发生变化时更新
    void notifyDataSetChanged();

}
