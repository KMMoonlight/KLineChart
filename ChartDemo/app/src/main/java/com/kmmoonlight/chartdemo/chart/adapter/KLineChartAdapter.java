package com.kmmoonlight.chartdemo.chart.adapter;

import com.kmmoonlight.chartdemo.repo.KLineRepo;

import java.util.ArrayList;
import java.util.List;

public class KLineChartAdapter extends BaseKLineChartAdapter {

    private List<KLineRepo> datas = new ArrayList<>();

    public KLineChartAdapter() {

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public String getDate(int position) {
        return datas.get(position).Date;
    }

    /**
     * 向头部添加数据
     */
    public void addHeaderData(List<KLineRepo> data) {
        if (data != null && !data.isEmpty()) {
            datas.clear();
            datas.addAll(data);
        }
    }

    /**
     * 向尾部添加数据
     */
    public void addFooterData(List<KLineRepo> data) {
        if (data != null && !data.isEmpty()) {
            datas.clear();
            datas.addAll(0, data);
        }
    }

    /**
     * 改变某个点的值
     *
     * @param position 索引值
     */
    public void changeItem(int position, KLineRepo data) {
        datas.set(position, data);
        notifyDataSetChanged();
    }

    /**
     * 数据清除
     */
    public void clearData() {
        datas.clear();
        notifyDataSetChanged();
    }
}
